<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="shopping_write">
  <div class="goods">
    <h4>상품문의 쓰기</h4>
    <div class="goods_info">
      <div class="goods_img">
        <img src="/images/shopping/1000000369_main_074.jpg" alt="이미지">
      </div>
      <div>
        <strong>포레스트 워크S 하이브리드 스프링 매트리스 / 30cm</strong>
        <p>*항균효과가 뛰어난 Sanitized® 코퍼 메모리폼<br>
          *부드러운 퀼팅으로 마감된 타이트탑</p>
      </div>            
    </div>
  </div>

  <div class="contain">
    <div class="container">
      <table>
        <tr>
          <th>
            구분
          </th>
          <td class="select_wrap1 ">
            <button>상품</button>
            <ul class="option1">
              <li>상품</li>
              <li>배송</li>
              <li>반품/환불</li>
              <li>교환/변경</li>
              <li>기타</li>
            </ul>
          </td>
        </tr>
        <tr>
          <th>작성자</th>
          <td class="input_txt"><input type="text"></td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td class="input_txt"><input type="password"></td>
        </tr>
        <tr>
          <th>제목</th>
          <td class="input_txt">
            <input type="text" placeholder="제목 입력">
            
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td class="content">
            <div class="checkbox1_wrap">
              <label class="checkbox1">비밀글</label>
              <input type="checkbox">
            </div>
            <p>
              ※ 네이버 등 기타 온라인몰에서 구매하신 고객분들께서는 구매처에 문의를 부탁드립니다. <br>
              ※ 올려주신 상품후기는 사진과 함께 마케팅 용도로 활용될 수 있습니다. <br>
              ※ 상품후기를 올리실 경우 이에 동의하시는 걸로 간주됩니다.
            </p>
            <textarea></textarea>
          </td>
        </tr>
        <tr class="file_upload">
          <th>첨부파일</th>
          <td class="file_upload">
            <input type="text">
            <label for="input-file">
              찾아보기
            </label>
            <input type="file" id="input-file" style="display:none">
            <button type="button">+ 추가</button>
          </td>
        </tr>
      </table>
    </div>
    <div class="allow">
      <h6>비회원 개인정보 수집동의</h6>
        <p>- 수집항목: 성명, 이메일, 휴대전화번호, 전화번호 <br>
          - 수집/이용목적: 문의 접수 및 결과 회신 <br>
          - 이용기간: 원칙적으로 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. <br>
          단, 관계법령의 규정에 의하여 보전할 필요가 있는 경우 일정기간 동안 개인정보를 보관할 수 있습니다. <br>
          그 밖의 사항은 지누스 개인정보처리방침을 준수합니다.
        </p>
      <div >
        <div class="checkbox1_wrap">
          <label class="checkbox1">위 내용에 동의합니다.</label>
          <input type="checkbox">
        </div>
        <a href="#">전체보기></a>
      </div>
    </div>
  </div>
  <div class="bottom">
    <button>취소</button>
    <button>등록</button>
  </div>
</div>
>