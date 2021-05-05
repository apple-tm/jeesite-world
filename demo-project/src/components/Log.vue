<template>
  <div id="logModel"  v-show="isShowLogModelCur" class="col-md-9 column">
    <form class="form-inline">
      <div class="form-group">
        <label class="sr-only">名称</label>
        <input v-model="searchKey" type="text" class="form-control"  placeholder="请输入关键字">
      </div>
      <button type="button" class="btn btn-default">查询</button>
    </form>

    <table class="table table-hover">
      <thead><tr>
        <th>操作</th>
        <th>用户名</th>
        <th>用户id</th>
        <th>所属角色</th>
        <th>注</th>
        <th>操作</th>
      </tr></thead>
      <tbody><tr v-for="log in logs">
        <td>{{log.logAction}}</td>
        <td>{{log.logUserName}}</td>
        <td>{{log.logUserId}}</td>
        <td>{{log.logUserRoleName}}</td>
        <td>{{log.logNotes}}</td>
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


</template>

<script>

  import axios from 'axios'
  import Paging from './paging'

  export default {

    components: {
      Paging
    },

    mounted() {

    },
    name : 'Log',
    //通过props来接受从父组件传递过来的值
    props : {

      //页面中的可见页码，其他的以...替代, 必须是奇数

      showlog: {
        type: Boolean,
        default: false
      }
    },
    methods : {

      queryLog() {
        let _this = this
        axios.post("/api/log/query", {"pageSize": this.$data.pageSize, "pageNo": this.$data.currentPage},
          {headers: {"token":JSON.parse(localStorage.getItem("userInfo")).token}})
          .then(function (result) {
            if (result.data.respCode === '0') {
              _this.$data.logs = result.data.body.listBody
              _this.$data.count = result.data.body.total
            } else if (result.data.respCode === "99"){
              alert(result.data.respMsg)
              _this.$router.push({path: '/'})
            } else {
              alert(result.data.respMsg)
            }
          })
      },


      pageChange (page) {
        this.currentPage = page
        this.queryLog()
      },

      go (page) {
        if (this.index !== page) {
          this.index = page
          //父组件通过change方法来接受当前的页码
          this.$emit('change', this.index)
        }
      }
    },
    computed : {


    },
    data () {
      return {
        isShowLogModelCur: this.showlog,
        logs:[],
        searchKey:null,
        deleteLog:{},
        isShowDeleteLogModel:false,
        pageSize: 10, //每页显示20条数据
        currentPage: 1, //当前页码
        count: 1 //总记录数
      }
    },
    watch : {
      pageIndex(val) {
        this.index = val || 1
      },
      pageSize(val) {
        this.limit = val || 10
      },
      total(val) {
        this.size = val || 1
      },
      showlog(val) {
        this.isShowLogModelCur = val

      }
    }


  }
</script>

<style scoped>

</style>
