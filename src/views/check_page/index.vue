<template>
  <div class="container">
    <el-table :data="tableData" border stripe :loading="loading">
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
      <el-table-column min-width="220" label="操作" align="center">
        <template v-slot="scoped">
          <div>
            <el-button
              type="text"
              size="default"
              @click="checkWeeklyReport(scoped.row)"
              v-if="userInfo.userRole === '3' || userInfo.userRole === '1'"
              >周记审核</el-button
            >
            <el-button
              type="text"
              size="default"
              @click="checkTrainee(scoped.row)"
              v-if="userInfo.userRole === '3' || userInfo.userRole === '1'"
              >实习申请</el-button
            >
            <el-button
              type="text"
              size="default"
              @click="studentEvaluate(scoped.row)"
              v-if="userInfo.userRole === '3' || userInfo.userRole === '1'"
              >学生评价</el-button
            >
            <el-button
              type="text"
              size="default"
              v-if="userInfo.userRole === '2' || userInfo.userRole === '1'"
              @click="checkEvaluate(scoped.row)"
              >实习评价</el-button
            >
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import API from '@apis/userlist/index';
export default {
  name: 'checKPage',
  computed: {
    userRole() {
      const tempInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
      return tempInfo;
    }
  },
  data() {
    return {
      tableData: [],
      loading: false,
      pageNum: 1,
      pageSize: 50,
      userInfo: {}
    };
  },
  created() {
    this.userInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
    this.getTableData();
  },
  methods: {
    checkWeeklyReport(row) {
      // 周记管理
      this.$router.push({
        path: '/weekReport',
        query: {
          userInfo: row
        }
      });
    },
    checkTrainee(row) {
      // 实习申请
      this.$router.push({
        path: '/trainee',
        query: {
          userInfo: row
        }
      });
    },
    checkEvaluate(row) {
      //  实习评价
      this.$router.push({
        path: '/traineeEvaluate',
        query: {
          userInfo: row
        }
      });
    },
    studentEvaluate(row) {
      // 学生评价
      this.$router.push({
        path: '/studentEvaluate',
        query: {
          userInfo: row
        }
      });
    },
    getTableData() {
      const tempInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
      const tempRole = tempInfo.userRole;
      this.loading = true;
      const param = {
        userClass:
          tempRole === '2'
            ? 'admin'
            : ['3', '4'].includes(tempRole)
            ? tempInfo.userClass
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
        })
        .finally(() => {
          this.loading = false;
        });
    }
  }
};
</script>
<style lang="scss" scoped></style>
