console.log('boardwrite.js');

getCategory();
function getCategory(){
    // 어디에
    let category = document.querySelector("#category");
    let html = ``;
    // 무엇을
    $.ajax({
        method:'get',
        url:"/board/getcategory",
        success:(result) =>{
                console.log(result);
                for(let i = 0; i < result.legnth; i++){
                    html+=`<option value="${result[i].bcno}">${result[i].bcname}</option>`;
                }
            category.innerHTML=html;
        }
    })
}

