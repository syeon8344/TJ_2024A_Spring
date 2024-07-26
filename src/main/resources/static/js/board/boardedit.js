//1.
let urlParams = new URL(location.href).searchParams;
let currentBno = parseInt(urlParams.get("bno")) //글번호
getCategory()
loadText()
console.log(currentBno);
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
    let newTitle = document.querySelector("#btitle").value
    let newContent = document.querySelector("#bcontent").value
    let newCategory = document.querySelector("#category").value
    $.ajax({
        method : "PUT",
        url : "/board/edit",
        data : JSON.stringify({bno : currentBno, btitle : newTitle, bcontent : newContent, bcno : newCategory}),
        contentType : "application/JSON",
        success : r => {
            if (r) {
                alert("글 수정 완료!")
                history.go(-1)
            } else {
                alert("글 수정 실패")
            }
        }
    })
}