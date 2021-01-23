$(document).ready(function(){
    $("#btn_login").click(function () {

        var user = {
            userPhone:$("#login_phoneNumber").val(),
            userPassword:$("#login_password").val(),

        };
        $.ajax({
            url:"/userLogin",
            type:"post",
            contentType:"application/json",
            data:JSON.stringify(user),
            dataType:"json",
            async:true,
            success:function(data){
                localStorage.setItem("userInfo",JSON.stringify(data.body.js02Role.js02MenuList));
                if (data.respCode == 0) {
                    // $.ajax({
                    //     url:"/homePage",
                    //     type:"get",
                    //     contentType:"application/json",
                    //     dataType:"html",
                    //     async:true,
                    //     success:function(data2){
                    //         $("html").html(data2);
                    //         setMenuLi();
                    //     }
                    // });
                    window.location.href="http://127.0.0.1:8081/homePage";
                    setMenuLi();
                }
            }
        });


    });






});