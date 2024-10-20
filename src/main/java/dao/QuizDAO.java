package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Pagination;
import dto.QuizDTO;

public class QuizDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// Quiz 경우의 수 && 결과
	public List<QuizDTO> mattressQuiz(String sumQ) {
		List<QuizDTO> list = new ArrayList<QuizDTO>();
		
		try {
			conn = getConn();
					
			String sql = "SELECT * FROM ITEM ";
			switch (sumQ) {
				case "1111": 
					sql += " WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%096%') ";
					break;
					
				case "1112": case "1222": 
					sql += " WHERE (POSTER LIKE '%058%' OR POSTER LIKE '%082%') ";
					break;
					
				case "1121": case "2221":
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%069%') ";
					break;
					
				case "1122": case "2111": case "2112": case "2222": 
				case "2311": case "2312":
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%032%') ";
					break;
					
				case "1131":
					sql += " WHERE (POSTER LIKE '%071%' OR POSTER LIKE '%096%') ";
					break;
					
				case "1132": case "2132":
					sql += " WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%071%') ";
					break;
					
				case "1211": case "1311":
					sql += " WHERE (POSTER LIKE '%073%')						";
					break;
					
				case "1212": case "1312":
					sql += " WHERE (POSTER LIKE '%048%' OR POSTER LIKE '%073%') ";
					break;
					
				case "1221":
					sql += " WHERE (POSTER LIKE '%058%' OR POSTER LIKE '%087%') ";
					break;
				
				case "1231": case "1232":
					sql += " WHERE (POSTER LIKE '%053%' OR POSTER LIKE '%085%') ";
					break;
					
				case "1321":
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%087%') ";
					break;
					
				case "1322":
					sql += " WHERE (POSTER LIKE '%053%' OR POSTER LIKE '%087%') ";
					break;
					
				case "1331":
					sql += " WHERE (POSTER LIKE '%071%' OR POSTER LIKE '%085%') ";
					break;
					
				case "1332":
					sql += " WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%053%') ";
					break;
					
				case "2121": case "2122": case "2321": case "2322": case "2331":
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%071%') ";
					break;
					
				case "2211":
					sql += " WHERE (POSTER LIKE '%087%')						";
					break;
					
				case "2212":
					sql += " WHERE (POSTER LIKE '%073%' OR POSTER LIKE '%087%') ";
					break;
					
				case "2231": case "2232":
					sql += " WHERE (POSTER LIKE '%025%')						";
					break;
					
				case "2332":
					sql += " WHERE (POSTER LIKE '%032%' OR POSTER LIKE '%071%') ";
					break;
					
				case "3111": case "3112": case "3222": case "3321": case "3322":
					sql += " WHERE (POSTER LIKE '%058%' OR POSTER LIKE '%082%') ";
					break;
					
				case "3121":
					sql += " WHERE (POSTER LIKE '%069%' OR POSTER LIKE '%082%') ";
					break;
					
				case "3122":
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%032%') ";
					break;
					
				case "3131":
					sql += " WHERE (POSTER LIKE '%071%' OR POSTER LIKE '%096%') ";
					break;
					
				case "3132":
					sql += " WHERE (POSTER LIKE '%050%' OR POSTER LIKE '%071%') ";
					break;
					
				case "3211": case "3212": case "3311": case "3312":
					sql += " WHERE (POSTER LIKE '%048%' OR POSTER LIKE '%073%') ";
					break;
					
				case "3221":
					sql += " WHERE (POSTER LIKE '%058%' OR POSTER LIKE '%087%') ";
					break;
					
				case "3231": case "3232": case "3331": case "3332":
					sql += " WHERE (POSTER LIKE '%053%' OR POSTER LIKE '%085%') ";
					break;
					
				case "4111": case "4112": case "4231": case "4232":
					sql += " WHERE (POSTER LIKE '%025%') ";
					break;
					
				case "4121": case "4122": case "4131": case "4321": case "4322": case "4331":
					sql += " WHERE (POSTER LIKE '%071%') ";
					break;
					
				case "4132": case "4332":
					sql += " WHERE (POSTER LIKE '%050%') ";
					break;
					
				case "4211": case "4212": 
					sql += " WHERE (POSTER LIKE '%073%' OR POSTER LIKE '%087%') ";
					break;
					
				case "4221": 
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%069%') ";
					break;
					
				case "4222": case "4311": case "4312":
					sql += " WHERE (POSTER LIKE '%025%' OR POSTER LIKE '%032%') ";
					break;
				
				default: break;
			}

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
				
				list.add(new QuizDTO(no, name, type, text, price, point, regdate, sizename, poster, 0));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// search 페이지
	public List<QuizDTO> setGoods(Pagination pg, String key, String keyword) {
		List<QuizDTO> list = new ArrayList<QuizDTO>();
		try {
			conn = getConn();
			// 숫자 외엔 검색 막기
			String regExp = "^[0-9]+$";
			
			String tmp = "SELECT * FROM ITEM WHERE 1 = 1";
			// 검색 단어가 있으면
			if (keyword != null && keyword.length() > 0) {
				// 검색 옵션이 상품명일 때
				if (key.equals("goodsName")) {
					tmp += " AND UPPER(NAME) LIKE UPPER('%" + keyword + "%')";
				// 검색 옵션이 상품 코드일 때
				} else if (key.equals("goodsNo") && keyword.matches(regExp)) {
					tmp += " AND NO = " + keyword; 
				// 검색 옵션이 상품 설명일 때
				} else if (key.equals("goodsText")) {
					tmp += " AND UPPER(TEXT) LIKE UPPER('%" + keyword + "%')"; 
				}
			}
			
			// 검색 결과 개수
			String tc = "";
			tc += "SELECT count(a.no) cnt";
			tc += " FROM ( ";
			tc += tmp;
			tc += " ) a";
			
			ps = conn.prepareStatement(tc);
			
			rs = ps.executeQuery();
			
			int totalCount = 0;
			if (rs.next()) totalCount = rs.getInt("cnt");
			
			
			String sql = pg.getQuery(conn, tmp);
			
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
				
				list.add(new QuizDTO(no, name, type, text, price, point, regdate, sizename, poster, totalCount));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
}
