$(document).ready(function () {

    //先隐藏用户表
    $("#userTable").hide();
    //隐藏新增弹出框
    $("#userSearch").hide();
    $("#addUserTable").hide();
    //隐藏修改弹出框
    $("#updateUserTable").hide();
    //隐藏删除弹出框
    $("#deleteUserTable").hide();


    //点击了用户管理，请求用户表并渲染
    $("#userManager").click(function(){
        $("#deptTable").hide();
        $("#roleTable").hide();
        $("#menuTable").hide();
        $("#userTable").show();
        $("#userSearch").show();
        $("#deptSearch").hide();
        $("#roleSearch").hide();
        $("#menuSearch").hide();

        var pageSize = $("#pageSize option:selected").val();

        var pageObj = {
            pageNo: 1,
            pageSize: pageSize
        };

        $.ajax({
            url:"/userList",
            type:"post",
            async:false,
            contentType: "application/json",
            data: JSON.stringify(pageObj),
            dataType: "json",
            success:function(data){
                if(data.body != null){
                    setTableData(data);
                }

            }
        });
    });




    /*新增弹出框*/
    $("#add_user_btn").click(function(){
        $("#addUserTable").show();

        /*点击取消，隐藏*/
        $("#no").click(function () {
            $("#addUserTable").hide();
        });

        /*点击确定，先隐藏新增弹出框，然后拿数据，最后发送添加用户请求*/
        $("#yes").click(function(){
            $("#addUserTable").hide();

            var user = {
                userName: $("#userName").val(),
                userCode: $("#userCode").val(),
                userPhone: $("#userPhone").val(),
                userEmail: $("#userEmail").val(),
                userPassword: $("#userPassword").val(),
                reUserPassword: $("#reUserPassword").val(),
                createdBy: $("#createdBy").val(),
                updatedBy: $("#updatedBy").val(),
                userGender: $('#userGender input[name="userSex"]:checked ').val(),
                status: $('#status input[name="status"]:checked ').val(),
                userDeptId: $('#userDeptId option:selected').val(),
                userRoleId: $('#userRoleId input[name="userRoleId"]:checked ').val()
        };

            $.ajax({
                url:"/addUser",
                type:"post",
                contentType:"application/json",
                data:JSON.stringify(user),
                dataType:"json",
                async:false,
                success:function(data){

                    if (data != null){
                    }

                }
            });

            $.ajax({
                url:"/userList",
                type:"get",
                async:false,
                success:function(data){
                    if(data.body != null){
                        setTableData(data);
                    }

                }
            });

        });
    });


    /*模糊查询*/
    $("#searchClick").click(function () {
        $("#userTable").show();

         var searchKey = $("#searchKey").val();
        var pageSize = $("#pageSize option:selected").val();

        var pageObj = {
            searchKey: searchKey,
            pageNo: 1,
            pageSize: pageSize
        };



        $.ajax({
            url:"/fuzzyQuery",
            type:"post",
            contentType:"application/json",
            data:JSON.stringify(pageObj),
            dataType:"json",
            async:false,
            success:function(data){
                if (data.body != null){
                    console.log(data);
                    setTableData(data);
                }
            }
        });

    });





    /*渲染*/
    function setTableData(data) {

        var userList = data.body.listBody;

        var userTableBody = $("#userTableBody");
        userTableBody.empty();

        // userTableBody.siblings().remove(".userItem");

        for(let i = 0;i < userList.length; i++){

            userTableBody.append("<tr class='userItem'>" +
                "<td>"+userList[i].userId+"</td>" +
                "<td>"+userList[i].userName+"</td>" +
                "<td>"+userList[i].userCode+"</td>" +
                "<td>"+userList[i].userGender+"</td>" +
                "<td>"+userList[i].userPhone+"</td>" +
                "<td>"+userList[i].js02Role.roleName+"</td>" +
                "<td>"+userList[i].js02Dept.deptName+"</td>" +
                "<td class='updateAndDelete'><button class='updateUser'>修改</button>&nbsp<button class='deleteUser'>删除</button></td>"+
                "</tr>");
        }


        //渲染后为修改，删除绑定事件
        $(".updateUser").click(function () {
            //点击修改弹出修改框
            $("#updateUserTable").show();
            /*点击取消，隐藏*/
            $("#update_no").click(function () {
                $("#updateUserTable").hide();
            });

            //获取该用户的id
            userId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text(),

            /*点击确定，先隐藏弹出框，然后拿数据，最后发送添加用户请求*/
            $("#update_yes").click(function(){
                $("#updateUserTable").hide();

                var user = {
                    userId,
                    userName: $("#update_userName").val(),
                    userCode: $("#update_userCode").val(),
                    userPhone: $("#update_userPhone").val(),
                    userEmail: $("#update_userEmail").val(),
                    userPassword: $("#update_userPassword").val(),
                    reUserPassword: $("#update_reUserPassword").val(),
                    createdBy: $("#update_createdBy").val(),
                    updatedBy: $("#update_updatedBy").val(),
                    userGender: $('#update_userGender input[name="userSex"]:checked ').val(),
                    status: $('#update_status input[name="status"]:checked ').val(),
                    userDeptId: $('#update_userDeptId option:selected').val(),
                    userRoleId: $('#update_userRoleId input[name="userRoleId"]:checked ').val()
                };
                $.ajax({
                    url:"/updateUser",
                    type:"post",
                    contentType:"application/json",
                    data:JSON.stringify(user),
                    dataType:"json",
                    async:false,
                    success:function(data){

                    }
                });
            });
        });

        $(".deleteUser").click(function () {
            $("#deleteUserTable").show();

              userId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text(),

                  $("#delete_no").click(function () {
                     $("#deleteUserTable").hide()
                  });

              $("#delete_yes").click(function () {
                  $("#deleteUserTable").hide(),
                  $.ajax({
                      url:"/deleteUser",
                      type:"post",
                      contentType:"application/json",
                      data:JSON.stringify(userId),
                      dataType:"json",
                      async:false,
                      success:function(data){

                      }
                  });
              })
        })

    }









});