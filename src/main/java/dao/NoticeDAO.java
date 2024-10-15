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
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public List<NoticeDTO> getSelectAll(Pagination pg){
		List<NoticeDTO> list = new ArrayList<>();
		
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
	
	public NoticeDTO getSelectOne(int num) {
		NoticeDTO dto = null;
		
		try {
			conn = getConn();
			String sql = "SELECT n.NO"
					+ "	  , n.title"
					+ "	  , n.content"
					+ "	  , to_char(n.REGDATE, 'YYYY-MM-DD') as regdate"
					+ "	  , n.VISITCOUNT"
					+ "	  , nf.OFILE"
					+ "	  , nf.NFILE"
					+ "	FROM notice n "
					+ " LEFT OUTER JOIN notice_file nf "
					+ " ON N.NO = NF.NOTICENO"
					+ "	WHERE N.NO=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdate = rs.getString("regdate");
				int visit = rs.getInt("visitcount");
				String ofile = rs.getString("ofile");
				String nfile = rs.getString("nfile");
				
				dto = new NoticeDTO(no, title, content, regdate, visit, ofile, nfile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return dto;
	}
	
	public int getInsertNotice(NoticeDTO dto) {
		int result = 0;
		try {
			conn = getConn();
			String sql = "INSERT INTO notice n values(seq_notice.nextval, ?, ?, SYSDATE, 0)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}
	
	public int getDeleteNotice(NoticeDTO dto) {
		int result = 0;
		try {
			conn = getConn();
			String sql = "DELETE FROM notice WHERE NO=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getNo());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}
}
