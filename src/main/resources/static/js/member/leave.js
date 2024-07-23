function delAccount(){
    $.ajax({
        method : "DELETE",
        url : "/member/delAccount",
        data : JSON.stringify({pw : document.querySelector("#pwConfirm").value}),
        contentType : "application/json",
        success : resp => {
            if (resp) {
                alert("회원 탈퇴 완료, 메인 페이지로 돌아갑니다.")
                location.href="/"
            } else {
                alert("회원 탈퇴 실패, 비밀번호를 다시 확인해 주세요.")
            }
        }
    })
}