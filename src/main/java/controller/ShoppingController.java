package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
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
		System.out.println(action);
		
		ShoppingService shopSer = new ShoppingServiceImpl();
		
		
		if (action.equals("/main")) {
			String ordered = req.getParameter("ordered");
			String type = req.getParameter("type");
			List<ShoppingDTO> list = shopSer.viewMain(type, ordered);
			
			String typecheck = "&type=" + type;
			System.out.println(typecheck);
			req.setAttribute("typecheck", typecheck);
			req.setAttribute("list", list);
			
		} else if (action.equals("/buy")) {
			
		} else if (action.equals("/like")) {
			Map<String, Object> data = Common.jsonConvert(req);
			int no = Integer.parseInt(data.get("no") + "");
			String id = data.get("id") + "";
			
			shopSer.insertLike(no, id);
			return;
		}
		
		req.setAttribute("layout", "/shopping" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
