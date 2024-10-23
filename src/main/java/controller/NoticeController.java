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
			NoticeDTO dto = Common.convert(req, NoticeDTO.class);
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
			
		// 글 첨부파일 삭제
		} else if(action.equals("/deleteFile")) {
			
			
		// 글수정
		} else if (action.equals("/update")) {
			int no = Integer.parseInt(req.getParameter("no"));
			NoticeDTO dto = new NoticeDTO();
			
			dto = sc.selectOne(no);
			req.setAttribute("dto", dto);
			
		// 글수정OK
		} else if (action.equals("/updateOk")) {
			NoticeDTO dto = Common.convert(req, NoticeDTO.class);
			// 글 수정시 기존파일 제거 로직 추가 해야함!
			Map<String, String> nData = Common.fileUpload(req, "files/notice");
			System.out.println(nData);
			if(nData != null && !nData.isEmpty()) {
				dto.setOfile("ofile");
				dto.setNfile("nfile");
				
				Common.fileDelete(req, "files/notice", sc.selectOne(dto.getNo()).getNfile());
			}
			
			sc.noticeUpdate(dto);
			
			
			
			// 관리자 페이지에서 수정한경우 관리자 페이지로 이동해야함!!
			resp.sendRedirect("/Notice/list");
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
