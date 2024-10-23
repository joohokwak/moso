package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.FaqDTO;

public class FaqDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	// 전체조회
	public List<FaqDTO> selectList(Pagination pg) {
		List<FaqDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
	        String sql = pg.getQuery(conn, "SELECT NO, CATE, TITLE, CONTENT, TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE FROM FAQ");
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String cate = rs.getString("CATE");
				String content = rs.getString("CONTENT");
				String regdate = rs.getString("REGDATE");
				
				list.add(new FaqDTO(no, title, cate, content, regdate));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// 상세조회
	public FaqDTO selectFaq(int no) {
		FaqDTO faq = null;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM FAQ WHERE NO = ?";
	        
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("TITLE");
				String cate = rs.getString("CATE");
				String content = rs.getString("CONTENT");
				String regdate = rs.getString("REGDATE");
				
				faq = new FaqDTO(no, title, cate, content, regdate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return faq;
	}
	
	// 글등록
	public int insertFaq(FaqDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, ?, ?, ?, SYSDATE)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getCate());
			ps.setString(3, dto.getContent());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 글삭제
	public int deleteFaq(String...no) {
		int result = 0;
		
		try {
			conn = getConn();
			
			for (String num : no) {
				String sql = "DELETE FROM FAQ WHERE NO = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(num));
				
				result = ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 글수정
	public int updateFaq(FaqDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "UPDATE FAQ SET TITLE = ?, CATE = ?, CONTENT = ? WHERE NO = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getCate());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
}
