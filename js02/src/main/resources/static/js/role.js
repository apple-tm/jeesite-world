$(document).ready(function () {
   $("#roleTable").hide();
   $("#roleSearch").hide();
   $("#updateRoleTable").hide();
   $("#deleteRoleTable").hide();
   $("#addRoleTable").hide();

   $("#roleManager").click(function () {
       $("#userTable").hide();
       $("#deptTable").hide();
       $("#menuTable").hide();
       $("#userSearch").hide();
       $("#roleTable").show();
       $("#roleSearch").show();
       $("#userSearch").hide();
       $("#deptSearch").hide();
       $("#menuSearch").hide();

       var pageSize = $("#role_pageSize option:selected").val();

       var pageObj = {
           pageNo: 1,
           pageSize: pageSize
       }

       $.ajax({
           url: "/roleList",
           type: "post",
           async: false,
           contentType: "application/json",
           data: JSON.stringify(pageObj),
           dataType: "json",
           success: function (data) {
               if(data.body != null){
                   setRoleTable(data)
               }
           }
       });

   });

  function setRoleTable(data) {


      var roleList = data.body.listBody;

      var roleTableBody = $("#roleTableBody");
      roleTableBody.empty();

      // userTableBody.siblings().remove(".userItem");

      for(let i = 0;i < roleList.length; i++){

          roleTableBody.append("<tr class='roleItem'>" +
              "<td>"+roleList[i].roleId+"</td>" +
              "<td>"+roleList[i].roleCode+"</td>" +
              "<td>"+roleList[i].roleName+"</td>" +
              "<td>"+roleList[i].roleGrade+"</td>" +

              "<td class='updateAndDelete'><button class='updateRole'>修改</button>&nbsp<button class='deleteRole'>删除</button></td>"+
              "</tr>");
      }




      //渲染后为修改，删除绑定事件
      $(".updateRole").click(function () {

          var pageObj = {
              pageNo: 1,
              pageSize: 5
          };


          $.ajax({
              url: "/menuList",
              type: "post",
              async: false,
              contentType: "application/json",
              data:JSON.stringify(pageObj),
              dataType: "json",
              success: function (data) {
                  if(data.body != null){

                      update_setRoleCheckbox(data);
                  }
              }
          });

          //点击修改弹出修改框
          $("#updateRoleTable").show();
          /*点击取消，隐藏*/
          $("#updateRole_no").click(function () {
              $("#updateRoleTable").hide();
          });

          //获取该用户的id
          roleId = $(this).parent().prev().prev().prev().prev().text(),

              $("#updateRole_yes").click(function(){
                  $("#updateRoleTable").hide();


                  var menuArr = new Array();

                  var checkedList = $("#update_menuForRole :checkbox:checked");
                  checkedList.each(function (i) {
                      menuArr[i] = {
                          menuId :$(this).val()
                      };

                  });


                  var role = {
                      roleId,
                      roleName: $("#update_roleName").val(),
                      roleCode: $("#update_roleCode").val(),
                      js02MenuList: menuArr,
                      updatedBy: $("#update_roleupdatedBy").val(),
                      roleGrade: $('#update_roleGrade option:selected').val(),
                  };
                  $.ajax({
                      url:"/updateRole",
                      type:"post",
                      contentType:"application/json",
                      data:JSON.stringify(role),
                      dataType:"json",
                      async:false,
                      success:function(data){

                      }
                  });
              });
      });

      $(".deleteRole").click(function () {
          $("#deleteRoleTable").show();

          roleId = $(this).parent().prev().prev().prev().prev().text(),

              $("#deleteRole_no").click(function () {
                  $("#deleteRoleTable").hide()
              });

          $("#deleteRole_yes").click(function () {
              $("#deleteRoleTable").hide(),
                  $.ajax({
                      url:"/deleteRole",
                      type:"post",
                      contentType:"application/json",
                      data:JSON.stringify(roleId),
                      dataType:"json",
                      async:false,
                      success:function(data){

                      }
                  });
          })
      })





  }




    $("#add_role_btn").click(function(){

        var pageObj = {
            pageNo: 1,
            pageSize: 5
        };


        $.ajax({
            url: "/menuList",
            type: "post",
            async: false,
            contentType: "application/json",
            data:JSON.stringify(pageObj),
            dataType: "json",
            success: function (data) {
                if(data.body != null){

                    setRoleCheckbox(data);
                }
            }
        });

        $("#addRoleTable").show();

        /*点击取消，隐藏*/
        $("#addRole_no").click(function () {
            $("#addRoleTable").hide();
        });

        $("#addRole_yes").click(function(){
            $("#addRoleTable").hide();

            var menuArr = new Array();

            var checkedList = $("#menuForRole :checkbox:checked");
            checkedList.each(function (i) {
                menuArr[i] = {
                    menuId :$(this).val()
                };

            });


            var role = {
                roleName: $("#addRoleName").val(),
                roleCode: $("#addRoleCode").val(),
                status:1,
                js02MenuList: menuArr,
                roleGrade: $("#addRoleGrade option:selected").val(),
                createdBy: $("#addRole_createdBy").val(),
                updatedBy: $("#addRole_updatedBy").val()
            };

            $.ajax({
                url:"/addRole",
                type:"post",
                contentType:"application/json",
                data:JSON.stringify(role),
                dataType:"json",
                async:false,
                success:function(data){

                    if (data != null){
                    }

                }
            });

            $.ajax({
                url:"/roleList",
                type:"get",
                async:false,
                success:function(data){
                    if(data.body != null){
                        setRoleTable(data);
                    }

                }
            });

        });
    });



  /*
  *1.获取菜单数据
  * 2.找到渲染html容器
  * 3.清空容器子元素
  * 4.for菜单数据向容器中追加复选框元素
  * */
  function setRoleCheckbox(data) {
      //
      var menuList = data.body.listBody;
      var menuForRole = $("#menuForRole");
      menuForRole.empty();

      for(let i = 0;i < menuList.length; i++){

          menuForRole.append(
              "<input class='role_menuCheckbox' type='checkbox' value="+menuList[i].menuId+" name='menuCheckbox' />"+menuList[i].menuName+"<br />"
          );
      }

  }

    function update_setRoleCheckbox(data) {
        //
        var menuList = data.body.listBody;
        var menuForRole = $("#update_menuForRole");
        menuForRole.empty();

        for(let i = 0;i < menuList.length; i++){

            menuForRole.append(
                "<input class='role_menuCheckbox' type='checkbox' value="+menuList[i].menuId+" name='menuCheckbox' />"+menuList[i].menuName+"<br />"
            );
        }

    }




});