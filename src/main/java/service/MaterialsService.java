package service;

import java.util.List;

import common.Pagination;
import dto.MaterialsDTO;

public interface MaterialsService {
	List<MaterialsDTO> selectList(Pagination pg);
	MaterialsDTO selectOne(int no);
	int deleteMaterial(int no);
	int deleteMaterial(String[] no);
	int insertMaterial(MaterialsDTO dto);
	int updateMaterial(MaterialsDTO dto);
}
