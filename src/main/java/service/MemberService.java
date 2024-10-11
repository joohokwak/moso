package service;

import dto.MemberDTO;

public interface MemberService {
	int insertMember(MemberDTO member);
	boolean idCheck(String id);
	MemberDTO login(MemberDTO member);
	MemberDTO idFined(MemberDTO member);
	MemberDTO pwFined(String id);
}
