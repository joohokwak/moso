package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.CatalogDTO;

public class CatalogDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	// 리스트
	public List<CatalogDTO> selectList(Pagination pg) {
		List<CatalogDTO> list = new ArrayList<CatalogDTO>();

		try {
			conn = getConn();

			// 2. sql Query문 작성
			// String sql = "SELECT NO, TITLE, TO_CHAR(REGDATE,'YYYY-MM-DD') REGDATE,
			// VISITCOUNT FROM CATALOG ORDER BY NO DESC ";

			String str = "";
			str += "SELECT c.NO                                            ";
			str += "	 , c.TITLE                                         ";
			str += "	 , TO_CHAR(c.REGDATE, 'YYYY-MM-DD') AS  REGDATE    ";
			str += "	 , c.VISITCOUNT                                    ";
			str += "	 , cf.OFILE                                        ";
			str += "	 , cf.NFILE                                        ";
			str += "  FROM CATALOG c LEFT OUTER JOIN CATALOG_FILE cf       ";
			str += "	ON c.NO = cf.CATALNO                               ";

			String sql = pg.getQuery(conn, str);

			// 3. preparedstatement 연결
			ps = conn.prepareStatement(sql);

			// 4. mapping

			// 5. Query문 실행
			rs = ps.executeQuery();

			while (rs.next()) {
				CatalogDTO dto = new CatalogDTO();
				dto.setNo(rs.getInt("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setVisitcount(rs.getInt("VISITCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setNfile(rs.getString("NFILE"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return list;
	}

	// 상세조회
	public CatalogDTO selectOne(int num) {
		CatalogDTO dto = null;

		try {
			conn = getConn();

			String sql = "SELECT * FROM CATALOG c, CATALOG_FILE cf WHERE c.NO = cf.CATALNO(+) AND c.NO = ?";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, num);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new CatalogDTO();
				dto.setNo(rs.getInt("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setVisitcount(rs.getInt("VISITCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setNfile(rs.getString("NFILE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return dto;
	}

	// 조회수
	public int plusVisitCount(int num) {
		int result = 0;

		try {
			conn = getConn();

			String sql = "UPDATE CATALOG SET VISITCOUNT = VISITCOUNT + 1  WHERE NO = ? ";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, num);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return result;
	}

	// 추가
	public int insertCatalog(CatalogDTO dto) {
		int result = 0;

		try {
			conn = getConn();

			int insertNo = 0;

			// CATALOG c, CATALOG_FILE cf
			// c.NO와 cf.CATALNO를 일치시키기 위해
			// 더미테이블을 이용해 다음 시퀀스값을 조회
			String sql = "SELECT SEQ_CATALOG.NEXTVAL FROM DUAL";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			// insertNo에 시퀀스를 변수로 담기
			if (rs.next()) {
				insertNo = rs.getInt(1);

				// insertNo가 0보다 크면 CATALOG c 에 담기
				if (insertNo > 0) {
					conn.setAutoCommit(false);
	
					sql = "INSERT INTO CATALOG VALUES (?, ?, ?, SYSDATE, 0)";
	
					ps = conn.prepareStatement(sql);
					ps.setInt(1, insertNo);
					ps.setString(2, dto.getTitle());
					ps.setString(3, dto.getContent());
	
					result = ps.executeUpdate();
	
					// insert 성공했다면 파일 업로드 여부 체크
					if (result > 0) {
						// 파일 업로드를 할 때
						if (dto.getOfile() != null) {
							sql = "INSERT INTO CATALOG_FILE VALUES (SEQ_CATALOG_FILE.NEXTVAL, ?, ?, SYSDATE, ?)";
	
							ps = conn.prepareStatement(sql);
							ps.setString(1, dto.getOfile());
							ps.setString(2, dto.getNfile());
							ps.setInt(3, insertNo);
	
							result = ps.executeUpdate();
	
							// insert 성공하면 커밋
							if (result > 0) {
								conn.commit();
	
							// 실패하면 catalog, catalog_file 모두 롤백
							} else {
								conn.rollback();
							}
	
							// 파일 업로드 없이 커밋
						} else {
							conn.commit();
						}
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

	// 수정
	public int updateCatalog(CatalogDTO dto) {
		int result = 0;

		try {
			conn = getConn();

			conn.setAutoCommit(false);

			// 카탈로그 테이블
			String sqlC = "UPDATE CATALOG c SET TITLE = ?, CONTENT = ? WHERE NO = ?";

			ps = conn.prepareStatement(sqlC);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getNo());

			ps.executeUpdate();
			
			// setString, setInt 파라미터 인덱스 값을 increase++로 대체
			int increase = 1;

			// 카탈로그 파일 테이블
			// 수정 시 파일이 있는 경우
			String sqlCf = "";
			sqlCf += "MERGE                                                                                                              ";
			sqlCf += "INTO CATALOG_FILE CF                                                                                               ";
			sqlCf += "USING (SELECT CATALOG.NO FROM CATALOG WHERE CATALOG.NO = ?) C                                                      ";
			sqlCf += "ON (CF.CATALNO = C.NO)                                                                                             ";
			sqlCf += "WHEN MATCHED THEN                                                                                                  ";
			sqlCf += "	UPDATE SET 																										 ";
			sqlCf += "		CF.OFILE = ?																								 ";
			sqlCf += " 	  , CF.NFILE = ?																								 ";
			sqlCf += "	WHERE CF.CATALNO = ?																							 ";
			
			// 수정 시 파일이 있던 게시글에 파일을 지우는 경우
			if (dto.getOfile() == null) {
				sqlCf += "DELETE WHERE CF.CATALNO = ?																					 "; 
			}
			
			// 수정 시 파일이 없던 게시글에 파일을 추가하는 경우
			sqlCf += "WHEN NOT MATCHED THEN 																							 ";
			sqlCf += "	INSERT (NO, OFILE, NFILE, REGDATE, CATALNO) 																	 ";
			sqlCf += "	VALUES (SEQ_CATALOG_FILE.NEXTVAL, ?, ?, SYSDATE, ?)																 ";

			ps = conn.prepareStatement(sqlCf);
			ps.setInt(increase++, dto.getNo());
			ps.setString(increase++, dto.getOfile());
			ps.setString(increase++, dto.getNfile());
			ps.setInt(increase++, dto.getNo());
			
			if (dto.getOfile() == null) {
				ps.setInt(increase++, dto.getNo());
			}
			
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

	// 삭제
	public int deleteCatalog(String...no) {
		int result = 0;

		try {
			conn = getConn();
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM CATALOG_FILE WHERE CATALNO = ?";
			ps = conn.prepareStatement(sql);
			for (String num : no) {
				ps.setInt(1, Integer.parseInt(num));
				ps.executeUpdate();
			}
			
			sql = "DELETE FROM CATALOG WHERE NO = ?";
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
