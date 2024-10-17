package controller;

import java.io.IOException;
import java.util.Map;

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
		
		// 조립설명서 리스트
		if (action.equals("/list")) {
			Pagination pg = Common.getParameter(req);
			pg.setPageSize(5);
			
			req.setAttribute("list", ms.selectList(pg));
			req.setAttribute("paging", pg.paging(req));
			
		// 파일 다운로드
		} else if (action.equals("/download")) {
			String path = req.getServletContext().getRealPath("files/materials");
			String ofile = req.getParameter("ofile");
			String nfile = req.getParameter("nfile");
			
			Common.fileDownLoad(resp, path, nfile, ofile);
			return;
			
		// 글쓰기
		} else if (action.equals("/writeOk")) {
			MaterialsDTO dto = Common.convert(req, new MaterialsDTO());
			
			Map<String, String> rData = Common.fileUpload(req, "files/materials");
			if (!rData.isEmpty()) {
				dto.setOfile(rData.get("ofile"));
				dto.setNfile(rData.get("nfile"));
			}
			
			ms.insertMaterial(dto);
			resp.sendRedirect("/Materials/list");
			return;
			
		// 글삭제
		} else if (action.equals("/delete")) {
			String strNo = req.getParameter("no");
			if (strNo != null) {
				int no = Integer.parseInt(strNo);
				ms.deleteMaterial(no);
			}
			
			resp.sendRedirect("/Materials/list");
			return;
			
		// 글수정
		} else if (action.equals("/update")) {
			String strNo = req.getParameter("no");
			if (strNo != null) {
				int no = Integer.parseInt(strNo);
				req.setAttribute("data", ms.selectOne(no));
				
			} else {
				resp.sendRedirect("/Materials/list");
				return;
			}
			
		// 글수정 실행
		} else if (action.equals("/updateOk")) {
			MaterialsDTO dto = Common.convert(req, new MaterialsDTO());
			
			Map<String, String> rData = Common.fileUpload(req, "files/materials");
			if (rData != null && !rData.isEmpty()) {
				dto.setOfile(rData.get("ofile"));
				dto.setNfile(rData.get("nfile"));
			}
			
			ms.updateMaterial(dto);
			
			String isadmin = req.getParameter("isadmin");
			if ("Y".equals(isadmin)) {
				resp.sendRedirect("/Admin/materials");
			} else {
				resp.sendRedirect("/Materials/list");
			}
			
			return;
		}
		
		req.setAttribute("layout", "/materials" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
