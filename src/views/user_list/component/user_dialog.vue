<template>
  <div class="dialog-container">
    <el-dialog
      :title="title"
      :visible.sync="visible"
      width="590px"
      @close="handleClose"
    >
      <el-form
        :model="form"
        ref="form"
        :rules="rules"
        label-width="120px"
        :inline="false"
        size="normal"
      >
        <el-form-item label="用户角色" size="normal" prop="userRole">
          <el-radio-group v-model="form.userRole">
            <el-radio
              v-for="item in userRoleList"
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
          v-if="['3', '4'].includes(form.userRole)"
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
        <el-form-item>
          <el-button @click="cancel">取消</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import API from '@apis/userlist/index.js';
export default {
  name: 'userDialog',
  props: {
    data: {
      type: Object,
      default() {
        return {};
      }
    },
    isVisible: {
      type: Boolean
    },
    title: {
      type: String
    },
    isCreate: {
      type: Boolean
    }
  },
  computed: {
    visible: {
      get() {
        return this.isVisible;
      },
      set(val) {
        this.$emit('update:isVisible', val);
      }
    }
  },
  data() {
    return {
      userRoleList: [
        {
          label: '学生',
          key: '4'
        },
        {
          label: '校内导师',
          key: '3'
        },
        {
          label: '企业导师',
          key: '2'
        }
      ],
      useGenderList: ['男', '女'],
      rules: {
        userRole: [
          { required: true, message: '用户角色不能为空', trigger: 'change' }
        ],
        useClass: [
          { required: true, message: '用户班级不能为空', trigger: 'change' }
        ],
        useGender: [
          { required: true, message: '用户性别不能为空', trigger: 'change' }
        ],
        useName: [
          { required: true, message: '账号不能为空', trigger: 'change' }
        ],
        userId: [
          { required: true, message: '账号不能为空', trigger: 'change' }
        ],
        userPwd: [
          { required: true, message: '密码不能为空', trigger: 'change' }
        ],
        confirmPassword: [
          { required: true, message: '密码不能为空', trigger: 'change' }
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
    if (!this.isCreate) {
      this.form = this.data;
    }
  },
  methods: {
    handleClose() {
      this.cancel();
    },
    cancel() {
      this.visible = false;
    },
    onSubmit() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return;
        }
        this.isLoading = true;
        const param = {
          creatBy: 'admin',
          updateBy: 'admin'
        };
        this.isCreate
          ? this.addUSer({
              ...this.form,
              ...param
            })
          : this.updateUser({
              ...this.form,
              ...param
            });
      });
    },
    addUSer(data) {
      API.addUser(data)
        .then(res => {
          if (res.code === -1) {
            this.$message.error('添加用户失败');
            return;
          }
          this.$message.success('添加用户成功');
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
    updateUser(data) {
      API.updateUser(data)
        .then(res => {
          if (res.code === -1) {
            this.$message.error('添加用户失败');
            return;
          }
          this.$message.success('添加用户成功');
        })
        .finally(() => {
          this.isLoading = false;
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.dialog-container {
  .button-box {
    width: 400px;
    .el-button {
      width: 150px;
    }
  }
  .el-textarea {
    width: 450px;
  }
}
</style>
