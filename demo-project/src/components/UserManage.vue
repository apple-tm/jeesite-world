<template>
  <div>
    home
    <span>{{ parentMessage }}</span>
  </div>
</template>

<script>
  export default {
    props: {
      parentMessage: {
        type: String,
        default: '默认显示的信息'
        // require: true // 必填
      }
    }
  }
</script>
<style scoped>
</style>
