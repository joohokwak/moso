package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.CatalogDTO;

public class CatalogDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

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
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String regdate = rs.getString("REGDATE");
				int visitcount = rs.getInt("VISITCOUNT");
				String ofile = rs.getString("OFILE");
				String nfile = rs.getString("NFILE");

				list.add(new CatalogDTO(no, title, null, regdate, visitcount, ofile, nfile));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return list;
	}

	public CatalogDTO selectOne(int num) {
		CatalogDTO dto = null;

		try {
			conn = getConn();

			String sql = "SELECT * FROM CATALOG c, CATALOG_FILE cf WHERE c.NO = cf.CATALNO (+) AND c.NO = ?";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, num);

			rs = ps.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String regdate = rs.getString("REGDATE");
				int visitcount = rs.getInt("VISITCOUNT");
				String ofile = rs.getString("OFILE");
				String nfile = rs.getString("NFILE");

				dto = new CatalogDTO(no, title, content, regdate, visitcount, ofile, nfile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return dto;
	}

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
			}

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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return result;
	}

	public int updateCatalog(CatalogDTO dto) {
		int result = 0;

		try {
			conn = getConn();

			conn.setAutoCommit(false);

			String sql = "UPDATE CATALOG c SET TITLE = ?, CONTENT = ? WHERE NO = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getNo());

			result = ps.executeUpdate();

			// update 성공했다면 파일 업로드 여부 체크
			if (result > 0) {
				// 파일 업로드를 할 때
				if (dto.getOfile() != null) {

					sql = "UPDATE CATALOG_FILE SET ofile = ?, nfile = ? where no = ? and catalno = ?";

					ps = conn.prepareStatement(sql);
					ps.setString(1, dto.getOfile());
					ps.setString(2, dto.getNfile());
					ps.setInt(3, dto.getNo());

					result = ps.executeUpdate();

					// update 성공하면 커밋
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return result;
	}

	public int deleteCatalog(CatalogDTO dto) {
		int result = 0;

		try {
			conn = getConn();

			String sql = "DELETE FROM CATALOG_FILE WHERE CATALNO = ?";

			ps = conn.prepareStatement(sql);

			ps.setInt(1, dto.getNo());

			ps.executeUpdate();

			sql = "DELETE FROM CATALOG WHERE NO = ?";

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

	public int deleteCatalog(String[] no) {
		int result = 0;

		try {
			conn = getConn();

			for (String num : no) {
				String sql = "DELETE FROM CATALOG_FILE WHERE CATALNO = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(num));
				ps.executeUpdate();

				sql = "DELETE FROM CATALOG WHERE NO = ?";
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
}
