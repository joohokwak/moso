package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Pagination;
import service.MaterialsService;
import serviceImpl.MaterialsServiceImpl;

@WebServlet("/Materials/*")
public class MaterialsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		MaterialsService ms = new MaterialsServiceImpl();
		
		// list
		if (action.equals("/list")) {
			Pagination pg = Common.getParameter(req);
			pg.setPageSize(5);
			
			req.setAttribute("list", ms.selectList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// download
		} else if (action.equals("/download")) {
			String path = req.getServletContext().getRealPath("files/materials");
			String ofile = req.getParameter("ofile");
			Common.fileDownLoad(resp, path, ofile, ofile);
			return;
		}
		
		req.setAttribute("layout", "/materials" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
