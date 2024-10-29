package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		MemberDTO user = (MemberDTO) session.getAttribute("member");
		String id = (user != null) ? user.getId() : "";
		
		
		// 상품 리스트 페이지
		if (action.equals("/main")) {
			//	정렬하기
			String ordered = req.getParameter("ordered");
			String type = req.getParameter("type");
			
			if (ordered == null) ordered = "pop";
			if (type == null) type = "all";
					
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
			req.setAttribute("orderBy", ordered);
			req.setAttribute("type", type);
			req.setAttribute("list", list);
			req.setAttribute("paging", pg.paging(req));
			
		// 상품상세 페이지
		} else if (action.equals("/buy")) {		
			// main에서 클릭한 객체
			int itemNo = Integer.parseInt(req.getParameter("itemno"));
			
			ShoppingDTO dto = shopSer.buyMain(itemNo);
			// 客體 이미지
			List<String> images = shopSer.imageName(itemNo);
			
			int pageno = 1;			
			String pagenum = req.getParameter("pageNum");
			if(pagenum != null) pageno = Integer.parseInt(pagenum);
			
			// 리뷰 페이징
			Pagination pg = new Pagination();
			pg.setPageSize(3);
			pg.setPageNum(pageno);
			
			// QNA 페이징
			Pagination pg2 = new Pagination();
			pg2.setPageSize(10);
			pg2.setPageNum(pageno);

			List<ItemReviewDTO> reviewAll = shopSer.reviewAll(itemNo, pg);
			List<ItemReviewDTO> qnaAll = shopSer.qnaAll(itemNo, pg2);
			
			req.setAttribute("dto"		, dto);
			req.setAttribute("images"	, images);
			req.setAttribute("review"	, reviewAll);
			req.setAttribute("paging"	, pg.paging(req));
			req.setAttribute("qnaAll"	, qnaAll);
			req.setAttribute("paging2"	, pg2.paging(req));
			
		// 좋아요
		}  else if (action.equals("/like")) {
			String like = req.getParameter("like");
			int no = Integer.parseInt(like);
			
			if (user != null) {
				int result = shopSer.insertLike(no, id);
				Common.jsonResponse(resp, result);
			}
			
			return;
			
		// QNA PAGE
		} else if (action.equals("/write")) {
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			
			if(req.getParameter("qnano") != null) {
				int qnano = Integer.parseInt(req.getParameter("qnano"));
				ItemReviewDTO dto = shopSer.qnaOne(qnano);
				req.setAttribute("qna", dto);
			}
			
			req.setAttribute("item", writeItem);
			
		// QNA 등록
		} else if (action.equals("/writeOk")) {
			String itemno = req.getParameter("itemno");

			ItemReviewDTO qnaCre = Common.convert(req, ItemReviewDTO.class);
			shopSer.qnaCreate(qnaCre);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno + "#view_question");
			
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
	
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("reviewAll", reviewAll);
			resMap.put("paging", pg.paging(req));
			
			Common.jsonResponse(resp, resMap);
			
			return;
			
		// review write
		} else if(action.equals("/rvwrite")) {
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			req.setAttribute("item", writeItem);
			
		// 리뷰 작성	
		} else if(action.equals("/rvwriteOk")) {
			ItemReviewDTO dto = Common.convert(req, ItemReviewDTO.class);
			shopSer.rvwrite(dto);	
		
			return;
			
		// 리뷰 업데이트 페이지
		} else if(action.equals("/rvupdate")) {
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			int rvno = Integer.parseInt(req.getParameter("rvno"));
			
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			ItemReviewDTO reviewOne = shopSer.reviewOne(rvno);
			
			req.setAttribute("item", writeItem);
			req.setAttribute("rv", reviewOne);
			
		// 리뷰 업데이트	
		} else if(action.equals("/rvupdateOk")) {
			ItemReviewDTO rvUpdate = Common.convert(req, ItemReviewDTO.class);
			shopSer.rvupdate(rvUpdate);
			
			return;
			
		// 리뷰 삭제
		} else if(action.equals("/rvdelete")) {
			int rvno = Integer.parseInt(req.getParameter("rvno"));
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			
			shopSer.rvdelete(rvno);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno + "#view_review");
			
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
			Common.jsonResponse(resp, qnaAll);
			
			return;
			
		// 체크
		} else if (action.equals("/check")) {
			Map<String, Object> qdata = Common.jsonConvert(req);
			int no = Integer.parseInt(qdata.get("no") + "");
			String pass = qdata.get("pass") + "";
			char check = '0';
			ItemReviewDTO qnaOne = shopSer.qnaOne(no);
			
			if (qnaOne.getPass().equals(pass)) {
				check = '1';
			} 
			
			Common.jsonResponse(resp, check);
			
			return;
			
		// QNA 수정하기 페이지
		} else if(action.equals("/update")) {
			
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			int qnano = Integer.parseInt(req.getParameter("qnano"));
			ItemReviewDTO dto = shopSer.qnaOne(qnano);
			
			req.setAttribute("qna", dto);
			req.setAttribute("item", writeItem);
			
		// Q&A수정
		} else if(action.equals("/updateOk")) {
			String itemno =req.getParameter("itemno");
			ItemReviewDTO qnaUp = Common.convert(req, ItemReviewDTO.class);
			
			shopSer.qnaUpdate(qnaUp);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno + "#view_question");
			
			return;
			
		// Q&A 삭제
		} else if (action.equals("/qnadelete")) {
			Map<String, Object> ddata = Common.jsonConvert(req);
			int no = Integer.parseInt(ddata.get("no") + "");
			int qnaDel = shopSer.qnaDel(no);
			
			Common.jsonResponse(resp, qnaDel);
			
			return;
			
		// 답변 페이지
		} else if (action.equals("/answre")) {
			int num = Integer.parseInt(req.getParameter("qnano"));
			int itemno = Integer.parseInt(req.getParameter("itemno"));
		
			ItemReviewDTO qnaOne = shopSer.qnaOne(num);
			ShoppingDTO writeItem = shopSer.writeItem(itemno);
			
			req.setAttribute("qna", qnaOne);
			req.setAttribute("item", writeItem);
		
		// Q&A 답변 달기 
		} else if(action.equals("/answreOk")) {
			int num = Integer.parseInt(req.getParameter("no"));
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			String ans = req.getParameter("answre");
			shopSer.ansCreate(num, ans);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno + "#view_question");
			
			return;
			
		// Q&A 답변 삭제
		} else if (action.equals("/ansdelete")) {
			int num = Integer.parseInt(req.getParameter("qnano"));
			int itemno = Integer.parseInt(req.getParameter("itemno"));
			
			String ans = "";
			shopSer.ansCreate(num, ans);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno + "#view_question");
			
			return;
		}
		
		req.setAttribute("layout", "/shopping" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
