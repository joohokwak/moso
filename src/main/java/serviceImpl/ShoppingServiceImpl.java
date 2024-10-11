package serviceImpl;

import java.util.List;

import dao.ShoppingDAO;
import dto.ShoppingDTO;
import service.ShoppingService;

public class ShoppingServiceImpl implements ShoppingService {
	private ShoppingDAO dao;
	
	public ShoppingServiceImpl() {
		dao = new ShoppingDAO();
	}

	@Override
	public List<ShoppingDTO> viewMain(String ty, String ordered) {
		return dao.viewMain(ty, ordered);
	}

	@Override
	public void insertLike(int no, String id) {
		dao.insertLike(no, id);
	}
	
	
}
