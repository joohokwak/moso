package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import dto.ShoppingDTO;

public class ShoppingDAO extends DBCP{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public List<ShoppingDTO> viewMain(String ty, String ordered, String id, int pg) {
		List<ShoppingDTO> list = new ArrayList<>();
		conn = getConn();
		
		try {
			
			String sql = "";
			sql += "WITH QDATA AS (                                                 ";
			sql += "		SELECT ROW_NUMBER() OVER ( 								";
			
			if (ordered.equalsIgnoreCase("new")) {
				sql += " ORDER BY REGDATE DESC";
			} else if(ordered.equalsIgnoreCase("review")) {
				sql += " ";
			} else if(ordered.equalsIgnoreCase("price")) {
				sql += " ORDER BY PRICE";
			} else if(ordered.equalsIgnoreCase("pop")) {
				sql += " ORDER BY (SELECT COUNT(ITEM_NO) FROM ITEM_LIKE WHERE ITEM_NO = a.ITEM_NO GROUP BY ITEM_NO) DESC NULLS LAST";
			}
			
			sql += "			 ) AS rn   											";
			sql += "		 	 , a.*                                              ";
			sql += "	  	  FROM (                                                ";
			sql += "	  	  		SELECT DISTINCT i.NO                            ";
			sql += "	  	  			 , i.NAME                                   ";
			sql += "	  	  			 , i.TYPE                                   ";
			sql += "	  	  			 , i.TEXT                                   ";
			sql += "	  	  			 , i.PRICE                                  ";
			sql += "	  	  			 , i.POINT                                  ";
			sql += "	  	  			 , i.REGDATE                                ";
			sql += "	  	  			 , i.SIZENAME                               ";
			sql += "	  	  			 , i.POSTER                                 ";
			sql += "	  	  			 , (                                        ";
			sql += "	  	  			 	SELECT ITEM_NO                          ";
			sql += "	  	  			 	  FROM ITEM_LIKE                        ";
			sql += "	  	  			 	 WHERE ITEM_NO = il.ITEM_NO             ";
			
			if(id != null) sql += " 		   AND MEMBER_ID = " + id;
			
			sql += "	  	  			   ) AS ITEM_NO                             ";
			sql += "	  	  			 , (                                        ";
			sql += "	  	  			 	SELECT MEMBER_ID                        ";
			sql += "	  	  			 	  FROM ITEM_LIKE                        ";
			sql += "	  	  			 	 WHERE ITEM_NO = il.ITEM_NO             ";
			
			if(id != null) sql += " 		   AND MEMBER_ID = " + id;
			
			sql += "	  	  			   ) AS MEMBER_ID                           ";
			sql += "	  	  		  FROM ITEM i                                   ";
			sql += "	  	  		  LEFT OUTER JOIN ITEM_LIKE il                  ";
			sql += "	  	  		  	ON i.NO = il.ITEM_NO                        ";
			sql += "	  	  		  LEFT OUTER JOIN MEMBER m                      ";
			sql += "	  	  		  	ON il.MEMBER_ID = m.ID                      ";
			sql += "	  	  		 WHERE 1 = 1                                    ";
			
			if(ty != null) {
				if(ty.equalsIgnoreCase("memory")) {
					sql +=" AND TYPE = '메모리폼 매트리스'";
				} else if(ty.equalsIgnoreCase("air")) {
					sql +=" AND TYPE = '에어 매트리스'";
				} else if(ty.equalsIgnoreCase("luxe")) {
					sql +=" AND TYPE = 'Luxe S collection'";
				} else if(ty.equalsIgnoreCase("forest")) {
					sql +=" AND TYPE = 'ForestWalk'";
				} else if (ty.equalsIgnoreCase("spring")) {
					sql +=" AND TYPE = '스프링 매트리스'";
				} else if (ty.equalsIgnoreCase("all")) {
					sql +="";
				}
			}
			
			sql += "	  	  		) a                                             ";
			sql += "	)                                                           ";
			sql += "	SELECT *                                                    ";
			sql += "	  FROM QDATA                                                ";
			sql += "	 WHERE rn BETWEEN 1 AND 20                                  ";
			
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ShoppingDTO dto = new ShoppingDTO();
				
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
				dto.setItemnum(rs.getInt("ITEM_NO"));
				dto.setId(rs.getString("MEMBER_ID"));
				
				list.add(dto);
			}
			
		
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return list;
	}
	
	public int insertLike(int no, String id) {
		conn = getConn();
		
		int result = 0;
		
		try {
			String sql = "SELECT * FROM ITEM_LIKE WHERE ITEM_NO = ? AND MEMBER_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				sql = "DELETE FROM ITEM_LIKE WHERE ITEM_NO = ? AND MEMBER_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.setString(2, id);
				
				result = ps.executeUpdate();
				
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
				return result;
			}
			
			sql = "INSERT INTO ITEM_LIKE VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			
			result = ps.executeUpdate();
			
			if (ps != null) ps.close();
			if (conn != null) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
