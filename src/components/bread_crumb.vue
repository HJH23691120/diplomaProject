<template>
  <div class="container">
    <section class="section">
        <el-button
          type="text"
          size="default"
          @click="back"
          icon="el-icon-arrow-left"
          >返回</el-button
        >
        <span style="margin: 0 14px 0 20px">|</span>
      <span class="title">{{ $route.meta.title }}</span>
    </section>
  </div>
</template>
<script>
export default {
  name: 'breadCrumb',
  methods: {
    back() {
      this.$router.go(-1);
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  .section {
    display: flex;
    flex-direction: row;
    align-items: center;
    .el-button {
      font-size: 20px;
    }
    .title {
      font-size: 16px;
    }
  }
}
</style>
