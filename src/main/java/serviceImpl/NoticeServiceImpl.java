package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.NoticeDAO;
import dto.NoticeDTO;
import service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDAO dao;
	
	public NoticeServiceImpl () {
		dao = new NoticeDAO();
	}
	
	@Override
	public List<NoticeDTO> selectAll(Pagination pg) {
		return dao.getSelectAll(pg);
	}

	@Override
	public NoticeDTO selectOne(int no) {
		dao.getVisitCount(no);
		return dao.getSelectOne(no);
	}

	@Override
	public boolean insertNotice(NoticeDTO dto) {
		return dao.getInsertNotice(dto) > 0;
	}

	@Override
	public boolean deleteNotice(String[] selNo) {
		return dao.getDeleteNotice(selNo) > 0;
	}

	@Override
	public boolean noticeUpdate(NoticeDTO dto) {
		return dao.getNoticeUpdate(dto) > 0;
	}

}
