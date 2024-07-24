console.log('boardwrite.js');

getCategory();

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
                console.log(result);
                result.forEach(dto =>{
                    
                    html+=`<option value="${dto.bcno}">${dto.bcname}</option>`;
                })
            category.innerHTML=html;
        }
    })
}

function _write(){
    //{"bcno" : bcno, "btitle" : btitle, "bcontent" : bcontent}
    let btitle = document.querySelector("#btitle").value
    let bcontent = document.querySelector("#bcontent").value
    let bcno = document.querySelector("#category").value
    let dto = {btitle : btitle, bcontent : bcontent, bcno : bcno}
    let login = false;
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/my/info",
        success : response => {
            if (response.id){
                login = true;
            }
        }
    })
    if (!login){
        alert("로그인이 필요한 기능입니다.")
        location.href="/member/login"
        return;
    }
    $.ajax({
        async : false,
        method : "POST",
        url : "/board/write",
        data : JSON.stringify(dto),
        contentType : "application/json",
        success : resp => {
            if (resp) {
                alert("글쓰기 완료.")
                location.href="/board/getall"
            } else {
                alert("글쓰기 오류.")
            }
        }
    })
}

