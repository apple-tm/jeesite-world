<template>
  <div id="app">
    <!--<user-manage></user-manage>-->
    <router-view/>
  </div>
</template>

<script>
import UserManage from '@/components/UserManage.vue'
export default {
  name: 'App',
  components: {
    "user-manage": UserManage
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
