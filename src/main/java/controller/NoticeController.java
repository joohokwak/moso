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
import dto.NoticeDTO;
import service.NoticeService;
import serviceImpl.NoticeServiceImpl;

@WebServlet("/Notice/*")
@MultipartConfig
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		NoticeService sc = new NoticeServiceImpl();
		
		// 리스트
		if (action.equals("/list")) {
			Pagination pg = Common.getParameter(req);
			List<NoticeDTO> list = sc.selectAll(pg);
			
			req.setAttribute("list", list);
			req.setAttribute("paging", pg.paging(req));
			
		// 상세보기
		} else if (action.equals("/view")) {
			int no = Integer.parseInt(req.getParameter("no"));
			NoticeDTO dto = new NoticeDTO();
			
			dto = sc.selectOne(no);
			req.setAttribute("dto", dto);
			
		// 글쓰기OK
		} else if (action.equals("/writeOk")) {
			NoticeDTO dto = Common.convert(req, new NoticeDTO());
			Map<String, String> map = Common.fileUpload(req, "files/notice");
			if(map != null && !map.isEmpty()) {
				dto.setOfile(map.get("ofile"));
				dto.setNfile(map.get("nfile"));
			}
			
			sc.insertNotice(dto);
			resp.sendRedirect("/Notice/list");
			return;
			
		// 글삭제
		} else if (action.equals("/delete")) {
			String selNo = req.getParameter("no");
			//System.out.println(selNo);
			
			sc.deleteNotice(selNo.split(","));
			resp.sendRedirect("/Notice/list");
			return;
		
		// 상세보기 글삭제
		} else if (action.equals("/deleteView")) {
			String viewNo = req.getParameter("no");
			//System.out.println(viewNo);
			
			sc.deleteView(viewNo);
			resp.sendRedirect("/Notice/list");
			return;
			
		// 글수정
		} else if (action.equals("/update")) {
			
			
		// 글수정OK
		} else if (action.equals("/updateOk")) {
			
			
		}
		
		req.setAttribute("layout", "/notice" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
