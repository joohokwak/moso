package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.MaterialsDTO;

public class MaterialsDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// list
	public List<MaterialsDTO> selectList(Pagination pg) {
		List<MaterialsDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
	        String sql = pg.getQuery(conn, "SELECT * FROM MATERIALS");
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String txt = rs.getString("TXT");
				String msize = rs.getString("MSIZE");
				String poster = rs.getString("POSTER");
				String ofile = rs.getString("OFILE");
				String nfile = rs.getString("NFILE");
				String regdate = rs.getString("REGDATE");
				
				list.add(new MaterialsDTO(no, title, txt, msize, poster, ofile, nfile, regdate));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// insert
	public int insertMaterial(MaterialsDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
	        String sql = "";
	        sql += "INSERT INTO MATERIALS VALUES (							";
        	sql += "    SEQ_MATERIALS.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE	";
        	sql += ")														";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getTxt().replaceAll("<[^>]*>", ""));
			ps.setString(3, dto.getMsize());
			ps.setString(4, dto.getPoster());
			ps.setString(5, dto.getOfile());
			ps.setString(6, dto.getNfile());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// update
	public int updateMaterial(MaterialsDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
	        String sql = "";
	        sql += "UPDATE MATERIALS SET ";
        	sql += " TITLE = ?, TXT = ?, MSIZE = ?, POSTER = ? ";
        	
        	if (dto.getOfile() != null) sql += ", OFILE = '" + dto.getOfile() + "'";
        	if (dto.getNfile() != null) sql += ", NFILE = '" + dto.getNfile() + "'";
        	
        	sql += " WHERE NO = ?";
        	
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getTxt().replaceAll("<[^>]*>", ""));
			ps.setString(3, dto.getMsize());
			ps.setString(4, dto.getPoster());
			ps.setInt(5, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// delete
	public int deleteMaterial(String...no) {
		int result = 0;
		
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM MATERIALS WHERE NO = ?";
			ps = conn.prepareStatement(sql);
			
			for (String num : no) {
				ps.setInt(1, Integer.parseInt(num));
				result += ps.executeUpdate();
			}
			
			conn.commit();
			
		} catch (Exception e) {
			if (conn != null) {
	            try {
	                conn.rollback(); // 오류 발생 시 롤백
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 상세조회
	public MaterialsDTO selectOne(int no) {
		MaterialsDTO dto = null;
		
		try {
			conn = getConn();
			
	        String sql = "SELECT * FROM MATERIALS WHERE NO = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("TITLE");
				String txt = rs.getString("TXT");
				String msize = rs.getString("MSIZE");
				String poster = rs.getString("POSTER");
				String ofile = rs.getString("OFILE");
				String nfile = rs.getString("NFILE");
				String regdate = rs.getString("REGDATE");
				
				dto = new MaterialsDTO(no, title, txt, msize, poster, ofile, nfile, regdate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
		
}
