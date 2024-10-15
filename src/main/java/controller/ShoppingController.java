package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import dto.MemberDTO;
import dto.ShoppingDTO;
import service.ShoppingService;
import serviceImpl.ShoppingServiceImpl;

@WebServlet("/Shop/*")
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		
		ShoppingService shopSer = new ShoppingServiceImpl();
		
		HttpSession session = req.getSession();
		Object user = session.getAttribute("member");
		String id = null;
		if(user != null)  {
			MemberDTO member = (MemberDTO) user;
			id = member.getId();
		}
		if (action.equals("/main")) {
			String ordered = req.getParameter("ordered");
			if(ordered == null) ordered = "pop";
			String type = req.getParameter("type");
			int pg = 1;
						
			String typecheck = "type=" + type + "&";
			String orderBy = "&ordered=" + ordered;
			
			String page = req.getParameter("paging");
			if(page != null) pg = Integer.parseInt(page);
			
			List<ShoppingDTO> list = shopSer.viewMain(type, ordered, id, pg);
			
			
			
			
			req.setAttribute("orderBy", orderBy);
			req.setAttribute("typecheck", typecheck);
			req.setAttribute("list", list);
//			req.setAttribute("paging", list.getLast());
			
		} else if (action.equals("/buy")) {
			
		} else if (action.equals("/like")) {
			
			String like = req.getParameter("like");
			int no = Integer.parseInt(like);

			if(user != null) {
	
				int result = shopSer.insertLike(no, id);
				Common.jsonResponse(resp, result);
				
			}
			return;
		}
		
		req.setAttribute("layout", "/shopping" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
