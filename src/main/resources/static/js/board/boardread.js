//1.
let urlParams = new URL(location.href).searchParams;
let currentBno = parseInt(urlParams.get("bno")) //글번호

boardRead();
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
            <p>카테고리 : ${result.bcname}<p>
            <p>글 번호 : ${result.bno}<p>
            <p>제목 : ${result.btitle}<p>
            <p>내용 : ${result.bcontent}<p>
            <p>아이디 : ${result.id}<p>
            <p>작성일 : ${result.bdate}<p>
            <p>조회수 : ${result.bview}<p>
            `
            boardBox.innerHTML=divHTML;
            buttonBox.innerHTML = `<button class="btn btn-success my-3" type="button" onclick="_delete()">삭제</button>
                                   <button class="btn btn-success my-3" type="button" onclick="location.href='/board/edit?bno=${currentBno})">수정</button>`
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

