package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Pagination;
import dto.FaqDTO;
import service.FaqService;
import serviceImpl.FaqServiceImpl;

@WebServlet("/Faq/*")
public class FaqController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		FaqService fs = new FaqServiceImpl();
		
		// FAQ
		if (action.equals("/list")) {
			Pagination pg = Common.getParameter(req);
			
			req.setAttribute("list", fs.selectList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 글쓰기
		} else if (action.equals("/writeOk")) {
			FaqDTO dto = Common.convert(req, new FaqDTO());
			fs.insertFaq(dto);
			resp.sendRedirect("/Faq/list");
			return;
			
		// 글삭제
		} else if (action.equals("/delete")) {
			int no = Integer.parseInt(req.getParameter("no"));
			fs.deleteFaq(no);
			resp.sendRedirect("/Faq/list");
			return;
			
		// 글수정
		} else if (action.equals("/update")) {
			int no = Integer.parseInt(req.getParameter("no"));
			req.setAttribute("faq", fs.selectFaq(no));
			
		// 글수정OK
		} else if (action.equals("/updateOk")) {
			FaqDTO dto = Common.convert(req, new FaqDTO());
			fs.updateFaq(dto);
			resp.sendRedirect("/Faq/list");
			return;
		}
		
		req.setAttribute("layout", "/faq" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
