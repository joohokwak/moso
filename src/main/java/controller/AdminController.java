package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Pagination;
import service.CatalogService;
import service.FaqService;
import service.MemberService;
import service.NoticeService;
import serviceImpl.CatalogServiceImpl;
import serviceImpl.FaqServiceImpl;
import serviceImpl.MemberServiceImpl;
import serviceImpl.NoticeServiceImpl;

@WebServlet("/Admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		// 회원 목록
		if (action.equals("/member")) {
			MemberService ms = new MemberServiceImpl();
			
			Pagination pg = Common.getParameter(req);
			
			req.setAttribute("list", ms.selectMemberAll(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 회원 수정 페이지 이동
		} else if (action.equals("/update")) {
			String id = req.getParameter("id");
			
			MemberService ms = new MemberServiceImpl();
			req.setAttribute("member", ms.selectMember(id));
			
			req.setAttribute("layout", "/member" + action);
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
			return;
			
		// FAQ 목록
		} else if (action.equals("/faq")) {
			FaqService fs = new FaqServiceImpl();
			
			Pagination pg = Common.getParameter(req);
			
			req.setAttribute("list", fs.selectList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 카탈로그 목록
		} else if (action.equals("/catalog")) {
			CatalogService cs = new CatalogServiceImpl();
			
			Pagination pg = Common.getParameter(req);
			
			req.setAttribute("list", cs.selectList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 공지사항 목록
		} else if (action.equals("/notice")) {
			NoticeService ns = new NoticeServiceImpl();
			
			Pagination pg = Common.getParameter(req);
			
			req.setAttribute("list", ns.selectAll(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 엑셀 다운로드
		} else if (action.equals("/excelDown")) {
			// TODO : 해야함
			System.out.println("excel");
//			MemberService ms = new MemberServiceImpl();
			
			return;
			
		// 삭제 공통
		} else if (action.equals("/delete")) {
			String _path = req.getParameter("path");
			String _no = req.getParameter("no");
			System.out.println(_path);
			System.out.println(_no);
			
			resp.sendRedirect(_path);
			return;
		}
		
		req.setAttribute("layout", action);
		req.getRequestDispatcher("/jsp/admin/layout.jsp").forward(req, resp);
	}
}
