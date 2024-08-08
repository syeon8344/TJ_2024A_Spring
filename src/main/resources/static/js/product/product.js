getProductList()
function getProductList(){
    // 4. 등록 완료후 이미지 띄우기
    let imageBox = document.querySelector("#imageBox")
    let imageHtml = ""
    $.ajax({
        async : false,
        method : "GET",
        url : "/product/getProductList",
        success : r => {
            console.log("getProductList() Response")
            console.log(r);
            r.forEach(dto => {
                // 각 DTO = 제품 레코드, 제품 정보를 행 하나씩 출력
                imageHtml += `<tr><td>${dto.pno}</td><td>${dto.pname}</td><td>${dto.pcontent}</td><td>${dto.pprice}</td><td>${dto.pdate}</td><td>${dto.pview}</td><td class="imageTd">`
                for(names of dto.fileNames){ // 파일명 목록을 폴더 주소에 대입해서 여러개의 이미지 불러오기
                    imageHtml += `<img width="150px" height="150px" src="/upload/${names}"/>`
                }
                imageHtml += "</td></tr>"
            });
            imageBox.innerHTML = imageHtml;

            /*
             *  <div> 로 출력하기  
             *  // 여러 개 제출
             *  r.forEach( product => {
             *      imageHtml += `<div class="productBox">` // 제품 1개당 div 하나씩 div start
             *      // 여러개의 이미지 파일, 제품마다의 이미지
             *      product.fileNames.forEach(name => {
             *          // 업로드된 이미지 파일명을 서버로 요청, 응답 받아 img마크업 src속성에 대입
             *          imageHtml += `<img style="width:100px;" src="/upload/${name}"/>`
             *      })
             *      imageHtml += `</div>` // 제품 div end
             *  }) // r 반복문 끝
             * 
            */
        }
    })
}