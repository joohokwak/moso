package service;

import java.util.List;

import common.Pagination;
import dto.MemberDTO;

public interface MemberService {
	int insertMember(MemberDTO member);
	boolean idCheck(String id);
	MemberDTO login(MemberDTO member);
	MemberDTO idFined(MemberDTO member);
	MemberDTO pwFined(String id);
	int updateMember(MemberDTO member);
	List<MemberDTO> selectMemberAll(Pagination pg);
	MemberDTO selectMember(String id);
	int memberDelete(String...id);
}
