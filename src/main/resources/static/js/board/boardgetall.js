let urlParams = new URL(location.href).searchParams;
let pageNo = parseInt(urlParams.get("page")) //페이지번호
let category = parseInt(urlParams.get("cat")) //카테고리
if (isNaN(pageNo)){pageNo=1}
if (isNaN(category)){category=0}
// 페이지화 변수
let totalBoardSize;
let totalPage;
let startBtn;
let endBtn;

//페이지 오픈시 자동 실행
getall();
paging();

//게시글 출력
function getall(){ //getall(page, bcno)
    let board=document.querySelector('#list');
    let html='';
    $.ajax({
        async : false,
        method:'get',
        url:"/board/all",
        data : {bcno : category, page : pageNo},
        success:result =>{
            totalBoardSize = result.totalBoardSize;
            totalPage = result.totalPage;
            startBtn = result.startBtn;
            endBtn = result.endBtn;
            result.data.forEach(result =>{
                html+=`<tr>
                        <th>${result.bno}</th>
                        <td>${result.bcname}</td>
                        <td><a href="/board/getread?cat=${category}&page=${pageNo}&bno=${result.bno}" >${result.btitle}</a></td>
                        <td>${result.id}</td>
                        <td>${result.bdate}</td>
                        <td>${result.bview}</td>
                    </tr>`;

            });
            board.innerHTML=html;
        }
    })

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
    pageHtml += `<li class="page-item"><a class="page-link" href="/board/getall?cat=${category}&page=${pageNo-1 == 0 ? 1 : pageNo-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    </a></li>`
    // 페이지버튼
        // 페이지마다 시작 버튼 수
        // 페이지마다 끝 버튼 수
        // 최대 페이지 수
    for (i=startBtn;i<=endBtn;i++){
        pageHtml += `<li class="page-item"><a class="page-link ${i==pageNo?'active':''}" href="/board/getall?cat=${category}&page=${i}">${i}</a></li>`
        
    }   
    // 다음버튼
    pageHtml += `<li class="page-item"><a class="page-link" href="/board/getall?cat=${category}&page=${pageNo+1 <totalPage ? pageNo+1 : totalPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    </a></li>`
    document.querySelector(".pagination").innerHTML = pageHtml;
}