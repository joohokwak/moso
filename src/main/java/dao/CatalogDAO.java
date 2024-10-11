package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import dto.CatalogDTO;

public class CatalogDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public CatalogDAO() {
	}

	public List<CatalogDTO> selectList() {
		List<CatalogDTO> list = new ArrayList<CatalogDTO>();

		try {
			conn = getConn();

			// 2. sql Query문 작성
			String sql = "SELECT NO, TITLE, TO_CHAR(REGDATE,'YYYY-MM-DD') REGDATE, VISITCOUNT  FROM CATALOG ORDER BY NO DESC ";

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

				list.add(new CatalogDTO(no, title, null, regdate, visitcount, null, null));
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

			String sql = "SELECT * FROM CATALOG c, CATALOG_FILE cf WHERE c.NO = cf.CATALNO (+) AND c.NO=?";

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

}
