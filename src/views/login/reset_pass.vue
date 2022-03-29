<template>
  <div class="reset-container">
    <el-form
      :model="form"
      ref="form"
      :rules="myRules"
      label-width="120px"
      :inline="false"
      size="normal"
    >
      <el-form-item prop="useName" label="用户名：">
        <el-input v-model="form.useName" placeholder="请输入用户名" clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input
          v-model="form.password"
          type="password"
          placeholder="请输入密码"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="请确认密码" prop="confirmPassword">
        <el-input
          v-model="form.confirmPassword"
          type="password"
          placeholder="请输入密码"
          clearable
        >
        </el-input>
      </el-form-item>
      <div class="button-box">
        <el-button @click="goBack">返回</el-button>
        <el-button type="primary" @click="confirm">确认</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import { rules } from './until';
import API from '@apis/login/index.js';
export default {
  name: 'resetPass',
  components: {},
  computed: {},
  watch: {},
  data() {
    return {
      myRules: {
        ...rules,
        confirmPassword: [
          {
            required: true,
            validator: this.checkPass.bind(this),
            trigger: 'change',
          },
        ],
      },
      form: {
        useName: '',
        password: '',
        confirmPassword: '',
      },
    };
  },
  created() {},
  mounted() {},
  methods: {
    checkPass(rule, value, callback) {
      if (!value) {
        callback('请输入密码');
        return;
      }

      if (value !== this.form.password) {
        callback('两次输入的密码必须一致');
        return;
      }
      callback();
    },
    goBack() {
      this.$refs.form.clearValidate();
      this.$emit('handleBackLogin');
    },
    confirm() {
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
  },
};
</script>
<style lang="scss" scoped>
.reset-container {
  padding: 10px 20px;
  .button-box {
    margin-top: 40px;
    display: flex;
    justify-content: space-between;
    .el-button {
      width: 200px;
    }
  }
}
</style>
