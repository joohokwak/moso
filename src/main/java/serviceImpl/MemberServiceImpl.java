package serviceImpl;

import java.util.List;

import common.Pagination;
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

	@Override
	public int updateMember(MemberDTO member) {
		return dao.updateMember(member);
	}

	@Override
	public List<MemberDTO> selectMemberAll(Pagination pg) {
		return dao.selectMemberAll(pg);
	}

	@Override
	public MemberDTO selectMember(String id) {
		return dao.selectMember(id);
	}

	@Override
	public int memberDelete(String id) {
		return dao.deleteMember(id);
	}

}
