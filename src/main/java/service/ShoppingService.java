package service;

import java.util.List;

import dto.ShoppingDTO;

public interface ShoppingService {

	int insertLike(int no, String id);

	List<ShoppingDTO> viewMain(String ty, String ordered, String id, int pg);
}
