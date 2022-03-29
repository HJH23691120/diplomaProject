<template>
  <div class="container">
    <el-form
      :model="form"
      ref="form"
      :rules="myRules"
      label-width="120px"
      :inline="false"
      size="normal"
    >
      <el-form-item label="用户角色" size="normal" prop="useRole">
        <el-radio-group v-model="form.useRole">
          <el-radio
            v-for="item in useRoleList"
            :key="item.key"
            :label="item.label"
          >
            {{ item.label }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="用户性别" size="normal" prop="useGender">
        <el-radio-group v-model="form.useGender">
          <el-radio v-for="item in useGenderList" :key="item" :label="item">
            {{ item }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
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
      <el-form-item label="用户班级" prop="useClass">
        <el-input
          v-model="form.useClass"
          type="password"
          placeholder="请输入用户班级"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="用户电话" prop="useTel">
        <el-input
          v-model="form.useTel"
          type="password"
          placeholder="请输入用户电话"
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
import { rules, useRoleList, useGenderList } from './until';
const reg = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
export default {
  name: 'register',
  components: {},
  computed: {},
  watch: {},
  data() {
    return {
      useRoleList,
      useGenderList,
      myRules: {
        ...rules,
        confirmPassword: [
          {
            required: true,
            validator: this.checkPass.bind(this),
            trigger: 'change',
          },
        ],
        useTel: [
          {
            required: true,
            validator: this.checkTel.bind(this),
            trigger: 'blur',
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
    checkTel(rule, value, callback) {
      if (!value) {
        callback('请输入手机号');
        return;
      }
      if (!reg.test(value)) {
        callback('手机号不合法，请重新输入');
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
.container {
  padding: 10px 20px;
  .button-box {
    margin-top: 40px;
    display: flex;
    justify-content: space-between;
    .el-button {
      width: 200px;
    }
  }
  .el-radio-group {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    .el-radio {
      margin: 10px;
    }
  }
}
</style>
