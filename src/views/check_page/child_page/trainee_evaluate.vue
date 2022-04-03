<template>
  <div class="container">
    <!-- 学生评价 -->
    <BreadCrumb></BreadCrumb>
    <section class="section">
      <el-form
        :model="form"
        ref="form"
        :rules="rules"
        label-width="120px"
        :inline="false"
        size="normal"
        :loading="loading"
        :disabled="isDisabled"
      >
        <el-form-item label="实习成绩" prop="firmResult">
          <el-input
            v-model="form.firmResult"
            placeholder="请输入成绩"
          ></el-input>
        </el-form-item>
        <el-form-item label="考勤是否合格" prop="attendQualified">
          <el-radio-group v-model="form.attendQualified">
            <el-radio
              v-for="item in options"
              :key="item.key"
              :label="item.label"
            >
              {{ item.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工作是否完成" prop="workComplete">
          <el-radio-group v-model="form.workComplete">
            <el-radio
              v-for="item in options"
              :key="item.key"
              :label="item.label"
            >
              {{ item.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="企业导师评语" prop="firmComment">
          <el-input
            type="textarea"
            :rows="10"
            placeholder="请输入评价"
            v-model="form.firmComment"
          ></el-input>
        </el-form-item>
      </el-form>

      <div class="button-box">
        <el-button size="default" @click="cancel">取消</el-button>
        <el-button type="primary" size="default" @click="confirm"
          >提交评价</el-button
        >
      </div>
    </section>
  </div>
</template>

<script>
import BreadCrumb from '@components/bread_crumb.vue';
import API from '@apis/student_mange/index';
const sessionInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
export default {
  name: 'studentEvaluate',
  components: {
    BreadCrumb
  },
  data() {
    return {
      remark: '',
      isDisabled: false,
      loading:false,
      form: {
        firmResult: '',
        attendQualified: '',
        workComplete: '',
        firmComment: ''
      },
      options: [
        {
          key: '1',
          label: '是'
        },
        {
          key: '0',
          label: '否'
        }
      ],
      rules: {
        firmResult: [
          { required: true, message: '成绩不能为空', trigger: 'change' }
        ],
        attendQualified: [
          { required: true, message: '不能为空', trigger: 'change' }
        ],
        workComplete: [
          { required: true, message: '不能为空', trigger: 'change' }
        ],
        firmComment: [
          { required: true, message: '不能为空', trigger: 'change' }
        ]
      }
    };
  },
  mounted() {},
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    cancel() {
      this.$refs.form.clearValidate();
      this.goBack();
    },
    confirm() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return;
        }
        const param = {
          ...this.form,
          userTableId: this.$route.query.userInfo.userId,
          firmId: sessionInfo.userId,
          creatBy: sessionInfo.userId,
          updateBy: sessionInfo.userId
        };
        API.addEvaluate(param)
          .then(res => {
            if (res.code === -1) {
              this.$message.error('评价失败');
              return;
            }
            this.$message.success('提交成功');
            this.form = res.data;
            this.isDisabled = true;
          })
          .finally(() => {
            this.loading = false;
          });
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  .section {
    margin-top: 30px;
    .el-form {
      .el-input,
      .el-textarea {
        width: 450px;
      }
    }
  }
  .button-box {
    margin-top: 30px;
    width: 600px;
    text-align: center;
    .el-button {
      width: 200px;
    }
  }
}
</style>
