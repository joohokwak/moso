package controller;

import java.io.IOException;
import java.util.List;

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

			List<ItemReviewDTO> reviewAll = shopSer.reviewAll(itemNo, pg);
			
			
			req.setAttribute("numuri", numuri);
			req.setAttribute("dto", dto);
			req.setAttribute("images", images);
			if(reviewAll != null) req.setAttribute("review", reviewAll);
			req.setAttribute("paging", pg.paging(req));
			
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
			ShoppingDTO qnaItem = shopSer.qnaItem(itemno);
			
						
			req.setAttribute("item", qnaItem);
		// qna 등록
		} else if (action.equals("/writeOk")) {
			String itemno = req.getParameter("itemno");
			ItemReviewDTO qnaCre = Common.convert(req, new ItemReviewDTO());
			
			shopSer.qnaCreate(qnaCre);
			System.out.println(qnaCre);
			
			resp.sendRedirect("/Shop/buy?itemno=" + itemno);
			return;
		}
		
		req.setAttribute("layout", "/shopping" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
