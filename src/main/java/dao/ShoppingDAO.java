package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import dto.ShoppingDTO;

public class ShoppingDAO extends DBCP{
//	private Connection conn;
//	private PreparedStatement ps;
//	private ResultSet rs;
	

	public List<ShoppingDTO> viewMain(String ty, String ordered) {
		List<ShoppingDTO> list = new ArrayList<>();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "";
			sql = "SELECT NO, NAME,TYPE, TEXT, PRICE, POINT, REGDATE, SIZENAME, POSTER FROM ITEM WHERE 1 = 1";
			
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
					} 
				}

			
			if (ordered != null) {				
				if (ordered.equalsIgnoreCase("new")) {
					sql += " ORDER BY REGDATE DESC";
				} else if(ordered.equalsIgnoreCase("pop")) {
					sql += " ";
				} else if(ordered.equalsIgnoreCase("review")) {
					sql += " ";
				} else if(ordered.equalsIgnoreCase("price")) {
					sql += " ORDER BY PRICE";
				}
			}
			
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
	
	public void insertLike(int no, String id) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
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
				
				ps.executeUpdate();
				
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
				return;
			}
			
			sql = "INSERT INTO ITEM_LIKE VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			
			ps.executeUpdate();
			
			if (ps != null) ps.close();
			if (conn != null) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
