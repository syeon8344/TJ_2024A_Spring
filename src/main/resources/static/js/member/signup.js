function doSignup(){
    let idInput = document.querySelector("#id")
    let pwInput = document.querySelector("#pw")
    let nameInput = document.querySelector("#name")
    let emailInput = document.querySelector("#email")
    let phoneInput = document.querySelector("#phone")
    $.ajax({
        async : false,
        method : "POST",
        url : "/member/signup",
        data : JSON.stringify({id : idInput.value, 
            pw : pwInput.value, name : nameInput.value, 
            email : emailInput.value, phone : phoneInput.value}),
        contentType : "application/json",
        success : response => {
            if (response == true){
                alert("회원가입 성공")
                location.href = "/member/login"
            } else {alert("회원가입 실패")}
            idInput.value = ""
            pwInput.value = ""
            nameInput.value = ""
            emailInput.value = ""
            phoneInput.value = ""
        }, 
        error : error => {alert("HTTP 통신 오류, 관리자에게 문의 바랍니다 : " + error.responseText)}
    })
    
}