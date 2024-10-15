package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.MagazineDTO;

public class MagazineDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public MagazineDAO() {
		conn = getConn();
	}
	
	// 전체조회
	public List<MagazineDTO> magazineList(Pagination pg) {
		List<MagazineDTO> mglist = new ArrayList<>();
		
		try {
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
			String sql = "UPDATE MAGAZINE SET TITLE=?, TEXT=?, CONTENT=?, MTYPE=? WHERE NO=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getText());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getMtype());
			ps.setInt(5, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
}