package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.MagazineDTO;

public class MagazineDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// 전체조회
	public List<MagazineDTO> magazineList(Pagination pg) {
		List<MagazineDTO> mglist = new ArrayList<>();
		
		try {
			conn = getConn();
			
			String sql = pg.getQuery(conn, "SELECT * FROM MAGAZINE");
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String poster = rs.getString("POSTER");
				String content = rs.getString("CONTENT");
				String regdate = rs.getString("REGDATE");
				String mtype2 = rs.getString("MTYPE");
				
				mglist.add(new MagazineDTO(no, title, text, poster, content, regdate, mtype2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return mglist;
	}
	
	// 상세보기
	public MagazineDTO magazineView(int no) {
		MagazineDTO mview = null;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM MAGAZINE WHERE NO = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String poster = rs.getString("POSTER");
				String content = rs.getString("CONTENT");
				String regdate = rs.getString("REGDATE");
				String mtype = rs.getString("MTYPE");
				
				mview = new MagazineDTO(no, title, text, poster, content, regdate, mtype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return mview;
	}
	
	// 수정완료
	public int magazineUpdateOk(MagazineDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String mtype = dto.getMtype();
			if ("매거진".equals(mtype)) {
				mtype = "magazine";
			} else if ("월간 리뷰".equals(mtype)) {
				mtype = "review";
			}
			
			String sql = "UPDATE MAGAZINE SET TITLE = ?, TEXT = ?, POSTER = ?, CONTENT = ?, MTYPE = ? WHERE NO = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getText());
			ps.setString(3, dto.getPoster());
			ps.setString(4, dto.getContent());
			ps.setString(5, mtype);
			ps.setInt(6, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 글쓰기
	public int magazineWriteOk(MagazineDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String mtype = dto.getMtype();
			if ("매거진".equals(mtype)) {
				mtype = "magazine";
			} else if ("월간 리뷰".equals(mtype)) {
				mtype = "review";
			}
			
			String sql = "INSERT INTO MAGAZINE VALUES(SEQ_MAGAZINE.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getText());
			ps.setString(3, dto.getPoster());
			ps.setString(4, dto.getContent());
			ps.setString(5, mtype);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 글삭제
	public int magazineDelete(String...no) {
		int result = 0;
		
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM MAGAZINE WHERE NO = ?";
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
	
}