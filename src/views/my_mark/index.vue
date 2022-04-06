<template>
  <div class="container">
    <section class="section">
      <el-form
        :model="form"
        ref="form"
        label-width="120px"
        :inline="false"
        size="normal"
        :loading="loading"
        :disabled="isDisabled"
      >
        <el-form-item label="实习成绩">
          <el-input
            :value="form.firmResult"
            placeholder="请输入成绩"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="考勤是否合格">
          {{radioMap[form.attendQualified]}}
        </el-form-item>
        <el-form-item label="工作是否完成">
            {{radioMap[form.workComplete]}}
        </el-form-item>
        <el-form-item label="企业导师评语">
          <el-input
            :value="form.firmComment"
            type="textarea"
            :rows="10"
            placeholder="请输入评价"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="学生成绩" prop="teacherResult">
          <el-input
            :value="form.teacherResult"
            placeholder="请输入成绩"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="学生评价" prop="teacherAppraise">
          <el-input
            :value="form.teacherAppraise"
            type="textarea"
            :rows="10"
            disabled
            placeholder="请输入评价"
          ></el-input>
        </el-form-item>
      </el-form>
    </section>
  </div>
</template>

<script>
import API from '@apis/student_mange/index';
const sessionInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
const radioMap = {
  '1':'是',
  '0':'否'
}
export default {
  name: 'MyMark',
  components: {
  },
  data() {
    return {
      radioMap,
      remark: '',
      isDisabled: false,
      loading: false,
      form: {
        firmResult: '',
        attendQualified: '',
        workComplete: '',
        firmComment: '',
        teacherResult: '',
        teacherAppraise: ''
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
      ]
    };
  },
  created() {
    const param = {
      userId: sessionInfo.userId
    };
    this.loading = true;
    API.getEvaluate(param)
      .then(res => {
        if (res.code === -1) {
          this.$confirm('暂时没有成绩')
            // 请求接口，弹窗提示
            .then(() => {
              this.goBack();
            })
            .catch(error => {
              console.log(error);
            });
          return;
        }
        this.form = res.data;
      })
      .finally(() => {
        this.loading = true;
      });
  },
  methods: {
    goBack() {
      this.$router.go(-1);
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
