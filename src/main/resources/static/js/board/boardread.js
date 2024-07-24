//1.
let tableContent = [];
tableContent = JSON.parse(localStorage.getItem("tContent"))
console.log( new URL(location.href).searchParams); // 쿼리스트링(? 뒤 매개변수들)
console.log( new URL(location.href).searchParams.get("no")) // .get('key')

let urlParams = new URL(location.href).searchParams;
let currentBno = urlParams.get("no") //글번호

let accNo = 0; // 로그인 유저 번호
accNo = sessionStorage.getItem("loginNo")
if (accNo == null) {accNo = 0;}

let memberList = [];
memberList = JSON.parse(localStorage.getItem("mList"))
if (memberList == null) {memberList = []}

boardRead();
//2. 페이지 열릴 때 출력하기
function boardRead(){ // 어디에 무엇을 {boardNo : brdNo, title : bTitle, userId : uId, bDate : writtenDate, bView : view, bContent : content}
    let boardBox = document.querySelector("#boardBox")
    let divHTML = ''
    let foundItem = {}
    for (item of tableContent) {
        if (item.boardNo == curNo) {foundItem = item}
    }
    foundItem.bView ++
    localStorage.setItem('tContent', JSON.stringify(tableContent));
    divHTML += `<div class="row">글 번호 : ${foundItem.boardNo}</div>
            <div class="row">제목 : ${foundItem.title}</div>
            <div class="row">아이디 : ${foundItem.userId}</div>
            <div class="row">날짜 : ${foundItem.bDate}</div>
            <div class="row">조회수 : ${foundItem.bView}</div>
            <div class="row">내용 : ${foundItem.bContent}</div>
            `
    console.log(divHTML)
    document.querySelector("#boardBox").innerHTML = divHTML
}

//3. 삭제 : 현재 로그인된 회원과 작성자와 아이디가 같을때
function _delete(){
    let parity = checkParity();
    if (parity == false) {alert("권한이 없습니다"); return;}
    let loggedId = ''
    for (mem of memberList) { // 로그인된 유저 아이디
        if (mem.no == accNo) {loggedId = mem.id}
    }

    for (item of tableContent) {
        if (item.boardNo == curNo && item.userId == loggedId) {
            tableContent.splice(tableContent.indexOf(item), 1)
            localStorage.setItem('tContent', JSON.stringify(tableContent));
            alert("삭제성공"); location.href="board.html"; return;
        }
    }
}

function _edit(){
    let parity = checkParity();
    console.log("parity",parity)
    if (parity == true) {location.href="modify.html?no="+curNo; return;}
    alert("권한이 없습니다")
}

//유효성 체크 함수
function checkParity(){ console.log("checkparity")
    if (accNo < 1) {alert("권한이 없습니다"); return false;}
    let loggedId = ''
    for (mem of memberList) { // 로그인된 유저 아이디
        if (mem.no == accNo) {loggedId = mem.id}
    }

    for (item of tableContent) {
        if (item.boardNo == curNo && item.userId == loggedId) {
            return true;
        }
    }
    return false;
}
//http://192.168.30.186:5500/day19/index.html