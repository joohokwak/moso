package service;

import java.util.List;
import java.util.Map;

import common.Pagination;
import dto.QuizDTO;

public interface QuizService {

	List<QuizDTO> mattressQuiz(String sumQ);
	List<QuizDTO> setGoods(Pagination pg, List<Map<String, String>> searchList, String sort, String id);
}
