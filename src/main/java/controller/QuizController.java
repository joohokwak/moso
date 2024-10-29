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
import dto.MemberDTO;
import dto.QuizDTO;
import service.QuizService;
import serviceImpl.QuizServiceImpl;

@WebServlet("/Quiz/*")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static List<Map<String, String>> searchList = new ArrayList<>();

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
			String key 			= req.getParameter("key");
			String keyword 		= req.getParameter("keyword");
			String sort 		= req.getParameter("sort");
			String reSearch 	= req.getParameter("reSearch");
			
			// 헤더 검색 영역에서 검색 시 key를 상품명으로 설정
			if (headerSearch != null) {
				key = "goodsName";
				keyword = headerSearch;
			}
			
			// 결과 내 재검색 기능
			Map<String, String> searchMap = new HashMap<>();
			searchMap.put(key, keyword);
			
			// 결과내 재검색
			if (reSearch == null) searchList.clear();
			
			boolean isExists = false;
			for (Map<String, String> map : searchList) {
				if (map.equals(searchMap)) {
					isExists = true;
					break;
				}
			}
			
			// 동일 검색어가 존재하는 경우 제외
			if (!isExists) searchList.add(searchMap);
			
			// 기본 정렬값 설정
			if (sort == null) sort = "pop_desc";
			
			// 시작 페이지 기본 값 설정
			String strNum = req.getParameter("pageNum");
			int pageNum = strNum != null ? Integer.parseInt(strNum) : 1;
			
			Pagination pg = new Pagination();
			pg.setPageNum(pageNum);
			// 한 페이지에 보여줄 게시글 수 세팅
			pg.setPageSize(12);
			
			// 세션에서 로그인 정보 가져오기
			MemberDTO member = (MemberDTO) req.getSession().getAttribute("member");
			String id = (member != null) ? member.getId() : "";
			
			List<QuizDTO> searchAll = qs.setGoods(pg, searchList, sort, id);
			req.setAttribute("sort", sort);
			req.setAttribute("search", searchAll);
			req.setAttribute("paging", pg.paging(req));
			req.setAttribute("searchKeywords", searchList);
		}
		
		req.setAttribute("layout", "/quiz" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
