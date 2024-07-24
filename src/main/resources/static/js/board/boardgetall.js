console.log('getall.js');
getall()
function getall(){
    let board=document.querySelector('#list');
    let html='';
    $.ajax({
        method:'get',
        url:"/board/all",
        success:result =>{
            result.forEach(result =>{
                html+=`<tr>
                        <th>${result.bno}</th>
                        <td><a href="/board/read?bno=${result.bno}" >${result.btitle}</a></td>
                        <td>${result.id}</td>
                        <td>${result.bdate}</td>
                        <td>${result.bview}</td>
                    </tr>`;

            });
            board.innerHTML=html;
        }
    })

}

function boardRead(bno){
    console.log("bRead")
    $.ajax({
        method : "GET",
        url : "/board/read?bno="+bno,
    })
}

function boardwrite(){
    console.log('boardwrite()');
    location.href='/board/write';
}