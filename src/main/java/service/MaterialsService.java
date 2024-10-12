package service;

import java.util.List;

import common.Pagination;
import dto.MaterialsDTO;

public interface MaterialsService {
	List<MaterialsDTO> selectList(Pagination pg);
}
