$(document).ready(function () {
    $("#menuTable").hide();
    $("#menuSearch").hide();
    $("#updateMenuTable").hide();
    $("#deleteMenuTable").hide();
    $("#addMenuTable").hide();

    $("#menuManager").click(function () {
        $("#userTable").hide();
        $("#deptTable").hide();
        $("#roleTable").hide();
        $("#userSearch").hide();
        $("#menuTable").show();
        $("#menuSearch").show();
        $("#userSearch").hide();
        $("#deptSearch").hide();
        $("#roleSearch").hide();


        var pageSize = $("#menu_pageSize option:selected").val();

        var pageObj = {
            pageNo: 1,
            pageSize: pageSize
        }

        $.ajax({
            url: "/menuList",
            type: "post",
            async: false,
            contentType: "application/json",
            data: JSON.stringify(pageObj),
            dataType: "json",
            success: function (data) {
                if(data.body != null){
                    setMenuTable(data)
                }
            }
        });

    });



    function setMenuTable(data) {
        var menuList = data.body.listBody;

        var menuTableBody = $("#menuTableBody");
        menuTableBody.empty();

        for(let i = 0;i < menuList.length; i++){

            menuTableBody.append("<tr class='menuItem'>" +
                "<td>"+menuList[i].menuId+"</td>" +
                "<td>"+menuList[i].menuCode+"</td>" +
                "<td>"+menuList[i].menuName+"</td>" +
                "<td>"+menuList[i].menuUrl+"</td>" +

                "<td class='updateAndDelete'><button class='updateMenu'>修改</button>&nbsp<button class='deleteMenu'>删除</button></td>"+
                "</tr>");
        }





        //渲染后为修改，删除绑定事件
        $(".updateMenu").click(function () {
            //点击修改弹出修改框
            $("#updateMenuTable").show();
            /*点击取消，隐藏*/
            $("#updateMenu_no").click(function () {
                $("#updateMenuTable").hide();
            });

            //获取该机构的id
            menuId = $(this).parent().prev().prev().prev().prev().text(),

                $("#updateMenu_yes").click(function(){
                    $("#updateMenuTable").hide();

                    var menu = {
                        menuId,
                        menuCode: $("#update_menuCode").val(),
                        menuName: $("#update_menuName").val(),
                        menuUrl: $("#update_menuUrl").val(),
                        updatedBy: $("#update_menuupdatedBy").val()
                    };
                    $.ajax({
                        url:"/updateMenu",
                        type:"post",
                        contentType:"application/json",
                        data:JSON.stringify(menu),
                        dataType:"json",
                        async:false,
                        success:function(data){

                        }
                    });
                });
        });

        $(".deleteMenu").click(function () {
            $("#deleteMenuTable").show();

            menuId = $(this).parent().prev().prev().prev().prev().text(),

                $("#deleteMenu_no").click(function () {
                    $("#deleteMenuTable").hide()
                });

            $("#deleteMenu_yes").click(function () {
                $("#deleteMenuTable").hide(),
                    $.ajax({
                        url:"/deleteMenu",
                        type:"post",
                        contentType:"application/json",
                        data:JSON.stringify(menuId),
                        dataType:"json",
                        async:false,
                        success:function(data){

                        }
                    });
            })
        })
    }


    $("#add_menu_btn").click(function(){
        $("#addMenuTable").show();


        /*点击取消，隐藏*/
        $("#addMenu_no").click(function () {
            $("#addMenuTable").hide();
        });

        $("#addMenu_yes").click(function(){
            $("#addMenuTable").hide();

            var menu = {
                menuName: $("#addMenuName").val(),
                menuCode: $("#addMenuCode").val(),
                menuUrl: $("#addMenuUrl").val(),
                status:1,

                createdBy: $("#addMenu_createdBy").val(),
                updatedBy: $("#addMenu_updatedBy").val()
            };

            $.ajax({
                url:"/addMenu",
                type:"post",
                contentType:"application/json",
                data:JSON.stringify(menu),
                dataType:"json",
                async:false,
                success:function(data){

                    if (data != null){
                    }

                }
            });

            $.ajax({
                url:"/menuList",
                type:"get",
                async:false,
                success:function(data){
                    if(data.body != null){
                        setMenuTable(data);
                    }

                }
            });

        });
    });





});