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
	public List<MaterialsDTO> selectList(Pagination pg) {
		return dao.selectList(pg);
	}

	@Override
	public int deleteMaterial(int no) {
		return dao.deleteMaterial(no);
	}

}
