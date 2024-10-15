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
			MagazineDTO md = mgs.magazineView(Integer.parseInt(no));
			
			req.setAttribute("magazine", md);
		
		// 월간리뷰 리스트
		} else if (action.equals("/review")) {
			Pagination pg = Common.getParameter(req);
			pg.getSearchMap().put("mtype", "review");
			pg.setPageSize(8);
			
			req.setAttribute("magazine", mgs.magazineList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 수정페이지
		} else if (action.equals("/update")) {
			
		
		// 수정완료
		} else if (action.equals("/updateOk")) {
			
		}
		
		req.setAttribute("layout", "/magazine" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
