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
    
}

