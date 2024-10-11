package dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String email;
	private String phone;
	private String tel;
	private String zipcode;
	private String address;
	private String addr_detail;
	private String gender;
	private String birth;
	private String isadmin;
}
