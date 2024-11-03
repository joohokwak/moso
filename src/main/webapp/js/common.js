window.addEventListener('DOMContentLoaded', function() {
	// 웹 에디터가 되기위해 editor 속성 찾기
	const editor = document.querySelector('[data-editor]');
	if (editor) {
		// 해당 속성 숨기기
		editor.style.display = 'none';

		// ID값 얻기
		const id = editor.getAttribute('id');
		// 데이터 VALUE 얻기
		const body = editor.dataset.editor;

		// 웹 에디터 생성함수 호출
		quill(id, body);
	}
	
}); // DOMContentLoaded

// 웹 에디터 생성 함수
function quill(id, body) {
	// div 태크 생성
	const template = document.createElement('div');
	// 속성, style, body 추가
	template.setAttribute('id', 'editor-container');
	template.setAttribute('style', 'width: 100%; height: 300px;');
	template.innerHTML = body;

	// 기존 태그뒤에 웹에디터 추가
	document.getElementById(id).after(template);

	// 웹 에디터 객체 생성
	const quill = new Quill('#editor-container', {
		placeholder: 'Compose an epic...',
		theme: 'snow'
	});

	// 서버에 전송을 위해 기존 태그에 추가된 내용을 붙이기
	quill.on('text-change', function() {
		document.getElementById(id).value = quill.root.innerHTML;
	});
}

function showLoading(isShow) {
	// 로딩 동적으로 생성
	const container = document.getElementById('container');
	let loadingBar = container.querySelector('#mosoLoading');
	
	if (!loadingBar) {
		loadingBar = document.createElement('img');
		loadingBar.src 				= '/images/common/moso_loading.svg';
		loadingBar.alt 				= '로딩 중...';
		loadingBar.id 				= 'mosoLoading';
		loadingBar.style.position 	= 'fixed';
		loadingBar.style.top 		= '50%';
		loadingBar.style.left 		= '50%';
		loadingBar.style.transform 	= 'translate(-50%, -50%)';
		loadingBar.style.zIndex 	= '99999';
		loadingBar.style.display 	= 'block';
		
		// 로딩 영역에 추가
        container.appendChild(loadingBar);
		
		// 오버레이 생성
        const overlay = document.createElement('div');
        overlay.id = 'loadingOverlay';
        overlay.style.position = 'fixed';
        overlay.style.top = '0';
        overlay.style.left = '0';
        overlay.style.width = '100%';
        overlay.style.height = '100%';
        overlay.style.backgroundColor = 'rgba(255, 255, 255, 0.3)';
        overlay.style.zIndex = '99998'; // 로딩바보다 낮게
        overlay.style.display = 'none'; // 처음에는 숨김
        
        // 오버레이 추가
        container.appendChild(overlay);
	}
	
	// 로딩 영역에 추가
    loadingBar.style.display = isShow ? 'block' : 'none';
	const overlay = document.getElementById('loadingOverlay');
    overlay.style.display = isShow ? 'block' : 'none';
	
	// 키 입력 차단 및 클릭 이벤트 차단
    if (isShow) {
        window.onkeydown = function(e) {
            e.preventDefault();
        };
        overlay.onclick = function(e) {
            e.preventDefault();
            e.stopPropagation();
        };
		overlay.onwheel = function(e) {
            e.preventDefault();
        };
    } else {
        // 키 입력 및 클릭 이벤트 복원
        window.onkeydown = null;
        overlay.onclick = null;
		overlay.onwheel = null;
    }
}

// post 방식으로 서버와 비동기 통신 함수
// 사용예시
// post('/Board/fetch', params, (data) => {
//		console.log(data);
//	});
function post(url, reqData, callback) {
	showLoading(true);
	
	// 설정
	const options = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(reqData), // json데이터로 변환
	};
	
	// 서버와 통신후 callback 응답하기위한 로직
	fetch(url, options)
		.then(res => res.json())
		.then(data => callback(data))
		.catch(err => console.log(err))
		.finally(() => showLoading(false));
}

// 공통 클릭 이벤트 처리 함수
function clickEvent(selector, callback) {
    const element = document.querySelector(selector);
    if (element) {
        element.addEventListener('click', function(e) {
            e.preventDefault();
            callback.call(this);
        });
    }
}

// 입력값에 특정 문자가 포함되었는지 체크
function isContains(target, chars) {
	for (var i = 0; i < target.value.length; i++) {
		if (chars.indexOf(target.value.charAt(i)) != -1) return true;
	}
	return false;
}

// alert(공통)
function alert(str, callback) {
	Swal.fire({
		text: str,
		icon: "warning"
	}).then(result => {
		if (result.isConfirmed) {
			if (callback) setTimeout(() => {callback()}, 300);
		}
	});
}

// confirm(공통)
function confirm(str, callback) {
	Swal.fire({
		text: str,
		icon: "question",
		showCancelButton: true,
		confirmButtonColor: "#003628",
		cancelButtonColor: "#f7c8a8",
		confirmButtonText: "확인",
		cancelButtonText: "취소"
	}).then((result) => {
		if (result.isConfirmed) {
			if (callback) callback();
		}
	});
}

// prompt(공통)
function prompt(str, callback) {
	Swal.fire({
		title: str,
		input: "text",
		inputAttributes: {
			autocapitalize: "off"
		},
		showCancelButton: true,
		confirmButtonText: "확인",
		cancelButtonText: "취소",
		showLoaderOnConfirm: true,
		preConfirm: () => { },
		allowOutsideClick: () => !Swal.isLoading()
	}).then((result) => {
		if (result.isConfirmed) {
			const inputVal = result.value;
			if (callback) callback(inputVal);
		}
	});
}