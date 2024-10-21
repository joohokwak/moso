<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="shopping_buy">
	<div class="shopping_goods">
		<div class="inner">
			<div class="goods_menu">
				<span>Home</span> <span class="slash">/</span>
				<div class="show_more">
					<a href="#" class="current_active">매트리스</a>
					<ul class="category">
						<li><a href="#" class="active">매트리스</a></li>
						<li><a href="#">침대프레임</a></li>
						<li><a href="#">토퍼</a></li>
						<li><a href="#">필로우</a></li>
						<li><a href="#">베딩</a></li>
						<li><a href="#">Sale</a></li>
					</ul>
				</div>
				<span class="slash2">/</span>
				<div class="show_detail">
					<a href="#" class="current_active">${dto.type}</a>
					<ul class="category">
						<li><a href="#" class="active">메모리폼 매트리스</a></li>
						<li><a href="#">스프링 매트리스</a></li>
						<li><a href="#">에어 매트리스</a></li>
						<li><a href="#">Luxe S collection</a></li>
						<li><a href="#">Forest Walk</a></li>
					</ul>
				</div>
			</div>
			<div class="goods">
								
				<div class="goods_img">
					<div class="main_img">
						<img src="/images/shopping/${dto.poster }"
							alt="메인 이미지">
					</div>
					<!-- Slider main container -->
					<div class="shopping_swiper">
						<div class="swiper">
							<!-- Additional required wrapper -->
							<div class="swiper-wrapper">
								<!-- Slides -->
								<c:forEach var="image" items="${images }">
									<div class="swiper-slide">
										<img src="/images/shopping/${image}" alt="상세보기">
									</div>
								</c:forEach>
							</div>
							<!-- If we need navigation buttons -->
							<div class="swiper-button-prev"></div>
							<div class="swiper-button-next"></div>
						</div>
					</div>
				</div>
				<div class="goods_select">
					<div class="info_txt">
						<h3>${dto.name }</h3>
						<div>
							<a href="#">리뷰 등록</a> <a href="#">상품 Q&amp;A</a>
						</div>
						<p>${dto.text }</p>
					</div>
					<div class="info_sub">
						<p>
							<strong>배송비</strong>무료
						</p>
						<p>
							<strong>배송방법</strong>택배
						</p>
						<p>
							<strong>제품코드</strong>
						</p>
					</div>
					<div class="select_detail">
						<div class="size">
							<strong>사이즈</strong>
							<div >
								<c:set var="size" value="${fn:split(dto.sizename, ',') }" />
								<c:forEach var="s" items="${size }">
									<c:if test="${s eq 'S' }"><a href="#">S</a></c:if>
									<c:if test="${s eq 'SS' }"><a href="#">SS</a></c:if>
									<c:if test="${s eq 'Q' }"><a href="#">Q</a></c:if>
									<c:if test="${s eq 'K' }"><a href="#">K</a></c:if>
									<c:if test="${s eq 'LK' }"><a href="#">LK</a></c:if>
								</c:forEach>
							</div>
						</div>
						<div class="delivery">
							<strong>설치배송여부</strong>
							<div>
								<a href="#">미신청</a> 
								<a href="#">수도권</a> 
								<a href="#">지방</a>
								<a href="#">제주도</a>
							</div>
						</div>
						<div class="price">
							<div class="price_txt">
								<strong>판매가</strong><span>제품 가격은 옵션 선택 후 확인하실 수 있습니다.</span>
							</div>
							<p class="total_price">
								<em>${dto.price } </em>원
							</p>
						</div>
						<div class="buttons">
							<a href="#" class="payment">결제하기</a>
							<a href="/Shop/cart?itemno=${dto.no }" class="cart" data-item="${dto }">장바구니</a>
							<a href="#" class="like" data-no="${dto.no }" data-islogin="${not empty member }">
								<img src="${(dto.id eq member.id) and (dto.no eq list.itemnum) ? '/images/shopping/wish_on.png' : '/images/shopping/wish_off.png'}" alt="좋아요">
							</a>
						</div>
						<div class="relation">
							<p>설치배송 신청 시 입력해주신 수령자 분의 연락처로 배송일 지정을 위한 해피콜이 진행됩니다. (주문 완료후 영업일 기준 D+1일 이내 해피콜 진행)</p>
							<p>지정된 배송일에 설치기사 님께서 주문주신 제품의 배송 및 설치 서비스를 제공해드립니다.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="shopping_view">
		<div class="inner">
			<div id="view_info">
				<div class="shopping_go_tab">
					<!-- db에 따라 리뷰, q&a 숫자 변경 설정할 것 -->
					<a href="#view_info" class="underline">상세정보</a>
					<a href="#view_review">리뷰 (${review[0].cnt })</a>
					<a href="#view_question">Q&amp;A(${qnaAll[0].cnt })</a>
					<a href="#view_service">배송/반품/설치</a>
				</div>
				<div class="ad_more">
					<img src="/images/shopping/spec_bg.png" alt="침대">
					<div class="ad_txt">
						<div class="txt_par">
							<p class="txt1">ADAPTIVE® FABRIC</p>
							<p class="txt2">Innovative +<br> Fresh</p>
							<p class="txt3">어떤 환경에서도<br>쾌적한 수면온도를</p>
							<p class="txt4">온도가 높아지면 섬유가 수축하여 열을 밖으로 분출하고, 온도가 낮아지면 열기를섬유 안에 가둬 쾌적한 수면 환경을 유지해 줍니다.</p>
						</div>
						<div class="txt_par">
							<p class="txt1">COPPER FOAM</p>
							<p class="txt2"> Antimicrobial +<br> Comfy </p>
							<p class="txt3">구리성분 함유로<br>쾌적하고 청결하게</p>
							<p class="txt4">세계 최대 검사 기관인 intertek으로부터 구리성분을 통한 항균 기능 96%을 입증 받았습니다.</p>

						</div>
						<div class="txt_par">
							<p class="txt1">FIRE RETARDANT</p>
							<p class="txt2">Safety +<br> Fire guard</p>
							<p class="txt3">화재로부터<br>안전한 난연 소재</p>
							<p class="txt4">지누스 파이어 가드는 화재 발생 시에 매트리스가 불에 타지 않도록 도와주는 안전소재로, 예측할 수 없는 화재로부터 안전하고 편안한 수면환경을 제공합니다.</p>
						</div>
						<div class="txt_par">
							<p class="txt1">CERTIPUR-US®</p>
							<p class="txt2">Tested +<br> Perfected</p>
							<p class="txt3">다양한 제품 테스트 인증을 통해<br>믿고 사용하는</p>
							<p class="txt4">난연 테스트, CertiPUR-US®, OEKO TEX, Sanitized®,SGS, 라돈 안전 테스트 등, 엄격한 자체 품질 규정에 따라 다양한 인증을 통해 제품의 안전성을 확보하였습니다.</p>
						</div>
					</div>
				</div>
				<div class="ad_image">
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut01.gif" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>내 몸을 편안히 받쳐줄 메모리폼</h4>
							<p>
								고밀도의 메모리폼은 수면 시 나의 몸의 굴곡대로<br> 포근히 감싸 안아 몸의 압력을 분산시켜주어<br>
								편안한 잠자리가 유지되도록 도와줍니다.
							</p>
						</div>
					</div>
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut02.png" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>청정 수면 환경, 코퍼 메모리폼</h4>
							<p>
								코퍼 메모리폼의 96%의 강력한 항균 효과와 폼에<br> 함유되어 있는 젤 알갱이로 늘 상쾌한 온도로 수면
								환경을 조절해 줍니다.
							</p>
						</div>
					</div>
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut03.png" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>온도 변화에 민감한 어댑티브 패브릭</h4>
							<p>
								어댑티브 패브릭은 유기적으로 주변환경의<br> 온도와 습도를 조절하여 늘 편안한 수면환경을 조성해 줍니다.
							</p>
						</div>
					</div>
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut04.gif" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>편안하고 풍성한 퀼팅</h4>
							<p>
								몸을 안아주듯 감싸는 파이버 퀼팅과 깔끔하면서<br> 과하지 않은 멜란지 그레이 컬러 원단을 통해<br>
								편안하고 고급스러운 침실생활을 경험해 보세요.
							</p>
						</div>
					</div>
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut05.png" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>푹신한 볼륨감을 느끼게 해줄 그랜드탑</h4>
							<p>
								포근한 메모리폼 매트리스 위에 부드러운 감촉과<br> 풍성한 볼륨을 제공하는 충전재를 더해 두배로<br>
								안락한 느낌을 주는 프리미엄 메모리폼 매트리스입니다.
							</p>
						</div>
					</div>
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut06.png" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>화재로부터 안전한 난연 소재</h4>
							<p>
								화재 발생 시에 매트리스가 불에 타지 않도록 도와주는 안전 소재로<br> 예측할 수 없는 화재로부터
								안전하고 편안한 수면환경을 제공합니다.
							</p>
						</div>
					</div>
					<div class="image">
						<div class="image_img">
							<img src="/images/shopping/img_cut07.png" alt="이미지">
						</div>
						<div class="image_desc">
							<h4>다양한 제품 안전 인증 테스트 완료</h4>
							<p>
								충전재와 배출 성분, 내구성 등 엄격한 인증 기준을 통과한<br> 검증된 매트리스입니다. <br>
								유해성분 없이 좋은 소재로만 만들어져 믿고 잠드실 수 있습니다.
							</p>
						</div>
					</div>
				</div>

				<div class="show_performance">
					<div class="per">
						<div>
							<img src="/images/shopping/ico_fa01.png" alt="효과/성능">
						</div>
						<strong>온도에 따라 변화하는 어댑티브 패브릭</strong>
						<p>온도가 높아지면 섬유가 수축하여 열을 밖으로 분출하고, 온도가 낮아지면 열기를 섬유 안에 가둬 쾌적한 수면 환경을 유지해 줍니다.</p>
					</div>
					<div class="per">
						<div>
							<img src="/images/shopping/ico_fa02.png" alt="효과/성능">
						</div>
						<strong>구리 성분이 포함된 코퍼 메모리폼</strong>
						<p>세계 최대 검사 기관인 intertek으로부터 구리성분을 통한 항균 기능 96%을 입증 받았습니다.</p>
					</div>
					<div class="per">
						<div>
							<img src="/images/shopping/ico_fa03.png" alt="효과/성능">
						</div>
						<strong>하이브리드 매트리스</strong>
						<p>폼 매트리스의 안락함과 스프링 매트리스의 탄탄함을 동시에 경험할 수 있습니다.</p>
					</div>
					<div class="per">
						<div>
							<img src="/images/shopping/ico_fa04.png" alt="효과/성능">
						</div>
						<strong>프리미엄급 인케이스드 폼</strong>
						<p>고밀도폼이 포켓 스프링의 4면과 상/하단을 모두 감싸 주어 더욱 견고하게 지지해주며, 어느 위치에서도 편안하고 넓은 수면 면적을 제공합니다.</p>
					</div>
					<div class="per">
						<div>
							<img src="/images/shopping/ico_fa05.png" alt="효과/성능">
						</div>
						<strong>지누스 파이어 가드</strong>
						<p>화재 발생 시에 매트리스가 불에 잘 타지 않도록 도와주는 특수한 안전 소재인 지누스 파이어 가드 소재가 사용되었습니다.</p>
					</div>
					<div class="per">
						<div>
							<img src="/images/shopping/ico_fa06.png" alt="효과/성능">
						</div>
						<strong>Sanitized® 항균 기능</strong>
						<p>Sanitized AG로부터 항균 기능을 인증 받은 폼을 사용하였습니다. Sanitized® 항균 처리 기술은 위생 기능과 소재 보호를 통해 제품의 안전성을 보증합니다.</p>
					</div>
				</div>

				<div class="single_content">
					<img src="/images/shopping/firmness_3step.png" alt="단단함 정도">
				</div>

				<div class="layering">
					<h3>Layering</h3>
					<p>매트리스 코어가 어떻게 구성되는지 확인하세요.</p>
					<img src="/images/shopping/img_layer01.png" alt="레이어링">
				</div>

				<div class="materials">
					<div class="mat_inner">
						<div class="mat">
							<div>
								<img src="/images/shopping/ico_sp01.png" alt="재료">
							</div>
							<p class="green">Green Tea</p>
							<p class="gray">천연 녹차 추출물 성분이 항상 쾌적한 상태로 유지</p>
						</div>
						<div class="mat">
							<div>
								<img src="/images/shopping/ico_sp02.png" alt="재료">
							</div>
							<p class="green">BioFoam</p>
							<p class="gray">천연 식물성유 함유로 안전하게 사용 가능</p>
						</div>
						<div class="mat">
							<div>
								<img src="/images/shopping/ico_sp03.png" alt="재료">
							</div>
							<p class="green">ActivCharcoal</p>
							<p class="gray">흡착성 강한 활성탄이 안전하고 효율적으로 악취 제거 및 습기 흡수</p>
						</div>
						<div class="mat">
							<div>
								<img src="/images/shopping/ico_sp04.png" alt="재료">
							</div>
							<p class="green">Memory Foam</p>
							<p class="gray">체형과 체중에 맞게 최적화되는 편안함</p>
						</div>
						<div class="mat">
							<div>
								<img src="/images/shopping/ico_sp05.png" alt="재료">
							</div>
							<p class="green">CertiPUR-US®</p>
							<p class="gray">내구성, 성능, 소재 안전성 검사인 미국 CertiPUR-US 인증 통과</p>
						</div>
					</div>
				</div>

				<div class="detail_more_box">
					<div class="box_inner">
						<h5>포레스트 워크S 하이브리드 스프링 매트리스 30cm의 더 많은 정보를 원하시나요?</h5>
						<div class="line">
							<p class="sub_tit">MATERIALS</p>
						</div>
						<div class="more_size">
							<img src="/images/shopping/img_size.png" alt="사이즈">
						</div>
						<div class="table">
							<h6>
								사이즈<i></i>
							</h6>
							<div>
								<p>Super Single</p>
								110 x 200 x 30cm, 31kg
							</div>
							<div>
								<p>Queen</p>
								150 x 200 x 30cm, 44kg
							</div>
							<div>
								<p>Large King</p>
								180 x 200 x 30cm, 52kg
							</div>
							<h6>소재</h6>
							<div>
								<p>내장재 : 코퍼 메모리폼, 폴리우레탄폼, 독립 스프링</p>
								커버 : 폴리에스터
							</div>
						</div>
						<p class="last_txt">※ 오차범위 : ±2cm</p>
					</div>
				</div>
				<div class="view_benefit">
					<div class="benefit">
						<img src="/images/shopping/ico_deli01.png" alt="무료배송">
						<h6>무료 배송</h6>
						<p>
							지누스는 무료로 택배를<br> 배송해드립니다.<br> (제주 및 도서산간지역 배송 시에는<br>
							30,000원의 배송비가 발생합니다.)
						</p>
					</div>
					<div class="benefit">
						<img src="/images/shopping/ico_deli02.png" alt="무료배송">
						<h6>5년 품질 보증</h6>
						<p>
							지누스는 제조 시 발생한 결함에 대해서<br> 구입일로부터 5년간 보증을 제공합니다.
						</p>
					</div>
					<div class="benefit">
						<img src="/images/shopping/ico_deli03.png" alt="무료배송">
						<h6>설치 배송</h6>
						<p>
							설치배송을 선택하시면 지누스<br>설치배송 기사님이 직접<br>원하시는 위치에 설치해 드립니다.<br>(설치배송비는
							지역마다 상이합니다.)
						</p>
					</div>
				</div>
			</div>
			<div id="view_review" class="shopping_borad" data-itemno="${dto.no }">
				<div class="shopping_go_tab">
					<a href="#view_info">상세정보</a>
					<a href="#view_review" class="underline">리뷰 (${review[0].cnt })</a>
					<a href="#view_question">Q&amp;A(${qnaAll[0].cnt })</a>
					<a href="#view_service">배송/반품/설치</a>
				</div>
				<div class="board_inner">
					<div class="board_top">
						<h3>리뷰</h3>
						<div class="board_btn">
							<button class="board_all">리뷰 전체보기</button>
							<button class="board_write">리뷰 등록</button>
						</div>
					</div>
					<table class="board_body">
						<c:forEach var="rv" items="${review }">
							<tbody class="display_view">
								<tr>
									<td width="110">
										<span class="rating">
											<c:forEach var="rat" begin="1" end="${rv.rating }">
												<img src="/images/shopping/star-fill.png" alt="별점">
											</c:forEach>
											<c:forEach var="rat" begin="1" end="${5 - rv.rating }">
												<img src="/images/shopping/star-bg.png" alt="별점">
											</c:forEach>
										</span>
									</td>
									<td class="board_content">
										<a href="#">${rv.title }</a>
										<span><img src="/images/shopping/icon_board_attach_file.png"alt="file"></span>
									</td>
									<td width="112" align="center">
										<p>${rv.writer }</p>
									</td>
									<td width="112">
										<p class="center">${rv.regdate }</p>
									</td>
								</tr>
								<tr class="display none">
									<td></td>
									<td class="board_content">
										<div class="board_main_content">
											${rv.content }
										</div>
										<div>
											<p><span><img src="/images/shopping/icon_board_attach_file.png"alt="file"></span><strong>첨부파일</strong><i class="file_name">jpg</i></p>
										</div>
									</td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<div class="pagination">
						${paging }
					</div>
				</div>
			</div>
			<div id="view_question" class="shopping_borad">
				<div class="shopping_go_tab">
					<a href="#view_info">상세정보</a>
					<a href="#view_review">리뷰 (${review[0].cnt })</a>
					<a href="#view_question" class="underline">Q&amp;A (${qnaAll[0].cnt })</a>
					<a href="#view_service">배송/반품/설치</a>
				</div>
				<div class="board_inner">
					<div class="board_top">
						<h3>Q&amp;A</h3>
						<div class="board_btn">
							<button class="board_all">고객센터</button>
							<button class="board_write qna" type="button" data-no="${dto.no }">Q&amp;A 등록</button>
						</div>
					</div>
					<table class="board_body">
						<colgroup>
							<col>
							<col style="width: 112px;">
							<col style="width: 112px;">
							<col style="width: 112px;">
						</colgroup>
						<c:forEach var="qna" items="${qnaAll }">
							<tbody class="display_view">						
								<tr>
									<td class="board_content">										
										<span data-sr='${qna.secrite }'>
											<img src="/images/shopping/icon_board_secret.png" alt="비밀글">
										</span>
										<a href="#">${qna.title }</a>
									</td>
									<td>
										<p>${qna.writer }</p>
									</td>
									<td>
										<p class="center">${qna.regdate }</p>
									</td>
									<td>
										<p class="center">답변완료</p>
									</td>
								</tr>
								<tr >
									<td colspan='3' class="board_content display_none">
										<span><img src="/images/shopping/q.png" alt="질문"></span>
										<p>${qna.question }</p>
									</td>
									<td class="display_none" >
										<div class="qna_btn">
											<a href="/Shop/write?itemno=${dto.no }&qnano=${qna.no }">수정</a>
											<a href="#">삭제</a>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan='4' class="board_content display_none">
										<span><img src="/images/shopping/a.png" alt="답변"></span>
										<p>${qna.answre }</p>
									</td>
								</tr>
							</tbody>
						</c:forEach>

					</table>
					
					<!-- 페이징 -->
					<div class="pagination">
						
					</div>
				</div>
			</div>
			
			
			<div id="view_service">
				<div class="shopping_go_tab">
					<a href="#view_info">상세정보</a>
					<a href="#view_review">리뷰 (${review[0].cnt })</a>
					<a href="#view_question">Q&amp;A (${qnaAll[0].cnt })</a>
					<a href="#view_service" class="underline">배송/반품/설치</a>
				</div>
				<div class="service_info">
					<h4>배송/반품/교환</h4>
					<p class="sub_tit">배송안내</p>
					<p>
						오후 3시까지 주문하시면 익일 출고됩니다. (주말, 공휴일 제외)<br> 설치배송 미신청 시 택배로 출고되어,
						제품 배송일/수령일은 지정이 불가한 점 참고해주세요.<br> ※ 엘레베이터가 없는 3층 이상의 설치장소일
						경우, 사다리차 사용 등 별도 비용이 발생할 수 있습니다.<br>(제품 출고 이후 해당 비용 미수긍 시 해당
						건물 주소의 1층으로 배송될 수 있습니다.)
					</p>
					<p>&nbsp;</p>
					<p class="sub_tit">교환 및 반품 안내</p>
					<p>
						교환 및 반품 신청은 제품 수령일로부터 7일 이내, 구매 영수증 및 불량 제품 증빙 사진과 함께 신청해주세요.<br>
						제품 하자 및 불량으로 인한 반품 및 교환 배송비는 판매처에서 부담합니다.<br> 단순 변심 반품의 경우 왕복
						배송비가 청구되며, 상품마다 다르게 부과됩니다.<br> 하단의 표를 참조해주세요.
					</p>
					<p>&nbsp;</p>
					<table class="table1">
						<thead>
							<tr>
								<th>품목</th>
								<th>반품배송비(왕복)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>매트리스</td>
								<td>30,000원 ~ 50,000원</td>
							</tr>
							<tr>
								<td>프레임</td>
								<td>30,000원 ~ 50,000원</td>
							</tr>
							<tr>
								<td>토퍼 및 기타</td>
								<td>8,000원 ~ 30,000원</td>
							</tr>
						</tbody>
					</table>
					<p>&nbsp;</p>
					<p>배송 당일에 배송일과 모델을 변경하거나 제품 취소 시 부과되는 별도 비용, 수취인 부재 시나 거부 또는
						주소지 및 연락처 오류로 인한 반송 배송비는 고객님 부담입니다.</p>
					<p>&nbsp;</p>
					<p>
						1. 회수된 제품에 이상이 있는 경우 (개봉 후 사용, 제품 오염 및 훼손, 세탁)<br>2. 제품 박스 및
						비닐 포장이 훼손되거나 분실한 경우 (최초 수령한 박스 및 포장재 동봉)<br>3. 반품 배송비가 확인되지
						않은 경우<br>4. 상품 수령일로부터 7일이 경과한 경우<br>5. 매트리스/토퍼 제품 등의 압축
						포장을 이미 개봉했을 경우<br>6. 프레임 제품 등의 조립을 이미 마쳤을 경우<br>7. 매트리스
						제품은 커버의 탈착이 불가합니다.<br>8. 나사 및 볼트, 조립하는데 사용하는 라쳇 및 우든 슬랫, 고무
						패킹 등 분실 또는 조립 불량으로 인한 파손의 경우<br>9. 단순 변심으로 인한 출고된 제품의 교환 요청의
						경우
					</p>
					<h4>설치배송 서비스</h4>
					<p class="sub_tit">유의사항</p>
					<p>
						원활한 설치를 위해 설치공간 마련해주세요.<br>기존 가구 내림서비스를 요청하실 경우, 1층까지 이동해드리며
						별도 비용이 발생합니다.<br>주문일자 기준으로 수도권 3일~5일, 지방 7일~10일 이내 설치서비스를
						제공해드립니다.<br>일요일, 공휴일에는 배송이 불가합니다.<br>배송지역에 따라 가능한 배송일
						상이하여 해피콜을 통해 협의해주세요.<br>엘레베이터가 없는 3층 이상의 설치장소일 경우, 사다리차 사용 등
						별도 비용이 발생할 수 있습니다.<br>부득이하게 설치 공간이 없는 경우 설치가 불가할 수 있습니다.
					</p>
					<p>&nbsp;</p>
					<table class="table2">
						<colgroup>
							<col style="width: 30%;">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>지역 구분</th>
								<th>지역</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>매트리스</td>
								<td class="pad">서울, 인천, 경기</td>
							</tr>
							<tr>
								<td>프레임</td>
								<td class="pad">서울, 인천, 경기를 제외한 모든 지역</td>
							</tr>
							<tr>
								<td>토퍼 및 기타</td>
								<td class="pad">제주 전 지역</td>
							</tr>
							<tr>
								<td>토퍼 및 기타</td>
								<td class="pad_all">인천 영종도, 경남 거제도 : 20,000원 (VAT별도)<br>
									전남 신안군 암태도, 자은도, 팔금도, 안좌도, 자라도, 추포도 : 30,000원 (VAT별도)
								</td>
							</tr>
						</tbody>
					</table>
					<p>&nbsp;</p>
					<p class="sub_tit">안내사항</p>
					<p>
						1. 엘리베이터가 없는 건물의 층수에 따라 양중비와 사다리차 비용이 추가 될 수 있으며, 해당 비용은 설치시공 기사님께
						직접 결제해주시면 됩니다.<br> 2. 엘리베이터가 없는 2층부터 양중비가 발생됩니다.<br> 3.
						엘리베이터에 제품이 들어가지 않거나 구조상 제품 진입이 불가능 할 경우 사다리차 비용이 추가될 수 있습니다.
					</p>
				</div>
			</div>
		</div>
	</div>
</div>