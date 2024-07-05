console.log("phonebook script open");
let phonebookDB = [];
getPhone();
//1.
function postPhone(){
    // 1. 입력받기
    let name = document.querySelector("#name").value;
    let phone = document.querySelector("#phone").value;

    // 2. 객체화
    let phoneDto = {name : name, phone : phone};
    // 3. 배열 저장
    phonebookDB.push(phoneDto);
    // 4. 안내//새로고침
    alert("saved");
    getPhone();
}

//2. 출력 : 등록 처리 되었을때, js 오픈시 최초 한번
function getPhone(){
    // 1. 어디에
    let phoneListBox = document.querySelector("#phoneListBox");
    // 2. 무엇을
    let html = "";
    phonebookDB.forEach(phone =>{
        html += `<span>${phone.name}</span>
        <span>${phone.phone}</span><br>`
        });

    // 3. 출력
    phoneListBox.innerHTML = html;
}