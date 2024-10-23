package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Pagination;
import dto.MemberDTO;
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

		// 세션에서 로그인 정보 가져오기
		HttpSession session = req.getSession();
		Object user = session.getAttribute("member");
		String id = null;
		if (user != null) {
			MemberDTO member = (MemberDTO) user;
			id = member.getId();
		}

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
			String sort = req.getParameter("sort");

			if (headerSearch != null) {
				key = "goodsName";
				keyword = headerSearch;
			}

			Pagination pg = new Pagination();
			pg.setPageSize(12);
			List<QuizDTO> searchAll = qs.setGoods(pg, key, keyword, sort);
			req.setAttribute("search", searchAll);
			req.setAttribute("paging", pg.paging(req));

//			System.out.println(sort);

			req.setAttribute("layout", "/quiz" + action);
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
}
