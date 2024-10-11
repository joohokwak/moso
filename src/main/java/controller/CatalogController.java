package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import service.CatalogService;
import serviceImpl.CatalogServiceImpl;

@WebServlet("/Catalog/*")
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		String action = req.getPathInfo();

		CatalogService cs = new CatalogServiceImpl();
		
		if (action.equals("/list")) {
			req.setAttribute("catalog", cs.selectList());
			
		} else if (action.equals("/view")) {
			int num = Integer.parseInt(req.getParameter("no"));

			cs.plusVisitCount(num);
			req.setAttribute("view", cs.selectOne(num));

		} else if (action.equals("/download")) {
			String ofile = req.getParameter("ofile");
			String nfile = req.getParameter("nfile");

			Common.fileDownLoad(resp, nfile, ofile);
			return;

		} else if (action.equals("/write")) {
			
		}

		req.setAttribute("layout", "/catalog" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
