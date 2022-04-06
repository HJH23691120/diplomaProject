<template>
  <div class="container">
    <div class="button">
        <el-button
        type="text"
        @click="changeEdit"
        v-if="form.agreeApply !== '1' && form.id"
      >
        修改
      </el-button>
    </div>
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
      <el-form-item label="单位名称" prop="unitName">
        <el-input
          v-model="form.unitName"
          placeholder="请输入单位名称"
          size="normal"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="实习岗位" prop="practiceJob">
        <el-input
          v-model="form.practiceJob"
          placeholder="请输入实习岗位"
          size="normal"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="企业联系人" prop="firmContact">
        <el-input
          v-model="form.firmContact"
          placeholder="请输入企业联系人"
          size="normal"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="企业电话" prop="firmTel">
        <el-input
          v-model="form.firmTel"
          placeholder="请输入企业电话"
          size="normal"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="企业导师" prop="firmTutor">
        <el-select
          v-model="form.firmTutor"
          filterable
          remote
          reserve-keyword
          placeholder="请输入企业导师"
          :remote-method="querySearch"
          :loading="selectLoading"
        >
          <el-option
            v-for="item in tutorList"
            :key="item.user_id"
            :label="item.user_name"
            :value="item.user_name"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="企业待遇" prop="practiceTreatment">
        <el-input
          v-model="form.practiceTreatment"
          placeholder="请输入企业待遇"
          size="normal"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="实习时间" prop="practiceTime">
        <el-date-picker
          v-model="form.practiceTime"
          type="datetime"
          placeholder="选择日期时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实习地点" prop="practicePlace">
        <el-input
          v-model="form.practicePlace"
          placeholder="请输入实习地点"
          size="normal"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="证明材料">
        <el-upload
          action
          ref="upload"
          :auto-upload="false"
          :on-change="handleUpload"
        >
          <el-button type="primary" plain size="small" :loading="uploadLoading">
            <i class="nb-icon-plus"></i>
            上传文件
          </el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="审核结果" v-if="form.agreeApply">
        {{ form.agreeApply === "1" ? "同意" : "驳回" }}
      </el-form-item>
    </el-form>
    <div class="button-box">
      <div v-if="form.id">
        <div class="tips" v-if="form.agreeApply === '1'">
          已审核过的实习申请不允许修改
        </div>
        <el-button
          type="primary"
          :disabled="form.agreeApply === '1'"
          size="default"
          @click="update"
          >修改申请</el-button
        >
      </div>

      <el-button type="primary" v-else size="default" @click="confirm"
        >提交申请</el-button
      >
    </div>
  </div>
</template>

<script>
import API from '@apis/student_mange/index';
export default {
  name: 'internship',
  components: {},
  computed: {},
  watch: {},
  data() {
    return {
      loading: false,
      uploadLoading: false,
      isDisabled: false,
      rules: {
        unitName: [
          { required: true, message: '账号不能为空', trigger: 'change' }
        ],
        practiceJob: [
          { required: true, message: '账号不能为空', trigger: 'change' }
        ],
        firmContact: [
          { required: true, message: '不能为空', trigger: 'change' }
        ],
        firmTel: [{ required: true, message: '不能为空', trigger: 'change' }],
        firmTutor: [{ required: true, message: '不能为空', trigger: 'blur' }],
        practiceTreatment: [
          { required: true, message: '不能为空', trigger: 'change' }
        ],
        practiceTime: [
          { required: true, message: '不能为空', trigger: 'change' }
        ],
        practicePlace: [
          { required: true, message: '不能为空', trigger: 'change' }
        ],
        uploaadProve: [
          { required: true, message: '密码不能为空', trigger: 'change' }
        ]
      },
      form: {
        unitName: '',
        practiceJob: '',
        firmContact: '',
        firmTel: '',
        firmTutor: '',
        practiceTreatment: '',
        practiceTime: '',
        practicePlace: '',
        uploaadProve: ''
      },
      tutorList: [],
      selectLoading: false
    };
  },
  created() {
    this.initData();
  },
  mounted() {},
  methods: {
    initData() {
      API.getPracticeApply({
        userId: sessionStorage.getItem('userId')
      }).then(res => {
        if (res.code === -1) {
          this.$message.warning('未查出实习申请');
          return;
        }
        this.form = res.data;
        this.isDisabled = true;
      });
    },
    update() {
      const param = {
        ...this.form,
        userTableId: sessionStorage.getItem('userId'),
        creatBy: sessionStorage.getItem('userId'),
        updateBy: sessionStorage.getItem('userId')
      };
      API.updatePracticeApply(param).then(res => {
        if (res.code === -1) {
          this.$message.error('修改实习申请失败');
          return;
        }
        this.$message.success('修改成功');
        this.form = res.data;
        this.isDisabled = true;
      });
    },
    confirm() {
      const param = {
        ...this.form,
        userTableId: sessionStorage.getItem('userId'),
        creatBy: sessionStorage.getItem('userId'),
        updateBy: sessionStorage.getItem('userId')
      };
      API.applyPractice(param).then(res => {
        if (res.code === -1) {
          this.$message.error('提交实习申请失败');
          return;
        }
        this.$message.success('提交成功');
        this.form = res.data;
        this.isDisabled = true;
      });
    },
    handleUpload(file) {
      this.errMsg = '';
      // handleUpload(file, fileList) {
      // const reg = /\.xlsx$/;
      // const fileName = file.name;
      // if (!reg.test(fileName)) {
      //   this.$alert('请上传正确的表格文件！', '提示', {
      //     confirmButtonText: '确定',
      //     type: 'warning'
      //   });
      //   return;
      // }
      this.uploadLoading = true;
      const reader = new FileReader();
      reader.onload = () => {
        const fileBuffer = reader.result;
        API.upload({
          userId: sessionStorage.getItem('userId'),
          File: fileBuffer
        })
          .then(res => {
            if (res.code === -1) {
              this.$message.error('上传失败');
              return;
            }
            this.$message.success('上传成功');
          })
          .finally(() => {
            this.uploadLoading = false;
          });
      };
      reader.readAsArrayBuffer(file.raw);
    },
    querySearch(query) {
      console.log(query);
      if (!query) {
        return;
      }
      this.selectLoading = true;
      API.getFirmTutro({
        userName: query
      })
        .then(res => {
          if (res.code === -1) {
            this.$message.error('未查询到企业导师');
            return;
          }
          this.tutorList = res.data;
        })
        .finally(() => {
          this.selectLoading = false;
        });
    },
    changeEdit() {
      this.isDisabled = false;
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  .el-input,
  .el-select {
    width: 480px;
  }
  .button-box {
    margin-top: 30px;
    .el-button {
      margin-left: 50px;
      width: 200px;
    }
    .tips {
      margin: 10px 0;
      font-size: 10px;
      color: red;
    }
  }
  .button {
     width: 200px;
    height: 50px;
    position: absolute;
    left: 420px;
    top: 23px;
    .el-button {
      float: right;
    }
  }
}
</style>
