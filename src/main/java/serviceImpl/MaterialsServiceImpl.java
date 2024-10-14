package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.MaterialsDAO;
import dto.MaterialsDTO;
import service.MaterialsService;

public class MaterialsServiceImpl implements MaterialsService {
	private MaterialsDAO dao;
	
	public MaterialsServiceImpl() {
		dao = new MaterialsDAO();
	}

	@Override
	public MaterialsDTO selectOne(int no) {
		return dao.selectOne(no);
	}
	
	@Override
	public List<MaterialsDTO> selectList(Pagination pg) {
		return dao.selectList(pg);
	}

	@Override
	public int deleteMaterial(int no) {
		return dao.deleteMaterial(no);
	}

	@Override
	public int insertMaterial(MaterialsDTO dto) {
		return dao.insertMaterial(dto);
	}

	@Override
	public int updateMaterial(MaterialsDTO dto) {
		return dao.updateMaterial(dto);
	}

}
