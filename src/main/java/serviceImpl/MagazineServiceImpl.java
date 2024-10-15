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
	
	@Override
	public List<MagazineDTO> magazineList(Pagination pg) {
		return dao.magazineList(pg);
	}
	
	@Override
	public MagazineDTO magazineView(int no) {
		return dao.magazineView(no);
	}
	
	@Override
	public int magazineUpdate(MagazineDTO dto) {
		return dao.magazineUpdate(dto);
	}
}
 