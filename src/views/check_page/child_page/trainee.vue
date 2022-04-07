<template>
  <div class="container">
    <header class="header">{{ tableInfo.userName }}实习申请</header>
    <el-form
      :model="form"
      ref="form"
      label-width="120px"
      :inline="false"
      size="normal"
    >
      <el-form-item label="学生名称：">
        <div>{{ form.unitName }}</div>
      </el-form-item>
      <el-form-item label="单位名称：">
        <div>{{ form.unitName }}</div>
      </el-form-item>
      <el-form-item label="实习岗位：">
        <div>{{ form.practiceJob }}</div>
      </el-form-item>
      <el-form-item label="企业联系人：">
        <div>{{ form.firmContact }}</div>
      </el-form-item>
      <el-form-item label="企业电话：">
        <div>{{ form.firmTel }}</div>
      </el-form-item>
      <el-form-item label="企业导师：">
        <div>{{ form.firmTutor }}</div>
      </el-form-item>
      <el-form-item label="实习待遇：">
        <div>{{ form.practiceTreatment }}</div>
      </el-form-item>
      <el-form-item label="实习时间：">
        <div>{{ form.practiceTime }}</div>
      </el-form-item>
      <el-form-item label="实习地点：">
        <div>{{ form.practicePlace }}</div>
      </el-form-item>
      <el-form-item label="证明材料：">
        <el-button type="text" @click="download(form.uploaadProve)">
          {{ form.uploaadProve }}
        </el-button>
      </el-form-item>
      <el-form-item label="是否同意申请：">
        <el-radio-group v-model="form.agreeApply">
          <el-radio
            v-for="item in options"
            :key="item.value"
            :label="item.value"
          >
            {{ item.label }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <div class="button-box">
      <el-button @click="cancel">取消</el-button>
      <el-button type="primary" @click="confirm">提交</el-button>
    </div>
  </div>
</template>

<script>
import API from '@apis/student_mange/index';
export default {
  name: 'trainee',
  computed: {
    tableInfo() {
      return this.$route.query.userInfo;
    },
  },
  data() {
    return {
      form: {},
      options: [
        {
          value: '1',
          label: '同意',
        },
        {
          value: '0',
          label: '打回',
        },
      ],
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      const tempInfo = this.$route.query.userInfo;
      const param = {
        userId: tempInfo.userId,
      };
      API.getPracticeApply(param).then((res) => {
        if (res.code === -1) {
          this.$message.error('未查询出实习申请');
          return;
        }
        this.form = res.data;
      });
    },
    cancel() {
      this.$router.go(-1);
    },
    confirm() {
      const param = {
        ...this.form,
        updateBy: sessionStorage.getItem('userId'),
      };
      API.updatePracticeApply(param).then((res) => {
        if (res.code === -1) {
          this.$message.error('审核实习申请失败');
          return;
        }
        this.$message.success('审核成功');
        this.form = res.data;
      });
    },
    download(fileName) {
      const tempInfoID = this.$route.query.userInfo.userId;
    
        window.open(`http://localhost:8080/download?userId=${tempInfoID}&fileName=${fileName}`,'_blank')
     
    },
   
  },
};
</script>
<style lang="scss" scoped>
.container {
  .header {
    width: 400px;
    margin: 30px 0;
    text-align: center;
  }
  .el-form {
    width: 800px;
  }
  .button-box {
    .el-button {
      width: 200px;
    }
  }
}
</style>
