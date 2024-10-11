package service;

import java.util.List;

import dto.CatalogDTO;

public interface CatalogService {
	List<CatalogDTO> selectList();

	CatalogDTO selectOne(int num);

	int plusVisitCount(int num);
}
