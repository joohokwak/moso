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
			
			if (ordered.equalsIgnoreCase("new")) {
				pg.setOrderName("REGDATE");
				pg.setOrder(Order.DESC.getOrder());
			} else if(ordered.equalsIgnoreCase("review")) {
				pg.setOrderName(" ");
			} else if(ordered.equalsIgnoreCase("price")) {
				pg.setOrderName("PRICE");
				pg.setOrder(Order.ASC.getOrder());
			} else if(ordered.equalsIgnoreCase("pop")) {
				pg.setOrderName("CNT");
				pg.setOrder(Order.DESC_NULLS_LAST.getOrder());
			}
			                                                                         
			String sql = "";
			sql += "SELECT DISTINCT i.NO                                                                           ";
			sql += "	 , i.NAME                                                                                  ";
			sql += "	 , i.TYPE                                                                                  ";
			sql += "	 , i.TEXT                                                                                  ";
			sql += "	 , i.PRICE                                                                                 ";
			sql += "	 , i.POINT                                                                                 ";
			sql += "	 , i.REGDATE                                                                               ";
			sql += "	 , i.SIZENAME                                                                              ";
			sql += "	 , i.POSTER                                                                                ";
			sql += "	 , (                                                                                       ";
			sql += "		SELECT ITEM_NO                                                                         ";
			sql += "		  FROM ITEM_LIKE                                                                       ";
			sql += "		 WHERE ITEM_NO = il.ITEM_NO                                                            ";
			sql += "		   AND MEMBER_ID = il.MEMBER_ID                                                        ";
			sql += "	   ) AS ITEM_NO                                                                            ";
			sql += "	 , (                                                                                       ";
			sql += "		SELECT MEMBER_ID                                                                       ";
			sql += "		  FROM ITEM_LIKE                                                                       ";
			sql += "		 WHERE ITEM_NO = il.ITEM_NO                                                            ";
			sql += "		   AND MEMBER_ID = il.MEMBER_ID                                                        ";
			sql += "	   ) AS MEMBER_ID                                                                          ";
			sql += "	 , (SELECT COUNT(ITEM_NO) FROM ITEM_LIKE WHERE ITEM_NO = il.ITEM_NO GROUP BY ITEM_NO) CNT  ";
			sql += "  FROM ITEM i                                                                                  ";
			sql += "  LEFT OUTER JOIN ITEM_LIKE il                                                                 ";
			sql += "	ON i.NO = il.ITEM_NO                                                                       ";
			sql += "  LEFT OUTER JOIN MEMBER m                                                                     ";
			sql += "	ON il.MEMBER_ID = m.ID                                                                     ";
			sql += " WHERE 1 = 1                                                                                   ";
				
			if (ty != null) {
				if (ty.equalsIgnoreCase("memory")) {
					sql += " AND TYPE = '메모리폼 매트리스'";
				} else if (ty.equalsIgnoreCase("air")) {
					sql += " AND TYPE = '에어 매트리스'";
				} else if (ty.equalsIgnoreCase("luxe")) {
					sql += " AND TYPE = 'Luxe S collection'";
				} else if (ty.equalsIgnoreCase("forest")) {
					sql += " AND TYPE = 'ForestWalk'";
				} else if (ty.equalsIgnoreCase("spring")) {
					sql += " AND TYPE = '스프링 매트리스'";
				} else if (ty.equalsIgnoreCase("all")) {
					sql += "";
				}
			}
			
			sql = pg.getQuery(conn, sql);
			
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
				sql = "DELETE FROM ITEM_LIKE WHERE ITEM_NO = ? AND MEMBER_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.setString(2, id);
				
				result = ps.executeUpdate();
				
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (conn != null) conn.close();
				return result;
			}
			
			sql = "INSERT INTO ITEM_LIKE VALUES (?, ?)";
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
			sql += " SELECT NO				";
			sql += "	  , NAME			";
			sql += "	  , TYPE			";
			sql += "	  , TEXT			";
			sql += "	  , PRICE			";
			sql += "	  , POINT			";
			sql += "	  , REGDATE			";
			sql += "	  , SIZENAME		";
			sql += "	  , POSTER			";
			sql += " FROM ITEM 				";
			sql += " WHERE NO = ?";
			
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
			sql += "SELECT *				";
			sql += "  FROM ITEM_IMAGE		";
			sql += " WHERE ITEMNO = ?		";
			
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
	
	public int rvwrite(ItemReviewDTO dto, int rating) {
		conn = getConn();
		int result = 0;
		dto.setRating(rating);
		
		try {
			
			String sql = " INSERT INTO               ";
				sql += " ITEM_REVIEW                 ";
				sql += " VALUES (                    ";
				sql += " SEQ_ITEM_REVIEW.NEXTVAL     ";
				sql += " , ?            	         ";
				sql += " , ?             		     ";
				sql += " , SYSDATE                   ";
				sql += " , ?                         ";
				sql += " , ?                         ";
				sql += " , ?                         ";
				sql += " , ? )                       ";
			
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
	
	public List<ItemReviewDTO> qnaAll(int itemNo, Pagination pg) {
		List<ItemReviewDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			String sql = "";                                               
			 sql += " SELECT											";
			 sql += " 		 (SELECT COUNT(q.NO) FROM QNA q) AS CNT		";                                           
			 sql += "	   , NO                                         ";
			 sql += "	   , CATE                                       ";
			 sql += "	   , WRITER                                     ";
			 sql += "	   , PASS                                       ";
			 sql += "	   , TITLE                                      ";
			 sql += "	   , QUESTION                                   ";
			 sql += "	   , ANSWRE                                     ";
			 sql += "	   , TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE  ";
			 sql += "	   , ITEMNO                                     ";
			 sql += "	   , SECRET                                     ";
			 sql += "	FROM QNA                                        ";
			 sql += "  WHERE ITEMNO = " + itemNo;
			
			sql = pg.getQuery(conn, sql);
			ps = conn.prepareStatement(sql);
			
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
				dto.setAnswre(rs.getString("ANSWRE"));
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
			sql += " 	  , ANSWRE                                      ";
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
				dto.setAnswre(rs.getString("ANSWRE"));
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

	public int qnaCreate(ItemReviewDTO qnaCre) {
		int rsu = 0;
		
		try {
			conn = getConn();
			
			String sql = "";
			sql +="	INSERT INTO QNA (NO, CATE, TITLE, WRITER, PASS, QUESTION, REGDATE, ITEMNO, SECRET) VALUES(             ";
			sql +="			SEQ_QNA.NEXTVAL             ";
			sql +="			, ?        			        ";
			sql +="			, ?        	   		        ";
			sql +="			, ?         		        ";
			sql +="			, ?            		        ";
			sql +="			, ?  						";
			sql +="			, SYSDATE                   ";
			sql +="			, ?                         ";
			sql +="			, ?                         ";
			sql +="		)             		            ";
			
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
	
	public int qnaDel(int no) {
		conn = getConn();
		int result = 0;
		
		try {
			
			String sql = "";
			sql += "DELETE QNA WHERE NO = ?";
			
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
	
	public int qnaUpdate(ItemReviewDTO qnaUp) {
		conn = getConn();
		
		int result = 0;
		
		try {
			String sql = "";                         
			sql += "UPDATE QNA SET					";
			sql += "	  CATE = ?                  ";
			sql += "	, WRITER = ?                ";
			sql += "	, PASS = ?                  ";
			sql += "	, TITLE = ?                 ";
			sql += "	, QUESTION = ?              ";
			sql += "	, REGDATE = SYSDATE         ";
			sql += "	, SECRET = ?                ";
			sql += "WHERE                           ";
			sql += " 	  NO = ?                    ";
			
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
	
	public int ansCreate(int no, String ans) {
		int result = 0;
		
		try {
			conn = getConn();
			String sql = "UPDATE QNA SET ANSWRE = ? WHERE NO = ? ";
			
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
	
	
//	public void ansUpdate() {
//		try {
//			String sql = 
//				    "MERGE INTO QNA q " +
//				    "USING (SELECT ? AS NO, ? AS CATE, ? AS WRITER, ? AS PASS, ? AS TITLE, ? AS QUESTION, ? AS ANSWRE, SYSDATE AS REGDATE, ? AS ITEMNO, ? AS SECRET FROM DUAL) data " +
//				    "ON (q.NO = data.NO) " +
//				    "WHEN MATCHED THEN " +
//				    "  UPDATE SET " +
//				    "    q.CATE = data.CATE, " +
//				    "    q.WRITER = data.WRITER, " +
//				    "    q.PASS = data.PASS, " +
//				    "    q.TITLE = data.TITLE, " +
//				    "    q.QUESTION = data.QUESTION, " +
//				    "    q.ANSWRE = data.ANSWRE, " +
//				    "    q.REGDATE = data.REGDATE, " +
//				    "    q.ITEMNO = data.ITEMNO, " +
//				    "    q.SECRET = data.SECRET " +
//				    "WHEN NOT MATCHED THEN " +
//				    "  INSERT (NO, CATE, WRITER, PASS, TITLE, QUESTION, ANSWRE, REGDATE, ITEMNO, SECRET) " +
//				    "  VALUES (SEQ_QNA.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";
//				    
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//
//				// 값을 설정 (Java 코드에서 가져오는 값)
//				pstmt.setInt(1, no); // NO (이 값이 중복 여부를 결정함)
//				pstmt.setString(2, cate); // CATE
//				pstmt.setString(3, writer); // WRITER
//				pstmt.setString(4, pass); // PASS
//				pstmt.setString(5, title); // TITLE
//				pstmt.setString(6, question); // QUESTION
//				pstmt.setString(7, answer); // ANSWRE
//				pstmt.setInt(8, itemno); // ITEMNO
//				pstmt.setInt(9, secret); // SECRET
//
//				// INSERT 부분의 값들 (NO는 SEQ_QNA.NEXTVAL이므로 생략)
//				pstmt.setString(10, cate); // CATE
//				pstmt.setString(11, writer); // WRITER
//				pstmt.setString(12, pass); // PASS
//				pstmt.setString(13, title); // TITLE
//				pstmt.setString(14, question); // QUESTION
//				pstmt.setString(15, answer); // ANSWRE
//				pstmt.setInt(16, itemno); // ITEMNO
//				pstmt.setInt(17, secret); // SECRET
//
//				// 실행
//				pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			
//		}
//	}
	
}
