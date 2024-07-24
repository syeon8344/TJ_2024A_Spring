loadInfo()
function loadInfo(){
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/my/info",
        success : response => {
            if (response.id){
                document.querySelector("#name").innerHTML = response.name
                document.querySelector("#phone").innerHTML = response.phone
                document.querySelector("#email").innerHTML = response.email
            } else {
                alert("먼저 로그인해 주세요.")
                location.href="/member/login"
            }
        }
    })
}
function updateInfo(){
    let newName = document.querySelector("#newName").value
    let newPhone = document.querySelector("#newPhone").value
    let newEmail = document.querySelector("#newEmail").value
    let oldPw = document.querySelector("#oldPw").value
    let newPw = document.querySelector("#newPw").value
    let dto = {};
    if (newName != ""){
        let nameRegex = /^[가-힣]{2,20}$/
        if (nameRegex.test(newName)){
            dto.name = newName
        } else {alert("새 이름이 잘못되었습니다.")
            return;
        }
    }
    if (newPhone != ""){
        let phoneRegex = /^([0-9]{2,3})+-([0-9]{3,4})+-([0-9]{3,4})+$/
        if (phoneRegex.test(newPhone)){
            dto.phone = newPhone
        } else {alert("새 전화번호가 잘못되었습니다.")
            return;
        }
    }
    if (newEmail != ""){
        let emailRegex = /^[A-Za-z0-9_-]+@[A-Za-z0-9_-]+\.[A-Za-z]+$/
        if (emailRegex.test(newEmail)){
            dto.email = newEmail
        } else {alert("새 이메일이 잘못되었습니다.")
            return;
        }
    }
    if (oldPw != "" ){
        if(!pwCheck()){alert("기존 비밀번호를 다시 확인해 주세요."); return;}
        let pwRegex = /^(?=.*[A-Za-z])(?=.*[0-9])[a-zA-Z0-9]{5,30}$/
        if (pwRegex.test(newPw)){
            dto.pw = newPw
        } else {
            alert("비밀번호는 5~30글자의 영문 대소문자, 숫자여야 합니다.")
            return;
        }
    }

    $.ajax({
        async : false,
        method : "PUT",
        url : "/member/updateInfo",
        data : JSON.stringify(dto),
        contentType : "application/JSON",
        success : resp => {
            if(resp){
                alert("정보 수정 완료")
                location.href="/member/mypage"
            } else {alert("정보 수정 실패")}
        }
    })

    // 기존 비밀번호 확인
    function pwCheck(){
        let dto = {"pw" : document.querySelector("#oldPw").value}
        let result = false;
        $.ajax({
            async : false,
            method : "POST",
            url : "/member/update/pwCheck",
            data : JSON.stringify(dto),
            contentType : "application/json",
            success : r => {
                result = r;
            }
        })
        return result;
    }
}