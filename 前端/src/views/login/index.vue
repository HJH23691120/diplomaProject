<template>
  <div class="main">
    <section class="section">
      <h3>{{ title }}</h3>
        <component
          :is="componentName"
          @handleResetPass="handleResetPass"
          @handleRegister="handleRegister"
          @handleBackLogin="handleBackLogin"
        ></component>
    </section>
  </div>
</template>
<script>
import Login from './login.vue';
import Reset from './reset_pass.vue';
import Register from './register.vue';
export default {
  title: 'login',
  components: {
    Login, // 登录组件
    Reset, //重置密码
    Register, //注册组件
  },
  data() {
    return {
      componentName: 'Login',
      title: '用户登录',
    };
  },
  methods: {
    handleResetPass() {
      this.title = '重置密码';
      this.componentName = 'Reset';
    },
    handleRegister() {
      this.title = '注册新用户';
      this.componentName = 'Register';
    },
    handleBackLogin() {
      this.title = '用户登录';
      this.componentName = 'Login';
    },
  },
};
</script>

<style scoped lang="scss">
.main {
  height: 100%;
  .section {
    background: #ffffff;
    box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.15);
    border-radius: 4px;
    width: 450px;
    height: auto;
    padding-top: 30px;
    padding-bottom: 30px;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    right: 0;
    left: 0;
    margin: auto;
  }
}
.main::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 50%;
  background: #0352db;
  z-index: -1;
}
</style>
<style lang="scss">
  .main{
    text-align: center;
  }
</style>
