package service;

import java.util.List;

import common.Pagination;
import dto.ItemReviewDTO;
import dto.ShoppingDTO;

public interface ShoppingService {
	
	// 전체 item list 조회/정렬
	List<ShoppingDTO> viewMain(String ty, String ordered, String id, Pagination pg);
	
	// 아이템 조회
	ShoppingDTO buyMain(int num);
	ShoppingDTO writeItem(int itemno);
	
	// like 관련 조회/추가/삭제
	int insertLike(int no, String id);
	
	// item_image 테이블 조회
	List<String> imageName(int num);
	
	// review 조회
	List<ItemReviewDTO> reviewAll(int num, Pagination pg);
	ItemReviewDTO reviewOne(int rvno);
	
	// review 작성/수정/삭제
	int rvwrite(ItemReviewDTO dto);
	int rvupdate(ItemReviewDTO dto);
	int rvdelete(int rvno);
	
	// Q&A 조회
	List<ItemReviewDTO> qnaAll(int num, Pagination pg);
	ItemReviewDTO qnaOne(int qnano);
	
	// Q&A 질문/수정/삭제
	boolean qnaCreate(ItemReviewDTO qnaCre);
	boolean qnaUpdate(ItemReviewDTO qnaUp);
	int qnaDel(int no);
	
	// Q&A 답변
	int ansCreate(int no, String ans);

}
