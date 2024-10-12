package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.MaterialsDTO;

public class MaterialsDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public List<MaterialsDTO> selectList(Pagination pg) {
		List<MaterialsDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
	        String sql = pg.getQuery(conn, "SELECT * FROM MATERIALS");
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String txt = rs.getString("TXT");
				String msize = rs.getString("MSIZE");
				String poster = rs.getString("POSTER");
				String ofile = rs.getString("OFILE");
				
				list.add(new MaterialsDTO(no, title, txt, msize, poster, ofile)) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
}
