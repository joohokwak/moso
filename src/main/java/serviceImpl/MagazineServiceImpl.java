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
	public int magazineUpdateOk(MagazineDTO dto) {
		return dao.magazineUpdateOk(dto);
	}
	
	@Override
	public int magazineWriteOk(MagazineDTO dto) {
		return dao.magazineWriteOk(dto);
	}
		
	@Override
	public int magazineDelete(String...no) {
		return dao.magazineDelete(no);
	}
}
 