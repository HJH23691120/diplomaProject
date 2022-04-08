<template>
  <div class="container">
    <el-form
      :model="form"
      ref="form"
      label-width="80px"
      :inline="true"
      size="normal"
      v-if="userInfo.userRole === '1'"
    >
      <el-form-item label="">
        <el-input v-model="form.userClass" placeholder="请输入班级"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="form.userRole" placeholder="请选择角色"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="reset">重置</el-button>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>
    <div class="add">
      <el-button type="primary" size="default" @click="addUser"
        >新增用户</el-button
      >
    </div>
    <el-table
      :data="tableData"
      border
      stripe
      max-height="850px"
      :loading="loading"
    >
      <el-table-column
        prop="userId"
        label="用户ID"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="userName"
        label="用户名"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="userGender"
        label="用户性别"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="userClass"
        label="用户班级"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        label="用户角色"
        min-width="150"
        align="center"
      >
        <template v-slot="scoped">
          <div>
            {{ userType[scoped.row.userRole] }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="用户手机号" min-width="150" align="center">
        <template v-slot="scoped">
          <div>
            {{ scoped.row.userTel ? scoped.row.userTel : "-" }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="creatBy"
        label="创建人"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="creatDate"
        label="创建时间"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="updateBy"
        label="更新人"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="updateDate"
        label="更新时间"
        min-width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column min-width="220" label="操作" align="center">
        <template v-slot="scoped">
          <div>
            <el-button type="text" size="default" @click="edit(scoped.row)"
              >修改</el-button
            >
            <el-button type="text" size="default" @click="remove(scoped.row)"
              >删除</el-button
            >
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div class="page-turner">
      <el-pagination
        background
        :page-sizes="[50, 100, 150, 200, 300]"
        :page-size="pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        :current-page="pageNum"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
    <Dialog
      :isVisible.sync="visible"
      :editData="rowData"
      :isCreate="isCreate"
      :title="title"
      @success="getTableData"
    />
  </div>
</template>

<script>
import API from '@apis/userlist/index.js';
import Dialog from './component/user_dialog.vue';
const userType={
  '1':'管理员',
  '2':'企业导师',
  '3':'校内导师',
  '4':'学生',
}
export default {
  name: 'userList',
  components: {
    Dialog
  },
  data() {
    return {
      userType,
      form: {},
      userInfo: {},
      pageNum: 1, // 当前页数
      pageSize: 50, // 当前每页数量
      total: 0, // 总数
      tableData: [{}],
      visible: false,
      rowData: {},
      title: '',
      isCreate: false,
      loading: false
    };
  },
  created() {
    const tempInfo = sessionStorage.getItem('userInfo');
    this.userInfo = JSON.parse(tempInfo || '{}');
    this.getTableData();
  },
  mounted() {},
  methods: {
    handleSizeChange(value) {
      this.pageSize = value;
      this.getTableData();
    },
    // 改变pageNum
    handleCurrentChange(value) {
      this.pageNum = value;
      this.getTableData();
    },
    getTableData() {
      const tempRole = this.userInfo.userRole;
      this.loading = true;
      const param = {
        userClass:
          tempRole === '2'
            ? 'admin'
            : ['3', '4'].includes(tempRole)
            ? this.userInfo.userClass
            : '',
        userRole: tempRole === '1' ? '' : '4',
        userId:
          tempRole === '2'
            ? this.userInfo.userId
            : tempRole === '1'
            ? ''
            : 'admin',
        pageIndex: this.pageNum,
        pageSize: this.pageSize
      };
      API.getUserList(param)
        .then(res => {
          if (res.code === -1) {
            this.$message.error('未查到用户信息');
            return;
          }
          this.tableData = res.data.list;
          this.total = res.data.totalSize;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    search() {
      this.pageNum = 1;
      this.getTableData();
    },
    reset() {
      Object.keys(this.form).forEach(key => (this.form[key] = ''));
      this.pageNum = 1;
      this.getTableData();
    },
    addUser() {
      this.visible = true;
      this.isCreate = true;
      this.title = '新增用户';
    },
    edit(row) {
      this.visible = true;
      this.isCreate = false;
      this.title = '修改用户';
      console.log(row);
      this.rowData = row;
    },
    remove(row) {
      this.$confirm('确认删除当前用户么')
        // 请求接口，弹窗提示
        .then(() => {
          API.delUser({
            id: row.userId,
            userRole: row.userRole
          }).then(res => {
            if (res.code === 1) {
              this.$message.error('删除失败');
              return;
            }
            this.$message.success('删除成功');
            this.getTableData();
          });
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  padding-top: 20px;
  .page-turner {
    margin-top: 20px;
    padding: 10px 0;
    float: right;
  }
  .add {
    margin-bottom: 20px;
  }
}
</style>
