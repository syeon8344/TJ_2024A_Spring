let todoList = []

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
} */

function uploadItem() {
    todoList.push(document.querySelector('#item').value)
    let index = todoList.length -1
    let html = document.querySelector("#list").innerHTML
    html += `<li id="list${index}" style="background-color: #FFFFFF; font-weight: bold;">
                <p id="text${index}">${todoList[index]}</p>
                <div>
                    <button type="button" onclick="changeStyle(${index})">변경</button>
                    <button type="button" onclick="itemDelete(${index})">삭제</button>
                </div>
            </li>`
    document.querySelector("#list").innerHTML = html
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
}

function itemDelete(x) {
    document.getElementById(`list${x}`).remove()
}
