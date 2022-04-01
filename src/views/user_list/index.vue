<template>
  <div class="container">
    <el-form
      :model="form"
      ref="form"
      label-width="80px"
      :inline="true"
      size="normal"
      v-if="this.userInfo.userRole === 1"
    >
      <el-form-item label="">
        <el-input v-model="form.userClass" placeholder="请输入班级"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="form.useRole" placeholder="请选择角色"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="reset">重置</el-button>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" border stripe max-height="850px">
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
        prop="userRole"
        label="用户角色"
        min-width="150"
        align="center"
      >
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
  </div>
</template>

<script>
export default {
  name: 'userList',
  data() {
    return {
      form: {},
      userInfo: {},
      pageNum: 1, // 当前页数
      pageSize: 50, // 当前每页数量
      total: 0, // 总数
      tableData: []
    };
  },
  created() {
    const tempInfo = sessionStorage.getItem('userInfo');
    this.userInfo = JSON.parse(tempInfo || '{}');
    console.log(this.userInfo);
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
    getTableData() {},
    search() {},
    reset() {}
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
}
</style>
