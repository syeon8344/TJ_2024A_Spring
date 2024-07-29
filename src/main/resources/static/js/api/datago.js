/*
    - 공공데이터 : https://www.data.go.kr
        - 공공기관에서 제공하는 정보들
        - 1. 로그인 (간편로그인)
        
    - 카카오 지도 API : https://developers.kakao.com/
        1. 로그인
        2. 플랫폼 신청
            - 상단 메뉴 > 내 애플리케이션 > 애플리케이션 추가
        3. 애플리케이션 선택/클릭
            사이드바 메뉴
                APP KEY : JavaScript 키 확인, 카카오지도 활용
                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=< JS 앱 키 >&libraries=services,clusterer,drawing"></script>

*/
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(37.47401607, 126.6432441), //지도의 중심좌표
	level: 6 //지도의 레벨(확대, 축소 정도, 0~14(최대 축소))
};
var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
let positions = [];

apiPharmacy()
function apiPharmacy(){
    let htmlP = ``;
    $.ajax({
        async : false,
        method : "GET",
        url : "https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=35&serviceKey=vNfgLVxLbZXXsph0YXXYowJbj0l2sFWuPbYbstVgs%2BsEov%2FEIdlED3TYBGt5RrDDIFSmEhw5a53IAvqgm5AszQ%3D%3D",
        success : r => {
            r.data.forEach(e => {
                htmlP += `<tr><td>${e.약국명}</td><td>${e["전화번호"]}</td><td>${e['소재지도로명주소']}</td></tr>`
                /*
                4. 마커를 표시할 위치와 title 객체 배열
                    positions : 여러개 마커의 위도/경도  ,  { } : 하나의 마커의 위도/경도 , latlng : new kakao.maps.LatLng( 위도 , 경도 )
                        - 1. 리스트명.forEach( 반복요소 => {  } );                               : 리스트내 요소를 하나씩 반환해서 반복
                        - 2. let 반환리스트 = 리스트명.map( 반복요소 => {  return 반환값 } );       : 리스트내 요소를 하나씩 반환해서 반복
                            차이점 : 실행문 결과값을 반환 가능 , 반복 반환 결과를 배열로 최종 반환
                let positions = [ ]
                for( let i = 0 ; i < data.length ; i++ ){
                    // 1. 객체 생성
                    let location = { title : data[i].약국명 , latlng : new kakao.maps.LatLng( data[i].위도 , data[i].경도 ) }
                    positions.push( location );
                }
                let pos = new Object;
                pos.title = e.약국명;
                pos.latlng = new kakao.maps.LatLng(e.위도, e.경도)
                positions.push(pos)
                */
            })
            document.querySelector("#tbodyPharm").innerHTML = htmlP;
            // 새로운 리스트 = 리스트.map(요소 => {함수, return 새로운 리스트에 추가할 객체})
            positions = r.data.map(  d => {
                // 1. 객체 생성
                let location = { title : d.약국명 , latlng : new kakao.maps.LatLng( d.위도 , d.경도 )  }
                // 2. 객체 리턴
                return location; // 반복하면서 생성된 객체를 반환해서 positions 배열에 최종 대입
            });
        }
        
    })


    for (var i = 0; i < positions.length; i ++) {
        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35); 
        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image : markerImage // 마커 이미지 
            });
        }
}

api()
function api(){
    let html = ``;
    $.ajax({
        async : false,
        method : "GET",
        url : "https://api.odcloud.kr/api/15102672/v1/uddi:5e2a4b30-28fb-4e8f-bc44-9a6db8a6a8db?page=1&perPage=39&serviceKey=vNfgLVxLbZXXsph0YXXYowJbj0l2sFWuPbYbstVgs%2BsEov%2FEIdlED3TYBGt5RrDDIFSmEhw5a53IAvqgm5AszQ%3D%3D",
        success : r => {
            
            r.data.forEach(e => {
                html += `<tr><td>${e['순번']}</td><td>${e.상호}</td><td>${e["전화번호"]}</td><td>${e.주소}</td></tr>`

            });
            document.querySelector("#tbody").innerHTML = html;
        }
    })

    
}
