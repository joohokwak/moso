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
			System.out.println("상세보기: "+dto);
			req.setAttribute("dto", dto);
			
		// 글쓰기OK
		} else if (action.equals("/writeOk")) {
			NoticeDTO dto = Common.convert(req, NoticeDTO.class);
			Map<String, String> map = Common.fileUpload(req, "files/notice");
			if(map != null && !map.isEmpty()) {
				dto.setOfile(map.get("ofile"));
				dto.setNfile(map.get("nfile"));
			}
			
			sc.insertNotice(dto);
			System.out.println("글쓰기 후: "+dto);
			resp.sendRedirect("/Notice/list");
			return;
			
		// 글삭제
		} else if (action.equals("/delete")) {
			String selNo = req.getParameter("no");
			
			sc.deleteNotice(selNo.split(","));
			resp.sendRedirect("/Notice/list");
			return;
			
		// 글수정
		} else if (action.equals("/update")) {
			int no = Integer.parseInt(req.getParameter("no"));
			NoticeDTO dto = new NoticeDTO();
			
			dto = sc.selectOne(no);
			System.out.println("수정하기 클릭 후: "+dto);
			req.setAttribute("dto", dto);
			
		// 글수정OK
		} else if (action.equals("/updateOk")) {
			NoticeDTO dto = Common.convert(req, NoticeDTO.class);
			
			NoticeDTO dto2 = sc.selectOne(dto.getNo());
			
			// 글 수정시 기존파일 제거할 경우
			Map<String, String> nData = Common.fileUpload(req, "files/notice");
			if(nData != null && !nData.isEmpty()) {
				dto.setOfile(nData.get("ofile"));
				dto.setNfile(nData.get("nfile"));
				
				if(dto2.getOfile() != null) 
					Common.fileDelete(req, "files/notice", dto2.getNfile());
			}
//			else {
//				dto.setOfile(dto2.getOfile());
//				dto.setNfile(dto2.getNfile());
//			}
			
			sc.noticeUpdate(dto);
			// 관리자 페이지에서 수정한경우 관리자 페이지로 이동
			String isadmin = req.getParameter("isadmin");
			if("Y".equals(isadmin)) {
				resp.sendRedirect("/Admin/notice");
			} else {
				resp.sendRedirect("/Notice/list");
			}
			
			return;
		
		// 첨부파일 다운로드
		} else if(action.equals("/filedownload")) {
			NoticeDTO dto = Common.convert(req, NoticeDTO.class);
			String path = req.getServletContext().getRealPath("files/notice");
			
			Common.fileDownLoad(resp, path, dto.getNfile(), dto.getOfile());
			return;
		}
		
		req.setAttribute("layout", "/notice" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
