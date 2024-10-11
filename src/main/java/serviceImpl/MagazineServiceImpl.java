package serviceImpl;

import java.util.List;

import dao.MagazineDAO;
import dto.MagazineDTO;
import service.MagazineService;

public class MagazineServiceImpl implements MagazineService {
	private MagazineDAO dao;
	
	public MagazineServiceImpl() {
		dao = new MagazineDAO();
	}
	
	public List<MagazineDTO> magazineList(String mtype) {
		return dao.magazineList(mtype);
	}
}
