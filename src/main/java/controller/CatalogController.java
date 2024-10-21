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
import common.Pagination;
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

		// 카탈로그
		if (action.equals("/list")) {
			Pagination pg = Common.getParameter(req);
			pg.setPageNavSize(5);

			List<CatalogDTO> list = cs.selectList(pg);

			req.setAttribute("catalog", list);
			req.setAttribute("paging", pg.paging(req));

		// 상세보기
		} else if (action.equals("/view")) {
			int num = Integer.parseInt(req.getParameter("no"));

			cs.plusVisitCount(num);
			req.setAttribute("view", cs.selectOne(num));

		// 파일 다운로드
		} else if (action.equals("/download")) {
			String path = req.getServletContext().getRealPath("files/catalog");
			String ofile = req.getParameter("ofile");
			String nfile = req.getParameter("nfile");

			Common.fileDownLoad(resp, path, nfile, ofile);
			return;

		// 글쓰기
		} else if (action.equals("/writeOk")) {
			CatalogDTO dto = Common.convert(req, CatalogDTO.class);
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

		// 수정하기 페이지
		} else if (action.equals("/update")) {
			int num = Integer.parseInt(req.getParameter("no"));
			req.setAttribute("update", cs.selectOne(num));

		// 수정 버튼 클릭
		} else if (action.equals("/updateOk")) {
			CatalogDTO dto = Common.convert(req, CatalogDTO.class);
			Map<String, String> map = Common.fileUpload(req, "files/catalog");

			if (map != null) {
				String nfile = map.get("nfile");
				String ofile = map.get("ofile");

				dto.setOfile(ofile);
				dto.setNfile(nfile);
			}

			cs.updateCatalog(dto);
			cs.updateCatalogfile();

			String isadmin = req.getParameter("isadmin");
			if ("Y".equals(isadmin)) {
				resp.sendRedirect("/Admin/catalog");
			} else {
				resp.sendRedirect("/Catalog/list");
			}

			return;

		// 삭제하기
		} else if (action.equals("/deleteOk")) {
			CatalogDTO dto = Common.convert(req, CatalogDTO.class);

			cs.deleteCatalog(dto);
			resp.sendRedirect("/Catalog/list");
			return;
		}

		req.setAttribute("layout", "/catalog" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
