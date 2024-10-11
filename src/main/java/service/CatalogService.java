package service;

import java.util.List;

import common.Pagination;
import dto.CatalogDTO;

public interface CatalogService {
	List<CatalogDTO> selectList(Pagination pg);
	
	List<CatalogDTO> selectList(int pageNum, String title);

	CatalogDTO selectOne(int num);

	int plusVisitCount(int num);

	boolean insertCatalog(CatalogDTO dto);

	void insertCatalogfile();
	
	int totalPage();
}
