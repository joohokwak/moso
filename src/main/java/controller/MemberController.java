package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import dto.MemberDTO;
import service.MemberService;
import serviceImpl.MemberServiceImpl;

@WebServlet("/Member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getPathInfo();
		MemberService mService = new MemberServiceImpl();
		
		// 아이디 중복체크
		if (action.equals("/idCheck")) {
			Map<String, Object> data = Common.jsonConvert(req);
			String result = mService.idCheck(data.get("id") + "") ? "no" : "ok";
			Common.jsonResponse(resp, result);
			return;
		
		// 회원가입
		} else if (action.equals("/joinOk")) {
			MemberDTO member = Common.convert(req, MemberDTO.class);
			if (member != null) {
				int re = mService.insertMember(member);
				if (re == 0) {
					req.setAttribute("msg", "회원가입에 실패하였습니다. 잠시후 다시 시도 하시거나 관리자에게 문의하세요.");
					action = "/join";
				}
			} else {
				resp.sendRedirect("/Main");
				return;
			}
			
		// 로그인
		} else if (action.equals("/login")) {
			MemberDTO member = new MemberDTO();
			Map<String, Object> data = Common.jsonConvert(req);
			member.setId(data.get("id") + "");
			member.setPass(data.get("pw") + "");
			
			MemberDTO user = mService.login(member);
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("member", user);
				Common.jsonResponse(resp, 0);
				return;
				
			} else { // 로그인 실패
				Common.jsonResponse(resp, -1);
				return;
			}
			
		// 로그아웃
		} else if (action.equals("/logout")) {
			req.getSession().invalidate();
			resp.getWriter().write("<script>alert('로그아웃 되었습니다.'); location.href = '/Main';</script>");
			return;
			
		// 아이디 찾기
		} else if (action.equals("/idFined")) {
			MemberDTO member = new MemberDTO();
			Map<String, Object> data = Common.jsonConvert(req);
			member.setName(data.get("name") + "");
			member.setEmail(data.get("email") + "");
			
			MemberDTO dto = mService.idFined(member);
			String result = "";
			if (dto != null) result = dto.getName() + " 회원님의 아이디는<br><strong>" + dto.getId() + "</strong> 입니다";
			Common.jsonResponse(resp, result);
			return;
			
		// 비밀번호 찾기
		} else if (action.equals("/pwFined")) {
			Map<String, Object> data = Common.jsonConvert(req);
			
			MemberDTO dto = mService.pwFined(data.get("id") + "");
			String result = "";
			if (dto != null) result = dto.getName() + " 회원님의 임시비밀번호는<br><strong>" + dto.getPass() + "</strong> 입니다";
			Common.jsonResponse(resp, result);
			return;
			
		// 회원정보 수정
		} else if (action.equals("/updateOk")) {
			MemberDTO dto = Common.convert(req, MemberDTO.class);
			int re = mService.updateMember(dto);
			if (re > 0) req.setAttribute("msg", "정상적으로 변경되었습니다.");
			else req.setAttribute("msg", "회원정보 변경에 실패하였습니다. 잠시후 다시 시도 하시거나 관리자에게 문의하세요.");
			req.getSession().invalidate();
			
			MemberDTO user = mService.login(dto);
			HttpSession session = req.getSession();
			session.setAttribute("member", user);
			
			action = "/update";
		
		// 회원탈퇴
		} else if (action.equals("/withdraw")) {
			HttpSession session = req.getSession();
			MemberDTO user = (MemberDTO) session.getAttribute("member");
			if (user != null && user.getId() != null)  {
				int re = mService.memberDelete(user.getId());
				if(re > 0) {
					req.getSession().invalidate();
				}
			}
			resp.sendRedirect("/");
			return;
		}
		
		
		req.setAttribute("layout", "/member" + action);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
