package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Order;
import common.Pagination;
import dto.ShoppingDTO;

public class ShoppingDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private List<ShoppingDTO> list;

	public List<ShoppingDTO> viewMain(String ty, String ordered, String id, Pagination pg) {
		list = new ArrayList<>();
		conn = getConn();
		
		try {
			if (ordered.equalsIgnoreCase("new")) {
				pg.setOrderName("REGDATE");
				pg.setOrder(Order.DESC.getOrder());
			} else if(ordered.equalsIgnoreCase("review")) {
				pg.setOrderName(" ");
			} else if(ordered.equalsIgnoreCase("price")) {
				pg.setOrderName("PRICE");
				pg.setOrder(Order.ASC.getOrder());
			} else if(ordered.equalsIgnoreCase("pop")) {
				pg.setOrderName("CNT");
				pg.setOrder(Order.DESC_NULLS_LAST.getOrder());
			}
			                                                                         
			String sql = "";
			sql += "SELECT DISTINCT i.NO                                                                           ";
			sql += "	 , i.NAME                                                                                  ";
			sql += "	 , i.TYPE                                                                                  ";
			sql += "	 , i.TEXT                                                                                  ";
			sql += "	 , i.PRICE                                                                                 ";
			sql += "	 , i.POINT                                                                                 ";
			sql += "	 , i.REGDATE                                                                               ";
			sql += "	 , i.SIZENAME                                                                              ";
			sql += "	 , i.POSTER                                                                                ";
			sql += "	 , (                                                                                       ";
			sql += "		SELECT ITEM_NO                                                                         ";
			sql += "		  FROM ITEM_LIKE                                                                       ";
			sql += "		 WHERE ITEM_NO = il.ITEM_NO                                                            ";
			sql += "		   AND MEMBER_ID = il.MEMBER_ID                                                        ";
			sql += "	   ) AS ITEM_NO                                                                            ";
			sql += "	 , (                                                                                       ";
			sql += "		SELECT MEMBER_ID                                                                       ";
			sql += "		  FROM ITEM_LIKE                                                                       ";
			sql += "		 WHERE ITEM_NO = il.ITEM_NO                                                            ";
			sql += "		   AND MEMBER_ID = il.MEMBER_ID                                                        ";
			sql += "	   ) AS MEMBER_ID                                                                          ";
			sql += "	 , (SELECT COUNT(ITEM_NO) FROM ITEM_LIKE WHERE ITEM_NO = il.ITEM_NO GROUP BY ITEM_NO) CNT  ";
			sql += "  FROM ITEM i                                                                                  ";
			sql += "  LEFT OUTER JOIN ITEM_LIKE il                                                                 ";
			sql += "	ON i.NO = il.ITEM_NO                                                                       ";
			sql += "  LEFT OUTER JOIN MEMBER m                                                                     ";
			sql += "	ON il.MEMBER_ID = m.ID                                                                     ";
			sql += " WHERE 1 = 1                                                                                   ";
				
			if (ty != null) {
				if (ty.equalsIgnoreCase("memory")) {
					sql += " AND TYPE = '메모리폼 매트리스'";
				} else if (ty.equalsIgnoreCase("air")) {
					sql += " AND TYPE = '에어 매트리스'";
				} else if (ty.equalsIgnoreCase("luxe")) {
					sql += " AND TYPE = 'Luxe S collection'";
				} else if (ty.equalsIgnoreCase("forest")) {
					sql += " AND TYPE = 'ForestWalk'";
				} else if (ty.equalsIgnoreCase("spring")) {
					sql += " AND TYPE = '스프링 매트리스'";
				} else if (ty.equalsIgnoreCase("all")) {
					sql += "";
				}
			}
			
			sql = pg.getQuery(conn, sql);
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
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
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
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

	public ShoppingDTO buyMain(String num) {
		
		ShoppingDTO dto = new ShoppingDTO();
		conn = getConn();
		
		try {
			String sql = "";
			
			sql += " SELECT i.NO																						";
			sql += "	  , i.NAME                                                                                  	";
			sql += "	  , i.TYPE                                                                                  	";
			sql += "	  , i.TEXT                                                                                  	";
			sql += "	  , i.PRICE                                                                                 	";
			sql += "	  , i.POINT                                                                                 	";
			sql += "	  , i.REGDATE                                                                               	";
			sql += "	  , i.SIZENAME                                                                              	";
			sql += "	  , i.POSTER  																					";
			sql += " FROM ITEM i 																						";
			sql += " WHERE i.NO = " + num ;
			
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
	
	public List<String> imageName(String num) {
		conn = getConn();
		List<String> images = new ArrayList<>();
		
		
		
		try {
			String sql = "";
			sql += "SELECT * 									";
			sql += " FROM item_image 							";
			sql += "WHERE ITEMNO = " + num;
			
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				images.add(rs.getString("NAME"));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(conn, ps, rs);
		}
		
		
		
		
		
		return images;
	}
	
	
}
