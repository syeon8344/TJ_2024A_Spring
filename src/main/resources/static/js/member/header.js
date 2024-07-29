//console.log("header")
doLoginCheck();
function doLoginCheck(){
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/login/check",
        success : result => {
            if (result != ""){
                document.querySelector("#loginMenu").innerHTML = `<li class="nav-item">
                                            <a class="nav-link" href="/member/mypage">마이페이지</a>
                                        </li>
                                        <li class="nav-item">
                                        <a class="nav-link" href="#" onclick="doLogOut()">로그아웃</a>
                                        </li>`
            }else {
                document.querySelector("#loginMenu").innerHTML = `<li class="nav-item">
                                            <a class="nav-link" href="/member/signup">회원가입</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/member/login">로그인</a>
                                        </li>`
            }
        }
    })
}
function doLogOut(){
    $.ajax({
        method : "GET",
        url : "/member/logout",
        success : result => {//console.log(result);
            location.href="/";
        }
    })
}