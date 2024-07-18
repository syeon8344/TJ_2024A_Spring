getMyInfo()

function getMyInfo(){
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/my/info",
        success : response => {
            if (response.id){
                document.querySelector("#no").innerHTML = response.no
                document.querySelector("#id").innerHTML = response.id
                document.querySelector("#phone").innerHTML = response.phone
                document.querySelector("#name").innerHTML = response.name
                document.querySelector("#email").innerHTML = response.email
            } else {
                alert("먼저 로그인해 주세요.")
                location.href="/member/login"
            }
        }
    })
}