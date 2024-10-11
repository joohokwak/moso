package serviceImpl;

import java.util.List;

import dao.CatalogDAO;
import dto.CatalogDTO;
import service.CatalogService;

public class CatalogServiceImpl implements CatalogService {
	private CatalogDAO dao;

	public CatalogServiceImpl() {
		dao = new CatalogDAO();
	}

	@Override
	public List<CatalogDTO> selectList() {
		return dao.selectList();
	}

	@Override
	public CatalogDTO selectOne(int num) {
		return dao.selectOne(num);
	}

	@Override
	public int plusVisitCount(int num) {
		return dao.plusVisitCount(num);
	}
	
	

}
