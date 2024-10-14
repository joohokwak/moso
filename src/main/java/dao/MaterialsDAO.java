package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	// delete
	public int deleteMaterial(int no) {
		int result = 0;
		
		try {
			conn = getConn();
			
	        String sql = "DELETE FROM MATERIALS WHERE NO = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
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
