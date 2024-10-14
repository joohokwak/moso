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
			String sql = pg.getQuery(conn, "select * from magazine");
			
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
	
	
}
