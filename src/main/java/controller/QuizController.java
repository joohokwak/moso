package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Pagination;
import dto.QuizDTO;
import service.QuizService;
import serviceImpl.QuizServiceImpl;

@WebServlet("/Quiz/*")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		String action = req.getPathInfo();
		QuizService qs = new QuizServiceImpl();
		
		// 결과 페이지
		if (action.equals("/result")) {
			String q1 = req.getParameter("Q1");
			String q2 = req.getParameter("Q2");
			String q3 = req.getParameter("Q3");
			String q4 = req.getParameter("Q4");
			String sumQ = q1 + q2 + q3 + q4;

			List<QuizDTO> list = qs.mattressQuiz(sumQ);
			
			req.setAttribute("quiz", list);
			
		// search 페이지
		} else if (action.equals("/search")) {
			String headerSearch = req.getParameter("name");
			String key = req.getParameter("key");
			String keyword = req.getParameter("keyword");
			
			if (headerSearch != null) {
				key = "goodsName";
				keyword = headerSearch;
			}
			
			Pagination pg = new Pagination();
			pg.setPageSize(12);
			List<QuizDTO> searchAll = qs.setGoods(pg, key, keyword);
			Map<String, Object> searchMap = new HashMap<>();
			List<Map<String, Object>> listMap = new ArrayList<>();
			
			
			searchMap.put("keyword", keyword);
			
			listMap.add(searchMap);
			
			System.out.println(searchMap);
			
			
			req.setAttribute("search", searchAll);
			req.setAttribute("paging", pg.paging(req));
		}
		
		req.setAttribute("layout", "/quiz" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}

