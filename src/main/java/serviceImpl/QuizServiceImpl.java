package serviceImpl;

import java.util.List;

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
}
