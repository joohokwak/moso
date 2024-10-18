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
	private Connection conn; 
	private PreparedStatement ps;
	private ResultSet rs;
	
	public List<NoticeDTO> getSelectAll(Pagination pg){
		List<NoticeDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			String str = "";
			str += "SELECT n.NO											";
			str += "	 , n.TITLE										";
			str += "	 , TO_CHAR(n.REGDATE, 'YYYY-MM-DD') AS REGDATE	";
			str += "	 , n.VISITCOUNT									";
			str += "	 , nf.OFILE										";
			str += "	 , nf.NFILE										";
			str += "  FROM NOTICE n										";
			str += "  LEFT OUTER JOIN NOTICE_FILE nf					";
			str += "	ON n.NO = nf.NOTICENO							";
			
			String sql = pg.getQuery(conn, str);
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String regdate = rs.getString("REGDATE");
				int visit = rs.getInt("VISITCOUNT");
				String ofile = rs.getString("OFILE");
				String nfile = rs.getString("NFILE");
				
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
	
	public void getVisitCount(int no) {
		try {
			conn = getConn();
			String sql = "UPDATE NOTICE SET VISITCOUNT = VISITCOUNT + 1 WHERE NO=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
	}
	
	public NoticeDTO getSelectOne(int num) {
		NoticeDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "SELECT n.NO											";
			sql += "	 , n.TITLE										";
			sql += "	 , n.CONTENT									";
			sql += "	 , TO_CHAR(n.REGDATE, 'YYYY-MM-DD') AS REGDATE	";
			sql += "	 , n.VISITCOUNT									";
			sql += "	 , nf.OFILE										";
			sql += "	 , nf.NFILE										";
			sql += "  FROM NOTICE n 									";
			sql += "  LEFT OUTER JOIN NOTICE_FILE nf 					";
			sql += "	ON N.NO = NF.NOTICENO							";
			sql += " WHERE N.NO = ?										";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String regdate = rs.getString("REGDATE");
				int visit = rs.getInt("VISITCOUNT");
				String ofile = rs.getString("OFILE");
				String nfile = rs.getString("NFILE");
				
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
			
			String sql = "INSERT INTO NOTICE VALUES(SEQ_NOTICE.NEXTVAL, ?, ?, SYSDATE, 0)";
			
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
	
	public int getDeleteNotice(String[] selNo) {
		int result = 0;
		
		try {
			conn = getConn();
			for(int i =0; i < selNo.length ; i++) {
				String delSql = "DELETE FROM NOTICE_FILE WHERE NOTICENO = ?";
				ps = conn.prepareStatement(delSql);
				ps.setInt(1, Integer.parseInt(selNo[i]));
				
				ps.executeUpdate();
				
				String sql = "DELETE FROM NOTICE WHERE NO = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(selNo[i]));
				
				result = ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}
	
	public int getNoticeUpdate (NoticeDTO dto) {
		int result = 0;
		try {
			conn = getConn();
			String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE NO=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {

		} finally {
			close(conn, ps, rs);
		}
		return result;
	}
}





