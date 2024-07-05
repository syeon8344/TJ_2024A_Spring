console.log("phonebook script open");
//let phonebookDB = [];
getPhone();
//1.
function postPhone(){
    // 1. 입력받기
    let name = document.querySelector("#name").value;
    let phone = document.querySelector("#phone").value;

    // 2. 객체화
    let phoneDto = {"name" : name, "phone" : phone};
    // 3. 배열 저장
    //phonebookDB.push(phoneDto);
    // 4. 안내//새로고침
    //alert("saved");
    //getPhone();
    // 2. HTML에 jQuery 라이브러리를 가져오면 Ajax 함수 사용 가능
        // 2-1. Ajax에 들어갈 옵션 객체 설정
    let option = {
        url : "http://localhost:8080/phone/create", // 통신할 경로 --> spring 컨트롤러
        method : "post",                            // HTTP가 지원하는 함수중 사용할 함수명 (POST(C), GET(R), PUT(U), DELETE(D))
        data : JSON.stringify(phoneDto),            // 통신할 경로에 보낼 데이터 --> spring 컨트롤러로
        contentType : "application/json",           // data 옵션에 있는 것의 타입
        success : function response(result){        // 통신 성공시 응답받을 함수 정의, 매개변수는 응답 데이터
            console.log(result);
            if(result){alert("saved"); getPhone();}
            else{ alert("fail")}
        }
    }
        // 2-2. Ajax 함수 호출  [ $ : jQuery문법 ]
    $.ajax(option);
}

//2. 출력 : 등록 처리 되었을때, js 오픈시 최초 한번
function getPhone(){
    // 1. 어디에
    let phoneListBox = document.querySelector("#phoneListBox");
    // 2. 무엇을
    let html = "";
    //
    let option = {
        url:"http://localhost:8080/phone/read",  // 누구에게
        method: "get",                           // 어떤 방식으로
        // data: "application/JSON",             // 무엇을 보내고 (매개변수 없음)
        success: (result) => {
            console.log(result);
            result.forEach(result =>{
                html += `<span>${result.name}</span>
                <span>${result.phone}</span><br>`
            });
            phoneListBox.innerHTML = html;
        }
    }
    // ajax 실행
    $.ajax(option);

    // 3. 출력
}