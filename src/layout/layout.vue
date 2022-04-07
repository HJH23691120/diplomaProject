<template>
  <div class="app-container">
    <el-container>
      <el-header>
        <div class="title">
          学生实习管理系统
        </div>
        <div>
          <el-button type="text" size="default" @click="showCenter"
            >个人中心</el-button
          >

          <el-button
            class="logout-btn"
            type="text"
            size="default"
            @click.stop="logout"
            >退出</el-button
          >
        </div>
      </el-header>
      <el-container class="my-container">
        <el-card
          class="my-card"
          shadow="always"
          :body-style="{ height: '100%', padding: '0' }"
        >
          <el-menu mode="horizontal" :default-active="selectedIndex">
            <div v-for="route in routeList" :key="route.path">
              <router-link :to="route.path">
                <el-menu-item
                  v-if="!route.hidden"
                  :key="route.name"
                  :index="route.path"
                >
                  <div>
                    <i class="el-icon-menu"></i>
                    <span>{{ route.meta && route.meta.title }}</span>
                  </div>
                </el-menu-item>
              </router-link>
            </div>
          </el-menu>
        </el-card>
        <el-main class="my-main">
          <BreadCrumb class="bread-crumb"></BreadCrumb>
          <router-view :key="key"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import BreadCrumb from '@components/bread_crumb.vue';
import { routeMaps } from './../router/index';
export default {
  name: 'LayoutView',
  computed: {
    key() {
      return this.$route.path;
    }
  },
  components: {
    BreadCrumb
  },
  data() {
    return {
      routeList: [],
      selectedIndex: '/'
    };
  },
  created() {
    const tempInfo = JSON.parse(sessionStorage.getItem('userInfo') || '{}');
    const tempArr = routeMaps.filter(item => 
      item.meta.limits.includes(tempInfo.userRole));
    this.routeList = tempArr.length !== 0 ? tempArr : routeMaps;
    if (this.$route.name === 'homepage') {
      this.selectedIndex = '/';
    } else {
      this.selectedIndex = `/${this.$route.name}`;
    }
  },
  mounted() {},
  methods: {
    logout() {
      try {
        this.$router.push('/login');
      } catch (error) {
        console.log('error');
      }
    },
    showCenter() {
      if (this.$route.path !== '/personalCenter') {
        this.$router.push('/personalCenter');
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.app-container {
  height: 100%;
  width: 100%;
  box-sizing: border-box;
  background-color: #eff1f5;
  .el-container {
    height: 100%;
  }
  .el-header {
    position: fixed;
    z-index: 999;
    left: 10px;
    right: 10px;
    height: 64px;
    line-height: 64px;
    background-color: #fff;
    box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.15);
    border-radius: 5px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .title {
      display: flex;
      align-items: center;
      font-weight: 600;
    }
  }
  .my-container {
    position: relative;
    height: 100%;
    display: flex;
    .my-card {
      height: calc(100% - 82px);
      position: fixed;
      left: 10px;
      top: 80px;
      width: 250px;
      overflow-y: auto;
    }
    .my-main {
      flex: 1;
      background-color: #ffffff;
      border-radius: 5px;
      margin: 82px 10px 0 270px;
      height: calc(100% - 82px);
      position: relative;
      .bread-crumb {
        margin: 10px 0 30px 0;
      }
    }
    a {
      text-decoration: none;
    }
  }
}
</style>
