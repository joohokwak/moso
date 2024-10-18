package service;

import java.util.List;

import common.Pagination;
import dto.MagazineDTO;

public interface MagazineService {
	List<MagazineDTO> magazineList(Pagination pg);
	MagazineDTO magazineView(int no);
	int magazineUpdateOk(MagazineDTO dto);
	int magazineWriteOk(MagazineDTO dto);
	int magazineDelete(int no);
}
