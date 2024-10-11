package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.NoticeDAO;
import dto.NoticeDTO;
import service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	NoticeDAO dao;
	
	public NoticeServiceImpl () {
		dao = new NoticeDAO();
	}
	
	@Override
	public List<NoticeDTO> selectAll(Pagination pg) {
		
		return dao.getSelectAll(pg);
	}

}
