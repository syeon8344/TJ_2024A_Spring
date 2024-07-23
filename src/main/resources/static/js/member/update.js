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
}