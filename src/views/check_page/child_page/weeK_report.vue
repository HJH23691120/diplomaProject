<template>
  <div class="container">
    <!-- 周记审核 -->
    <BreadCrumb></BreadCrumb>
    <section class="section">
      <!-- 周记选择框-->
      <div class="select">
        <span>请选择第</span
        ><el-select v-model="weekNum" clearable filterable @change="handleWeek">
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
        <el-button type="primary" size="default" @click="audit">
          审核
        </el-button>
      </div>
      <!-- 审核弹窗 -->
      <Dialog
        :title="'周记审核'"
        :isVisible.sync="visible"
        @confirm="confirm"
      />
    </section>
  </div>
</template>

<script>
import BreadCrumb from '@components/bread_crumb.vue';
import Dialog from './../component/audit_dialog.vue';
import API from '@apis/student_mange/index';
export default {
  name: 'weekReport',
  components: {
    BreadCrumb,
    Dialog
  },
  data() {
    return {
      weekNum: '1',
      weekReport: '',
      visible: false
    };
  },
  created() {
    this.initData();
  },
  methods: {
    handleWeek() {
      this.initData();
    },
    initData() {
      const tempInfo = this.$route.query.userInfo;
      const param = {
        userId: tempInfo.userId,
        weekNum: this.weekNum
      };
      API.getWeek(param).then(res => {
        if (res.code === -1) {
          this.$message.error(`第${this.weekNum}周的周记为空，请联系学生添加`);
          return;
        }
        this.weekReport = res.data.practiceWeekReport;
      });
    },
    audit() {
      this.visible = true;
    },
    confirm(data) {
      const tempInfo = this.$route.query.userInfo;
      const userInfo = JSON.parse(sessionStorage.getItem('userInfo')|| '{}');
      const param = {
        userTableId: tempInfo.userId,
        practiceWeek: String(this.week),
        auditTeacherId: sessionStorage.getItem('userId'),
        creatBy: tempInfo.userName,
        updateBy: userInfo.userName,
        weekReportAudit: data
      };
      API.updateWeek(param).then(res => {
        if (res.code === -1) {
          this.$message.error('提交失败');
          return;
        }
        this.$message.success('审核成功');
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
