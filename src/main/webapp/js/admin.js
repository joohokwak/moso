window.addEventListener('DOMContentLoaded', function() {
	// 관리자 페이지 왼쪽 메뉴 선택
	const adminLeftMenus = document.querySelectorAll('.admin_content_left a');
	if (adminLeftMenus) {
		adminLeftMenus.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				const menu = this.dataset.tit;
				location.href = `/Admin/${menu}`;
			});
		});
	}

	let memberListArr = [];
	// 회원 전체 선택 체크박스 (공통)
	const memberAllBtn = document.querySelector('#chkAll');
	const _memberChk = document.querySelectorAll('.admin_list_wrap td input[type=checkbox]');

	if (memberAllBtn) {
		memberAllBtn.addEventListener('click', function() {
			_memberChk.forEach(chk => {
				chk.checked = this.checked;

				if (chk.checked) memberListArr.push(chk.dataset.ids);
			});

			if (!this.checked) memberListArr = [];
		});
	}

	// 개별 선택 체크박스 (공통)
	if (_memberChk) {
		_memberChk.forEach(chk => {
			chk.addEventListener('click', function() {
				const _ids = this.dataset.ids;
				if (this.checked) memberListArr.push(_ids);
				else memberListArr.splice(memberListArr.indexOf(_ids), 1);

				let tmp = false;
				for (const checkbox of _memberChk) {
					if (checkbox.checked) tmp = true;
					else {
						tmp = false;
						break;
					}
				}

				memberAllBtn.checked = tmp;
			});
		});
	}

	// 삭제 버튼 (공통)
	const memberDeleteBtn = document.querySelector('.delete_btn');
	if (memberDeleteBtn) {
		memberDeleteBtn.addEventListener('click', function() {
			if (memberListArr.length > 0) {
				// TODO: 구현해야함
				console.log(memberListArr);
			} else {
				alert('삭제할 회원을 선택해주세요');
			}
		});
	}
	
	// 다운로드 버튼 (공통)
	const memberDownloadBtn = document.querySelector('.down_btn');
	if (memberDownloadBtn) {
		memberDownloadBtn.addEventListener('click', function() {
			// TODO: 구현해야함
		});
	}
	
	// 회원명 클릭 시 수정페이지 이동
	const memberIdClick = document.querySelectorAll('#adminMemberList .mem_id');
	if (memberIdClick) {
		memberIdClick.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				
				// 동적 form 태그 생성하여 전달
				const form = document.createElement("form");
				form.setAttribute("method", "Post");
				form.setAttribute("action", "/Admin/update");
				
				const hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "id");
				hiddenField.setAttribute("value", this.innerText);
				
				form.appendChild(hiddenField);
				
				document.body.appendChild(form);
				form.submit();
			});
		});
	}

});
