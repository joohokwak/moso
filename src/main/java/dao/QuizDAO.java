package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import dto.QuizDTO;

public class QuizDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public List<QuizDTO> mattressQuiz(String sumQ) {
		List<QuizDTO> list = new ArrayList<QuizDTO>();
		
		try {
			conn = getConn();
					
			
			String sql = "";
			if (sumQ.equals("1111")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%096%') ";
			} 
			else if (sumQ.equals("1112") || sumQ.equals("1222")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%058%' OR POSTER LIKE '%082%') ";
			}
			else if (sumQ.equals("1121") || sumQ.equals("2221")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%069%') ";
			}
			else if (sumQ.equals("1122") || sumQ.equals("2111") || sumQ.equals("2112") || sumQ.equals("2222") || sumQ.equals("2231") || sumQ.equals("2311") || sumQ.equals("2312")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%032%') ";
			}
			else if (sumQ.equals("1131")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%071%' OR POSTER LIKE '%096%') ";
			}
			else if (sumQ.equals("1132") || sumQ.equals("2132")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%071%') ";
			}
			else if (sumQ.equals("1211") || sumQ.equals("1311")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%073%')						   ";
			}
			else if (sumQ.equals("1212") || sumQ.equals("1312")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%048%' OR POSTER LIKE '%073%') ";
			}
			else if (sumQ.equals("1221")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%058%' OR POSTER LIKE '%087%') ";
			}
			else if (sumQ.equals("1231") || sumQ.equals("1232")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%053%' OR POSTER LIKE '%085%') ";
			}
			else if (sumQ.equals("1321")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%087%') ";
			}
			else if (sumQ.equals("1322")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%053%' OR POSTER LIKE '%087%') ";
			}
			else if (sumQ.equals("1331")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%071%' OR POSTER LIKE '%085%') ";
			}
			else if (sumQ.equals("1332")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%053%') ";
			}
			else if (sumQ.equals("2121") || sumQ.equals("2122") || sumQ.equals("2231") || sumQ.equals("2321")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%071%') ";
			}
			else if (sumQ.equals("2211")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%087%') 					   ";
			}
			else if (sumQ.equals("2212")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%073%' OR POSTER LIKE '%087%') ";
			}
			else if (sumQ.equals("2231") || sumQ.equals("2232")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%025%')						   ";
			}
			else if (sumQ.equals("2322")) {
				sql += "SELECT *                 	   						   ";
				sql += "	FROM ITEM            	  						   ";
				sql += "  	WHERE (POSTER LIKE '%0%' OR POSTER LIKE '%0%') ";
			}

			// 2321
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String type = rs.getString("TYPE");
				String text = rs.getString("TEXT");
				int price = rs.getInt("PRICE");
				String point = rs.getString("POINT");
				String regdate = rs.getString("REGDATE");
				String sizename = rs.getString("SIZENAME");
				String poster = rs.getString("POSTER");
				
				list.add(new QuizDTO(no, name, type, text, price, point, regdate, sizename, poster));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
}
