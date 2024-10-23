package service;

import java.util.List;

import common.Pagination;
import dto.NoticeDTO;

public interface NoticeService {
	List<NoticeDTO> selectAll(Pagination pg);
	
	NoticeDTO selectOne(int no);
	
	boolean insertNotice(NoticeDTO dto);
	
	boolean deleteNotice(String...selNo);
	
	boolean noticeUpdate(NoticeDTO dto);
}
