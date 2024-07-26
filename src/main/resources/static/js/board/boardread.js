//1.
let urlParams = new URL(location.href).searchParams;
let currentBno = parseInt(urlParams.get("bno")) //글번호
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
            <div class="form-control">
            <span>글 번호 : ${result.bno} &nbsp;&nbsp;&nbsp;&nbsp; 카테고리 : ${result.bcname} &nbsp;&nbsp;&nbsp;&nbsp; 조회수 : ${result.bview}</span></br>
            <span>아이디 : ${result.id} &nbsp;&nbsp;&nbsp;&nbsp; 작성일 : ${result.bdate}</span></br>
            <div class="form-control">
            <p>제목 : ${result.btitle}</p>
            <p>내용 : ${result.bcontent}</p>
            `
            if (result.bfile!=null){
                divHTML += `<p>첨부파일 : ${result.bfile}</p> <a href="/file/download?filename=${result.bfile}">다운로드</a>`
            }
            boardBox.innerHTML=divHTML;
            buttonBox.innerHTML = `<button class="btn btn-success my-3" type="button" onclick="_delete()">삭제</button>
                                   <button class="btn btn-success my-3" type="button" onclick="_edit()">수정</button>`
        }
        
    })
    
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
                location.href=`/board/edit?bno=${currentBno}`
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

