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
            :label="item.key"
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
      <el-form-item label="新密码" prop="userPwd">
        <el-input
          v-model="form.userPwd"
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
      <el-form-item
        label="用户班级"
        v-if="['3', '4'].includes(form.useRole)"
        prop="useClass"
      >
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
      <div class="button-box">
        <el-button @click="goBack">返回</el-button>
        <el-button type="primary" @click="confirm" :loading="isLoading"
          >确认</el-button
        >
      </div>
    </el-form>
  </div>
</template>

<script>
import API from '@apis/login/index.js';
import { rules, useRoleList, useGenderList } from './until';
const reg = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
export default {
  name: 'register',
  data() {
    return {
      isLoading: false,
      useRoleList,
      useGenderList,
      myRules: {
        ...rules,
        confirmPassword: [
          {
            required: true,
            validator: this.checkPass.bind(this),
            trigger: 'change'
          }
        ],
        useTel: [
          // 验证手机号
          {
            required: true,
            validator: this.checkTel.bind(this),
            trigger: 'blur'
          }
        ]
      },
      form: {
        userId: '',
        userPwd: '',
        useName: '',
        userGender: '',
        userClass: '',
        userRole: '',
        userTel: ''
      },
      confirmPassword: ''
    };
  },
  created() {
    this.getUserId();
  },
  methods: {
    checkPass(rule, value, callback) {
      if (!value) {
        callback('请输入密码');
        return;
      }

      if (value !== this.form.userPwd) {
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
    getUserId() {
      API.getUserId().then(res => {
        if (res.code === -1) {
          this.$message.error(
            '多次申请新的用户Id失败，请联系管理员删除存量数据'
          );
          return;
        }
        this.form.userId = res.data;
      });
    },
    goBack() {
      // 返回登录
      this.$refs.form.clearValidate();
      this.$emit('handleBackLogin');
    },
    confirm() {
      // 确认提交
      this.$refs.form.validate(valid => {
        if (!valid) {
          return;
        }
        this.isLoading = true;
        const param = {
          creatBy: 'admin',
          updateBy: 'admin'
        };
        API.addUser({
          ...this.form,
          ...param
        })
          .then(res => {
            if (res.code === -1) {
              this.$message.error('添加用户失败');
              return;
            }
            sessionStorage.setItem('userInfo', userID);
            this.$confirm('用户ID作为登录凭证，请牢记')
              // 请求接口，弹窗提示
              .then(() => {
                this.goBack();
              })
              .catch(error => {
                console.log(error);
              });
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
