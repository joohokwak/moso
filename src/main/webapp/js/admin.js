// 삭제 목록
let deleteListArr = [];

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

	// 회원 전체 선택 체크박스 (공통)
	const adminChkAll = document.querySelector('#chkAll');
	const _chks = document.querySelectorAll('.admin_list_wrap td .chks');
	// 전체 선택 체크박스 클릭 이벤트 리스너
	if (adminChkAll) {
	    adminChkAll.addEventListener('click', function() {
			// 전체 체크박스 상태
	        const isChecked = this.checked;
	        _chks.forEach(chk => {
				// 개별 체크박스 상태를 전체 체크박스와 동기화
	            chk.checked = isChecked;
				
				// 삭제 리스트 업데이트
	            updateDeleteList(chk.dataset.no, isChecked);
	        });
	    });
	}

	// 개별 선택 체크박스 (공통)
	if (_chks) {
	    _chks.forEach(chk => {
	        chk.addEventListener('click', function() {
				// 체크박스에 저장된 데이터 번호
	            const itemNo = this.dataset.no;
				
				// 삭제 리스트 업데이트
	            updateDeleteList(itemNo, this.checked);
	            
	            // 전체 선택 체크박스 상태 업데이트
	            adminChkAll.checked = areAllCheckboxesChecked(_chks);
	        });
	    });
	}

	// 글쓰기 버튼 (공통)
	clickEvent('.admin_content_right .write_btn', function() {
		const $path = location.pathname.substring(location.pathname.lastIndexOf("/"));
		location.href = capitalize($path) + "/write?isadmin=Y";
	});
	
	// 삭제 버튼 (공통)
	clickEvent('.admin_content_right .delete_btn', function() {
		if (deleteListArr.length) {
			confirm('선택항목을 삭제하시겠습니까?', () => {
				const delList = deleteListArr.join();
				// 삭제시 페이징 상태라면 다시 해당 페이지로 이동할수 있도록 처리
				if (location.search) {
					location.href = `/Admin/delete${location.search}&path=${location.pathname}&no=${delList}`;
				} else {
					location.href = `/Admin/delete?path=${location.pathname}&no=${delList}`;
				}
			});
		} else {
			alert('삭제할 항목을 선택해주세요');
		}
	});
	
	// 회원명 클릭 시 수정 페이지로 이동 (개인정보 이기 때문에 POST 방식으로 전달하기 위해)
	const memberIdClick = document.querySelectorAll('#adminMemberList .mem_id');
	if (memberIdClick) {
	    memberIdClick.forEach(btn => {
	        btn.addEventListener('click', function(e) {
	            e.preventDefault();

	            // 동적 폼 생성 및 데이터 전송
	            createAndSubmitForm('/Admin/update', {
	                id: this.innerText,
	                isadmin: 'Y'
	            });
	        });
	    });
	}
	
});

// 삭제 리스트 업데이트 함수
function updateDeleteList(itemNo, isChecked) {
	const index = deleteListArr.indexOf(itemNo);
	
    if (isChecked) {
        if (index === -1) {
            deleteListArr.push(itemNo); // 체크된 경우 추가
        }
    } else {
        if (index > -1) {
            deleteListArr.splice(index, 1); // 체크 해제된 경우 삭제
        }
    }
}

// 모든 체크박스가 체크되었는지 확인하는 함수
function areAllCheckboxesChecked(checkboxes) {
    return Array.from(checkboxes).every(checkbox => checkbox.checked);
}

// 두번째 글자만 대문자로 변환
function capitalize(word) {
	return word.charAt(0) + word.charAt(1).toUpperCase() + word.slice(2);
}

// 폼 생성 및 제출 함수
function createAndSubmitForm(actionUrl, data) {
    const form = document.createElement("form");
    form.setAttribute("action", actionUrl);
    form.setAttribute("method", "POST");

    for (const key in data) {
        const hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", data[key]);
        form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form.submit();
}