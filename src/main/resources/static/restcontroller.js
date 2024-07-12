console.log("restController.js ON");

//GET
function rest3get(){
    console.log("rest3get")
    // 1. AJAX 옵션 설정
    let option = {
        // url : "http://localhost:8080/example/rest3?key=123" // 통신할 URL, 스프링 컨트롤러 매핑
        url : "/example/rest3?key=123",
        method : "get", // 통신할 HTTP 메소드 선택
        success : function(response){ console.log(response);} // 통신 성공시 응답받은 데이터
    }

    // 2. AJAX 메소드에 옵션 넣어서 실행
    $.ajax(option);
}

//POST
function rest3post(){
    console.log("rest3post")
    $.ajax({url:"/example/rest3?key=123", // HTTP 통신할 경로 
        method : "POST",  // HTML 메소드
        success : (response)=>{console.log(response)} // HTTP 성공 응답 (웹서버측에서 보낸 return값) 처리 함수
        }
    )
}

//PUT
function rest3put(){

    let value = document.querySelector("#value").value;
    console.log("rest3put")
    $.ajax({url:"/example/rest3", // HTTP 통신할 경로 
        method : "PUT",  // HTML 메소드
        data : { "key" : value},
        success : (response)=>{console.log(response)} // HTTP 성공 응답 (웹서버측에서 보낸 return값) 처리 함수
        }
    )
}

//DELETE
function rest3delete(){
    let value = document.querySelector("#value").value;
    console.log("rest3delete")
    $.ajax({url:"/example/rest3", // HTTP 통신할 경로 
        method : "DELETE",  // HTML 메소드
        data : { "key" : value},
        success : (response)=>{console.log(response)} // HTTP 성공 응답 (웹서버측에서 보낸 return값) 처리 함수
        }
    )
}