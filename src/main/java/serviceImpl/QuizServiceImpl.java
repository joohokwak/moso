package serviceImpl;

import java.util.List;
import java.util.Map;

import common.Pagination;
import dao.QuizDAO;
import dto.QuizDTO;
import service.QuizService;

public class QuizServiceImpl implements QuizService{
	private QuizDAO dao;
	
	public QuizServiceImpl() {
		dao = new QuizDAO();
	}		
	
	@Override
	public List<QuizDTO> mattressQuiz(String sumQ) {
		return dao.mattressQuiz(sumQ);
	}

	@Override
	public List<QuizDTO> setGoods(Pagination pg, List<Map<String, String>> searchList, String sort) {
		return dao.setGoods(pg, searchList, sort);
	}
}
