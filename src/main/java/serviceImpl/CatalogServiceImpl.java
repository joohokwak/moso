package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.CatalogDAO;
import dto.CatalogDTO;
import service.CatalogService;

public class CatalogServiceImpl implements CatalogService {
	private CatalogDAO dao;

	public CatalogServiceImpl() {
		dao = new CatalogDAO();
	}

	@Override
	public List<CatalogDTO> selectList(Pagination pg) {
		return dao.selectList(pg);
	}
	
	@Override
	public CatalogDTO selectOne(int num) {
		return dao.selectOne(num);
	}

	@Override
	public int plusVisitCount(int num) {
		return dao.plusVisitCount(num);
	}

	@Override
	public boolean insertCatalog(CatalogDTO dto) {
		return dao.insertCatalog(dto) > 0;
	}

	@Override
	public void insertCatalogfile() {
	}
	
	@Override
	public int totalPage() {
		return dao.totalPage();
	}

	@Override
	public boolean updateCatalog(CatalogDTO dto) {
		return dao.updateCatalog(dto) > 0;
	}

	@Override
	public void updateCatalogfile() {
	}

	@Override
	public boolean deleteCatalog(CatalogDTO dto) {
		return dao.deleteCatalog(dto) > 0;
	}
}
