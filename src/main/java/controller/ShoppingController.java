package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import common.Common;
import common.Pagination;
import dto.ItemReviewDTO;
import dto.MemberDTO;
import dto.ShoppingDTO;
import service.ShoppingService;
import serviceImpl.ShoppingServiceImpl;

@WebServlet("/Shop/*")
@MultipartConfig
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		ShoppingService shopSer = new ShoppingServiceImpl();
		
		// 세션에서 로그인 정보 가져오기
		HttpSession session = req.getSession();
		Object user = session.getAttribute("member");
		String id = null;
		if (user != null)  {
			MemberDTO member = (MemberDTO) user;
			id = member.getId();
		}
		
		// 상품 리스트 페이지
		if (action.equals("/main")) {
			//	정렬하기
			String ordered = req.getParameter("ordered");
			String type = req.getParameter("type");
			String typecheck = "type=" + type + "&";
			if (ordered == null) ordered = "pop";
			String orderBy = "&ordered=" + ordered;
					
			//	페이징
			int pnum = 1;
			String pagnum = req.getParameter("pageNum");
			if (pagnum != null) {
				pnum = Integer.parseInt(pagnum);
			}
			
			Pagination pg = new Pagination();
			pg.setPageSize(12);
			pg.setPageNum(pnum);
			
			//	리스트
			List<ShoppingDTO> list = shopSer.viewMain(type, ordered, id, pg);
			
			//	jsp로 보내기
			req.setAttribute("orderBy", orderBy);
			req.setAttribute("typecheck", typecheck);
			req.setAttribute("list", list);
			req.setAttribute("paging", pg.paging(req));
			
		// 상품상세 페이지
		} else if (action.equals("/buy")) {		
			//	main에서 클릭한 객체
			int itemNo = Integer.parseInt(req.getParameter("itemno"));
			String numuri = "num=" + itemNo + "&";
			
			ShoppingDTO dto = shopSer.buyMain(itemNo);
			//	客體 이미지
			List<String> images = shopSer.imageName(itemNo);
			
			Pagination pg = new Pagination();
			
			int pageno = 1;			
			String pagenum = req.getParameter("pageNum");
			if(pagenum != null) pageno = Integer.parseInt(pagenum);
			
			pg.setPageSize(3);
			pg.setPageNum(pageno);
			
			Pagination pg2 = new Pagination();
			
			pg2.setPageSize(10);
			pg2.setPageNum(pageno);
			

			List<ItemReviewDTO> reviewAll = shopSer.reviewAll(itemNo, pg);
			List<ItemReviewDTO> qnaAll = shopSer.qnaAll(itemNo, pg2);
			
			req.setAttribute("numuri", numuri);
			req.setAttribute("dto", dto);
			req.setAttribute("images", images);
			req.setAttribute("review", reviewAll);
			req.setAttribute("paging", pg.paging(req));
			req.setAttribute("qnaAll", qnaAll);
			req.setAttribute("paging2", pg2.paging(req));
			
		// 좋아요
		}  else if (action.equals("/like")) {
			String like = req.getParameter("like");
			int no = Integer.parseInt(like);
			
			if (user != null) {
				int result = shopSer.insertLike(no, id);
				Common.jsonResponse(resp, result);
			}
			return;
			
		// qna page
		} else if (action.equals("/write")) {
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			if(req.getParameter("qnano") != null) {
				int qnano = Integer.parseInt(req.getParameter("qnano"));
				
				ItemReviewDTO dto = shopSer.qnaOne(qnano);
				
				req.setAttribute("qna", dto);
			}
			
			req.setAttribute("item", writeItem);
			
		// qna 등록
		} else if (action.equals("/writeOk")) {
			String itemno = req.getParameter("itemno");

			ItemReviewDTO qnaCre = Common.convert(req, ItemReviewDTO.class);
						
			shopSer.qnaCreate(qnaCre);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno);
			return;
			
		// 리뷰 페이징
		} else if (action.equals("/review")) {
			Map<String, Object> rdata = Common.jsonConvert(req);
			int itemno = Integer.parseInt(rdata.get("ITEMNO") + "");
			int pageNum = Integer.parseInt(rdata.get("PAGENUM") + "");
			
			Pagination pg = new Pagination();
			pg.setPageSize(3);
			pg.setPageNum(pageNum);
			
			List<ItemReviewDTO> reviewAll = shopSer.reviewAll(itemno, pg);
			
			Common.jsonResponse(resp, reviewAll);
			return;
			
		// QNA 페이징
		} else if(action.equals("/qna")) {
			Map<String, Object> qdata = Common.jsonConvert(req);
			int itemno = Integer.parseInt(qdata.get("ITEMNO") + "");
			int pageNum = Integer.parseInt(qdata.get("PAGENUM") + "");
			
			Pagination pg2 = new Pagination();
			
			pg2.setPageSize(10);
			pg2.setPageNum(pageNum);
			
			List<ItemReviewDTO> qnaAll = shopSer.qnaAll(itemno, pg2);
//			Common.jsonResponse(resp, qnaAll);
			
			resp.setContentType("application/json; charset=utf-8");
			
			try (PrintWriter pw = resp.getWriter();) {
				// Gson 객체를 이용해 json 형태로 변환
				Gson gson = new Gson();
				String resData = gson.toJson(qnaAll);
				
				// 응답
				pw.write(resData);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		// 체크
		} else if (action.equals("/check")) {
			Map<String, Object> qdata = Common.jsonConvert(req);
			int no = Integer.parseInt(qdata.get("no") + "");
			String pass = qdata.get("pass") + "";
			char check = '0';
			ItemReviewDTO qnaOne = shopSer.qnaOne(no);
			
			if(qnaOne.getPass().equals(pass)) {
				check = '1';
			}
			System.out.println(check);
			Common.jsonResponse(resp, check);
			return;
			
		// qna 수정하기
		} else if(action.equals("/update")) {
			
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			int qnano = Integer.parseInt(req.getParameter("qnano"));
			ItemReviewDTO dto = shopSer.qnaOne(qnano);
			
			req.setAttribute("qna", dto);
			req.setAttribute("item", writeItem);
		
		// Q&A 삭제
		} else if (action.equals("/qnadelete")) {
			Map<String, Object> ddata = Common.jsonConvert(req);
			int no = Integer.parseInt(ddata.get("no") + "");
			int qnaDel = shopSer.qnaDel(no);
			
			Common.jsonResponse(resp, qnaDel);
			return;
		}
		
		req.setAttribute("layout", "/shopping" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
