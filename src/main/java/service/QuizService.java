package service;

import java.util.List;

import common.Pagination;
import dto.QuizDTO;

public interface QuizService {

	List<QuizDTO> mattressQuiz(String sumQ);
	List<QuizDTO> setGoods(Pagination pg);
}
