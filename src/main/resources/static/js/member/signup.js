function doSignup(){
    let idInput = document.querySelector("#id").value
    let pwInput = document.querySelector("#pw").value
    let nameInput = document.querySelector("#name").value
    let emailInput = document.querySelector("#email").value
    let phoneInput = document.querySelector("#phone").value
    $.ajax({
        async : false,
        method : "POST",
        url : "/member/signup",
        data : JSON.stringify({id : idInput, pw : pwInput, name : nameInput, email : emailInput, phone : phoneInput}),
        contentType : "application/json",
        success : response => {
            if (response == true){
                alert("회원가입 성공")
                location.href = "/member/login"
            } else {alert("회원가입 실패")}
        }, 
        error : error => {alert("HTTP 통신 오류, 관리자에게 문의 바랍니다 : " + error.responseText)}
    })
    
}