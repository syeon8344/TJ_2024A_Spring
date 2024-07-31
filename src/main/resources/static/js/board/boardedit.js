//1.
let urlParams = new URL(location.href).searchParams;
let currentBno = parseInt(urlParams.get("bno")) //글번호
let pageNo = parseInt(urlParams.get("page")) //글번호
getCategory()
loadText()
console.log(currentBno);

$(document).ready(function() {
    $('#bcontent').summernote({
        height : 500,
        lang : "ko-KR"
    });
  });
// 기존 글 띄우기
function loadText(){ 
    $.ajax({
        async:false,
        method:'get',
        url:"/board/read",
        data:{bno:currentBno},
        success: r =>{
            document.querySelector("#category").value = r.bcno
            document.querySelector("#btitle").value = r.btitle
            document.querySelector("#bcontent").value = r.bcontent
        }
        
    })
    
}
// 카테고리 불러오기
function getCategory(){
    // 어디에
    let category = document.querySelector("#category");
    let html = ``;
    // 무엇을
    $.ajax({
        async:false,
        method:'get',
        url:"/board/getcategory",
        success:(result) =>{
                result.forEach(dto =>{
                    html+=`<option value="${dto.bcno}">${dto.bcname}</option>`;
                })
            category.innerHTML=html;
        }
    })
}

function _edit(){
    // 1. form 가져오기, form 안에 있는 HTML모두 한번에 가져오기
    let boardWriteForm = document.querySelector("#boardBox")
    // 2. form HTML을 바이트로 변환하기
    let boardWriteFormData = new FormData(boardWriteForm);
    $.ajax({
        method : "PUT",
        url : "/board/edit",
        data : boardWriteFormData,
        contentType : false, processData : false,
        success : r => {
            if (r) {
                alert("글 수정 완료!")
                location.href=`/board/getread?page=${pageNo}&bno=${currentBno}`
            } else {
                alert("글 수정 실패")
            }
        }
    })
}