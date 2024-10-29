package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.NoticeDTO;

public class NoticeDAO extends DBCP {
	private Connection conn; 
	private PreparedStatement ps;
	private ResultSet rs;
	
	// 전체조회
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
	
	// 조회수
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
	
	// 상세조회
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
	
	// 공지 등록
	public int getInsertNotice(NoticeDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
			// 글쓰기의 첨부파일 여부를 확인하고 첨부파일이 있으면 첨부파일의 fileNo와 새 글의 no를 일치시키야 함
			// 글쓰기 번호를 더미테이블을 통해 확인 후 생성된 새 글의 no를 첨부파일 여부 체크 후 fileNo에 삽입하는 순서
			
			// 1. dummy table을 통해 seq_no 생성
			String sql = "SELECT SEQ_NOTICE.NEXTVAL FROM DUAL";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int insertNo = 0;
			
			if (rs.next()) {
				insertNo = rs.getInt(1);
			}
			
			// 2. 글쓰기 시 seq_no 반영 및 글쓰기 완료 후 Connection 자동 commit 방지(첨부파일 여부 확인해야 하므로)
			if (insertNo > 0) {
				conn.setAutoCommit(false);
				
				sql = "INSERT INTO NOTICE VALUES(?, ?, ?, SYSDATE, 0)";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, insertNo);
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContent());
				
				result = ps.executeUpdate();
			
				// 3. 첨부파일 여부 체크 및 Notice_File table 생성
				if (result > 0) {
					if (dto.getOfile() != null) {
						sql = "INSERT INTO NOTICE_FILE VALUES(SEQ_NOTICE_FILE.NEXTVAL, ?, ?, SYSDATE, ?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, dto.getOfile());
						ps.setString(2, dto.getNfile());
						ps.setInt(3, insertNo);
						
						result = ps.executeUpdate();
						
						// 최종 insert 완료되면 commit
						if (result > 0) {
							conn.commit();
						// 실패하면 notice, notice_file 모두 롤백
						} else {
							conn.rollback();
						} 
					// 첨부파일 없으면 그냥 commit
					} else {
						conn.commit();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 글삭제
	public int getDeleteNotice(String...selNo) {
		int result = 0;
		
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			
			String delSql = "DELETE FROM NOTICE_FILE WHERE NOTICENO = ?";
			ps = conn.prepareStatement(delSql);
			
			for (String no : selNo) {
				ps.setInt(1, Integer.parseInt(no));
				ps.executeUpdate();
			}
				
			String sql = "DELETE FROM NOTICE WHERE NO = ?";
			ps = conn.prepareStatement(sql);
				
			for (String no : selNo) {
				ps.setInt(1, Integer.parseInt(no));
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
	
	// 글 수정
	public int getNoticeUpdate(NoticeDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			String sqlN = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE NO=?";
			
			ps = conn.prepareStatement(sqlN);
			
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getNo());
			
			ps.executeUpdate();
			int increase = 1;
			
			String sqlNF = "";
			sqlNF += "MERGE INTO NOTICE_FILE NF                                           ";
			sqlNF += "USING (SELECT NOTICE.NO FROM NOTICE WHERE NO = ?) N                 ";
			sqlNF += "ON (NF.NOTICENO = N.NO)                                             ";
			sqlNF += "WHEN MATCHED THEN                                                   ";
			sqlNF += "	UPDATE SET                                                        ";
			sqlNF += "			NF.OFILE = ?                                        	  ";
			sqlNF += "		  , NF.NFILE = ?                                              ";
			sqlNF += "	WHERE NF.NOTICENO = ?                                             ";
			
			if (dto.getNfile() == null) {
				sqlNF += "	DELETE WHERE NF.NOTICENO = ?                                  ";
			}
			
			sqlNF += "WHEN NOT MATCHED THEN                                               ";
			sqlNF += "	INSERT VALUES (SEQ_NOTICE_FILE.NEXTVAL, ?, ?, SYSDATE, ?)         ";
			
			ps = conn.prepareStatement(sqlNF);
			
			ps.setInt(increase++, dto.getNo());
			ps.setString(increase++, dto.getOfile());
			ps.setString(increase++, dto.getNfile());
			ps.setInt(increase++, dto.getNo());
			
			if (dto.getNfile() == null) ps.setInt(increase++, dto.getNo());
			
			ps.setString(increase++, dto.getOfile());
			ps.setString(increase++, dto.getNfile());
			ps.setInt(increase++, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
}
