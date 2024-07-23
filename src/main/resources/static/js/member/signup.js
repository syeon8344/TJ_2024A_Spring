
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
// 8.인증코드 인증
function emailAuthCheck(){
    // 1. 입력한 입력번호 가져오기
    let authInput = document.querySelector(".authInput").value;
    // 인증 번호 - JS에서 인증번호 관리하지 않는 이유 : JS는 코드가 보이기 때문
    $.ajax({
        async: false,
        method: "POST",
        url: "/auth/check?authCodeInput="+authInput,
        success: resp => {
            // 2. 입력값이 인증번호와 동일하면
            if (resp) {
                authBox.innerHTML = "인증성공";
                clearInterval(timerInterval); // 타이머 종료
            } else {
                alert("인증번호가 일치하지 않습니다.");
            }
        }
    })
}

// 이메일 인증 버튼
let authBtn = document.querySelector("#emailAuth");
let authBox = document.querySelector("#emailAuthBox");
let timerInterval = null;
// 7. 이메일 인증
function sendEmailAuth(){
    // AJAX 인증 번호 요청
    $.ajax({
        async : false,
        method : "get",
        url : "/auth/code",
        data : {email : document.querySelector("#email").value},
        success : result = ()=>{
            if (result) {
                alert("이메일로 인증코드를 전송하였습니다.")
            }
        }
    })
    // 0. 인증 버튼 비활성화
    authBtn.disabled = true;
    // 1. 인증 번호 입력 구역 구성
    let html = `<span class="timerBox">00:00</span>
                <input type="text" class="authInput"/>
                <button type="button" class="authInputBtn" onclick="emailAuthCheck()">인증</button>`
    // 2.
    authBox.innerHTML = html
    // 3. 타이머
    let timer = 180; // 타이머 초 단위
    // 4. 인터벌 (JS 라이브러리) : 특정 주기에 따라 함수 실행
        // setInterval(함수 정의, 밀리초)
            // 함수 정의 != 함수 호출 -> setInterval(function(){}, 1000)/(()=>{}, 1000)
            // clearInterval(interval변수)
        // parseInt() : 정수로 타입 변환 (소수점 X)

    timerInterval = setInterval(()=>{
        // 1. 분 초 계산
        let m = parseInt(timer / 60)
        let s = parseInt(timer % 60)
        // 2. 두자릿수 표현
        m = m < 10 ? "0"+m : m; // 숫자가 10보다 작으면 0 추가
        s = s < 10 ? "0"+s : s;
        // 3. 분 초 출력
        document.querySelector(".timerBox").innerHTML= `${m}:${s}`
        // 4. 1초 차감
        timer--; console.log(timer);
        // 5. timer -1이면 interval 종료
        if (timer < 0 ) {
            clearInterval(timerInterval)
            authBox.innerHTML = "다시 인증 요청 해주세요"
            authBtn.disabled = false
        }
    }, 1000)
}
// 6. 이메일 정규표현식 유효성검사
function emailCheck(){
    let email = document.querySelector("#email")
    let emailCheckBox = document.querySelector("#emailCheckBox")
    authBtn.disabled = true
    // aaaa@bbb.com
        //aaaa = [A-Za-z0-9]
        // @bbb = @[A-Za-z0-9]
        // . vs \. = . => 문자 1개 의미, \. => 탈출문자 \, . 검색
        // + = 이 앞에 패턴이 한번 이상 나타나야 한다.
    let emailRegex = /^[A-Za-z0-9_-]+@[A-Za-z0-9_-]+\.[A-Za-z]+$/
    if (emailRegex.test(email.value)){
        // 이메일 인증검사 : regex 통과후 인증버튼 활성화
        emailCheckBox.innerHTML = "사용 가능한 이메일입니다."
        authBtn.disabled = false
        console.log("emailsuccess")
        return true;
    } else {
        emailCheckBox.innerHTML = "id@도메인 주소로 입력해주세요."
        console.log("emailfail")
        return false;
    }
}

// 5. 이름 정규표현식 유효성검사
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

// 4. 비밀번호 정규표현식 유효성검사
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

// 3. 아이디 정규표현식 유효성검사
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

// 2. 회원가입시 입력한 아이디가 현재 사용중인지 확인
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

// 1. 회원가입
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

