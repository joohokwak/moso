package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBCP;
import common.Order;
import common.Pagination;
import dto.ItemReviewDTO;
import dto.ShoppingDTO;

public class ShoppingDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private List<ShoppingDTO> list;

	// 상품 리스트 (조건별 조회)
	public List<ShoppingDTO> viewMain(String ty, String ordered, String id, Pagination pg) {
		list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT																											");
	        sql.append("	   DISTINCT i.NO																							");
	        sql.append("	 , i.NAME																									");
	        sql.append("	 , i.TYPE																									");
	        sql.append("	 , i.TEXT																									");
	        sql.append("	 , i.PRICE																									");
	        sql.append("	 , i.POINT																									");
	        sql.append("	 , i.REGDATE																								");
	        sql.append("	 , i.SIZENAME																								");
	        sql.append("	 , i.POSTER																									");
	        sql.append("	 , LISTAGG(il.MEMBER_ID, ',') WITHIN GROUP(ORDER BY IL.ITEM_NO) OVER (PARTITION BY IL.ITEM_NO) AS MEMBER_ID	");
	        sql.append("	 , IL.ITEM_NO																								");
	        sql.append("	 , (																										");
	        sql.append("		SELECT COUNT(ITEM_NO)																					");
	        sql.append("		  FROM ITEM_LIKE																						");
	        sql.append("		 WHERE ITEM_NO = I.NO																					");
	        sql.append("		 GROUP BY ITEM_NO																						");
	        sql.append("	   ) AS CNT																									");
	        sql.append("	 , (																										");
	        sql.append("		SELECT AVG(RATING)																						");
	        sql.append("		  FROM ITEM_REVIEW																						");
	        sql.append("		 WHERE ITEMNO = I.NO																					");
	        sql.append("		 GROUP BY ITEMNO																						");
	        sql.append("	   ) AS RAITNG																								");
		    sql.append("  FROM ITEM i																									");
		    sql.append("  LEFT OUTER JOIN ITEM_LIKE il																					");
		    sql.append("	ON i.NO = il.ITEM_NO																						");
		    sql.append(" WHERE 1 = 1																									");
			
		    // 타입 선택
			if (ty.equalsIgnoreCase("memory")) {
				sql.append(" AND TYPE = '메모리폼 매트리스'");
			} else if (ty.equalsIgnoreCase("air")) {
				sql.append(" AND TYPE = '에어 매트리스'");
			} else if (ty.equalsIgnoreCase("luxe")) {
				sql.append(" AND TYPE = 'Luxe S collection'");
			} else if (ty.equalsIgnoreCase("forest")) {
				sql.append(" AND TYPE = 'ForestWalk'");
			} else if (ty.equalsIgnoreCase("spring")) {
				sql.append(" AND TYPE = '스프링 매트리스'");
			} else if (ty.equalsIgnoreCase("all")) {
				sql.append("");
			}
			
			// 신상품순
			if (ordered.equalsIgnoreCase("new")) {
				pg.setOrderName("REGDATE");
				pg.setOrder(Order.DESC.getOrder());
			// 상평품순
			} else if(ordered.equalsIgnoreCase("review")) {
				pg.setOrderName("RAITNG");
				pg.setOrder(Order.DESC_NULLS_LAST.getOrder());
			// 가격순
			} else if(ordered.equalsIgnoreCase("price")) {
				pg.setOrderName("PRICE");
				pg.setOrder(Order.ASC.getOrder());
			// 인기순
			} else if(ordered.equalsIgnoreCase("pop")) {
				// 로그인 한 사용자별 우선 정렬
				pg.setOrderName("CASE WHEN MEMBER_ID LIKE '%" + id + "%' THEN 1 ELSE 2 END, CNT DESC NULLS LAST");
				pg.setOrder("");
			}
			
			String sqlQuery = pg.getQuery(conn, sql.toString());
			
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ShoppingDTO dto = new ShoppingDTO();
				
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
				dto.setItemnum(rs.getInt("ITEM_NO"));
				dto.setId(rs.getString("MEMBER_ID"));
				
				list.add(dto);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// 좋아요 버튼
	public int insertLike(int no, String id) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM ITEM_LIKE WHERE ITEM_NO = ? AND MEMBER_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				// 좋아요 존재 한다면 삭제
				sql = "DELETE FROM ITEM_LIKE WHERE ITEM_NO = ? AND MEMBER_ID = ?";
			} else {
				// 존재하지 않으면 추가
				sql = "INSERT INTO ITEM_LIKE VALUES (?, ?)";
			}
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}

	// 상품 상세 조회
	public ShoppingDTO buyMain(int num) {
		ShoppingDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += " SELECT NO			";
			sql += "	  , NAME		";
			sql += "	  , TYPE		";
			sql += "	  , TEXT		";
			sql += "	  , PRICE		";
			sql += "	  , POINT		";
			sql += "	  , REGDATE		";
			sql += "	  , SIZENAME	";
			sql += "	  , POSTER		";
			sql += " FROM ITEM 			";
			sql += " WHERE NO = ?		";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				dto = new ShoppingDTO();
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
	
	// 상품 상세보기 이미지 리스트
	public List<String> imageName(int num) {
		List<String> images = new ArrayList<>();
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "SELECT *			";
			sql += "  FROM ITEM_IMAGE	";
			sql += " WHERE ITEMNO = ?	";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				images.add(rs.getString("NAME"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return images;
	}
	
	// 리뷰 전체 조회
	public List<ItemReviewDTO> reviewAll(int num, Pagination pg) {
		List<ItemReviewDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "SELECT			           								";
			sql += "	   (SELECT COUNT(ir.NO) FROM ITEM_REVIEW ir) AS CNT ";
			sql += " 	 , NO				       							";
			sql += " 	 , TITLE                   							";
			sql += " 	 , WRITER                  							";
			sql += " 	 , TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE		";
			sql += " 	 , CONTENT                 							";
			sql += "	 , RATING                 			 				";
			sql += "	 , SECRET                 			 				";
			sql += "	 , ITEMNO                  							";
			sql += "  FROM ITEM_REVIEW          							";
			sql += " WHERE ITEMNO = " + num;
			
			sql = pg.getQuery(conn, sql);
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ItemReviewDTO dto = new ItemReviewDTO();
				
				dto.setCnt(rs.getInt("CNT"));
				dto.setNo(rs.getInt("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRating(rs.getInt("RATING"));
				dto.setSecret(rs.getInt("SECRET"));
				dto.setItemno(rs.getInt("ITEMNO")); 
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	} 
	
	// 리뷰 등록
	public int rvwrite(ItemReviewDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "INSERT INTO ITEM_REVIEW ";
			sql += " VALUES (SEQ_ITEM_REVIEW.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getWriter());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getRating());
			ps.setInt(5, dto.getSecret());
			ps.setInt(6, dto.getItemno());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return result;
	}
	
	// 선택된 리뷰 상세
	public ItemReviewDTO reviewOne(int rvno) {
		ItemReviewDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "SELECT NO          ";
			sql+= ", TITLE             ";
			sql+= ", WRITER            ";
			sql+= ", CONTENT           ";
			sql+= ", RATING            ";
			sql+= ", SECRET            ";
			sql+= ", ITEMNO            ";
			sql+= "FROM ITEM_REVIEW    ";
			sql+= "WHERE NO = ?        ";
			
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rvno);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				dto = new ItemReviewDTO();

				dto.setNo(rs.getInt("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRating(rs.getInt("RATING"));
				dto.setSecret(rs.getInt("SECRET"));
				dto.setItemno(rs.getInt("ITEMNO"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}

	// 리뷰 수정
	public int rvupdate(ItemReviewDTO dto) {
		int result = 0;
		
		try {
			conn = getConn();
		
			String sql = "";
			sql += "UPDATE ITEM_REVIEW SET        ";
			sql +=	 "  TITLE = ?                 ";
			sql +=	 " , WRITER = ?               ";
			sql +=	 " , CONTENT = ?              ";
			sql +=	 " , REGDATE = SYSDATE        ";
			sql +=	 " , RATING = ?               ";
			sql +=	 " , SECRET = ?               ";
			sql +=	 " WHERE NO = ?               ";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getWriter());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getRating());
			ps.setInt(5, dto.getSecret());
			ps.setInt(6, dto.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return result;
	}
	
	// 리뷰 삭제
	public int rvdelete(int rvno) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "DELETE ITEM_REVIEW WHERE NO = " + rvno;
			
			ps = conn.prepareStatement(sql);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return result;
	}
	
	// Q&A 리스트
	public List<ItemReviewDTO> qnaAll(int itemNo, Pagination pg) {
		List<ItemReviewDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			StringBuilder sql = new StringBuilder();                                               
			sql.append("SELECT												");
			sql.append("	   (SELECT COUNT(q.NO) FROM QNA q) AS CNT		");                                           
			sql.append("	 , NO											");
			sql.append("	 , CATE											");
			sql.append("	 , WRITER										");
			sql.append("	 , PASS											");
			sql.append("	 , TITLE										");
			sql.append("	 , QUESTION										");
			sql.append("	 , ANSWER										");
			sql.append("	 , TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE	");
			sql.append("	 , ITEMNO										");
			sql.append("	 , SECRET										");
			sql.append("  FROM QNA											");
			sql.append(" WHERE ITEMNO = " + itemNo							 );
			
			String query = pg.getQuery(conn, sql.toString());
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ItemReviewDTO dto = new ItemReviewDTO();
				
				dto.setCnt(rs.getInt("CNT"));
				dto.setNo(rs.getInt("NO"));
				dto.setCate(rs.getString("CATE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setPass(rs.getString("PASS"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setQuestion(rs.getString("QUESTION"));
				dto.setAnswer(rs.getString("ANSWER"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setItemno(rs.getInt("ITEMNO")); 
				dto.setSecret(rs.getInt("SECRET"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// Q&A 상세조회
	public ItemReviewDTO qnaOne(int qnano) {
		ItemReviewDTO dto = new ItemReviewDTO();
		
		try {
			conn = getConn();
			
			String sql = "";                                               
			sql += " SELECT 											";
			sql += "		NO                                          ";
			sql += " 	  , CATE                                        ";
			sql += " 	  , WRITER                                      ";
			sql += " 	  , PASS                                        ";
			sql += " 	  , TITLE                                       ";
			sql += " 	  , QUESTION                                    ";
			sql += " 	  , ANSWER                                      ";
			sql += " 	  , TO_CHAR(REGDATE, 'yyyy-mm-dd') AS REGDATE	";
			sql += " 	  , ITEMNO                                      ";
			sql += " 	  , SECRET                                      ";
			sql += "   FROM QNA                                         ";
			sql += "  WHERE NO = ?                                 		";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qnano);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto.setNo(rs.getInt("NO"));
				dto.setCate(rs.getString("CATE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setPass(rs.getString("PASS"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setQuestion(rs.getString("QUESTION"));
				dto.setAnswer(rs.getString("ANSWER"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setItemno(rs.getInt("ITEMNO"));
				dto.setSecret(rs.getInt("SECRET"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
	
	// Q&A 페이지
	public ShoppingDTO writeItem(int itemno) {
		ShoppingDTO dto = new ShoppingDTO();
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "SELECT NO			";
			sql	+= " 	 , NAME			";
			sql	+= " 	 , TEXT			";
			sql	+= " 	 , POSTER		";
			sql	+= "  FROM ITEM			";
			sql	+= " WHERE NO = ?		";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setText(rs.getString("TEXT"));
				dto.setPoster(rs.getString("POSTER"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
	
	// Q&A 등록
	public int qnaCreate(ItemReviewDTO qnaCre) {
		int rsu = 0;
		
		try {
			conn = getConn();
			
			String sql = "";
			sql +="INSERT INTO QNA (NO, CATE, TITLE, WRITER, PASS, QUESTION, REGDATE, ITEMNO, SECRET) ";
			sql +="VALUES(SEQ_QNA.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, qnaCre.getCate());
			ps.setString(2, qnaCre.getTitle());
			ps.setString(3, qnaCre.getWriter());
			ps.setString(4, qnaCre.getPass());
			ps.setString(5, qnaCre.getQuestion());
			ps.setInt(6, qnaCre.getItemno());
			ps.setInt(7, qnaCre.getSecret());
			
			rsu = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return rsu;
	}
	
	// Q&A 삭제
	public int qnaDel(int no) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "DELETE QNA WHERE NO = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return result;
	} 
	
	// Q&A 수정
	public int qnaUpdate(ItemReviewDTO qnaUp) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "";                         
			sql += "UPDATE QNA					";
			sql += "   SET CATE = ?				";
			sql += "	 , WRITER = ?			";
			sql += "	 , PASS = ?				";
			sql += "	 , TITLE = ?			";
			sql += "	 , QUESTION = ?			";
			sql += "	 , REGDATE = SYSDATE	";
			sql += "	 , SECRET = ?			";
			sql += " WHERE NO = ?				";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, qnaUp.getCate());
			ps.setString(2, qnaUp.getWriter());
			ps.setString(3, qnaUp.getPass());
			ps.setString(4, qnaUp.getTitle());
			ps.setString(5, qnaUp.getQuestion());
			ps.setInt(6, qnaUp.getSecret());
			ps.setInt(7, qnaUp.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return result;
	}
	
	// 답변등록
	public int ansCreate(int no, String ans) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "UPDATE QNA SET ANSWER = ? WHERE NO = ? ";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, ans);
			ps.setInt(2, no);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		
		return result;
	}
	
	// 관심상품 목록
	public List<ShoppingDTO> wishList(Pagination pg, String id) {
		List<ShoppingDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT								");
			sb.append("		  DISTINCT i.NO					");
			sb.append("		, i.NAME                    	");
			sb.append("		, i.TYPE                    	");
			sb.append("		, i.TEXT                    	");
			sb.append("		, i.PRICE                   	");
			sb.append("		, i.POINT                   	");
			sb.append("		, i.REGDATE                 	");
			sb.append("		, i.SIZENAME                	");
			sb.append("		, i.POSTER                  	");
			sb.append("		, il.ITEM_NO                	");
			sb.append("		, il.MEMBER_ID              	");
			sb.append("  FROM ITEM i						");
			sb.append("  LEFT OUTER JOIN ITEM_LIKE il		");
			sb.append("	   ON i.NO = il.ITEM_NO				");
			sb.append("	WHERE 1 = 1							");
			sb.append("	  AND il.MEMBER_ID LIKE '" + id + "'");
			
			String sql = pg.getQuery(conn, sb.toString());
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ShoppingDTO dto = new ShoppingDTO();
				
				dto.setNo(rs.getInt("NO"));
				dto.setName(rs.getString("NAME"));
				dto.setType(rs.getString("TYPE"));
				dto.setText(rs.getString("TEXT"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPoint(rs.getString("POINT"));
				dto.setRegdate(rs.getString("REGDATE"));
				dto.setSizename(rs.getString("SIZENAME"));
				dto.setPoster(rs.getString("POSTER"));
				dto.setItemnum(rs.getInt("ITEM_NO"));
				dto.setId(rs.getString("MEMBER_ID"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	// 관심상품 삭제
	public int wishDelete(String id, String...no) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "DELETE FROM ITEM_LIKE WHERE ITEM_NO = ? AND MEMBER_ID = ?";
			
			ps = conn.prepareStatement(sql);
			
			for (String itemNo : no) {
				ps.setInt(1, Integer.parseInt(itemNo));
				ps.setString(2, id);
				
				result += ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
}
