<template>
  <div class="login-container">
    <div class="form-container">
      <el-form
        :model="form"
        ref="form"
        :rules="rules"
        label-width="80px"
        :inline="false"
        size="normal"
      >
        <el-form-item prop="account" label="用户名：">
          <el-input v-model="form.account" placeholder="请输入用户名" clearable>
          </el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码：">
          <el-input
            v-model="form.password"
            placeholder="请输入密码"
            type="password"
            clearable
          >
          </el-input>
        </el-form-item>
        <div class="button-box">
          <el-button type="text" size="default" @click="forgetPass"
            >忘记密码</el-button
          >
          <el-button type="text" size="default" @click="goRegister"
            >注册</el-button
          >
        </div>
        <div>
          <el-button
            type="primary"
            class="login-btn"
            :loading="isLoading"
            @click="handleLogin"
            >登录</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import API from '@apis/login/index.js';
import { rules } from './until';
export default {
  name: 'Login',
  data() {
    return {
      rules,
      form: {
        account: '',
        password: '',
      },
      isLoading: false,
    };
  },
  methods: {
    handleLogin() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return;
        }
        this.isLoading = true;
        API.login({
          ...this.form,
        })
          .then(res => {
            console.log(res);
          })
          .finally(() => {
            this.isLoading = false;
          });
      });
    },
    forgetPass() {
      this.$refs.form.clearValidate();
      this.$emit('handleResetPass')
    },
    goRegister() {
      this.$refs.form.clearValidate();
       this.$emit('handleRegister')
    },
  },
};
</script>
<style lang="scss" scoped>
.login-container {
  padding: 10px 20px;
  .title {
    margin: 10px 0;
  }
  .login-btn {
    width: 80%;
    letter-spacing: 20px;
  }
  .button-box {
    display: flex;
    padding-left: 20px;
    margin: 10px 0;
    justify-content: space-between;
  }
  ::v-deep .el-input-group__prepend {
    width: 45px !important;
  }
}
</style>
