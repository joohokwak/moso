<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="quiz_container">
	<div id="quiz_content">
		<div class="brand_menu">
			<ul class="tabs">
				<li><a href="#">이벤트</a></li>
				<li><a href="#">매거진</a></li>
				<li><a href="#">지누스 스토리</a></li>
				<li><a href="#">Mattress in a Box</a></li>
				<li><a href="#">제품안전인증</a></li>
				<li><a href="#" class="on">내게 맞는 매트리스 찾기</a></li>
				<li><a href="#">현대백화점 그룹</a></li>
			</ul>
		</div>
		<div class="inner">
			<div class="route">
				<span>
					<a href="/">Home</a>
				</span>
				<span>브랜드</span>
				<span>내게 맞는 매트리스 찾기</span>
			</div>
			<div class="content_wrap">
				<div class="question">
					<h2>인생 매트리스, 지금 만나러 갑니다.</h2>
					<p>자, 지금부터 매트리스 퀴즈를 풀어보겠습니다! 점수는 걱정하지 마세요. </p>
					<p>정답과 오답은 없고, 당신에게 딱 맞는 매트리스만이 있습니다.</p>
					<p>지누스는 잠을 사랑하는 모든 분들 앞에 평등합니다. (서서 자는 분만 빼고요.)</p>
				</div>
				<form action="/Quiz/result" method="post" name="quizFrm" onsubmit="return quizValidate();">
					<div class="quiz_answer">
						<h3>1. 주로 어떤 자세로 잘 때가 가장 편한가요?</h3>
						<div class="item">
							<input type="radio" name="Q1" id="quiz1_1" value="1">
							<label for="quiz1_1">
								<span>똑바로<br>누워서 자요</span>
							</label>
							<input type="radio" name="Q1" id="quiz1_2" value="2">
							<label for="quiz1_2">
								<span>엎드려 자요</span>
							</label>
							<input type="radio" name="Q1" id="quiz1_3" value="3">
							<label for="quiz1_3">
								<span>옆으로 자요</span>
							</label>
							<input type="radio" name="Q1" id="quiz1_4" value="4">
							<label for="quiz1_4">
								<span>뒤척임이<br>많아요</span>
							</label>
						</div>
					</div>
					<div class="quiz_answer">
						<h3>2. 혹시 수면중 어떤 문제가 있나요?</h3>
						<div class="item">
							<input type="radio" name="Q2" id="quiz2_1" value="1">
							<label for="quiz2_1">
								<span>몸에 열이<br>많아요</span>
							</label>
							<input type="radio" name="Q2" id="quiz2_2" value="2">
							<label for="quiz2_2">
								<span>자고 나면<br>온몸이 아파요</span>
							</label>
							<input type="radio" name="Q2" id="quiz2_3" value="3">
							<label for="quiz2_3">
								<span>아무 문제<br>없어요</span>
							</label>
						</div>
					</div>
					<div class="quiz_answer">
						<h3>3. 가장 선호하는 매트리스의 강도는 어느 정도인가요?</h3>
						<div class="item">
							<input type="radio" name="Q3" id="quiz3_1" value="1">
							<label for="quiz3_1">
								<span>푹신하고<br>부드러운<br>(Plush, Soft)</span>
							</label>
							<input type="radio" name="Q3" id="quiz3_2" value="2">
							<label for="quiz3_2">
								<span>부드럽지만<br>안정적인<br>(Medium)</span>
							</label>
							<input type="radio" name="Q3" id="quiz3_3" value="3">
							<label for="quiz3_3">
								<span>탄탄하게<br>지지하는<br>(Supportive)</span>
							</label>
						</div>
					</div>
					<div class="quiz_answer">
						<h3>4. 누가 사용할 매트리스인가요?</h3>
						<div class="item">
							<input type="radio" name="Q4" id="quiz4_1" value="1">
							<label for="quiz4_1">
								<span>커플</span>
							</label>
							<input type="radio" name="Q4" id="quiz4_2" value="2">
							<label for="quiz4_2">
								<span>나 혼자</span>
							</label>
						</div>
					</div>
					<div class="quiz_btn_wrap">
						<button class="result_btn">결과보기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
