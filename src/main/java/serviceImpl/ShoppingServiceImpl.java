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
	public List<ShoppingDTO> viewMain(String ty, String ordered, String id, int pg) {
		return dao.viewMain(ty, ordered, id, pg);
	}

	@Override
	public int insertLike(int no, String id) {
		return dao.insertLike(no, id);
	}
	
	
}
