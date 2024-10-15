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

	@Override
	public NoticeDTO selectOne(int no) {
		
		return dao.getSelectOne(no);
	}

	@Override
	public boolean insertNotice(NoticeDTO dto) {
		return dao.getInsertNotice(dto) > 0;
	}

	@Override
	public boolean deleteNotice(NoticeDTO dto) {
		return dao.getDeleteNotice(dto) > 0;
	}

}
