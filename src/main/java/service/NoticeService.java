package service;

import java.util.List;

import common.Pagination;
import dto.NoticeDTO;

public interface NoticeService {
	List<NoticeDTO> selectAll(Pagination pg);
}
