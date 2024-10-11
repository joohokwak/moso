package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Notice/*")
@MultipartConfig
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		// 리스트
		if (action.equals("/list")) {
			
		// 상세보기
		} else if (action.equals("/view")) {
			
		// 글쓰기OK
		} else if (action.equals("/writeOk")) {
			
		// 글삭제
		} else if (action.equals("/delete")) {
			
		// 글수정
		} else if (action.equals("/update")) {
			
		// 글수정OK
		} else if (action.equals("/updateOk")) {
			
		}
		
		req.setAttribute("layout", "/notice" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
