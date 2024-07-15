//let todoList = [] // 1달째 JS에서 메모리 관리, 3달째 : 웹서버(Spring -> Tomcat)에서 관리하므로 필요 X

console.log("todo.js")
todoReadAll()

// 함수 종류
// 1. 일반 함수 : function response(result){}
// 2. 익명 함수 : function(result){}
// 3. 람다/화살표 함수 : result => {} (매개변수가 result 하나일 때 괄호 생략)

// 1. 추가
function addTodoList(){
    // [1] 입력받은 HTML 값 가져오기
    console.log("adding")
    let input = document.querySelector("#item").value
    console.log(input)
    $.ajax({ //$ : JQuery 문법, ajax : 웹서버와 통신, jQuery에 포함됨
        method : "post",
        url : "/day08/todo?tcontent=" + input,
        success : response => {
            if (response == true)
                console.log("post Success!")
            todoReadAll();
            document.querySelector("#item").value = ""
        }
    })
}
// 목록 출력
function todoReadAll(){
    console.log("reading")
    $.ajax({
        url : "/day08/todo",
        method : "get",
        success : response => { // ArrayList<TodoDto>
            console.log("todoReadAll Success!")
            let html = ""
            response.forEach(todoDto => {
                let bgColor;
                let color;
                if (todoDto.tstate == 0){
                    bgColor = "#FFFFFF"
                    color = "#000000"
                }
                else if (todoDto.tstate == 1){
                    bgColor = "#767373"
                    color = "#FFFFFF"
                }
                html += `<li id="list${todoDto.tno}" style="background-color: ${bgColor}; color: ${color}; font-weight: bold;">
                        <p id="text${todoDto.tno}">${todoDto.tcontent}</p>
                        <div>
                            <button type="button" onclick="todoChange(${todoDto.tno})">변경</button>
                            <button type="button" onclick="todoDelete(${todoDto.tno})">삭제</button>
                        </div>
                    </li>`
                })
            document.querySelector("#list").innerHTML = html
        }
    })
}
// 3. 수정
function todoChange(tno){
    $.ajax({
        method : "put",
        url : "/day08/todo?tno=" + tno,
        success : response => {
            if (response == true){
                console.log("Change Success!")
                todoReadAll();
            }
        }
    })
}
// 4. 삭제
function todoDelete(tno) {
    $.ajax({
        method : "delete",
        url : "/day08/todo",
        data : {tno : tno}, // tno 키값과 현재 함수의 매개변수 tno를 밸류값으로 묶어 전송 
        success : response => {
            if (response == true){
                console.log("Deletion Success!")
                todoReadAll();
            }
        }
    })
}

/* function uploadItem() {
    let item = document.querySelector('#item').value;
    let html = ''
    for (i=0; i<todoList.length; i++) {
        html += `<li id="list${i}" style="background-color: #FFFFFF; font-weight: bold; text-decoration-line: none;>
                    <span>${todoList[i]}</span>
                    <div>
                        <button onclick="changeStyle()">변경</button>
                        <button onclick="itemDelete(${i})">삭제</button>
                    </div>
                </li>`
    }
    document.querySelector("#").innerHTML = html
}
function printTodoList(){
    let html = "";
    for(i = 0; i < todoList.length; i++){
        html += `<li id="list${i}" style="background-color: #FFFFFF; font-weight: bold;">
                <p id="text${i}">${todoList[i]}</p>
                <div>
                    <button type="button" onclick="changeStyle(${i})">변경</button>
                    <button type="button" onclick="itemDelete(${i})">삭제</button>
                </div>
            </li>`
    }
    console.log(html)
    document.querySelector("#list").innerHTML = html;
}
function changeStyle(x) {
    console.log("CHANGE")
    console.log(document.getElementById(`list${x}`))
    let color = document.getElementById(`list${x}`).style.backgroundColor
    console.log(color)
    if (color == "rgb(255, 255, 255)") {
        console.log("CHANGE1")
        document.getElementById(`list${x}`).style.backgroundColor = "rgb(0, 0, 0)"
        document.getElementById(`text${x}`).style.textDecorationLine = "line-through"
        document.getElementById(`text${x}`).style.color = "rgb(255, 255, 255)"

    }
    if (color == "rgb(0, 0, 0)") {
        document.getElementById(`list${x}`).style.backgroundColor = "rgb(255, 255, 255)"
        document.getElementById(`text${x}`).style.textDecorationLine = "none"
        document.getElementById(`text${x}`).style.color = "rgb(0, 0, 0)"
    }
}*/


