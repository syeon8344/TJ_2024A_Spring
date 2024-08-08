console.log('register.js')

function onRegister(){
    console.log("onRegister()")
    // 대용량 첨부파일 보내기
    // 1. 폼 가져오기
    const productForm = document.querySelector("#productForm")
    // 2. 폼데이터를 바이트(binary) 로 변환 (첨부파일은 JSON/TEXT등으로 보낼 수 없음)
    const productFormData = new FormData(productForm)
    for(pair of productFormData.entries()){
        console.log(pair[0], pair[1])
    }
    console.log(productFormData.entries());
    
    // 3. ajax 이용한 데이터 전송
    $.ajax({
        async : false, method : "POST", url : "/product/register",
        contentType : false, processData : false, // multipartFile ajax 표현방법
        data : productFormData,
        success : r => {
            console.log("success " + r);
            alert("제품 등록 완료.")
        },
        error : e => {
            console.log("error " + e);
            alert("제품 등록 실패.")
        }
    })

}