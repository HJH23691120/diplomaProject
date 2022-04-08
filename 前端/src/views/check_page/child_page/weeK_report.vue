<template>
  <div class="container">
    <!-- 周记审核 -->
    <section class="section">
      <!-- 周记选择框-->
      <div class="select" v-loading="loading">
        <span>请选择第</span
        ><el-select
          v-model="practiceWeek"
          clearable
          filterable
          @change="handleWeek"
        >
          <el-option v-for="item in 8" :key="item" :label="item" :value="item">
          </el-option>
        </el-select>
        <span>/8周的周记查看</span>
      </div>
      <el-input
        :value="weekReport"
        type="textarea"
        size="normal"
        :rows="16"
        readonly
      ></el-input>

      <div class="audit-box">
        <el-input
          v-if="weekReportAudit"
          :value="weekReportAudit"
          type="textarea"
          size="normal"
          :rows="16"
          disabled
        ></el-input>
        <el-button
          type="primary"
          v-if="!weekReportAudit"
          size="default"
          @click="audit"
        >
          审核
        </el-button>
      </div>
      <!-- 审核弹窗 -->
      <Dialog
        :title="'周记审核'"
        :isVisible.sync="visible"
        @confirm="confirm"
        :loading="dialogLoading"
      />
    </section>
  </div>
</template>

<script>
import Dialog from './../component/audit_dialog.vue';
import API from '@apis/student_mange/index';
export default {
  name: 'weekReport',
  components: {
    Dialog
  },
  data() {
    return {
      practiceWeek: 1,
      weekReport: '',
      weekReportAudit: '',
      visible: false,
      loading: false,
      dialogLoading: false,
    };
  },
  created() {
    this.initData();
  },
  methods: {
    handleWeek(val) {
      if (val === '') {
        return;
      }
      this.initData();
    },
    initData() {
      const tempInfo = this.$route.query.userInfo;
      const param = {
        userId: tempInfo.userId,
        practiceWeek: this.practiceWeek + ''
      };
      this.loading = true;
      API.getWeek(param)
        .then(res => {
          if (res.code === -1) {
            this.$message.error(
              `第${this.practiceWeek}周的周记为空，请联系学生添加`
            );
            this.weekReportAudit = '';
            this.weekReport = '';
            return;
          }
          this.weekReport = res.data.practiceWeekReport;
          this.weekReportAudit = res.data.weekReportAudit;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    audit() {
      this.visible = true;
    },
    confirm(data) {
      const tempInfo = this.$route.query.userInfo;
      const userInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
      const param = {
        userTableId: tempInfo.userId,
        practiceWeek: String(this.week),
        auditTeacherId: sessionStorage.getItem('userId'),
        creatBy: tempInfo.userName,
        updateBy: userInfo.userName,
        weekReportAudit: data
      };
      this.dialogLoading = true;
      API.updateWeek(param)
        .then(res => {
          if (res.code === -1) {
            this.$message.error('提交失败');
            return;
          }
          this.$message.success('审核成功');
        })
        .finally(() => {
          this.dialogLoading = false;
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  .section {
    margin-top: 30px;
    padding-left: 10px;
    .select {
      display: flex;
      flex-direction: row;
      align-items: center;
      .el-select {
        width: 60px;
        margin: 0 10px;
      }
    }
    .el-textarea {
      margin-top: 20px;
      width: 500px;
    }
    .audit-box {
      margin-top: 30px;
      width: 500px;
      text-align: center;
      .el-button {
        width: 200px;
      }
    }
  }
}
</style>
