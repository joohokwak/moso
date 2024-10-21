package serviceImpl;

import java.util.List;

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
	public List<QuizDTO> setGoods(Pagination pg, String key, String keyword) {
		return dao.setGoods(pg, key, keyword);
	}
}
