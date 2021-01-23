$(document).ready(function () {

    $("#deptTable").hide();
    $("#deptSearch").hide();
    $("#addDeptTable").hide();
    //隐藏删除弹出框
    $("#deleteDeptTable").hide();
    //隐藏修改弹出框
    $("#updateDeptTable").hide();

    $("#deptManager").click(function () {
        $("#userTable").hide();
        $("#roleTable").hide();
        $("#menuTable").hide();
        $("#userSearch").hide();
        $("#deptTable").show();
        $("#deptSearch").show();
        $("#userSearch").hide();
        $("#roleSearch").hide();
        $("#menuSearch").hide();

        var pageSize = $("#dept_pageSize option:selected").val();

        var pageObj = {
            pageNo: 1,
            pageSize: pageSize
        }

        $.ajax({
            url: "/deptList",
            type: "post",
            async: false,
            contentType: "application/json",
            data: JSON.stringify(pageObj),
            dataType: "json",
            success: function (data) {
                if(data.body != null){
                    setDeptTable(data)
                }
            }
        });









    });

    function setDeptTable(data) {
        var deptList = data.body.listBody;

        var deptTableBody = $("#deptTableBody");
        deptTableBody.empty();

        // userTableBody.siblings().remove(".userItem");

        for(let i = 0;i < deptList.length; i++){

            deptTableBody.append("<tr class='deptItem'>" +
                "<td>"+deptList[i].deptId+"</td>" +
                "<td>"+deptList[i].deptCode+"</td>" +
                "<td>"+deptList[i].deptName+"</td>" +
                "<td>"+deptList[i].deptGrade+"</td>" +

                "<td class='updateAndDelete'><button class='updateDept'>修改</button>&nbsp<button class='deleteDept'>删除</button></td>"+
                "</tr>");
        }




        $("#add_dept_btn").click(function(){
            $("#addDeptTable").show();

            /*点击取消，隐藏*/
            $("#addDept_no").click(function () {
                $("#addDeptTable").hide();
            });

            /*点击确定，先隐藏新增弹出框，然后拿数据，最后发送添加用户请求*/
            $("#addDept_yes").click(function(){
                $("#addDeptTable").hide();

                var dept = {
                    deptName: $("#addDeptName").val(),
                    deptCode: $("#addDeptCode").val(),
                    status:1,

                    deptParentId: $("#addDeptParent option:selected").val(),
                    deptGrade: $("#addDeptGrade option:selected").val(),
                    createdBy: $("#addDept_createdBy").val(),
                    updatedBy: $("#addDept_updatedBy").val()
                };

                $.ajax({
                    url:"/addDept",
                    type:"post",
                    contentType:"application/json",
                    data:JSON.stringify(dept),
                    dataType:"json",
                    async:false,
                    success:function(data){

                        if (data != null){
                        }

                    }
                });

                $.ajax({
                    url:"/deptList",
                    type:"get",
                    async:false,
                    success:function(data){
                        if(data.body != null){
                            setDeptTable(data);
                        }

                    }
                });

            });
        });





        //渲染后为修改，删除绑定事件
        $(".updateDept").click(function () {
            //点击修改弹出修改框
            $("#updateDeptTable").show();
            /*点击取消，隐藏*/
            $("#updateDept_no").click(function () {
                $("#updateDeptTable").hide();
            });

            //获取该机构的id
            deptId = $(this).parent().prev().prev().prev().prev().text(),

                /*点击确定，先隐藏弹出框，然后拿数据，最后发送修改机构请求*/
                $("#updateDept_yes").click(function(){
                    $("#updateDeptTable").hide();

                    var dept = {
                        deptId,
                        deptCode: $("#update_deptCode").val(),
                        deptName: $("#update_deptName").val(),
                        updatedBy: $("#update_deptupdatedBy").val(),

                        deptGrade: $('#update_deptGrade option:selected').val(),
                    };
                    $.ajax({
                        url:"/updateDept",
                        type:"post",
                        contentType:"application/json",
                        data:JSON.stringify(dept),
                        dataType:"json",
                        async:false,
                        success:function(data){

                        }
                    });
                });
        });

        $(".deleteDept").click(function () {
            $("#deleteDeptTable").show();

            deptId = $(this).parent().prev().prev().prev().prev().text(),

                $("#deleteDept_no").click(function () {
                    $("#deleteDeptTable").hide()
                });

            $("#deleteDept_yes").click(function () {
                $("#deleteDeptTable").hide(),
                    $.ajax({
                        url:"/deleteDept",
                        type:"post",
                        contentType:"application/json",
                        data:JSON.stringify(deptId),
                        dataType:"json",
                        async:false,
                        success:function(data){

                        }
                    });
            })
        })




    }



});