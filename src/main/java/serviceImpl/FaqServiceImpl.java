package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.FaqDAO;
import dto.FaqDTO;
import service.FaqService;

public class FaqServiceImpl implements FaqService {
	private FaqDAO dao;
	
	public FaqServiceImpl() {
		dao = new FaqDAO();
	}

	@Override
	public List<FaqDTO> selectList(Pagination pg) {
		return dao.selectList(pg);
	}
	
	@Override
	public FaqDTO selectFaq(int no) {
		return dao.selectFaq(no);
	}

	@Override
	public boolean insertFaq(FaqDTO dto) {
		return dao.insertFaq(dto) > 0;
	}

	@Override
	public boolean deleteFaq(int no) {
		return dao.deleteFaq(no) > 0;
	}

	@Override
	public boolean updateFaq(FaqDTO dto) {
		return dao.updateFaq(dto) > 0;
	}

	@Override
	public boolean deleteFaq(String[] no) {
		return dao.deleteFaq(no) > 0;
	}

}
