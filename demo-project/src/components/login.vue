<template>
  <div class="container">
    <div class="row clearfix">
      <div class="col-md-4 column">
      </div>
      <div class="col-md-4 column">
        <form role="form" class="form-inline">
          <div class="form-group">
            <label for="exampleInputEmail1"></label><input v-model="userPhone" type="text" class="form-control" id="exampleInputEmail1" placeholder="输入手机号"/>
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1"></label><input v-model="userPassword" type="password" class="form-control" id="exampleInputPassword1" placeholder="输入密码"/>
          </div>
          <br />
          <div class="checkbox">
            <!--<router-link to="/home">登录</router-link>-->
            <button v-on:click="login">登录</button>
          </div>
        </form>
      </div>
      <div class="col-md-4 column">
      </div>
    </div>
  </div>

</template>

<script>
  import axios from 'axios'
  import $ from 'jquery'

  export default {
    data () {

      return {
        userPhone: "13281035058",
        userPassword: "1",
        userMenus: []
      }
    },
    methods:{
      login (){
        let _this = this;

        if (_this.$data.userPhone == null || _this.$data.userPhone.trim() == ""
        || _this.$data.userPhone == undefined || _this.$data.userPassword == null
        || _this.$data.userPassword.trim() == "" || _this.$data.userPassword == undefined){
          alert("手机号和密码不能为空");
          return;
        }

//        axios.post('/api/user/login',{phone:_this.$data.userPhone,password: _this.$data.userPassword})
//          .then(function (result) {
//            if (result.data.respCode=='0') {
//                localStorage.setItem("userInfo",JSON.stringify(result.data.body))
//              _this.$router.push({path:'/home'});
//            } else {
//              alert(result.data.respMsg);
//            }
//          });

        $.ajax({
          type : "POST", //请求方式
          async: false, // fasle表示同步请求，true表示异步请求
          contentType: "application/json;charset=UTF-8", //请求的媒体类型
          url : "/api/user/login",//请求地址
          data : JSON.stringify({phone:_this.$data.userPhone,password: _this.$data.userPassword}), //数据，json字符串
          success : function(result) { //请求成功
            if (result.respCode=='0') {
              localStorage.setItem("userInfo",JSON.stringify(result.body))
              _this.$router.push({path:'/home'});
            } else {
              alert(result.respMsg);
            }
          },
          error : function(e){  //请求失败，包含具体的错误信息


            console.log(e.status);
            console.log(e.responseText);
          }
        });


      }
    }
  }
</script>

