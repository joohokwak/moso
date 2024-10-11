<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="content_admin">
	<div class="inner">
		<div class="route">Home  /  비즈니스  /  공지사항/공시</div>
		<div class="admin_notice_wrap">
			<div class="admin_notice_header">
				<h2>공지사항/공시</h2>
				<ul class="tabs">
					<li>
						<a href="#">공지사항/공시</a>
					</li>
				</ul>
			</div>
			<div class="search_box">
				<input type="text" class="text_search" placeholder="Search" title="검색어">
			</div>
			<table class="admin_notice_table">
				<colgroup>
					<col style="width: 10%">
					<col>
					<col style="width: 10%">
					<col style="width: 10%">
				</colgroup>
				<thead>
					<tr>
						<th>번호 no changed</th>
						<th>제목</th>
						<th>등록일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>5</td>
						<td><a href="#">신주발행공고(안)</a></td>
						<td>2022.11.08</td>
						<td>3499</td>
					</tr>
					<tr>
						<td>4</td>
						<td><a href="#">신주발행공고(안)</a></td>
						<td>2021.02.25</td>
						<td>13092</td>
					</tr>
					<tr>
						<td>3</td>
						<td>
							<a href="#">전자증권 전환대상 주권 등의 권리자 보호 안내</a>
							<img src="/images/notice/icon_board_attach_img.png">
						</td>
						<td>2019.09.10</td>
						<td>3499</td>
					</tr>
					<tr>
						<td>2</td>
						<td>
							<a href="#">지누스 중견기업확인서 (2020.03.31)</a>
							<img src="/images/notice/icon_board_attach_file.png">
						</td>
						<td>2019.07.19</td>
						<td>18869</td>
					</tr>
					<tr>
						<td>1</td>
						<td>
							<a href="#">지누스 중견기업확인서 (2020.03.31)</a>
							<img src="/images/notice/icon_board_attach_file.png">
						</td>
						<td>2019.07.19</td>
						<td>18869</td>
					</tr>
				</tbody>
			</table>
			
			<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
				<button class="btn write_btn">글쓰기</button>
			</div>
			
			<!-- 페이징 -->
			<div class="pagination">
				<span class="page_num active">1</span>
				<span class="page_num"><a href="#">2</a></span>
				<span class="page_num"><a href="#">3</a></span>
				<span class="page_num"><a href="#">4</a></span>
			</div>
		</div>
	</div>
</div>
