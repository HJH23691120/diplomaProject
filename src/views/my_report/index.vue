<template>
  <div class="container">
    <el-form
      :model="form"
      ref="form"
      label-width="120px"
      :inline="false"
      size="normal"
      :loading="loading"
    >
      <el-form-item label="当前周：">
        <el-radio-group v-model="form.practiceWeek" @change="handleClick">
          <el-radio v-for="item in 8" :key="item" :label="item">
            第{{ item }}周
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="周记">
        <el-input
          v-model="form.practiceWeekReport"
          placeholder="请输入周记"
          size="normal"
          type="textarea"
          clearable
          minlength="300"
          :rows="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="是否已审核">
        {{ form.weekReportAudit ? "是" : "未审核" }}
      </el-form-item>
      <div v-if="form.weekReportAudit">
        <el-form-item label="审核老师：">
          {{ form.auditTeacherId }}
        </el-form-item>
        <el-form-item label="老师评价">
          <el-input
            :value="form.practiceWeekReport"
            type="textarea"
            clearable
            :rows="10"
            readOnly
          ></el-input>
        </el-form-item>
      </div>
    </el-form>
    <div class="button-box">
      <div v-if="isHas">
        <div class="tips">已审核过的周记不允许修改</div>
        <el-button
          type="primary"
          :disabled="form.weekReportAudit"
          size="default"
          @click="updateWeek"
          >修改周记</el-button
        >
      </div>

      <el-button type="primary" v-else size="default" @click="addWeek"
        >添加周记</el-button
      >
    </div>
  </div>
</template>

<script>
import API from '@apis/student_mange/index';
export default {
  name: 'myReport',
  components: {},
  computed: {},
  watch: {},
  data() {
    return {
      form: {
        practiceWeek: 1
      },
      isHas: false,
      loading: false
    };
  },
  created() {
    this.getWeeks('1');
  },
  mounted() {},
  methods: {
    handleClick(val) {
      console.log(val);
      this.getWeeks(String(val));
    },
    getWeeks(val) {
      this.loading = true;
      API.getWeek({
        userId: sessionStorage.getItem('userID'),
        practiceWeek: val
      })
        .then(res => {
          if (res.code !== -1) {
            this.$message.error(`第${val}周的周记为空，请添加`);
            this.isHas = false;
            return;
          }
          this.form = res.data;
          this.isHas = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    addWeek() {
      const id = sessionStorage.getItem('userId');
      const param = {
        userTableId: id,
        practiceWeek: this.form.practiceWeek + '',
        creatBy: id,
        updateBy: id,
        practiceWeekReport: this.form.practiceWeekReport
      };
      this.loading = true;
      API.addWeek(param)
        .then(res => {
          if (res.code !== -1) {
            this.$message.error('新增周记失败');
            this.isHas = false;
            return;
          }
          this.form = res.data;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    updateWeek() {
      const id = sessionStorage.getItem('userId');
      const param = {
        userTableId: id,
        practiceWeek: this.form.practiceWeek + '',
        creatBy: id,
        updateBy: id,
        practiceWeekReport: this.form.practiceWeekReport
      };
      this.loading = true;
      API.updateWeek(param)
        .then(res => {
          if (res.code === -1) {
            this.$message.error('提交失败');
            return;
          }
          this.$message.success('修改成功');
          this.form = res.data;
        })
        .finally(() => {
          this.loading = false;
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  .el-textarea {
    width: 700px;
  }
  .button-box {
    margin-top: 30px;
    .el-button {
      margin-left: 80px;
      width: 200px;
    }
    .tips {
      margin: 10px 0;
      font-size: 10px;
      color: red;
    }
  }
}
</style>
