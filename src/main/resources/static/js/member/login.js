function login(){
    let id = document.querySelector("#id").value
    let pw = document.querySelector("#pw").value
    $.ajax({
        method : "POST",
        url : "/member/login",
        data : JSON.stringify({id : id, pw : pw}),
        contentType : "application/json",
        success : response => {
            if (response == true){
                alert("로그인 성공")
            } else {
                alert("로그인 실패")
            }
        },
        error : error => {alert("HTTP 통신 오류, 관리자에게 문의 바랍니다 : " + error.responseText)}
    })
}