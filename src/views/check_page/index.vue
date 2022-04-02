<template>
  <div class="container">
    <el-table :data="tableData" border stripe>
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
      <el-table-column min-width="150" align="center">
        <template v-slot="scoped">
          <div>
            <el-button
              type="text"
              size="default"
              @click="checkWeeklyReport(scoped.row)"
              >周记审核</el-button
            >
            <el-button
              type="text"
              size="default"
              @click="checkTrainee(scoped.row)"
              >实习申请</el-button
            >
            <el-button
              type="text"
              size="default"
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
export default {
  name: 'checKPage',
  components: {},
  computed: {},
  watch: {},
  data() {
    return {
      tableData: []
    };
  },
  created() {},
  mounted() {},
  methods: {
    checkWeeklyReport(row) {
      console.log(row);
    },
    checkTrainee(row) {
      console.log(row);
    },
    checkEvaluate(row) {
      console.log(row);
    }
  }
};
</script>
<style lang="scss" scoped></style>
