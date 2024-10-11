package serviceImpl;

import dao.MemberDAO;
import dto.MemberDTO;
import service.MemberService;

public class MemberServiceImpl implements MemberService {
	private MemberDAO dao;
	
	public MemberServiceImpl() {
		dao = new MemberDAO();
	}

	@Override
	public int insertMember(MemberDTO member) {
		return dao.insertMember(member);
	}

	@Override
	public boolean idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public MemberDTO login(MemberDTO member) {
		return dao.login(member);
	}

	@Override
	public MemberDTO idFined(MemberDTO member) {
		return dao.idFined(member);
	}

	@Override
	public MemberDTO pwFined(String id) {
		return dao.pwFined(id);
	}

}
