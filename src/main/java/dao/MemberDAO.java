package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.Common;
import common.DBCP;
import common.Order;
import common.Pagination;
import dto.MemberDTO;

public class MemberDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// 회원가입
	public int insertMember(MemberDTO member) {
		int result = 0;
		
		System.out.println(member);
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "INSERT INTO MEMBER (ID, PASS, NAME, EMAIL, PHONE, TEL, ZIPCODE, ADDRESS, ADDR_DETAIL, GENDER, BIRTH) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPass());
			ps.setString(3, member.getName());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone().replaceAll("-", ""));
			ps.setString(6, member.getTel().replaceAll("-", ""));
			ps.setString(7, member.getZipcode().replaceAll("-", ""));
			ps.setString(8, member.getAddress());
			ps.setString(9, member.getAddr_detail());
			ps.setString(10, member.getGender());
			ps.setString(11, member.getBirth().replaceAll("-", ""));
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 아이디 체크
	public boolean idCheck(String id) {
		boolean result = false;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM MEMBER WHERE UPPER(ID) = UPPER(?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			 
			rs = ps.executeQuery();
			if (rs.next()) result = true;
			
//			String sqlLen = "SELECT * FROM MEMBER WHERE LENGTH(?) >= 12";
//			
//			ps = conn.prepareStatement(sqlLen);
//			ps.setString(1, id);
//
//			if(rs.next()) result = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	// 로그인
	public MemberDTO login(MemberDTO member) {
		MemberDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASS = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPass());
			
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("ID"));
				dto.setPass(rs.getString("PASS"));
				dto.setName(rs.getString("NAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setTel(rs.getString("TEL"));
				dto.setZipcode(rs.getString("ZIPCODE"));
				dto.setAddress(rs.getString("ADDRESS"));
				dto.setAddr_detail(rs.getString("ADDR_DETAIL"));
				dto.setGender(rs.getString("GENDER"));
				dto.setBirth(rs.getString("BIRTH"));
				dto.setIsadmin(rs.getString("ISADMIN"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
	
	// 회원 상세조회
	public MemberDTO selectMember(String id) {
		MemberDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("ID"));
				dto.setPass(rs.getString("PASS"));
				dto.setName(rs.getString("NAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setTel(rs.getString("TEL"));
				dto.setZipcode(rs.getString("ZIPCODE"));
				dto.setAddress(rs.getString("ADDRESS"));
				dto.setAddr_detail(rs.getString("ADDR_DETAIL"));
				dto.setGender(rs.getString("GENDER"));
				dto.setBirth(rs.getString("BIRTH"));
				dto.setIsadmin(rs.getString("ISADMIN"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}
	
	// 아이디 찾기
	public MemberDTO idFined(MemberDTO member) {
		MemberDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "SELECT * FROM MEMBER WHERE NAME = ? AND EMAIL = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("ID"));
				dto.setPass(rs.getString("PASS"));
				dto.setName(rs.getString("NAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setTel(rs.getString("TEL"));
				dto.setZipcode(rs.getString("ZIPCODE"));
				dto.setAddress(rs.getString("ADDRESS"));
				dto.setAddr_detail(rs.getString("ADDR_DETAIL"));
				dto.setGender(rs.getString("GENDER"));
				dto.setBirth(rs.getString("BIRTH"));
				dto.setIsadmin(rs.getString("ISADMIN"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}

	// 비밀번호 찾기 (비밀번호 초기화 후)
	public MemberDTO pwFined(String id) {
		MemberDTO dto = null;
		
		try {
			conn = getConn();
			
			String sql = "UPDATE MEMBER SET PASS = ? WHERE ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Common.getRandomPassword(10));
			ps.setString(2, id);
			
			int re = ps.executeUpdate();
			
			if (re > 0) {
				sql = "SELECT * FROM MEMBER WHERE ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				
				rs = ps.executeQuery();
				if (rs.next()) {
					dto = new MemberDTO();
					dto.setId(rs.getString("ID"));
					dto.setPass(rs.getString("PASS"));
					dto.setName(rs.getString("NAME"));
					dto.setEmail(rs.getString("EMAIL"));
					dto.setPhone(rs.getString("PHONE"));
					dto.setTel(rs.getString("TEL"));
					dto.setZipcode(rs.getString("ZIPCODE"));
					dto.setAddress(rs.getString("ADDRESS"));
					dto.setAddr_detail(rs.getString("ADDR_DETAIL"));
					dto.setGender(rs.getString("GENDER"));
					dto.setBirth(rs.getString("BIRTH"));
					dto.setIsadmin(rs.getString("ISADMIN"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return dto;
	}

	// 회원 수정
	public int updateMember(MemberDTO member) {
		int result = 0;
		
		try {
			conn = getConn();
			
			String sql = "";
			sql += "UPDATE MEMBER                         								";
			sql += "SET PASS = ?, NAME = ?, EMAIL = ?, PHONE = ?, TEL = ?,				";
			sql += "ZIPCODE = ?, ADDRESS = ?, ADDR_DETAIL = ?, GENDER = ?, BIRTH = ?,	";
			sql += "WHERE ID = ?														";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getPass());
			ps.setString(2, member.getName());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getPhone().replaceAll("-", ""));
			ps.setString(5, member.getTel().replaceAll("-", ""));
			ps.setString(6, member.getZipcode().replaceAll("-", ""));
			ps.setString(7, member.getAddress());
			ps.setString(8, member.getAddr_detail());
			ps.setString(9, member.getGender());
			ps.setString(10, member.getBirth().replaceAll("-", ""));
			ps.setString(11, member.getId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return result;
	}
	
	public int deleteMember(String id) {
		int result = 0;
		try {
			conn = getConn();
			
			String sqlItem = "DELETE FROM ITEM_LIKE WHERE MEMBER_ID=?";
			
			ps = conn.prepareStatement(sqlItem);
			ps.setString(1, id);
			ps.executeUpdate();
			
			String sql = "DELETE FROM MEMBER WHERE ID=?";
				
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
				
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return result;
	}

	// 관리자페이지 (회원전체 조회)
	public List<MemberDTO> selectMemberAll(Pagination pg) {
		List<MemberDTO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			
			pg.setOrderName("ID");
			pg.setOrder(Order.ASC.getOrder());
			String sql = pg.getQuery(conn, "SELECT * FROM MEMBER");
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("ID"));
				dto.setPass(rs.getString("PASS"));
				dto.setName(rs.getString("NAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setTel(rs.getString("TEL"));
				dto.setZipcode(rs.getString("ZIPCODE"));
				dto.setAddress(rs.getString("ADDRESS"));
				dto.setAddr_detail(rs.getString("ADDR_DETAIL"));
				dto.setGender(rs.getString("GENDER"));
				dto.setBirth(rs.getString("BIRTH"));
				dto.setIsadmin(rs.getString("ISADMIN"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		return list;
	}
	
	
	
}
