package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Pagination;
import dto.MagazineDTO;
import service.MagazineService;
import serviceImpl.MagazineServiceImpl;

@WebServlet("/Magazine/*")
@MultipartConfig
public class MagazineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		MagazineService mgs = new MagazineServiceImpl();
		
		// 매거진 리스트
		if (action.equals("/magazine")) {
			Pagination pg = Common.getParameter(req);
			pg.getSearchMap().put("mtype", "magazine");
			pg.setPageSize(9);
			
			req.setAttribute("magazine", mgs.magazineList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 상세보기
		} else if (action.equals("/view")) {
			String no = req.getParameter("no");
			MagazineDTO mv = mgs.magazineView(Integer.parseInt(no));
			
			req.setAttribute("magazine", mv);
		
		// 월간리뷰 리스트
		} else if (action.equals("/review")) {
			Pagination pg = Common.getParameter(req);
			pg.getSearchMap().put("mtype", "review");
			pg.setPageSize(8);
			
			req.setAttribute("magazine", mgs.magazineList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 글수정
		} else if (action.equals("/update")) {
			String no = req.getParameter("no");
			int mu = Integer.parseInt(no);
			
			req.setAttribute("magazine", mgs.magazineView(mu));
		
		// 수정완료
		} else if (action.equals("/updateOk")) {
			MagazineDTO dto = Common.convert(req, MagazineDTO.class);
			mgs.magazineUpdateOk(dto);
			
			resp.sendRedirect("/Magazine/view?no=" + dto.getNo());
			return;
			
		// 글쓰기
		} else if(action.equals("/writeOk")) {
//			System.out.println(req.getParameter("title"));
//			System.out.println(req.getParameter("text"));
//			System.out.println(req.getParameter("poster"));
//			System.out.println(req.getParameter("content"));
//			System.out.println(req.getParameter("mtype"));
			
			MagazineDTO dto = Common.convert(req, MagazineDTO.class);
			
			mgs.magazineWriteOk(dto);
			
			resp.sendRedirect("/Magazine/magazine");
			return;
			
		// 글삭제
		} else if (action.equals("/delete")) {
			int no = Integer.parseInt(req.getParameter("no"));
			mgs.magazineDelete(no);
			
			resp.sendRedirect("/Magazine/magazine");
			return;
		
		}
		
		req.setAttribute("layout", "/magazine" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
