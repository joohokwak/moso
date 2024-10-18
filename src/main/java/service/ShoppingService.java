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
	
	List<ItemReviewDTO> reviewAll(int num);
}
