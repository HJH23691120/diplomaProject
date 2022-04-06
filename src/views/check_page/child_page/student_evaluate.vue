<template>
  <div class="container">
    <!-- 学生评价 -->
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
        <el-form-item label="实习成绩">
          <el-input
            :value="form.firmResult"
            placeholder="请输入成绩"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="考勤是否合格">
          <el-radio-group v-model="form.attendQualified" disabled>
            <el-radio
              v-for="item in options"
              :key="item.key"
              :label="item.key"
            >
              {{ item.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工作是否完成">
          <el-radio-group v-model="form.workComplete" disabled>
            <el-radio
              v-for="item in options"
              :key="item.key"
              :label="item.key"
            >
              {{ item.label }}
            </el-radio>
          </el-radio-group>
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
            v-model="form.teacherResult"
            placeholder="请输入成绩"
          ></el-input>
        </el-form-item>
        <el-form-item label="学生评价" prop="teacherAppraise">
          <el-input
            v-model="form.teacherAppraise"
            type="textarea"
            :rows="10"
            placeholder="请输入评价"
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
import API from '@apis/student_mange/index';
const sessionInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
export default {
  name: 'studentEvaluate',
  data() {
    return {
      remark: '',
      isDisabled: false,
      loading: false,
      form: {
        firmResult: '',
        attendQualified: '',
        workComplete: '',
        firmComment: '',
        teacherResult:'',
        teacherAppraise:''

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
        teacherResult: [
          { required: true, message: '成绩不能为空', trigger: 'blur' }
        ],
        teacherAppraise: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    const tempInfo = this.$route.query.userInfo;
    const param = {
      userId: tempInfo.userId
    };
    this.loading = true;
    Promise.all([API.getWeekNum(param), API.getEvaluate(param)])
      .then(res => {
        const isAllReady = res.some(item => item.code === -1);
        if (isAllReady) {
          const msg =
            '当前学生周记数未提交，或者企业导师尚未评价，请联系学生补交周记或提醒企业导师评价';
          this.$confirm(msg)
            // 请求接口，弹窗提示
            .then(() => {
              this.goBack();
            })
            .catch(error => {
              this.goBack();
            });
            this.form= {}
        } else {
          this.form = {
            firmResult: res[1].data.firmResult,
            attendQualified: res[1].data.attendQualified,
            workComplete: res[1].data.workComplete,
            firmComment: res[1].data.firmComment,
            teacherResult:res[1].data.teacherResult || '',
            teacherAppraise:res[1].data.teacherResult || ''
          };
        }
      })
      .finally(() => {
        this.loading = true;
      });
  },
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
          teacherId:sessionInfo.userId,
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
