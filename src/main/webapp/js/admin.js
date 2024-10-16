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

	let deleteListArr = [];
	// 회원 전체 선택 체크박스 (공통)
	const adminChkAll = document.querySelector('#chkAll');
	const _chks = document.querySelectorAll('.admin_list_wrap td .chks');

	if (adminChkAll) {
		adminChkAll.addEventListener('click', function() {
			_chks.forEach(chk => {
				chk.checked = this.checked;

				if (chk.checked) deleteListArr.push(chk.dataset.no);
			});

			if (!this.checked) deleteListArr = [];
		});
	}

	// 개별 선택 체크박스 (공통)
	if (_chks) {
		_chks.forEach(chk => {
			chk.addEventListener('click', function() {
				const _no = this.dataset.no;
				if (this.checked) deleteListArr.push(_no);
				else deleteListArr.splice(deleteListArr.indexOf(_no), 1);

				let _tmp = false;
				for (const checkbox of _chks) {
					if (checkbox.checked) _tmp = true;
					else {
						_tmp = false;
						break;
					}
				}

				adminChkAll.checked = _tmp;
			});
		});
	}

	// 글쓰기 버튼 (공통)
	const adminWriteBtn = document.querySelector('.admin_content_right .write_btn');
	if (adminWriteBtn) {
		adminWriteBtn.addEventListener('click', function() {
			const $path = location.pathname.substring(location.pathname.lastIndexOf("/"));
			location.href = capitalize($path) + "/write?isadmin=Y";
		});
	}
	
	// 삭제 버튼 (공통)
	const adminDelBtn = document.querySelector('.admin_content_right .delete_btn');
	if (adminDelBtn) {
		adminDelBtn.addEventListener('click', function() {
			if (deleteListArr.length > 0) {
				const $memList = deleteListArr.join();
				location.href = `/Admin/delete?path=${location.pathname}&no=${$memList}`;
			} else {
				alert('삭제할 회원을 선택해주세요');
			}
		});
	}
	
	// 회원명 클릭 시 수정페이지 이동 (개인정보 이기 때문에 POST 방식으로 전달하기 위해)
	const memberIdClick = document.querySelectorAll('#adminMemberList .mem_id');
	if (memberIdClick) {
		memberIdClick.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				
				// 동적 form 태그 생성하여 전달
				const form = document.createElement("form");
				form.setAttribute("action", "/Admin/update");
				form.setAttribute("method", "POST");
				
				const hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "id");
				hiddenField.setAttribute("value", this.innerText);
				form.appendChild(hiddenField);
				
				const hiddenField2 = document.createElement("input");
				hiddenField2.setAttribute("type", "hidden");
				hiddenField2.setAttribute("name", "isadmin");
				hiddenField2.setAttribute("value", "Y");
				form.appendChild(hiddenField2);
				
				document.body.appendChild(form);
				form.submit();
			});
		});
	}

});

// 두번째 글자만 대문자로 변환
function capitalize(word) {
	return word.charAt(0) + word.charAt(1).toUpperCase() + word.slice(2);
}