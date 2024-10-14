package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.MagazineDAO;
import dto.MagazineDTO;
import service.MagazineService;

public class MagazineServiceImpl implements MagazineService {
	private MagazineDAO dao;
	
	public MagazineServiceImpl() {
		dao = new MagazineDAO();
	}
	
	public List<MagazineDTO> magazineList(Pagination pg) {
		return dao.magazineList(pg);
	}
}
