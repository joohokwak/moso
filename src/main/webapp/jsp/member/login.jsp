<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="login_wrap">
	<!-- 로그인 메인 -->
	<div class="login_main">
		<div class="login_box">
			<div class="login_top">
				<h3><img src="/images/login/login_logo.png" alt="logo"></h3>
				<button type="button" class="login_close"><span class="blind">닫기</span></button>
			</div>

			<div>
				<p class="login_tit">띵동, 편안한 잠이 배달되었어요.</p>
				<div class="login_sns">
					<a href="#" class="kakao"><img src="/images/login/ico_kakao_s.png" alt="kakao">카카오로 빠르게 시작하기</a>
					<a href="#" class="naver"><img src="/images/login/ico_naver_s.png" alt="naver">네이버 아이디로 로그인</a>
				</div>
				<p class="login_msg">지누스 회원이라면 계정으로 로그인 하세요.</p>

				<div class="login_area">
					<div class="login_info">
						<input type="text" name="id" placeholder="아이디">
						<input type="password" name="pass" placeholder="비밀번호">
					</div>
					<button type="button" id="memberLoginBtn">로그인</button>
				</div>

				<div class="login_save">
					<input type="checkbox" id="saveId" name="" value="y">
					<label for="saveId">아이디 저장</label>
				</div>

				<div class="login_btns">
					<button type="button" onclick="javascript:location.href='/Member/join_agreement'">회원가입</button>
					<button type="button" class="btn_find_id">아이디 찾기</button>
					<button type="button" class="btn_find_pw">비밀번호 찾기</button>
				</div>
			</div>

			<!-- 비회원 주문조회 -->
			<h3 class="login_another_tit">비회원 주문조회</h3>
			<form action="#" class="login_another" method="post">
				<div class="login_another_info">
					<input type="text" name="" placeholder="주문자명">
					<input type="text" name="" placeholder="주문번호">
				</div>
				<button type="submit">확인</button>
			</form>

			<p class="login_caution">주문번호와 비밀번호를 잊으신 경우, 고객센터로 문의하여 주시기 바랍니다.</p>
		</div>
	</div>

	<!-- 아이디 찾기 -->
	<div class="login_find_id">
		<div class="login_box">
			<div class="login_top">
				<h3>회원 아이디 찾기</h3>
				<button type="button" class="login_close"><span class="blind">닫기</span></button>
			</div>

			<div class="login_area">
				<div class="login_info">
					<input type="text" name="name" placeholder="이름">
					<input type="text" name="email" placeholder="가입메일주소">
				</div>
				<button type="button" id="findedIdBtn">아이디 찾기</button>
			</div>

			<div class="login_btns">
				<button type="button" class="btn_find_pw">비밀번호 찾기</button>
				<button type="button" class="btn_login_main">로그인하기</button>
				<button type="button" onclick="javascript:location.href='/Member/join_agreement'">회원가입하기</button>
			</div>
		</div>
	</div>

	<!-- 비밀번호 찾기 -->
	<div class="login_find_pw">
		<div class="login_box">
			<div class="login_top">
				<h3>아이디 입력</h3>
				<button type="button" class="login_close"><span class="blind">닫기</span></button>
			</div>
			<p class="login_guide">비밀번호를 찾고자 하는 아이디를 입력해 주세요.</p>

			<div class="login_find_pw_info">
				<input type="text" name="id" placeholder="아이디">
				<p>아이디를 모르시나요?<a href="#" class="btn_find_id">아이디 찾기</a></p>
				<p class="error_message">회원정보를 찾을 수 없습니다.</p>
			</div>

			<button type="button" id="loginFindedBtn">다음</button>
		</div>
	</div>
	
	<!-- 찾은 아이디 -->
	<div class="login_find_id_ok">
		<div class="finded_id">
			회원님의 아이디는<br>
			<strong>???</strong> 입니다
		</div>

		<div class="login_btns">
			<button type="button" class="btn_find_pw">비밀번호 찾기</button>
			<button type="button" class="btn_login_main2">로그인하기</button>
			<button type="button" onclick="javascript:location.href='/Member/join_agreement'">회원가입하기</button>
		</div>
	</div>
	
	<!-- 찾은 비밀번호 -->
	<div class="login_find_pw_ok">
		<div class="finded_pw">
			회원님의 임시비밀번호는<br>
			<strong>???</strong> 입니다
		</div>

		<div class="login_btns">
			<button type="button" class="btn_login_main3">로그인하기</button>
			<button type="button" onclick="javascript:location.href='/Member/join_agreement'">회원가입하기</button>
		</div>
	</div>
</div>
