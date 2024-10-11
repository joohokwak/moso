package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Magazine/*")
public class MagazineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		
		if (action.equals("/magazine")) {
			
		} else if (action.equals("/view")) {
			
		} else if (action.equals("/review")) {
			
		}
		
		req.setAttribute("layout", "/magazine" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
