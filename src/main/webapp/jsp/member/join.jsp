<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty msg }">
	<script>
		alert(`${msg }`, () => {
			history.back();
		});
	</script>
</c:if>

<div class="join_wrap">
	<div class="join_top">
		<h2>회원가입</h2>
	</div>

	<form action="/Member/joinOk" class="join_form" method="post">
		<div class="join_tit">
			<h3>기본 정보</h3>
			<p>표시는 반드시 입력하셔야 하는 항목입니다.</p>
		</div>

		<table>
			<caption>회원가입</caption>
			<colgroup>
				<col width="15%">
				<col>
			</colgroup>

			<tbody>
				<tr>
					<th class="required">아이디</th>
					<td>
						<input type="text" name="id" id="id" title="아이디를 최소 4자 이상 입력하세요" autocomplete="off">
						<div class="join_id_msg">이미 등록된 아이디입니다. 다른 아이디를 입력해 주세요.</div>
					</td>
				</tr>
				<tr>
					<th class="required">비밀번호</th>
					<td>
						<input type="password" name="pass" title="비밀번호">
						<div class="join_id_msg">사용불가! 영문대/소문자, 숫자, 특수문자 중 2가지 이상 조합하세요.</div>
					</td>
				</tr>
				<tr>
					<th class="required">비밀번호 확인</th>
					<td>
						<input type="password" name="pass2" title="비밀번호">
						<div class="join_id_msg">비밀번호가 서로 다릅니다.</div>
					</td>
				</tr>
				<tr>
					<th class="required">이름</th>
					<td>
						<input type="text" name="name" title="이름" autocomplete="off">
					</td>
				</tr>
				<tr>
					<th class="required">이메일</th>
					<td>
						<input type="text" name="email" title="이메일" autocomplete="off">
						<select name="domain" title="이메일 도메인">
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="icloud.com">icloud.com</option>
						</select>
						<div class="join_element">
							<input type="checkbox" id="maillingFl">
							<label for="maillingFl">정보/이벤트 메일 수신에 동의합니다.</label>
						</div>
					</td>
				</tr>
				<tr>
					<th class="required">휴대폰번호</th>
					<td>
						<input type="text" name="phone" title="휴대폰번호" placeholder="공백없이 숫자만 입력하세요." autocomplete="off">
						<div class="join_element">
							<input type="checkbox" id="smsFl">
							<label for="smsFl">정보/이벤트 SMS 수신에 동의합니다.</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" name="tel" title="전화번호"  placeholder="공백없이 숫자만 입력하세요." autocomplete="off">
					</td>
				</tr>
				<tr>
					<th class="required">주소</th>
					<td>
						<input type="text" id="zipcode" name="zipcode" title="주소" readonly>
						<button type="button" onclick="postcode();">우편번호검색</button>
						<div>
							<input type="text" id="address1" name="address" title="주소" readonly>
							<input type="text" id="address2" name="addr_detail" title="주소" placeholder="상세주소" autocomplete="off">
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<div class="join_tit">
			<h3>부가 정보</h3>
		</div>

		<table>
			<colgroup>
				<col width="163px">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th class="required">성별</th>
					<td>
						<span>
							<input type="radio" id="male" name="gender" value="M" checked>
							<label for="male">남자</label>
						</span>
						<span>
							<input type="radio" id="female" name="gender" value="F">
							<label for="female">여자</label>
						</span>
					</td>
				</tr>
				<tr>
					<th>생일</th>
					<td>
						<div class="calendar_wrap">
							<input type="date" class="join_calendar" name="birth" placeholder="예시) 2015-09-03" autocomplete="off">
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<div class="join_btns">
			<button type="reset" >취소</button>
			<button type="submit">회원가입</button>
		</div>
	</form>
</div>
