package service;

import java.util.List;

import common.Pagination;
import dto.MagazineDTO;

public interface MagazineService {
	List<MagazineDTO> magazineList(Pagination pg);
	MagazineDTO magazineView(int no);
	int magazineUpdate(MagazineDTO dto);
}
