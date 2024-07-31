let urlParams = new URL(location.href).searchParams;
let pageNo = parseInt(urlParams.get("page")) //페이지번호
//let category = parseInt(urlParams.get("cat")) //카테고리
if (isNaN(pageNo)){pageNo=1}
//if (isNaN(category)){category=0}
// 페이지화 변수
let totalBoardSize;
let totalPage;
let startBtn;
let endBtn;
// 페이지 정보 관리 객체
let pageInfo ={
    page : 1,   // 1. page : 현재페이지 기본값 : 1
    category : 0,   // 2. bcno : 카테고리번호 기본값 : 0
    searchKey : "btitle",   // 3. searchKey : 검색필드 기본값 : 제목
    searchKeyword : ''      // 4. searchKeyword : 검색필드값 기본값 : ""
}
console.log(pageInfo);
//페이지 오픈시 자동 실행
getall();
paging();
getCategory();
// 5. 검색 초기화
function searchClear(){
    // 입력창 초기화
    document.querySelector("#searchKey").value = "btitle";
    document.querySelector("#searchKeyword").value = "";
    // 전역변수 초기화
    pageInfo.searchKey = "btitle";
    pageInfo.searchKeyword = '';
    getall()
}
// 4. 카테고리 클릭
function catSelect(category){
    console.log("catSelect");
    pageInfo.category = category;
    pageInfo.page = 1;
    searchClear()
}
// 카테고리 출력
function getCategory(){
    // 1. 어디
    let categoryBox = document.querySelector("#category")
    // 2. 무엇
    let html = ''
    $.ajax({
        async:false,
        method:'get',
        url:"/board/getcategory",
        success:(result) =>{
            html+=`<div class="me-3 ${pageInfo.category==0?'categoryActive' : ''}" onclick="catSelect(0)">전체</div>`
            result.forEach(dto =>{
                html+=`<div class="mx-3 ${pageInfo.category==dto.bcno?'categoryActive' : ''}" onclick="catSelect(${dto.bcno})">${dto.bcname}</div>`;
            })
        }
    })
    // 3. 출력
    categoryBox.innerHTML=html;
}

//검색버튼 클릭
function search(){
    let sKey=document.querySelector("#searchKey").value
    let sKeyword=document.querySelector("#searchKeyword").value
    pageInfo.searchKey = sKey;
    pageInfo.searchKeyword = sKeyword;
    getall()
}

//게시글 출력
function getall(){ //getall(page, bcno)
    let board=document.querySelector('#list');
    let html='';
    getCategory()
    $.ajax({
        async : false,
        method:'get',
        url:"/board/all",
        data : {bcno : pageInfo.category, page : pageNo, searchKey : pageInfo.searchKey, searchKeyword : pageInfo.searchKeyword},
        success:result =>{
            totalBoardSize = result.totalBoardSize;
            totalPage = result.totalPage;
            startBtn = result.startBtn;
            endBtn = result.endBtn;
            result.data.forEach(result =>{
                html+=`<tr>
                        <th>${result.bno}</th>
                        <td>${result.bcname}</td>
                        <td><a href="/board/getread?page=${pageNo}&bno=${result.bno}" >${result.btitle}</a></td>
                        <td>${result.id}</td>
                        <td>${result.bdate}</td>
                        <td>${result.bview}</td>
                    </tr>`;
            });
        }
    })
    board.innerHTML=html;
    paging()
}


// 게시글 쓰기 버튼
function boardwrite(){
    console.log('boardwrite()');
    location.href='/board/write';
}

// 페이지 번호 출력
function paging(){
    let pageHtml = ``;
    // 이전 버튼
    pageHtml += `<li class="page-item"><a class="page-link" href="/board/getall?page=${pageNo-1 == 0 ? 1 : pageNo-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    </a></li>`
    // 페이지버튼
        // 페이지마다 시작 버튼 수
        // 페이지마다 끝 버튼 수
        // 최대 페이지 수
    for (i=startBtn;i<=endBtn;i++){
        pageHtml += `<li class="page-item"><a class="page-link ${i==pageNo?'active':''}" href="/board/getall?page=${i}">${i}</a></li>`
        
    }   
    // 다음버튼
    pageHtml += `<li class="page-item"><a class="page-link" href="/board/getall?page=${pageNo+1 <totalPage ? pageNo+1 : totalPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    </a></li>`
    document.querySelector(".pagination").innerHTML = pageHtml;
}