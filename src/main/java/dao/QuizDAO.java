package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBCP;
import common.Order;
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
				QuizDTO dto = new QuizDTO();
				
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// search 페이지
	public List<QuizDTO> setGoods(Pagination pg, List<Map<String, String>> searchList, String sort, String id) {
		List<QuizDTO> list = new ArrayList<QuizDTO>();
		try {
			conn = getConn();
			// 숫자 외엔 검색 막기
			String regExp = "^[0-9]+$";
			
			StringBuilder tmp = new StringBuilder();
			tmp.append("SELECT																											");
	        tmp.append("	   DISTINCT I.NO																							");
	        tmp.append("	 , I.NAME																									");
	        tmp.append("	 , I.TYPE																									");
	        tmp.append("	 , I.TEXT																									");
	        tmp.append("	 , I.PRICE																									");
	        tmp.append("	 , I.POINT																									");
	        tmp.append("	 , I.REGDATE																								");
	        tmp.append("	 , I.SIZENAME																								");
	        tmp.append("	 , I.POSTER																									");
	        tmp.append("	 , LISTAGG(IL.MEMBER_ID, ',') WITHIN GROUP(ORDER BY IL.ITEM_NO) OVER (PARTITION BY IL.ITEM_NO) AS MEMBER_ID ");
	        tmp.append("	 , IL.ITEM_NO																								");
	        tmp.append("	 , (																										");
	        tmp.append("    	SELECT COUNT(ITEM_NO)																					");
	        tmp.append("    	  FROM ITEM_LIKE																						");
	        tmp.append("    	 WHERE ITEM_NO = I.NO																					");
	        tmp.append("    	 GROUP BY ITEM_NO																						");
	        tmp.append("	   ) AS CNT																									");
	        tmp.append("	 , (																										");
	        tmp.append("    	SELECT AVG(RATING)																						");
	        tmp.append("    	  FROM ITEM_REVIEW																						");
	        tmp.append("    	 WHERE ITEMNO = I.NO																					");
	        tmp.append("    	 GROUP BY ITEMNO																						");
	        tmp.append("	   ) AS RAITNG																								");
		    tmp.append("  FROM ITEM I																									");
		    tmp.append("  LEFT OUTER JOIN ITEM_LIKE IL																					");
		    tmp.append("	ON I.NO = IL.ITEM_NO																						");
		    tmp.append(" WHERE 1 = 1																									");
			
			if (!searchList.isEmpty()) {
				for (Map<String, String> searchMap : searchList) {
					for (Map.Entry<String, String> entry : searchMap.entrySet()) {
						String key = entry.getKey();
						String keyword = entry.getValue();
						
						// 검색조건, 검색키워드가 있을 때
						if (key != null && keyword != null) {
							// 검색 옵션이 상품명일 때
							if ("goodsName".equals(key)) {
								tmp.append(" AND UPPER(NAME) LIKE UPPER('%" + keyword + "%')");
							// 검색 옵션이 상품 코드일 때 + 숫자만 검색되도록 처리
							} else if ("goodsNo".equals(key) && keyword.matches(regExp)) {
								tmp.append(" AND NO = " + keyword); 
							// 검색 옵션이 상품 설명일 때
							} else if ("goodsText".equals(key)) {
								tmp.append(" AND UPPER(TEXT) LIKE UPPER('%" + keyword + "%')"); 
							}
						}
					}
				}
			}
			
			// 상품 검색 결과 정렬 기준
			// 인기순 (로그인 된 경우 로그인 사용자 우선으로 정렬)
			if (sort.equals("pop_desc")) {
				pg.setOrderName("CASE WHEN MEMBER_ID LIKE '%" + id + "%' THEN 1 ELSE 2 END, CNT DESC NULLS LAST");
				pg.setOrder("");
			// 신상품순
			} else if (sort.equals("regdate_desc")) {
				pg.setOrderName("REGDATE");
				pg.setOrder(Order.DESC_NULLS_LAST.getOrder());
			// 가격순
			} else if (sort.equals("price_desc")) {
				pg.setOrderName("PRICE");
				pg.setOrder(Order.ASC_NULLS_LAST.getOrder());
			// 상품평순
			} else if (sort.equals("review_desc")) {
				pg.setOrderName("RAITNG");
				pg.setOrder(Order.DESC_NULLS_LAST.getOrder());
			}
			
			// 검색 결과 개수
			String tc = "";
			tc += "SELECT COUNT(a.NO) CNT";
			tc += "  FROM ( ";
			tc += tmp.toString();
			tc += " 	  ) a";
			
			ps = conn.prepareStatement(tc);
			rs = ps.executeQuery();
			
			// 검색 내 상품 총 개수
			int totalCount = 0;
			if (rs.next()) totalCount = rs.getInt("CNT");
			
			
			// 검색 결과 조회
			String sql = pg.getQuery(conn, tmp.toString());
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QuizDTO dto = new QuizDTO();
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
				dto.setMemberid(rs.getString("MEMBER_ID"));
				dto.setTotalCount(totalCount);
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
}
