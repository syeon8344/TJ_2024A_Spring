
/*
    onclick="function()" : 클릭했을 때
    onkeyup="function()" : 키보드 키를 눌렀다 뗐을 때 작동

*/
// 전화번호 정규표현식 유효성검사
function phoneCheck(){
    let phone = document.querySelector("#phone")
    let phoneCheckBox = document.querySelector("#phoneCheckBox")
    let phoneRegex = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{3,4})$/
    if (phoneRegex.test(phone)){
        phoneCheckBox.innerHTML = "통과"
        return true;
    } else {
        phoneCheckBox.innerHTML = "000-0000-0000 형식의 전화번호 입력"
        return false;
    }
}

// 이메일 정규표현식 유효성검사
function emailCheck(){
    let email = document.querySelector("#email")
    let emailCheckBox = document.querySelector("#emailCheckBox")
    // aaaa@bbb.com
        //aaaa = [A-Za-z0-9]
        // @bbb = @[A-Za-z0-9]
        // . vs \. = . => 문자 1개 의미, \. => 탈출문자 \, . 검색
        // + = 이 앞에 패턴이 한번 이상 나타나야 한다.
    let emailRegex = /^[A-Za-z0-9_-]+@[A-Za-z0-9_-]+\.[A-Za-z]+$/
    if (emailRegex.test(email)){
        emailCheckBox.innerHTML = "사용 가능한 이메일입니다."
        return true;
    } else {
        emailCheckBox.innerHTML = "id@도메인 주소로 입력해주세요."
        return false;
    }
}

// 이름 정규표현식 유효성검사
function nameCheck(){
    let name = document.querySelector("#name")
    let nameCheckBox = document.querySelector("#nameCheckBox")
    let nameRegex = /^[가-힣]{2,20}$/
    if (nameRegex.test(name)){
        nameCheckBox.innerHTML = "통과"
        return true;
    } else {
        nameCheckBox.innerHTML = "2~20글자의 한글 입력"
        return false;
    }
}

// 비밀번호 정규표현식 유효성검사
function pwCheck(){
    console.log("pwCheck")
    let pw = document.querySelector("#pw").value
    let pwConfirm = document.querySelector("#pwConfirm").value
    let pwCheck = /^(?=.*[A-Za-z])(?=.*[0-9])[a-zA-Z0-9]{5,30}$/
    if (pw==pwConfirm&&pwCheck.test(pw)){
        document.querySelector("#pwCheckBox").innerHTML = "통과"
        return true;
    } else {
        document.querySelector("#pwCheckBox").innerHTML = "5~30글자의 영문 대소문자, 숫자 입력";
        return false;
    }
}

// 아이디 정규표현식 유효성검사
function idCheck(){
    console.log("idcheck")
    // 1. 입력값 가져오기
    let id = document.querySelector("#id").value
    let idInputBox = document.querySelector("#idCheckBox")
    console.log(id)
    // 2. 정규표현식
    let idRegex = /^[a-zA-Z가-힣]{5,30}$/
    // 3.
    console.log(idRegex.test(id))
    if (idRegex.test(id)){
        idInputBox.innerHTML = "사용가능한 아이디입니다."
        return true;
    } else {
        idInputBox.innerHTML = "아이디 길이는 5~30글자여야 합니다."
        return false;
    }
}

// 회원가입시 입력한 아이디가 현재 사용중인지 확인
function idDupeCheck(){
    let id = document.querySelector("#id")
    if (idCheck()){
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/idCheck?id="+id.value,
        success : resp => {
            if (resp == true){
                document.querySelector("#idCheckBox").innerHTML = "아이디가 중복됩니다. 다시 확인해 주세요."
           
            } else {
                doSignup();
            }
                
        }
    })
    }
}

//회원가입
function doSignup(){
    let idInput = document.querySelector("#id")
    let pwInput = document.querySelector("#pw")
    let nameInput = document.querySelector("#name")
    let emailInput = document.querySelector("#email")
    let phoneInput = document.querySelector("#phone")
    if (idInput.value == "") {alert("ID를 입력해주세요."); return;}
    if (pwInput.value == "") {alert("비밀번호를 입력해주세요."); return;}
    if (nameInput.value == "") {alert("이름을 입력해주세요."); return;}
    if (emailInput.value == "") {alert("이메일주소를 입력해주세요."); return;}
    if (phoneInput.value == "") {alert("전화번호를 입력해주세요."); return;}
    if (pwCheck()){
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
}

