function findPw(){
    let id = document.querySelector("#id")
    let phone = document.querySelector("#phone")
    let dto = {id : id.value, phone : phone.value}
    $.ajax({
        async : false,
        method : "POST",
        url : "/member/findPw",
        data : JSON.stringify(dto),
        contentType : "application/json",
        success : response => {
            if (response != ""){
            document.querySelector("#foundPw").innerHTML = `
            비밀번호 찾기 결과는 ${response} 입니다.`
            } else {
                document.querySelector("#foundPw").innerHTML = `
            비밀번호 찾기 결과가 없습니다. 입력 정보를 다시 확인해 주세요.`
            }
            id.value = ""
            phone.value = ""
        }
    })
}