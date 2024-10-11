window.addEventListener('DOMContentLoaded', function() {
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	if (container) {
		const options = {
			//지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(35.872338, 128.585494), //지도의 중심좌표.
			level: 4, //지도의 레벨(확대, 축소 정도)
		};

		const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

		// 마커가 표시될 위치입니다
		var markerPosition = new kakao.maps.LatLng(35.872338, 128.585494);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({ position: markerPosition });

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		var iwContent =
			'<div style="padding:5px; font-size:12px;">대구 중구 서성로13길 32-3 <br><a href="https://map.kakao.com/link/map/Hello World!,35.872338,128.585494" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,35.872338,128.585494" style="color:blue" target="_blank">길찾기</a></div>',
			iwPosition = new kakao.maps.LatLng(35.872338, 128.585494); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			position: iwPosition,
			content: iwContent,
		});

		infowindow.open(map, marker);
	}
});
