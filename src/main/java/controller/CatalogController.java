package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import dto.CatalogDTO;
import service.CatalogService;
import serviceImpl.CatalogServiceImpl;

@WebServlet("/Catalog/*")
@MultipartConfig
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		String action = req.getPathInfo();

		CatalogService cs = new CatalogServiceImpl();
		
		if (action.equals("/list")) {
			String search = req.getParameter("title");
			
			
			String str = req.getParameter("pageNum");
			int pageNum = 1;
			if (str != null) {
				pageNum = Integer.parseInt(str);
			}
			
			List<CatalogDTO> list = cs.selectList(pageNum, search);
			
//			(int) Math.ceil(totalRecord / (double) pageSize)
			int totalPages = (int) Math.ceil(cs.totalPage() / (double) 10); 
			
//			Pagination pg = Common.getParameter(req);
			
//			req.setAttribute("catalog", cs.selectList(pg));
			req.setAttribute("catalog", list);
//			req.setAttribute("paging", pg.paging(req));
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("pageNum", pageNum);
			
		} else if (action.equals("/view")) {
			int num = Integer.parseInt(req.getParameter("no"));

			cs.plusVisitCount(num);
			req.setAttribute("view", cs.selectOne(num));

		} else if (action.equals("/download")) {
			String path = req.getServletContext().getRealPath("files/catalog");
			String ofile = req.getParameter("ofile");
			String nfile = req.getParameter("nfile");
			
			Common.fileDownLoad(resp, path, nfile, ofile);
			return;

		} else if (action.equals("/writeOk")) {
			CatalogDTO dto = Common.convert(req, new CatalogDTO());
			Map<String, String> map = Common.fileUpload(req, "files/catalog");
			
			if (map != null) {
				String nfile = map.get("nfile");
				String ofile = map.get("ofile");
				
				dto.setOfile(ofile);
				dto.setNfile(nfile);
			}
			
			cs.insertCatalog(dto);
			cs.insertCatalogfile();
			
			
			resp.sendRedirect("/Catalog/list");
			return;
		}

		req.setAttribute("layout", "/catalog" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
