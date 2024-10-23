package service;

import java.util.List;

import common.Pagination;
import dto.FaqDTO;

public interface FaqService {
	List<FaqDTO> selectList(Pagination pg);
	FaqDTO selectFaq(int no);
	boolean insertFaq(FaqDTO dto);
	boolean deleteFaq(String...no);
	boolean updateFaq(FaqDTO dto);
}
