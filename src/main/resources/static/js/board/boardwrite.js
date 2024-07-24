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
        success:result =>{
            result.forEach(result =>{
                console.log(result);
                html+=`<select>
                    <option value="${result[0].bcno}">${result[0].bcname}</option>
                    <option value="${result[1].bcno}">${result[1].bcname}</option>
                    <option value="${result[2].bcno}">${result[2].bcname}</option>
                    <option value="${result[3].bcno}">${result[3].bcname}</option>
                    </select> `;

            });
            category.innerHTML=html;
        }
    })
}

