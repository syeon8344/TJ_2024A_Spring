//1.
let urlParams = new URL(location.href).searchParams;
let currentBno = parseInt(urlParams.get("bno")) //글번호
//let category = parseInt(urlParams.get("cat")) //카테고리
let pageNo = parseInt(urlParams.get("page"))
//if (isNaN(category)){category=0}
viewPlus();
boardRead();
// 1. 조회수 증가
function viewPlus(){
    $.ajax({
        async : false,
        method : "PUT",
        url : "/board/view",
        data : {bno : currentBno},
    })
}
// 2. 댓글 쓰기
function writeReply(){
    // 1. 값 가져오기
    let brcontent = document.querySelector(".brcontent").value
    let bno = pageNo
    let brindex = 0

    $.ajax({
        method : "POST", url : "/board/reply/write",
        data : JSON.stringify({brindex:0, brcontent : brcontent, bno : currentBno}), // 문자열화 JSON 데이터 객체
        contentType : "application/json",
            // contentType : "application/x-www-form-urlencoded" : AJAX 기본값 (contentType 생략시)
            // contentType : 'application/json' : JSON 객체 타입
            // contentType : false, processData : false --> contentType : multipart/form-data raw데이터 보낼 시 (첨부파일 등)
        success : r => {
            alert("r = "+r)
            if (r) {
                alert("댓글쓰기 성공")
                document.querySelector(".brcontent").value = "";
            }
            else {alert("댓글쓰기 실패, 로그인 여부를 확인해 주세요")}
        }
    })

}
//2. 페이지 열릴 때 출력하기 + 수정페이지 가는 버튼
function boardRead(){ // 어디에 무엇을 {boardNo : brdNo, title : bTitle, userId : uId, bDate : writtenDate, bView : view, bContent : content}
    let boardBox = document.querySelector("#boardBox")
    let buttonBox = document.querySelector("#buttonBox")
    let divHTML = ''
    $.ajax({
        async:false,
        method:'get',
        url:"/board/read",
        data:{bno:currentBno},
        success:result =>{
            divHTML = `
            <span>글 번호 : ${result.bno} &nbsp;&nbsp;&nbsp;&nbsp; 카테고리 : ${result.bcname} &nbsp;&nbsp;&nbsp;&nbsp; 조회수 : ${result.bview}</span></br>
            <span>아이디 : ${result.id} &nbsp;&nbsp;&nbsp;&nbsp; 작성일 : ${result.bdate}</span></br>
            <p>제목 : ${result.btitle}</p>
            <p>내용 : ${result.bcontent}</p>
            `
            if (result.bfile!=null){
                divHTML += `<p>첨부파일 : ${result.bfile}</p> <a href="/file/download?filename=${result.bfile}">다운로드</a>`
            }
            boardBox.innerHTML=divHTML;
            if (ownCheck()){
                buttonBox.innerHTML = `<button class="btn btn-success my-3" type="button" onclick="_delete()">삭제</button>
                                    <button class="btn btn-success my-3" type="button" onclick="_edit()">수정</button>`
            }
            
        }
        
    })
    
}
function ownCheck(){
    let res = false;
    $.ajax({
        async : false,
        method : "GET",
        url : "/board/authorize",
        data : {bno:currentBno},
        success : r => {
            console.log("r = " + r);
            if (r==true){
                res = true;
            }
        }
    })
    return res;
}
function _edit(){
    $.ajax({
        async : false,
        method : "GET",
        url : `/board/edit/check?bno=${currentBno}`,
        success : response => {
            if (!response){
                alert("본인이 작성한 글만 수정 가능합니다.")
            } else {
                location.href=`/board/edit?page=${pageNo}&bno=${currentBno}`
            }
        }
    })
}

// 3. 페이지 삭제하기
function _delete(){
    $.ajax({
        async : false,
        method : "DELETE",
        url : "/board/delete?bno="+currentBno,
        success : r => {
            if(r){
                alert("글 삭제 완료. 이전 화면으로 돌아갑니다.")
                location.href="/board/getall"
            } else {
                alert("글 삭제 실패, 본인이 작성한 글인지 확인해주세요.")
            }
        }
    })
}

// 뒤로 가기
function goBack(){
    location.href=`/board/getall?page=${pageNo}`
}