package service;

import java.util.List;

import common.Pagination;
import dto.CatalogDTO;

public interface CatalogService {
	List<CatalogDTO> selectList(Pagination pg);
	
	CatalogDTO selectOne(int num);

	int plusVisitCount(int num);

	boolean insertCatalog(CatalogDTO dto);

	void insertCatalogfile();
	
	boolean updateCatalog(CatalogDTO dto);

	void updateCatalogfile();

	boolean deleteCatalog(CatalogDTO dto);
}
