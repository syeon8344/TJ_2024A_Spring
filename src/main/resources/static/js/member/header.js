//console.log("header")
doLoginCheck();
function doLoginCheck(){
    $.ajax({
        method : "GET",
        url : "/member/login/check",
        success : result => {
            if (result != ""){
            } //else {console.log("비로그인")}
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