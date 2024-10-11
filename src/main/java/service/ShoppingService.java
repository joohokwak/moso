package service;

import java.util.List;

import dto.ShoppingDTO;

public interface ShoppingService {
	List<ShoppingDTO> viewMain(String ty, String ordered);
	void insertLike(int no, String id);
}
