<template>
  <div class="container">
    <header class="header">
      个人中心
      <el-button type="text" size="default" @click="changeEdit" v-if="isEdit"
        >修改</el-button
      >
    </header>
    <el-form
      :model="form"
      ref="form"
      :rules="myRules"
      label-width="120px"
      :inline="false"
      size="normal"
      hide-required-asterisk
      :disabled="isEdit"
      :loading="isLoading"
    >
      <el-form-item label="用户角色" size="normal">
        <el-radio-group v-model="form.useRole" :disabled="true">
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
      <el-form-item label="新密码" prop="newPwd">
        <el-input
          v-model="form.newPwd"
          type="password"
          placeholder="请输入密码"
          clearable
        >
        </el-input>
      </el-form-item>
      <el-form-item label="请确认密码" prop="confirmPassword">
        <el-input
          v-model="confirmPassword"
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
      <el-form-item label="用户电话">
        <el-input
          v-model="form.useTel"
          type="password"
          placeholder="请输入用户电话"
          clearable
        >
        </el-input>
      </el-form-item>
      <div class="button-box" v-if="!isEdit">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="confirm">确认</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import { rules, useRoleList, useGenderList } from './../login/until';
import API from '@apis/userlist/index.js';
export default {
  name: 'personalCenter',

  data() {
    return {
      isLoading: false,
      isEdit: true,
      useRoleList,
      useGenderList,
      myRules: {
        ...rules,
        confirmPassword: [
          {
            required: true,
            validator: this.checkPass.bind(this),
            trigger: 'change'
          },
        ],
        useTel: [
          {
            required: true,
            validator: this.checkTel.bind(this),
            trigger: 'blur'
          },
        ]
      },
      form: {
        userId: '',
        newPwd: '',
        useName: '',
        userGender: '',
        userClass: '',
        userRole: '',
        userTel: ''
      },
      confirmPassword: ''
    };
  },
  created() {},
  mounted() {},
  methods: {
    init() {
      const tempUserID = sessionStorage.getItem('userId');
      API.getUser({ userId: tempUserID }).then(res => {
        if (res.code === -1) {
          this.$message('未查询到用户信息');
          return;
        }
        this.form = res.data;
      });
    },
    checkPass(rule, value, callback) {
      if (!value) {
        callback('请输入密码');
        return;
      }

      if (value !== this.form.newPwd) {
        callback('两次输入的密码必须一致');
        return;
      }
      callback();
    },
    checkTel(rule, value, callback) {
      if (value && !reg.test(value)) {
        callback('手机号不合法，请重新输入');
        return;
      }
      callback();
    },
    changeEdit() {
      this.isEdit = false;
    },
    cancel() {
      this.isEdit = true;
    },
    confirm() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return;
        }
        this.isLoading = true;
        API.updateUser({
          ...this.form
        })
          .then(res => {
            if (res.code === -1) {
              this.$message('修改用户失败');
              return;
            }
            this.isEdit = true;
          })
          .finally(() => {
            this.isLoading = false;
          });
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  .el-form {
    width: 800px;
    .el-input {
      width: 380px;
    }
  }
  .button-box {
    margin-top: 40px;
    .el-button {
      width: 243px;
    }
  }
  .header {
    margin: 15px 0 15px 0;
    width: 500px;
    line-height: 40px;
    font-weight: 700;
    font-size: 25px;
    .el-button {
      float: right;
    }
  }
}
</style>
