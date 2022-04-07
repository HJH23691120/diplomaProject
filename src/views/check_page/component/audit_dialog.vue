<template>
  <div class="dialog-container">
    <el-dialog
      :title="title"
      :visible.sync="visible"
      width="590px"
      @close="handleClose"
    >
      <el-form ref="form" label-width="80px" :inline="false" size="normal">
        <el-form-item label="评价：" size="normal">
          <el-input
            v-model="remark"
            placeholder="请输入评价"
            type="textarea"
            size="normal"
            clearable
            :rows="6"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item class="button-box">
          <el-button @click="cancel">取消</el-button>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'dialogAudit',
  props: {
    title: {
      type: String
    },
    isVisible: {
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
      remark: '',
    };
  },
  methods: {
    handleClose() {
      this.cancel();
    },
    cancel() {
      this.visible = false;
      this.remark = '';
    },
    onSubmit() {
      if (!this.remark) {
        this.$message.error('评价不能为空');
        return;
      }
      this.$emit('confirm', this.remark);
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
