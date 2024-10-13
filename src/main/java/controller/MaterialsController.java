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
import dto.MaterialsDTO;
import service.MaterialsService;
import serviceImpl.MaterialsServiceImpl;

@WebServlet("/Materials/*")
@MultipartConfig
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
			
		// 글쓰기
		} else if (action.equals("/writeOk")) {
			MaterialsDTO dto = Common.convert(req, new MaterialsDTO());
			System.out.println(dto.getTxt().replaceAll("<[^>]*>", ""));
			
			return;
			
		// 글삭제
		} else if (action.equals("/delete")) {
			String strNo = req.getParameter("no");
			if (strNo != null) {
				int no = Integer.parseInt(strNo);
				ms.deleteMaterial(no);
				resp.sendRedirect("/Materials/list");
				return;
				
			} else {
				req.setAttribute("deleteMsg", "글삭제에 실패하였습니다. 잠시후 다시 시도 하세요.");
				action = "/list";
			}
			
		// 글수정
		} else if (action.equals("/update")) {
			
		// 글수정 실행
		} else if (action.equals("/updateOk")) {
			
		}
		
		req.setAttribute("layout", "/materials" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
