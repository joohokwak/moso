package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.NoticeDTO;

public class NoticeDAO extends DBCP {
	private Connection conn = null; 
	
	public List<NoticeDTO> getSelectAll(Pagination pg){
		List<NoticeDTO> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConn();
			String sql = pg.getQuery(conn, "SELECT n.NO"
					+ "	  , n.title"
					+ "	  , to_char(n.REGDATE, 'YYYY-MM-DD') as regdate"
					+ "	  , n.VISITCOUNT"
					+ "	  , nf.OFILE"
					+ "	  , nf.NFILE"
					+ "	FROM notice n "
					+ "	LEFT OUTER JOIN NOTICE_FILE nf "
					+ "	ON n.NO = nf.NOTICENO");
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String regdate = rs.getString("regdate");
				int visit = rs.getInt("visitcount");
				String ofile = rs.getString("ofile");
				String nfile = rs.getString("nfile");
				
				NoticeDTO dto = new NoticeDTO(no, title, null, regdate, visit, ofile, nfile);
				
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}
}
