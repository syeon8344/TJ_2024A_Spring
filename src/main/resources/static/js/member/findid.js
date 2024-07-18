function findId(){
    let name = document.querySelector("#name")
    let phone = document.querySelector("#phone")
    let dto = {name : name.value, phone : phone.value}
    $.ajax({
        async : false,
        method : "POST",
        url : "/member/findId",
        data : JSON.stringify(dto),
        contentType : "application/json",
        success : response => {
            if (response != ""){
            document.querySelector("#foundId").innerHTML = `
            아이디 찾기 결과는 ${response} 입니다.`
            } else {
                document.querySelector("#foundId").innerHTML = `
            아이디 찾기 결과가 없습니다. 입력 정보를 다시 확인해 주세요.`
            }
            name.value = ""
            phone.value = ""
        }
    })

}