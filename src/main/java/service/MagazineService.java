package service;

import java.util.List;

import dto.MagazineDTO;

public interface MagazineService {
	List<MagazineDTO> magazineList(String mtype);
}
