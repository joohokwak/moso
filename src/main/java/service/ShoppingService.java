package service;

import java.util.List;

import common.Pagination;
import dto.ItemReviewDTO;
import dto.ShoppingDTO;

public interface ShoppingService {

	int insertLike(int no, String id);

	List<ShoppingDTO> viewMain(String ty, String ordered, String id, Pagination pg);

	ShoppingDTO buyMain(int num);

	List<String> imageName(int num);
	
	// reivew
	List<ItemReviewDTO> reviewAll(int num, Pagination pg);
	
	// qna
	List<ItemReviewDTO> qnaAll(int num, Pagination pg);
	
	ShoppingDTO writeItem(int itemno);

	boolean qnaCreate(ItemReviewDTO qnaCre);
	
	boolean qnaUpdate(ItemReviewDTO qnaUp);
	
	ItemReviewDTO qnaOne(int qnano);
	
	int qnaDel(int no);
	
	int ansCreate(int no, String ans);
	
	int rvwrite(ItemReviewDTO dto, int rating);
}
