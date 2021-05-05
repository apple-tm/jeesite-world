<template>

  <div class="container" @mousemove="lastTimeEvent">
    <div class="row clearfix">
      <!--导航栏-->

      <div class="col-md-3 column">
        <ul class="nav nav-pills nav-stacked">
          <!--<li v-for="menu in userMenus" v-show="menu.id" @click="showModel(menu)">{{menu.menuName}}</li>-->
          <li v-show="isShowHome" @click="showModel('home')">首页</li>
          <li v-show="isShowUser" @click="showModel('userManager')">用户管理</li>
          <li v-show="isShowRole" @click="showModel('roleManager')">角色管理</li>
          <li v-show="isShowDept" @click="showModel('deptManager')">机构管理</li>
          <li v-show="isShowMenu" @click="showModel('menuManager')">菜单管理</li>
          <li v-show="isShowLog" @click="showModel('log')">日志管理</li>

        </ul>
      </div>

      <log ref="log"
        :page-index="currentPage"
           :total="count"
           :page-size="pageSize"
           :showlog="isShowLogModel"
           @change="pageChange"></log>
      <!--首页-->
      <div id="homeModel"  v-show="isShowHomeModel" class="col-md-9 column">


        <form class="form-inline" role="form">
          <div class="form-group">
          <label class="sr-only" for="name">名称</label>
          <input v-model="searchKey" type="text" class="form-control"  placeholder="请输入关键字">
          <button @click="fuzzyQueryPic()" type="button" class="btn btn-default">查询</button>
          </div>
          <input  ref="picture" name="picture" class="form-group" type="file"/>
          <button @click="uploadPicture()">图片上传</button>
        </form>

        <table class="table table-hover">
          <thead><tr>
            <th>id</th>
            <th>图片名</th>
            <th>图片</th>
            <th>操作</th>
          </tr></thead>
          <tbody><tr v-for="file in files">
            <td>{{file.fileId}}</td>
            <td>{{file.fileName}}</td>
            <td><img :src="file.fileUrl"/></td>
            <td>
              <button @click="editFileAction(file)">编辑</button> <button @click="deleteFileAction(file)">删除</button>
            </td>
          </tr>
          <tr>
            <paging
              :page-index="currentPage"
              :total="count"
              :page-size="pageSize"
              @change="pageChange">
            </paging>
          </tr>
          </tbody>
        </table>

      </div>

      <!--图片删除提示框-->
      <div v-show="isShowDeletePictureModel" class="Tips">
        <div class="TipsFrame"><!--弹出框外框-->
          <h2>删除图片</h2>
          <div class="TipsMain"><!--弹出框内部-->
            <p>确定删除图片？</p>
            <button id="deletePicture_yes" v-on:click="deletePictureSubmit">确定</button> <button v-on:click="deletePictureCancel">取消</button>
          </div>
        </div>
      </div>


      <div id="editHomeModel"  v-show="isShowEditHomeModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label>图片id：</label>
            <input type="text" class="form-control" v-model="editFileModel.fileId" disabled="disabled">
            <label>图片名：</label>
            <input type="text" class="form-control" v-model="editFileModel.fileName" disabled="disabled">
            <label>当前图片：</label>
            <img :src="editFileModel.fileUrl"/>
            <label>修改图片</label>
            <input type="file" class="form-control" ref="editPicture"  />
          </div>
          <button v-on:click="editFileSubmit()" type="button">提交</button>
        </form>
      </div>


      <!--用户管理-->
      <div id="userModel"  v-show="isShowUserModel" class="col-md-9 column">
        <form class="form-inline" role="form">
          <div class="form-group">
            <label class="sr-only" for="name">名称</label>
            <input v-model="searchKey" type="text" class="form-control"  placeholder="请输入关键字">
          </div>
          <button @click="fuzzyQuery()" type="button" class="btn btn-default">查询</button>
          <button @click="showModel('addUser')" type="button" class="btn btn-default">新增</button>
          <input ref="file" name="file" class="form-group" type="file"><button @click="uploadFile()">上传文件</button>


        </form>

        <table class="table table-hover">
          <thead><tr>
            <th>id</th>
            <th>姓名</th>
            <th>code</th>
            <th>手机号</th>
            <th>操作</th>
          </tr></thead>
          <tbody><tr v-for="user in users">
            <td>{{user.id}}</td>
            <td>{{user.userName}}</td>
            <td>{{user.userCode}}</td>
            <td>{{user.phone}}</td>
            <td>
              <button v-on:click="editUserAction(user)">编辑</button> <button v-on:click="deleteUer(user)">删除</button>
            </td>
          </tr></tbody>
        </table>
      </div>
      <!--新增用户-->
      <div id="addUserModel"  v-show="isShowAddUserModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" v-model="userName"
                   placeholder="请输入用户名">

            <label for="name">密码：</label>
            <input type="password" class="form-control" v-model="password"
                   placeholder="请输入密码"><br>

            <label for="name">用户码：</label>
            <input type="text" class="form-control" v-model="userCode"
                   placeholder="请输入用户码">

            <label for="name">手机：</label>
            <input type="text" class="form-control" v-model="phone"
                   placeholder="请输入手机号">
          </div>
          <button v-on:click="addUser" type="button">提交</button>
        </form>
      </div>
      <!--编辑用户-->
      <div id="editUserModel"  v-show="isShowEditUserModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" v-model="editUserModel.userName"
                   placeholder="请输入用户名">

            <label for="name">密码：</label>
            <input type="password" class="form-control" v-model="editUserModel.password"
                   placeholder="请输入密码"><br>

            <label for="name">用户码：</label>
            <input type="text" class="form-control" v-model="editUserModel.userCode"
                   placeholder="请输入用户码">

            <label for="name">手机：</label>
            <input type="text" class="form-control" v-model="editUserModel.phone"
                   placeholder="请输入手机号">
          </div>
          <button v-on:click="editUserSubmit" type="button">提交</button>
        </form>
      </div>
      <!--删除用户-->
      <div id="deleteUserModel" v-show="isShowDeleteUserModel" class="Tips">
        <div class="TipsFrame"><!--弹出框外框-->
          <h2>删除用户</h2>
          <div class="TipsMain"><!--弹出框内部-->
            <p>确定删除用户？</p>
            <a href="#" id="delete_yes" v-on:click="deleteUserSubmit">确定</a> <a href="#" v-on:click="deleteUserCancel">取消</a>
          </div>
        </div>
      </div>


      <!--角色管理-->
      <div id="roleModel"  v-show="isShowRoleModel" class="col-md-9 column">
        <form class="form-inline" role="form">
          <div class="form-group">
            <label class="sr-only" for="name">名称</label>
            <input v-model="searchKey" type="text" class="form-control"  placeholder="请输入关键字">
          </div>
          <button @click="fuzzyQueryRole" type="button" class="btn btn-default">查询</button>
          <button @click="showModel('addRole')" type="button" class="btn btn-default">新增</button>
        </form>

        <table class="table table-hover">
          <thead><tr>
            <th>id</th>
            <th>角色名</th>
            <th>code</th>
            <th>操作</th>
          </tr></thead>
          <tbody><tr v-for="role in roles">
            <td>{{role.id}}</td>
            <td>{{role.roleName}}</td>
            <td>{{role.roleCode}}</td>
            <td>
              <button @click="editRoleAction(role)">编辑</button> <button @click="deleteRole(role)">删除</button>
            </td>
          </tr></tbody>
        </table>
      </div>
      <!--新增角色-->
      <div id="addRoleModel"  v-show="isShowAddRoleModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name"></label>
            <input v-model="addRoleModel.roleName" type="text" class="form-control"
                   placeholder="请输入角色名">

            <label for="name"></label>
            <input v-model="addRoleModel.roleCode" type="text" class="form-control"
                   placeholder="请输入code"><br>
            <label for="menuModel"></label>

            <label v-for="menu in menus">
              <input v-model="addRoleModel.js03MenuList" :value="menu" type="checkbox" name="permissionMenu" >{{menu.menuName}} &nbsp;
            </label>
          </div>
          <button v-on:click="addRoleSubmit" type="button">提交</button>
        </form>
      </div>
      <!--编辑角色-->
      <div id="editRoleModel"  v-show="isShowEditRoleModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">角色名</label>
            <input type="text" class="form-control" v-model="editRoleModel.roleName"
                   placeholder="请输入角色名">

            <label for="name">角色code</label>
            <input type="text" class="form-control" v-model="editRoleModel.roleCode"
                   placeholder="请输入code"><br>

            <label for="menuModel"></label>

            <label  v-for="menu in editRoleModelJs03MenuList">
              <input v-model="curRoleMenuList" :value="menu.permissionValue" type="checkbox" />{{menu.menuName}} &nbsp;
            </label>
          </div>
          <button v-on:click="editRoleSubmit" type="button">提交</button>
        </form>
      </div>
      <!--删除角色-->
      <div id="deleteRoleModel" v-show="isShowDeleteRoleModel" class="Tips">
        <div class="TipsFrame"><!--弹出框外框-->
          <h2>删除角色</h2>
          <div class="TipsMain"><!--弹出框内部-->
            <p>确定删除角色？</p>
            <a href="#" v-on:click="deleteRoleSubmit">确定</a> <a href="#" v-on:click="deleteRoleCancel">取消</a>
          </div>
        </div>
      </div>


      <!--机构管理-->
      <div id="deptModel"  v-show="isShowDeptModel" class="col-md-9 column">
        <form class="form-inline" role="form">
          <div class="form-group">
            <label class="sr-only" for="name">名称</label>
            <input v-model="searchKey"  type="text" class="form-control" placeholder="请输入关键字">
          </div>
          <button @click="fuzzyQueryDept" type="button" class="btn btn-default">查询</button>
          <button @click="showModel('addDept')" type="button" class="btn btn-default">新增</button>
        </form>
        <table class="table table-hover">
          <thead><tr>
            <th>id</th>
            <th>机构名</th>
            <th>机构code</th>
            <th>机构级别</th></tr>
          </thead><tbody>

        <tr v-for="dept in depts">
          <td>{{dept.id}}</td>
          <td>{{dept.deptName}}</td>
          <td>{{dept.deptCode}}</td>
          <td>{{dept.deptGrade}}</td>
          <td>
            <button v-on:click="editDeptAction(dept, 'editDept')">编辑</button> <button @click="deleteDept(dept)">删除</button>
          </td>
        </tr>
        </tbody>
        </table>
      </div>
      <!--新增机构-->
      <div id="addDeptModel"  v-show="isShowAddDeptModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">机构名</label>
            <input v-model="addDeptModel.deptName" type="text" class="form-control"
                   placeholder="请输入机构名">

            <label for="name">机构code</label>
            <input v-model="addDeptModel.deptCode" type="text" class="form-control"
                   placeholder="请输入code"><br>

            <label for="name">机构级别</label>
            <input v-model="addDeptModel.deptGrade" type="text" class="form-control"
                   placeholder="请输入级别">
          </div>
          <button v-on:click="addDeptSubmit" type="button">提交</button>
        </form>
      </div>
      <!--编辑机构-->
      <div id="editDeptModel"  v-show="isShowEditDeptModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">机构名</label>
            <input type="text" class="form-control" v-model="editDeptModel.deptName"
                   placeholder="请输入机构名">

            <label for="name">机构code</label>
            <input type="text" class="form-control" v-model="editDeptModel.deptCode"
                   placeholder="请输入code"><br>

            <label for="name">机构级别</label>
            <input type="text" class="form-control" v-model="editDeptModel.deptGrade"
                   placeholder="请输入级别">
          </div>
          <button v-on:click="editDeptSubmit" type="button">提交</button>
        </form>
      </div>
      <!--删除机构-->
      <div id="deleteDeptModel" v-show="isShowDeleteDeptModel" class="Tips">
        <div class="TipsFrame"><!--弹出框外框-->
          <h2>删除机构</h2>
          <div class="TipsMain"><!--弹出框内部-->
            <p>确定删除机构？</p>
            <a href="#" v-on:click="deleteDeptSubmit">确定</a> <a href="#" v-on:click="deleteDeptCancel">取消</a>
          </div>
        </div>
      </div>


      <!--菜单管理-->
      <div id="menuModel"  v-show="isShowMenuModel" class="col-md-9 column">
        <form class="form-inline" role="form">
          <div class="form-group">
            <label class="sr-only" for="name">名称</label>
            <input v-model="searchKey" type="text" class="form-control" id="name" placeholder="请输入关键字">
          </div>
          <button @click="fuzzyQueryMenu" type="button" class="btn btn-default">查询</button>
          <button @click="showModel('addMenu')" type="button" class="btn btn-default">新增</button>
        </form>
        <table class="table table-hover">
          <thead>
          <tr>
            <th>id</th>
            <th>menu-name</th>
            <th>permission-value</th>
            <th>parent-id</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="menu in menus">
            <td>{{menu.id}}</td>
            <td>{{menu.menuName}}</td>
            <td>{{menu.permissionValue}}</td>
            <td>{{menu.parentId}}</td>
            <td>
              <button @click="editMenuAction(menu)">编辑</button> <button @click="deleteMenuAction(menu)">删除</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <!--新增菜单-->
      <div id="addMenuModel"  v-show="isShowAddMenuModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">菜单名</label>
            <input v-model="addMenuModel.menuName" type="text" class="form-control"
                   placeholder="请输入菜单名">

            <label for="name">permissionValue</label>
            <input v-model="addMenuModel.permissionValue" type="text" class="form-control"
                   placeholder="请输入"><br>

            <label for="name">parentId</label>
            <input v-model="addMenuModel.parentId" type="text" class="form-control"
                   placeholder="请输入级别">
          </div>
          <button v-on:click="addMenuSubmit" type="button">提交</button>
        </form>
      </div>
      <!--菜单编辑-->
      <div v-show="isShowEditMenuModel" class="col-md-9 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="name">menu-name：</label>
            <input type="text" class="form-control" v-model="editMenuModel.menuName"
                   placeholder="请输入菜单名">

            <label for="name">permission-value：</label>
            <input type="text" class="form-control" v-model="editMenuModel.permissionValue"
                   placeholder="请输入"><br>

            <label for="name">parentId：</label>
            <input type="text" class="form-control" v-model="editMenuModel.parentId"
                   placeholder="请输入">
          </div>
          <button v-on:click="editMenuSubmit" type="button">提交</button>
        </form>
      </div>
      <!--菜单删除-->
      <div id="deleteMenuModel" v-show="isShowDeleteMenuModel" class="Tips">
        <div class="TipsFrame"><!--弹出框外框-->
          <h2>删除菜单</h2>
          <div class="TipsMain"><!--弹出框内部-->
            <p>确定删除菜单？</p>
            <a href="#" v-on:click="deleteMenuSubmit">确定</a> <a href="#" v-on:click="deleteMenuCancel">取消</a>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import layer from 'vue-layer'
//  import qs from 'qs'
  import Paging from './paging'
  import Log from './log'
  import $ from 'jquery'



  export default {
    components : {
      Paging,
      Log
    },
    data () {
      return {
        pageSize: 1, //每页显示20条数据
        currentPage: 1, //当前页码
        count: 300, //总记录数

        value:' ',
        token: null,
        parentMessage: "我是来自父组件的消息",
        editRoleModelJs03MenuList:[],

        /*登录的用户id*/
        userId:JSON.parse(localStorage.getItem("userInfo")).id,

        deleteFileModel:{},
        editFileModel:{},
        excelFile:{},

        addMenuModel:{},
        addRoleModel:{"roleName":null,"roleCode":null,"js03MenuList":[]},
        editRoleModel:{},

        editMenuModel:{},
        addDeptModel:{},
        editDeptModel: {},
        searchKey: null,
        editUserModel: {},

        userName: null,
        password: null,
        userCode: null,
        phone: null,

        isShowHome:false,
        isShowUser:false,
        isShowRole:false,
        isShowDept:false,
        isShowMenu:false,
        isShowLog: false,

        isShowHomeModel: true,
        isShowEditHomeModel:false,
        isShowDeletePictureModel:false,


        isShowUserModel: false,
        isShowAddUserModel: false,
        isShowEditUserModel: false,
        isShowDeleteUserModel: false,

        isShowRoleModel: false,
        isShowEditRoleModel:false,
        isShowDeleteRoleModel:false,
        isShowAddRoleModel: false,

        isShowDeptModel: false,
        isShowEditDeptModel: false,
        isShowDeleteDeptModel: false,
        isShowAddDeptModel: false,

        isShowMenuModel: false,
        isShowEditMenuModel: false,
        isShowDeleteMenuModel: false,
        isShowAddMenuModel:false,

        isShowLogModel: false,

        selectedMenu: [],
        roles: [],

        files:[],
        menus: [],
        depts: [],
        users: [],
        curRoleMenuList: [],
        showMenu: [],
        userMenus: [],
        lastTime: new Date().getTime(),
        timer: '',
        timeout: 30 * 60 * 1000
      }
    },


    mounted() {
      this.userAllowMenus()
      this.queryHome()
      this.queryMenu()
      /*this.queryUsers()
      this.queryRole()
      this.queryDept()
      this.queryMenu()*/
      this.timer = setInterval(this.timeLogout, 1000)
    },
    beforeDestroy() {
      clearInterval(this.timer);
    },
    methods: {

      /*设置登录后，30分钟无操作，跳转登录页面，当前时间戳减去最后鼠标移动的时间大于30分钟，跳转*/
      timeLogout() {
        if (new Date().getTime() - this.$data.lastTime > this.$data.timeout) {
          this.$router.push({path:'/'});
        }
      },
      lastTimeEvent() {
        this.$data.lastTime = new Date().getTime(); //更新操作时间
      },

      //从page组件传递过来的当前page
      pageChange (page) {
        this.currentPage = page
        this.queryHome()
      },

      /*模糊查询图片*/
      fuzzyQueryPic(){
        let _this = this
        axios.post("/api/image/fuzzyQuery",{pageNo:1,pageSize: 10,userId: _this.$data.userId,searchKey:_this.$data.searchKey},
          {headers: {"token":JSON.parse(localStorage.getItem("userInfo")).token}})
          .then(function (result) {
            if (result.data.respCode === "0"){
              _this.$data.files = result.data.body.listBody
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },

      /*首页图片删除提交*/
      deletePictureSubmit(){
        let _this = this
        axios.post("/api/image/delete",this.$data.deleteFileModel,
          {headers:{"token":JSON.parse(localStorage.getItem("userInfo")).token}})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.showModel("home")
            } else if (result.data.respCode === "99") {
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },

      /*首页图片删除取消*/
      deletePictureCancel() {
        this.$data.isShowDeletePictureModel = false
        this.showModel("home")
      },

      /*首页图片删除开始*/
      deleteFileAction(file) {
        this.$data.deleteFileModel = file
        this.showModel("deleteFile")
      },

      /*首页图片编辑开始*/
      editFileAction(file) {
        let _this = this
        _this.$data.editFileModel = file
        _this.showModel("editFile")
      },

      /*首页图片编辑提交*/
      editFileSubmit() {
        let _this = this
        let formData = new FormData();
        formData.append('picName',this.$refs.editPicture.files[0]);
        formData.append("oldPic",JSON.stringify(this.$data.editFileModel))
          axios.post("/api/image/update",formData,
          {headers: {"token": JSON.parse(localStorage.getItem("userInfo")).token}})
        .then(function (result) {
          if (result.data.respCode==='0') {
            _this.showModel("home")
          } else if (result.data.respCode==='99') {
            alert(result.data.respMsg)
            _this.$router.push({path:'/'})
          } else {
            alert(result.data.respMsg)
          }
        })
      },

      /*首页*/
      queryHome() {
        let _this = this;
//        axios.post('/api/file/pageQuery',{pageNo: _this.$data.currentPage,pageSize: _this.$data.pageSize,
//          userId:_this.$data.userId
//          },
//          {headers: {"token": JSON.parse(localStorage.getItem("userInfo")).token}})
//          .then(function (result) {
//            if (result.data.respCode==='0') {
//              _this.$data.files=result.data.body.listBody
//              _this.$data.count = result.data.body.total
//            }else if(result.data.respCode=='99') {
//              _this.$router.push({path:'/'});
//            } else {
//              alert((result.data.respMsg))
//            }
//          })

        $.ajax({
          type : "POST", //请求方式
          async: false, // fasle表示同步请求，true表示异步请求
          contentType: "application/json;charset=UTF-8", //请求的媒体类型
          url : "/api/file/pageQuery",//请求地址
          data : JSON.stringify({pageNo: _this.$data.currentPage,pageSize: _this.$data.pageSize, userId:_this.$data.userId}), //数据，json字符串
          headers: {"token": JSON.parse(localStorage.getItem("userInfo")).token},
          success : function(result) { //请求成功
            if (result.respCode==='0') {
              _this.$data.files=result.body.listBody
              _this.$data.count = result.body.total
            }else if(result.respCode=='99') {
              alert(result.data.respMsg)
              _this.$router.push({path:'/'});
            } else {
              alert((result.data.respMsg))
            }
          },
          error : function(e){  //请求失败，包含具体的错误信息
            console.log(e.status);
            console.log(e.responseText);
          }
        });
      },
      /*首页图片上传*/
      uploadPicture(){
        let _this = this;
        let formData = new FormData();
        formData.append('picName',this.$refs.picture.files[0])

        axios.post('/api/image/upload',formData,
          {headers: {"token": JSON.parse(localStorage.getItem("userInfo")).token}})
          .then(function (result) {
            if (result.data.respCode ==='0') {
              _this.showModel("home")
            } else if (result.data.respCode === '99') {
              alert(result.data.respMsg)
              _this.$router.push({path:'/'})
            }
            else {
              alert((result.data.respMsg))
            }
        })
      },

      /*用户允许，登录时，loclstarage存储userInfo，根据该用户菜单集合，显示对应菜单*/
      userAllowMenus(){
        let userMenus = JSON.parse(localStorage.getItem("userInfo")).js03Role.js03MenuList
//        this.$data.userMenus = userMenus
        let _this = this

        for(let i = 0;i < userMenus.length; i++){
          if (userMenus[i].menuName == '首页'){
            _this.$data.isShowHome = true
          } else if (userMenus[i].menuName === '用户管理'){
            _this.$data.isShowUser = true
          } else if (userMenus[i].menuName === '角色管理'){
            _this.$data.isShowRole = true
          } else if (userMenus[i].menuName === '机构管理'){
            _this.$data.isShowDept = true
          } else if (userMenus[i].menuName === '菜单管理'){
            _this.$data.isShowMenu = true
          } else if (userMenus[i].menuName == '日志管理') {
            _this.$data.isShowLog = true
          }
        }

      },

      showModel(model) {

        this.$data.isShowHomeModel = false
        this.$data.isShowEditHomeModel = false
        this.$data.isShowDeletePictureModel = false

        this.$data.isShowUserModel = false
        this.$data.isShowAddUserModel = false
        this.$data.isShowEditUserModel = false
        this.$data.isShowDeleteUserModel = false

        this.$data.isShowRoleModel = false
        this.$data.isShowAddRoleModel=false
        this.$data.isShowEditRoleModel = false
        this.$data.isShowDeleteRoleModel = false

        this.$data.isShowDeptModel = false
        this.$data.isShowAddDeptModel=false
        this.$data.isShowEditDeptModel = false
        this.$data.isShowDeleteDeptModel=false

        this.$data.isShowMenuModel = false
        this.$data.isShowAddMenuModel=false
        this.$data.isShowEditMenuModel = false
        this.$data.isShowDeleteMenuModel = false

        this.$data.isShowLogModel = false
        this.$refs.log.isShowDeleteLogModel = false

        if (model === "home") {
          this.queryHome()
          this.$data.isShowHomeModel = true
        } else if (model === "userManager") {
          this.queryUsers()
          this.$data.isShowUserModel = true
        } else if (model === "menuManager") {
          this.queryMenu()
          this.$data.isShowMenuModel = true
        } else if (model === "addUser") {
          this.$data.isShowAddUserModel = true
        } else if (model ==="editUser"){
          this.$data.isShowEditUserModel = true
        } else if (model === "deleteUser"){
          this.$data.isShowDeleteUserModel = true
        } else if (model === "deptManager"){
          this.queryDept()
          this.$data.isShowDeptModel = true
        } else if (model === "editDept"){
          this.$data.isShowEditDeptModel=true
        } else if (model === "deleteDept"){
          this.$data.isShowDeleteDeptModel=true
        } else if (model === "addDept"){
          this.$data.isShowAddDeptModel=true
        } else if (model === "editMenu"){
          this.$data.isShowEditMenuModel=true
        } else if (model === "deleteMenu"){
          this.$data.isShowDeleteMenuModel=true
        } else if (model === "roleManager"){
          this.queryRole()
          this.$data.isShowRoleModel = true
        } else if (model === "editRole"){
          this.$data.isShowEditRoleModel=true
        } else if (model === "deleteRole"){
          this.$data.isShowDeleteRoleModel=true
        } else if (model === "addRole"){
          this.$data.isShowAddRoleModel=true
        } else if (model === "addMenu"){
          this.$data.isShowAddMenuModel=true
        } else if (model === "home"){
          this.$data.isShowHomerModel = true
        } else if (model === "editFile"){
          this.$data.isShowEditHomeModel = true
        } else if (model === "deleteFile"){
          this.$data.isShowDeletePictureModel = true
        } else if (model === "log") {
          this.$refs.log.queryLog()
          this.$data.isShowLogModel = true
        }
      },

      /*菜单删除取消*/
      deleteMenuCancel(){
        let _this = this
        this.$data.isShowDeleteMenuModel = false
        _this.showModel("menuManager")
      },
      /*菜单删除提交*/
      deleteMenuSubmit(){
        var _this = this
        axios.post("api/menu/delete", _this.$data.editMenuModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.queryMenu()
              _this.showModel("menuManager")
            }else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*菜单删除开始*/
      deleteMenuAction(menu){
        let _this = this
        _this.$data.editMenuModel = menu
        _this.showModel("deleteMenu")
      },
      /*菜单编辑提交*/
      editMenuSubmit(){
        let _this = this
        axios.post("api/menu/update",_this.$data.editMenuModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0"){
              _this.showModel("menuManager")
            }else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*菜单编辑开始*/
      editMenuAction(menu){
        let _this = this
        _this.$data.editMenuModel = menu
        _this.showModel("editMenu")
      },
      /*菜单模糊查询*/
      fuzzyQueryMenu(){
        let _this = this
        axios.post("api/menu/fuzzy",{pageNo:1,pageSize: 10,searchKey:_this.$data.searchKey},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0"){
              _this.$data.menus = result.data.body
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
      });
  },
      /*菜单查询*/
      queryMenu() {
        let _this = this;
        axios.post('/api/menu/select',{pageNo: 1,pageSize: 10},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode==='0') {
              _this.$data.menus=result.data.body
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*菜单新增提交*/
      addMenuSubmit (){
        let _this = this;
        axios.post('api/menu/insert',_this.$data.addMenuModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode == "0"){
              _this.showModel("menuManager")
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },

      /*角色新增提交*/
      addRoleSubmit (){
        let _this = this;
        axios.post('api/role/insert',_this.$data.addRoleModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode == "0"){
              _this.showModel("roleManager")
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },
      /*角色模糊查询*/
      fuzzyQueryRole(){
        let _this = this
        axios.post("api/role/fuzzy",{pageNo:1,pageSize: 10,searchKey:_this.$data.searchKey},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.$data.roles = result.data.body.listBody
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },
      /*角色查询*/
      queryRole() {
        let _this = this;
        axios.post('api/role/query',{pageNo: 1,pageSize: 10},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode=='0') {
              _this.$data.roles = result.data.body
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },
      /*角色删除取消*/
      deleteRoleCancel(){
        let _this = this
        this.$data.isShowDeleteRoleModel = false
        _this.showModel("roleManager")
      },
      /*角色删除提交*/
      deleteRoleSubmit(){
        var _this = this
        axios.post("api/role/delete", _this.$data.editRoleModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.showModel("roleManager")
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*角色删除弹出*/
      deleteRole(role){
        let _this = this
        _this.showModel("deleteRole")
        _this.$data.editRoleModel.roleName = role.roleName
        _this.$data.editRoleModel.roleCode = role.roleCode
        _this.$data.editRoleModel.id = role.id


      },
      /*角色编辑提交*/
      editRoleSubmit(){
        let _this=this
        let checkedMenus = [];
        for (var i=0; i<_this.$data.menus.length; i++) {
          for (var j=0; j<_this.$data.curRoleMenuList.length; j++) {
            if (_this.$data.menus[i].permissionValue == _this.$data.curRoleMenuList[j]) {
              checkedMenus.push(_this.$data.menus[i])
              break
            }
          }
        }
        _this.$data.editRoleModel.js03MenuList = checkedMenus

        axios.post("api/role/update",_this.$data.editRoleModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0"){
              _this.showModel("roleManager")
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*角色编辑弹出*/
      editRoleAction(role){
        let _this=this
        _this.showModel("editRole")
        var roleMenu = [];
        for (var i=0; i<role.js03MenuList.length; i++) {
          roleMenu[i] = role.js03MenuList[i].permissionValue
        }
        console.log("roleMenu:"+JSON.stringify(role))
        _this.$data.curRoleMenuList = roleMenu
        _this.$data.editRoleModel = role

        _this.$data.editRoleModelJs03MenuList = _this.$data.menus
      },


      /*机构新增提交*/
      addDeptSubmit (){
        let _this = this;
        axios.post('api/dept/insert',_this.$data.addDeptModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode == "0"){
              _this.showModel("deptManager")
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },
      /*机构模糊查询*/
      fuzzyQueryDept(){
        let _this = this
        axios.post("api/dept/fuzzyQuery",{pageNo:1,pageSize: 10,searchKey:_this.$data.searchKey},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.$data.depts = result.data.body.listBody
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },
      /*机构编辑提交*/
      editDeptSubmit(){
        let _this=this
        axios.post("api/dept/update",_this.$data.editDeptModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0"){
              _this.showModel("deptManager")
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*机构删除弹出*/
      deleteDept(dept){
        let _this = this
        _this.showModel("deleteDept")
        _this.$data.editDeptModel = dept

      },
      /*机构删除提交*/
      deleteDeptSubmit(){
        var _this = this
        axios.post("api/dept/delete", _this.$data.editDeptModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.queryDept()
              _this.showModel("deptManager")
            }  else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*机构查询*/
      queryDept() {
        let _this = this;
        axios.post('/api/dept/query',{pageNo: 1,pageSize: 10},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode==='0') {
              _this.$data.depts = result.data.body
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*机构删除取消*/
      deleteDeptCancel(){
        let _this = this
        this.$data.isShowDeleteDeptModel = false
        _this.showModel("deptManager")
      },
      /*机构编辑弹出*/
      editDeptAction(dept, deptModel){
        let _this = this
        _this.showModel(deptModel)
        _this.$data.editDeptModel = dept
      },


      /*用户查询*/
      queryUsers() {
        let _this = this;
        axios.post('api/user/pageQuery',{pageNo: 1,pageSize: 10},
          {headers: {"token": JSON.parse(localStorage.getItem("userInfo")).token}})
          .then(function (result) {
            if (result.data.respCode=='0') {
              _this.$data.users = result.data.body.listBody
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          });
      },
      /*用户模糊查询*/
      fuzzyQuery(){
        let _this = this
        axios.post("api/user/fuzzyQuery",{pageNo:1,pageSize: 10,searchKey:_this.$data.searchKey},
          {headers:{"token":JSON.parse(localStorage.getItem("userInfo")).token}}
        )

          .then(function (result) {
              if (result.data.respCode=='0') {
                _this.$data.users = result.data.body.listBody
              }  else if (result.data.respCode === "99"){
                alert(result.data.respMsg)
                _this.$router.push({path: '/'})
              } else {
                alert(result.data.respMsg)
              }
            });
      },
      //Excel上传（批量新增用户）
      uploadFile(){
        let _this = this
        let formData = new FormData();
        formData.append('file',this.$refs.file.files[0])

        axios.post('/api/user/insertBatch',formData,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }}).then(function (result) {
          if (result.data.respCode === "0") {
            _this.showModel("userManager")
          }else if (result.data.respCode === "99"){
            alert(result.data.respMsg)
            _this.$router.push({path: '/'})
          }
        })
      },
      /*用户新增*/
      addUser (){
        let _this = this;
        axios.post('api/user/add',{userName:_this.$data.userName, password: _this.$data.password, userCode: _this.$data.userCode, phone: _this.$data.phone},{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode == "0"){
              _this.showModel("userManager")
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path:"/"})
            }
            else {
              alert(result.data.respMsg)
            }
          });
      },
      /*用户删除取消*/
      deleteUserCancel(){
         let _this = this
        this.$data.isShowDeleteUserModel = false
        _this.showModel("userManager")
      },
      /*用户删除提交*/
      deleteUserSubmit(){
        var _this = this
        axios.post("api/user/delete", _this.$data.editUserModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.showModel("userManager")
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*用户删除弹出*/
      deleteUer(user) {
        let _this = this
        _this.showModel("deleteUser")
        _this.$data.editUserModel = user
      },
      /*用户编辑提交*/
      editUserSubmit() {
        var _this = this
        axios.post("api/user/update", _this.$data.editUserModel,{headers:{
          "token": JSON.parse(localStorage.getItem("userInfo")).token
        }})
          .then(function (result) {
            if (result.data.respCode === "0") {
              _this.showModel("userManager")
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },
      /*用户编辑开始*/
      editUserAction(user) {
        let _this = this
        _this.showModel("editUser")
        _this.$data.editUserModel = user
      }
    }
  }
</script>

<style>

  .Tips {
    width: 400px;
    height: 190px;
    position: absolute;
    top: 100px;
    left: 500px;
    background: #fff;
    border-radius: 4px;
  }

  .TipsFrame {
    margin: 4px;
    border: 1px solid #ccc;
  }

  .TipsFrame h2 {
    padding-left: 8px;
    font-size: 14px;
    line-height: 30px;
    margin: 4px 8px;
    border-bottom: 1px solid gray;
  }

  .TipsMain {
    margin-top: 38px;
    text-align: center;
    margin-bottom: 30px;
    border-radius: 4px;
  }

  .TipsMain a {

    padding: 0 20px;
    display: inline-block;
    height: 30px;
    line-height: 30px;
    border-radius: 4px;
    border: 1px solid gray;
    margin-top: 10px;
    background: lightgray;
    color: #fff;
    text-decoration: none;
  }

  .TipsMain a:hover,.TipsMain a:active {
    background: gray;
  }

  img{
    width: 150px;

    height: 100px;

  }

</style>
