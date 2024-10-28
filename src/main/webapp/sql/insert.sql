-- << MEMBER >> --
INSERT INTO MEMBER 
VALUES ('admin', '1', '관리자', 'admin@naver.com', '01012345678', '024567890', '12345', '서울시 종로구', '종각역123', 'M', '19900101', 'Y');
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << FAQ >> --
-- 주문/배송
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '제품 구매 시 배송 기간은 어떻게 되나요?', '주문/배송', '지누스 온라인몰에서 구입 시, 재고가 보유된 상품에 한하여 주문 기준 익일 출고됩니다.<br>
배송 기간은 지역 및 경동택배 영업소마다 상이하며 주문 시점부터 수령시까지 평균적으로 3일 정도 소요됩니다.<br>
(주말 및 공휴일 제외, 재고 있는 제품에 한하여 적용되요!)', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '제주도 및 도서 산간 지역도 배송이 가능한가요?', '주문/배송', '수령지가 제주도일 경우 추가 배송비가 30,000원 부과 되며, 결제 시 자동으로 부과 됩니다.<br>
오지 혹은 섬과 같은 도서 산간 지역에 한하여 배송이 제한되거나 별도의 추가 비용이 발생할 수 있습니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '주문 취소는 어떻게 하나요?', '주문/배송', '주문 취소 신청은 주문한 상품이 발송되기 이전 구매 의사를 철회하는 것이므로 반드시 <결제완료> 상태에서만 즉시 취소가 가능합니다.
결제 완료 이후 상품 발송을 위한 배송 작업이 이미 시작되었을 경우에는 취소 요청이 거부되며, 이 경우에는 반품으로 간주되므로 지누스 고객센터로 문의 주시면 주문 취소 가능 여부와 자세한 절차를 안내해 드리겠습니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '배송지 주소를 변경하고 싶어요!', '주문/배송', '배송지 혹은 수령하시는 분의 연락처 등 기본적인 배송 정보 변경은 정확한 변경 처리를 위해 고객센터로 연락 주시면 감사하겠습니다.
* 지누스 고객센터의 운영 시간은 평일 오전 9시부터 오후 6시까지(점심시간 12시~13시 제외)이며, 주말 및 공휴일은 휴무입니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '배송일 지정이 따로 가능한가요?', '주문/배송', '택배의 경우, 부득이하게 배송일은 별도로 지정이 불가합니다. 지누스 온라인몰에서 구입 시 전 상품 모두 주문 기준 익일 출고됩니다. (주말 및 공휴일 제외, 재고 있는 상품에 한함) 배송 기간은 지역 및 관할 영업소에 따라 평균 1~2일정도 소요되며, 상품 배송은 주문 시점부터 수령일까지 평균 3일 정도 소요 되오니 참고 부탁 드립니다.
<br>설치배송의 경우, 주문시 영업일 기준 2일 이내 해피콜이 진행됩니다. 해피콜을 받으셨다면 물류센터와 배송일자 조율를 하시어 주문 배송일 지정이 가능합니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '설치배송도 가능한가요?', '주문/배송', '한번에 많은 제품을 구매하거나 설치할 시간이 부족한 분들에게 희소식!<br>
상품페이지에서 원하는 상품과 설치배송여부를 체크하면 지누스 배송기사님이 고객님의 집에 직접 찾아갑니다.<br>
자세한 내용은 아래 안내문을 참고해주세요.<br><br>
설치배송 서비스<br><br>
원활한 설치를 위해 설치공간 마련해주세요.<br>
기존 가구 내림서비스를 요청하실 경우, 1층까지 이동해드리며 별도 비용이 발생합니다.<br>
주문일자 기준으로 수도권 3일~5일, 지방 7일~10일 이내 설치서비스를 제공해드립니다.<br>
일요일, 공휴일에는 배송이 불가합니다.<br>
배송지역에 따라 가능한 배송일 상이하여 해피콜을 통해 협의해주세요.<br>
엘레베이터가 없는 3층 이상의 설치장소일 경우, 사다리차 사용 등 별도 비용이 발생할 수 있습니다.<br>
부득이하게 설치 공간이 없는 경우 설치가 불가할 수 있습니다.', SYSDATE);

-- 제품문의
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '매트리스는 온열기구와 함께 사용이 가능한가요?', '제품문의', '온열기구 사용은 40도 미만의 온도로 4시간 미만으로 사용 가능합니다. 사용 시 온도에 따른 황변현상이 일어날 수 있으나 이는 자연스러운 현상으로 상품 퀄리티와는 무관하니 안심하고 사용하셔도 됩니다. 혹시라도 이런 점이 신경 쓰이시면 온열기구와 매트리스 사이에 담요 등을 깔아 사용하시면 황변 현상 예방에 도움이 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '매트리스 커버도 포함되어 있나요?', '제품문의', '판매하는 매트리스는 커버 탈착이 안되는 일체형 제품입니다. 커버는 포함되어 있지 않으며, 고객님께서 별도로 구매하신 후 씌워서 사용하셔야 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '매트리스 커버는 분리 및 탈착이 가능한가요?', '제품문의', '지누스의 모든 매트리스는 생산 공정 상 커버를 벗길 수 없게 제작 되었습니다. (지퍼 고리 잘림)
임의로 탈거하여 커버 세탁 시 사이즈가 줄어들수도 있으며, 다시 커버를 씌우기도 어려우므로 고객님께서 별도의 커버 구매 후 매트리스에 씌우셔서 관리하시기를 권장해 드립니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '매트리스 냄새를 빨리 제거하는 방법이 있나요?', '제품문의', '압축된 매트리스를 개봉하면 제품에서 새 제품 냄새가 날 수 있습니다. 냄새는 인체에 무해하며 최대 2주 안으로 자연스럽게 사라집니다. 새 차를 구매했을 때 내부에서 새 차 냄새가 나는 것과 같은 원리입니다. 초기 개봉 시 환기가 잘 되는 곳에 두시면 냄새가 더욱 빠르게 사라집니다. 혹은 실내 온도를 높여 매트리스 내에 있는 냄새 입자를 바깥으로 빼주신 뒤에 통풍이 잘 되는 곳에 환기 시켜 주시거나 공기청정기를 이용한 환기 부탁 드립니다.
발생하는 냄새는 메모리폼 고유의 냄새로 인체에는 전혀 무해하므로 걱정하지 않으셔도 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '수령하고 72시간 이후에 개봉할 경우 상품에 이상이 있나요?', '제품문의', '압축 포장은 가급적 72시간 이내에 개봉하시는 것을 추천 드리오나 부득이한 사정으로 72시간 경과 후 개봉하실 경우 매트리스가 완벽하게 복원하는데는 시간이 조금 더 소요될 수 있으니 참고 부탁드려요!', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '이사할 때 다시 압축 할수 있는 압축팩이 따로 있나요?', '제품문의', '별도 압축팩은 없으며 제품 특성 상 재압축도 불가합니다. 복원이 완벽하게 된 매트리스는 기존의 타사 매트리스와 같이 이동 및 운반하셔야 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '개봉 후 매트리스의 두께가 완전히 부풀지 않았습니다.', '제품문의', '매트리스의 완벽한 복원은 통상적으로 72시간 정도 소요된다고 안내 드리고 있지만 메모리폼 자체가 주변 온도의 영향을 받는 부분도 있기 때문에 경우에 따라서 복원되는데 최대 7~14일 이상 소요되는 케이스도 있습니다.
해당 기간 동안은 제품 이용을 최대한 삼가 부탁 드리며 이후로도 복원이 안될 경우 지누스 고객센터로 연락 주시면 도움을 드리도록 하겠습니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '그린티 라인과 스마트텍 라인의 차이점이 무엇인가요?', '제품문의', '에센스 그린티 라인과 스마트텍 얼티마 라인 모두 내부 폼의 구성 및 성분의 함유랑이 다소 비슷하나 큰 차이점은 매트리스 겉 부분 소재입니다.
스마트텍 얼티마 라인의 경우 매트리스 겉커버 소재에 천연 모시가 함유되어 있어 수면 중 발생하는 땀과 열을 감소시켜주고 통풍을 원활하게 하는 기능이 보다 더 업그레이드 되었습니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '지누스 상품은 라돈 이슈가 없나요?', '제품문의', '지누스 매트리스는 라돈과는 전혀 관련이 없는 제품입니다. 라돈을 방출하는 모자나이트 성분이 함유된 음이온파우더를 전혀 취급하지 않습니다. 지누스는 각 국가, 기관이 제시한 실내 라돈 안전 권장 기준에 크게 밑도는 수치를 받아 적합 판정을 받았습니다. 따라서 제품의 안전성에 대해서는 사용하시는 데 걱정하지 않으셔도 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '메모리폼 매트리스는 신체에 열이 많거나 더위를 많이 타는 사람이 사용하기 불편한가요?', '제품문의', '간혹 메모리폼 매트리스를 사용 할 경우 신체가 매트리스에 푹 감기는 느낌때문에 답답하거나 덥다고 느끼시는 고객분들도 있으나, 에어컨 및 실내 공조가 잘 되어 있는 경우에는 크게 불편함을 느끼시지 않습니다.
또한 쿨링 효과가 있는 기능성 커버 및 이불과 함께 사용하시면 더위로 인한 불편함이 어느정도 해소될 수 있으니 참고 부탁 드려요!', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '메모리폼 매트리스와 스프링 매트리스의 차이점은 무엇인가요?', '제품문의', '메모리폼 매트리스는 푹신하고 신체에 감기는 느낌이 들며 대중적인 스프링 매트리스 특유의 단단함을 싫어하시는 분들에게 적합합니다. 어떤 자세로 누워도 체형에 맞게 몸을 감싸기 때문에 결림 없이 편하게 잘 수 있으며 따뜻하고 포근한 느낌을 선호하시면 메모리폼 매트리스를 추천해 드립니다.
반면 스프링 매트리스는 탄탄하게 받쳐주는 느낌이 들며 몸 전체를 살짝만 감싸고 일정한 압력 이상으로는 잠기지 않게 신체를 지지해줍니다. 침대 위에서 책을 읽는 등 일상의 많은 시간을 보내는 경우에는 스프링 매트리스를 추천해 드립니다.
두 제품 중, 고객님의 신체 컨디션 및 개인적인 취향에 부합하는 매트리스를 선택하셔서 구매해 주시면 되겠습니다. 제품에 대해 보다 더 심층적인 상담이 필요하시면 언제든지 지누스 고객센터로 연락 주세요! 친절하게 도움을 드리겠습니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '매트리스를 양면으로 사용할 수 있나요?', '제품문의', '지누스에서 판매중인 모든 매트리스는 내장재가 위, 아래 다르게 레이어드 되어 있기 때문에 뒤집어 사용시 불편함을 느끼실수도 있습니다. 따라서 가급적이면 정방향 사용을 권장하고 있습니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '베개의 경우 통째로 세탁이 가능한가요?', '제품문의', '지누스에서 판매하는 베개는 전 제품 메모리폼 제품입니다. 메모리폼의 경우 물이 유입되면 다시 빠져 나오지 못하는 구조로 되어 있습니다. 따라서 커버만 세탁 부탁 드리며 베개는 1~2주에 한 번씩 방망이 등을 이용하여 두들겨서 먼지를 제거하여 주시고, 통풍이 잘 되는 그늘에서 말린 후 베개 커버를 씌워서 사용하시면 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '매트리스를 놓을 때 머리와 다리 부분이 정해져 있나요?', '제품문의', '지누스에서 판매중인 모든 매트리스와 토퍼 제품은 머리 부분과 발 부분의 구분이 없는 상품입니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '토퍼는 커버의 탈착 및 세탁이 가능한가요?', '제품문의', '퀼팅이 없는 매트리스 및 토퍼류의 경우 커버 탈착이 가능하며, 커버의 세탁 방법은 라벨에 상세하게 안내되어 있습니다.<br>
(약 30도 온도에서 중성세제로 손세탁/ 140~160도 내에서 다림질 가능/ 염소,산소 표백제 사용 금지/ 그늘에 뉘어서 말릴 것)', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '메모리폼 베개의 경우 통째로 세탁이 가능한가요?', '제품문의', '지누스에서 판매하는 베개는 전 제품 메모리폼 제품입니다. 메모리폼의 경우 물이 유입되면 다시 빠져 나오지 못하는 구조로 되어 있습니다. 따라서 커버만 세탁 부탁 드리며 베개는 1~2주에 한 번씩 방망이 등을 이용하여 두들겨서 먼지를 제거하여 주시고, 통풍이 잘 되는 그늘에서 말린 후 베개 커버를 씌워서 사용하시면 됩니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '메모리폼 매트리스나 필로우가 젖었을 경우 어떻게 하나요?', '제품문의', '소재가 젖어 있을 경우 메모리폼 특유의 지지력과 체중을 견디는 힘이 줄어듭니다. 그러므로 소재가 젖지 않도록 각별히 주의하셔야 합니다. 부득이하게 젖었을 경우에는 스폰지나 천으로 물기를 닦아내어 공기가 통하도록 해서 습기를 건조시켜 주시고, 지누스 매트리스를 오래 사용하기 위해서 가급적이면 방수 커버를 사용해 주세요.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '지누스 프레임 우든슬팻(갈빗살)의 포름알데히드 방출량은 어떻게 되나요?', '제품문의', '프레임 우든슬랫(갈비살) 부분의 포름알데히드 방출량은 0.03ppm 입니다. 참고로 국내 포름알데히드 P2 통과 기준은 0.05ppm 이며, 안전하게 사용 가능하십니다.', SYSDATE);

-- 기타
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '지누스 고객센터 운영 시간은 어떻게 되나요?', '기타', '지누스 고객센터의 공식 운영 시간은 평일 오전 10시부터 오후 5시까지이며 주말 및 공휴일은 휴무입니다. (점심시간: 11:45-12:45)
지누스는 언제나 고객님의 편의를 위해 노력하고 있습니다. 감사합니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '운영중인 오프라인 매장이 따로 있나요?', '기타', '1. 롯데마트 잠실점<br>
1) 주소: 서울 송파구 올림픽로 240 롯데마트 잠실점 3층<br>
2) 영업시간: 오전 10:00-오후 10:00<br>
3) 운영기간: 상시 운영<br><br>
2. 롯데아울렛 고양점<br>
1) 주소: 경기 고양시 덕양구 권율대로 420 P1F(고양 이케아)<br>
2) 영업시간: 오전 10:00-오후 10:00<br>
3) 운영기간: 상시 운영<br><br>
3. 롯데백화점 청량리점<br>
1) 주소: 서울 동대문구 왕산로 214 6층<br>
2) 영업시간: 오전 10:30-오후 08:00<br>
3) 운영기간: 상시 운영<br><br>
4. 롯데백화점 김포공항점<br>
1) 주소: 서울 강서구 하늘길 77 롯데백화점 김포공항점 5층<br>
2) 영업시간: 오전 10:30-오후 08:00<br>
3) 운영기간: 상시 운영<br><br>
5. 롯데백화점 광복점<br>
1) 주소: 부산 중구 중앙대로 2 롯데백화점 광복점 9층<br>
2) 영업시간: 오전 10:30-오후 08:00<br>
3) 운영기간: 상시 운영<br><br>
6. 롯데백화점 창원점<br>
1) 주소: 경상남도 창원시 성산구 중앙대로 124 롯데백화점 창원점 지하 1층<br>
2) 영업시간: 오전 10:30~오후 8:00<br>
3) 운영기간: 상시 운영<br><br>
7. 롯데백화점 노원점<br>
1) 주소: 서울특별시 노원구 동일로 1414 (상계2동 713) 5층<br>
2) 영업시간: 오전 10:30~오후 8:00<br>
3) 운영기간: 상시 운영<br><br>
(*) 휴무 및 공휴일은 각 입점 지점과 동일합니다. (별도 확인 필요)', SYSDATE);

-- 교환/반품
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '제품 사용중 신체 통증을 느낄 경우 반품이 가능한가요?', '교환/반품', '지누스 제품의 경우 압축 포장을 제거 하면, 재판매가 불가능한 상품이어서 개인의 취향 및 신체 컨디션에 따른 교환이나 환불 처리가 어렵습니다. 사용자에 따라 기존 매트리스와 다르게 쿠션감이 익숙지 않아 불편함을 느끼는 경우도 있습니다. 모든 매트리스는 적응 기간이 필요하며 매트리스 내장재는 시간이 지나면서 사용자의 체형에 알맞게 맞춰짐에 따라 불편함이 사라집니다. 특히 탄탄한 매트리스를 사용하였거나 바닥 생활을 하시다가 푹신한 폼 매트리스로 교체하셨을 때 불편함을 느끼는 경우가 많습니다. 배송된 제품의 불량이 의심되시는 경우, 구매하신 온라인몰에 기재되어 있는 지누스 고객센터로 문의해 주세요.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '교환 혹은 반품은 어떻게 진행되나요?', '교환/반품', '교환 또는 반품 신청은 홈페이지 및 지누스 고객센터를 통해 접수 가능합니다.
고객 변심에 의한 교환 및 반품은 상품 인도 후 7일 이내 신청이 가능하며, 이 경우 반품 배송비가 부과됩니다. (모델에 따라 반품 배송비 상이하며 매트리스 제품은 압축 포장 미개봉 시에만 가능)
※ 단 매트리스, 프레임, 베개 등의 경우 고객님이 사용하신 후에는 가치가 현저히 감소하므로 비닐 포장을 제거하거나 조립 설치하여 사용한 후에는 교환 및 반품이 절대 불가합니다.', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '환불 금액은 언제 입금되나요?', '교환/반품', '평균적으로 실시간 계좌이체의 경우 2~3일, 무통장 입금의 경우 2~3일, 신용카드는 3~4일 정도 소요됩니다.<br>
주문 별, 결제수단 별 케이스가 다양하여 환불금 처리 완료일은 상이하니 자세한 진행내용은 지누스 유선 고객센터 또는 지누스몰 1:1문의 게시판으로 문의주시면 빠르게 확인해 드리겠습니다.', SYSDATE);

-- 결제
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '주문 후 바로 주문 취소를 했는데 결제 취소는 언제 되나요?', '결제', '결제 유형에 따른 결제 취소 소요 시간은 아래와 같습니다.<br>
1. 신용카드 결제 시 : 주문 당일 취소 시 당일 취소 가능하오나, 카드사 매입 이후 취소 시 2~3일 정도 시간이 소요 됩니다. (공휴일 제외)<br>
자세한 문의 사항은 결제 시 사용하신 카드의 카드사로 문의하시면 자세히 안내 받으실 수 있습니다.<br>
2. 실시간 계좌이체 및 무통장 입금 결제 시 : 취소 접수가 확인된 날로부터 최소 3~5일 정도 소요 됩니다. (공휴일 제외)', SYSDATE);

-- A/S
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '제품의 보증 기간은 어떻게 되나요?', 'A/S', '보증기간은 5년이며, 상품의 하자 및 불량이 있을 시에 본사 측에서 내부 절차에 따라 교환 및 반품 처리를 도와 드리고 있습니다.
(사용상의 문제나 이슈가 있을 경우는 제외, 5년 이내 내부 방침에 따른 제한적인 보증 적용)<br>
제품에 따라 품질 보증 기간과 내용이 상이하여 구매하신 온라인몰의 고객센터로 연락을 주시면 문제 해결을 도와 드리겠습니다.', SYSDATE);

-- 회원가입/탈퇴
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '회원 탈퇴는 어떻게 하나요?', '회원가입/탈퇴', '로그인 후 오른쪽 상단에 MY PAGE >회원정보>회원탈퇴 메뉴에서 탈퇴하실 수 있습니다.<br>
회원 탈퇴시 고객님의 회원정보와 거래정보도 동시 삭제되오니, 참고 부탁 드려요!', SYSDATE);
INSERT INTO FAQ VALUES(SEQ_FAQ.NEXTVAL, '비밀번호 및 개인정보를 변경할 수 있나요?', '회원가입/탈퇴', '비밀번호 및 개인정보 수정은 로그인 후 상단에 MY PAGE>회원정보>개인정보 변경 메뉴에서 변경하실 수 있습니다.<br>
수정 정보는 즉시 반영되며 구입하신 상품의 정확한 배송을 위해 변경된 회원정보는 꼭 수정 부탁 드립니다. ', SYSDATE);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << MATERIALS >> --
INSERT INTO MATERIALS VALUES (
    SEQ_MATERIALS.NEXTVAL, '트리샤 헤드보드 침대 프레임', '트리샤 헤드보드 침대 프레임 블랙/화이트', 'S, SS, Q', 'material_6.png', 'ZK-SSBFH-07S.pdf', 'ZK-SSBFH-07S.pdf', SYSDATE
);
INSERT INTO MATERIALS VALUES (
    SEQ_MATERIALS.NEXTVAL, '클라리사 침대 프레임', '클라리사 침대 프레임', 'SS, Q', 'material_5.png', 'ZK-FBMCMP-12.pdf', 'ZK-FBMCMP-12.pdf', SYSDATE
);
INSERT INTO MATERIALS VALUES (
    SEQ_MATERIALS.NEXTVAL, '앨런 침대 프레임', '앨런 침대 프레임', 'SS, Q, K', 'material_4.png', 'ZK-FPWHRL-12.pdf', 'ZK-FPWHRL-12.pdf', SYSDATE
);
INSERT INTO MATERIALS VALUES (
    SEQ_MATERIALS.NEXTVAL, '레이몬드 침대 프레임', '레이몬드 침대 프레임', 'SS, Q, K', 'material_3.png', 'Thumb1.png', 'Thumb1.png', SYSDATE
);
INSERT INTO MATERIALS VALUES (
    SEQ_MATERIALS.NEXTVAL, '웨슬리 침대 프레임', '웨슬리 침대 프레임', 'SS, Q, K', 'material_2.png', 'ZK-FBOWNM-12.pdf', 'ZK-FBOWNM-12.pdf', SYSDATE
);
INSERT INTO MATERIALS VALUES (
    SEQ_MATERIALS.NEXTVAL, '베이직 스마트베이스 침대 프레임', '베이직 스마트베이스 침대 프레임', 'SS, Q', 'material_1.png', 'ZK-SBBK-2.pdf', 'ZK-SBBK-2.pdf', SYSDATE
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << MAGAZINE >> --
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '찐유저(ZINuser) 2기를 소개합니다~!', '편안한 숙면의 여정을 함께할 20인을 소개합니다.', '73.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '수면 단계를 통해 알아보는 숙면 방법', '충분한 수면 시간에서 시작되는 숙면의 시작!', '72.jfif', '', SYSDATE, 'magazine' 
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, 'ZINUS Air Mattress Series', '에어 메모리폼으로 더욱 쾌적해진 지누스의 베스트셀러를 만나보세요.', '71.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, 'ZINUS Luxe S Mattress Series', '수면은 고품격으로 가격은 합리적으로, 지누스 럭스S 매트리스', '70.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '빈티지 매니아, 맥시멀리스트의 선택', '2.3평 작은 방에 가득한 빈티지&우드 취향', '69.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '플랜테리어의 방이 궁금하다면?🌿', '플랜테리어 @u_yuzu_님이 선택한 그린티 럭스S', '68.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스와 함께 매일 꿀잠🌙', '빈티지 침실 인테리어에 클라우드 매트리스를 매칭해보세요', '67.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL,  '고양이 무무네가 선택한 얼티마 럭스S', '무무네 삶의 질 수직상승 시켜준 지누스🎢', '66.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '아기자기한 말그미네 침실 인테리어', '인플루언서 @ oo_0vv님의 색감 가득 침실', '65.png', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스 스테이#4 모로코 창신힐스', '종로에서 가장 높은 곳', '64.png', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스 스테이#3 플랫트먼트', '용산 해방촌에 위치한 감성 북스테이', '63.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스 스테이#2 린덴 스튜디오', '일상과 멀지 않은 휴식의 공간', '62.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스 스테이#1 스테이넉넉', '서울 도심 속에서 한옥 감성이 주는 힐링감', '61.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '버터 옐로우톤 인터리어에 찰떡💛', '@alderdn님의 얼티마 에어 매트리스 활용법', '60.jfif', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '파스텔톤의 빈티지 인테리어 TIP', '지누스가 제안하는 빈티지 무드 인테리어', '59.png', '', SYSDATE, 'magazine' 
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스 x 꼬루비 작가 일러스트 콜라보', '귀여운 감성의 지누스 일러스트를 확인해보세요', '58.png', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '열대야를 버텨줄 메모리폼 매트리스', '오픈셀 에어 매트리스를 소개합니다', '57.png', '', SYSDATE, 'magazine'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '지누스 에어 매트리스💛', '난연 기능을 무장한 에어 매트리스 후기를 만나보세요', '56.png', '', SYSDATE, 'magazine'
);


-- << MAGAZINE (review) >> --
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 1월호', '2024.01.01 ~ 2024.01.31', '14.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 12월호', '2023.12.01 ~ 2023.12.31', '13.png', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 11월호', '2023.11.01 ~ 2023.11.30', '12.png', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 10월호', '2023.10.02 ~ 2023.10.31', '11.png', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 9월호', '2023.08.11 ~ 2023.09.10', '10.png', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 8월호', '2023.08.01 ~ 2023.08.31', '09.png', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 6월호', '2023.06.01 ~ 2023.06.30', '08.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 4월호', '2023.04.01 ~ 2023.04.30', '07.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 3월호', '2023.03.24 ~ 2023.03.31', '06.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 2월호', '2023.02.01 ~ 2023.02.28', '05.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 12월호', '2022.12.01 ~ 2022.12.31', '04.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 11월호', '2022.11.16 ~ 2022.11.30', '03.jfif', '', SYSDATE, 'review'
);
INSERT INTO MAGAZINE VALUES(
	SEQ_MAGAZINE.NEXTVAL, '이 달의 리뷰 9월호', '2022.09.01 ~ 2022.09.30', '02.jfif', '', SYSDATE, 'review'
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << CATALOG >> --
INSERT INTO CATALOG VALUES (SEQ_CATALOG.NEXTVAL, 'ZINUS Product Catalog (Jan, 2021)', '', SYSDATE, 0);
INSERT INTO CATALOG VALUES (SEQ_CATALOG.NEXTVAL, 'ZINUS Product Catalog (Mar, 2021)', '', SYSDATE, 0);

-- << CATALOG_FILE >> --
INSERT INTO CATALOG_FILE VALUES (SEQ_CATALOG_FILE.NEXTVAL, 'ZINUS Product Catalog (Jan, 2021).pdf', 'ZINUS Product Catalog (Jan, 2021).pdf', SYSDATE, 1);
INSERT INTO CATALOG_FILE VALUES (SEQ_CATALOG_FILE.NEXTVAL, 'ZINUS Product Catalog (Mar, 2021).pdf', 'ZINUS Product Catalog (Mar, 2021).pdf', SYSDATE, 2);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << ITEM >> --
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '젤 하이브리드 스프링 매트리스', '스프링 매트리스', '7cm 높이의 메모리폼 특유의 터치감과 포켓 스트링 매트리스의 탄탄함을 동시에 경험하는 제품', '418000', 'MH', SYSDATE, 'Q,SS', '1000000126_detail_096.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '그린티 하이브리드 스프링 매트리스', '스프링 매트리스', '*아이코일®기술로 안정감 있게 지지<br>*지누스 스테디셀러', '154000', 'MH', SYSDATE, 'Q,SS,S', '1000000034_detail_050.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '젤 메모리폼 매트리스', '메모리폼 매트리스', '특수 제작된 패브릭과 젤이 함유된 메모리폼은 처음 누웠을 때의 보송보송한 느낌을 장시간 유지해주는 제품', '30800', 'MH', SYSDATE, 'Q,SS,S', '1000000125_detail_058.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '젤인퓨즈드 메모리폼 매트리스', '메모리폼 매트리스', '*젤 메모리폼이 체온을 균형 있게 조절<br>*안락한 감촉의 초극세사 충전재', '37400', 'MH', SYSDATE, 'K', '1000000039_detail_082.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '클라우드 플러스 하이브리드 스프링 매트리스', '스프링 매트리스', '*두가지의 폼이 유기적으로 몸을 골고루 지지<br>*모서리까지 받쳐주는 인케이스드 폼', '495000', 'M', SYSDATE, 'K,Q,SS', '1000000138_detail_069.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '젤인퓨즈드 하이브리드 스프링 매트리스', '스프링 매트리스', '*젤 메모리폼이 체온을 균형 있게 조절<br>*두 배로 푹신한 유로탑', '341000', 'M', SYSDATE, 'Q,K', '1000000040_detail_025.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '그린티 플러스 하이브리드 스프링 매트리스', '스프링 매트리스', '*흔들림 없이 안정적인 타이트탑<br>*기본에 충실한 매트리스', '154000', 'M', SYSDATE, 'S,SS,Q', '1000000136_detail_032.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '얼티마 하이브리드 스프링 매트리스', '스프링 매트리스', '*두 배로 푹신한 유로탑<br>*대중적인 디자인과 조화로운 컬러', '231000', 'M', SYSDATE, 'K,Q,SS', '1000000038_detail_071.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '클라우드 메모리폼 매트리스', '메모리폼 매트리스', '*초극세사 충전재 퀼팅<br>*구름 위에 누운 듯한 포근함', '418000', 'M', SYSDATE, 'SS,Q,K', '1000000041_detail_073.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '그린티 메모리폼 매트리스', '메모리폼 매트리스', '*지누스의 스테디셀러<br>*기본에 충실한 메모리폼 매트리스', '187000', 'M', SYSDATE, 'Q,SS,S', '1000000033_detail_048.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '클라우드 플러스 메모리폼 매트리스', '메모리폼 매트리스', '*구름 위에 누운 듯한 포근함<br>*두가지의 폼이 유기적으로 몸을 골고루 지지', '495000', 'M', SYSDATE, 'Q,SS,S', '1000000137_detail_087.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '그린티 플러스 메모리폼 매트리스', '메모리폼 매트리스', '*두가지의 폼이 유기적으로 몸을 골고루 지지<br>*탄탄한 사용감', '495000', 'H', SYSDATE, 'Q,SS,S', '1000000135_detail_053.jpg'
);
INSERT INTO ITEM VALUES (SEQ_ITEM.NEXTVAL, '얼티마 메모리폼 매트리스', '메모리폼 매트리스', '사용자의 수면 중 움직임에 따라 유연하게 변형되며 신체를 지지, 부드러움과 포근함, 탄탄한 지지력을 동시에 제공', '264000', 'MS', SYSDATE, 'Q,SS,S', '1000000037_detail_085.jpg'
);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << ITEM_IMAGE >> --
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_027.jpg', 1);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_124.jpg', 1);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_227.jpg', 1);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_335.jpg', 1);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_487.jpg', 1);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_544.jpg', 1);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_648.jpg', 1);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000125_detail_058.jpg', 2);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000126_detail_096.jpg', 2);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000135_detail_053.jpg', 2);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000136_detail_032.jpg', 2);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000137_detail_087.jpg', 2);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000138_detail_069.jpg', 2);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_027.jpg', 3);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_124.jpg', 3);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_227.jpg', 3);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_335.jpg', 3);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_487.jpg', 3);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_544.jpg', 3);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_648.jpg', 3);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000033_detail_048.jpg', 4);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000034_detail_050.jpg', 4);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000037_detail_085.jpg', 4);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000038_detail_071.jpg', 4);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000039_detail_082.jpg', 4);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000040_detail_025.jpg', 4);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000041_detail_073.jpg', 4);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000125_detail_058.jpg', 5);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000126_detail_096.jpg', 5);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000135_detail_053.jpg', 5);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000136_detail_032.jpg', 5);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000137_detail_087.jpg', 5);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000138_detail_069.jpg', 5);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_027.jpg', 6);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_124.jpg', 6);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_227.jpg', 6);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_335.jpg', 6);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_487.jpg', 6);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_544.jpg', 6);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_648.jpg', 6);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_027.jpg', 7);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_124.jpg', 7);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_227.jpg', 7);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_335.jpg', 7);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_487.jpg', 7);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_544.jpg', 7);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_648.jpg', 7);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000033_detail_048.jpg', 8);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000034_detail_050.jpg', 8);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000037_detail_085.jpg', 8);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000038_detail_071.jpg', 8);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000039_detail_082.jpg', 8);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000040_detail_025.jpg', 8);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000041_detail_073.jpg', 8);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000125_detail_058.jpg', 9);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000126_detail_096.jpg', 9);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000135_detail_053.jpg', 9);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000136_detail_032.jpg', 9);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000137_detail_087.jpg', 9);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000138_detail_069.jpg', 9);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_027.jpg', 10);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_124.jpg', 10);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_227.jpg', 10);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_335.jpg', 10);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_487.jpg', 10);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_544.jpg', 10);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_648.jpg', 10);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000125_detail_058.jpg', 11);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000126_detail_096.jpg', 11);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000135_detail_053.jpg', 11);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000136_detail_032.jpg', 11);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000137_detail_087.jpg', 11);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000138_detail_069.jpg', 11);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000033_detail_048.jpg', 12);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000034_detail_050.jpg', 12);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000037_detail_085.jpg', 12);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000038_detail_071.jpg', 12);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000039_detail_082.jpg', 12);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000040_detail_025.jpg', 12);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000041_detail_073.jpg', 12);

INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_027.jpg', 13);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_124.jpg', 13);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_227.jpg', 13);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_335.jpg', 13);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_487.jpg', 13);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_544.jpg', 13);
INSERT INTO ITEM_IMAGE values(SEQ_ITEM_IMAGE.NEXTVAL, '1000000369_detail_648.jpg', 13);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << NOTICE >> --
INSERT INTO NOTICE VALUES (SEQ_NOTICE.NEXTVAL, '지누스 중견기업확인서 (2019.03.31)', '', SYSDATE, 0);
INSERT INTO NOTICE VALUES (SEQ_NOTICE.NEXTVAL, '지누스 중견기업확인서 (2020.03.31)', '', SYSDATE, 0);
INSERT INTO NOTICE VALUES (SEQ_NOTICE.NEXTVAL, '전자증권 전환대상 주권 등의 권리자 보호 안내', '', SYSDATE, 0);
INSERT INTO NOTICE VALUES (SEQ_NOTICE.NEXTVAL, '신주발행공고(안)', '', SYSDATE, 0);
INSERT INTO NOTICE VALUES (SEQ_NOTICE.NEXTVAL, '신주발행공고(안)', '', SYSDATE, 0);
INSERT INTO NOTICE VALUES (SEQ_NOTICE.NEXTVAL, '신주발행공고(안)', '', SYSDATE, 0);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-- << NOTICE_FILE >> --
INSERT INTO NOTICE_FILE VALUES(SEQ_NOTICE_FILE.NEXTVAL, '지누스_중견기업확인서(2019.03.31).pdf', '지누스_중견기업확인서(2019.03.31).pdf', SYSDATE, 1);
INSERT INTO NOTICE_FILE VALUES(SEQ_NOTICE_FILE.NEXTVAL, '지누스_중견기업확인서(2020.03.31).pdf', '지누스_중견기업확인서(2020.03.31).pdf', SYSDATE, 2);
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
