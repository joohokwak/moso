package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.Common;
import common.DBCP;
import dto.MemberDTO;

public class MemberDAO extends DBCP {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public MemberDAO() {
		conn = getConn();
	}
	
	// 회원가입
	public int insertMember(MemberDTO member) {
		int result = 0;
		
		try {
			String sql = "";
			sql += "INSERT INTO MEMBER                         ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
			ps.setString(12, member.getIsadmin());
			
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
			String sql = "SELECT * FROM MEMBER WHERE UPPER(ID) = UPPER(?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) result = true;
			
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
	
	// 아이디 찾기
	public MemberDTO idFined(MemberDTO member) {
		MemberDTO dto = null;
		
		try {
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
			String sql = "UPDATE MEMBER SET PASS = ? WHERE ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Common.getRandomPassword(10));
			ps.setString(2, id);
			
			int re = ps.executeUpdate();
			System.out.println("re : " + re);
			
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
}
