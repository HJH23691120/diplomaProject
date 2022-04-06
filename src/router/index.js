import Vue from 'vue';
import Router from 'vue-router';
import Layout from '@/layout/layout';
Vue.use(Router);

export const routeMaps = [
  {
    path: '/home',
    name: 'home',
    redirect: '/homePage',
    component: Layout,
    hidden: true,
    meta: {
      title: '首页',
      hiddenBack: true
    },
    children: [
      {
        path: '/homePage',
        name: 'homePage',
        component: () => import('@views/home/index.vue'),
        meta: {
          title: '',
          hiddenBack: true
        }
      }
    ]
  },
  {
    path: '/check',
    name: 'check',
    redirect: '/checkPage',
    component: Layout,
    hidden: false,
    meta: {
      title: '学生管理',
    },
    children: [
      {
        path: '/checkPage',
        name: 'checkPage',
        component: () => import('@views/check_page/index.vue'),
        meta: {
          title: '学生管理',
          hiddenBack: true
        }
      },
      {
        path: '/studentEvaluate', // 学生评价
        name: 'studentEvaluate',
        component: () =>
          import('@views/check_page/child_page/student_evaluate.vue'),
        meta: { hidden: false, title: '学生评价' }
      },
      {
        path: '/traineeEvaluate', // 实习评价
        name: 'traineeEvaluate',
        component: () =>
          import('@views/check_page/child_page/trainee_evaluate.vue'),
        meta: { hidden: false, title: '实习评价' }
      },
      {
        path: '/trainee', // 实习申请
        name: 'trainee',
        component: () => import('@views/check_page/child_page/trainee.vue'),
        meta: { hidden: false, title: '实习申请' }
      },
      {
        path: '/weekReport',
        name: 'weekReport',
        component: () => import('@views/check_page/child_page/weeK_report.vue'),
        meta: { hidden: false, title: '周记审核' }
      }
    ]
  },

  {
    path: '/center',
    name: 'center',
    redirect: '/personalCenter',
    component: Layout,
    hidden: true,
    meta: {
      title: '个人中心',
      hiddenBack: true
    },
    children: [
      {
        path: '/personalCenter',
        name: 'personalCenter',
        component: () => import('@views/personal_center/index.vue'),
        meta: {
          title: '',
          hiddenBack: true
        }
      }
    ]
  },
  {
    path: '/report',
    name: 'report',
    redirect: '/myReport',
    component: Layout,
    hidden: false,
    meta: {
      title: '我的周记',
      hiddenBack: true
    },
    children: [
      {
        path: '/myReport',
        name: 'myReport',
        component: () => import('@views/my_report/index.vue'),
        meta: {
          title: '我的周记',
          hiddenBack: true
        }
      }
    ]
  },
  {
    path: '/Mark',
    name: 'Mark',
    redirect: '/myMark',
    component: Layout,
    hidden: false,
    meta: {
      title: '我的成绩',
      hiddenBack: true
    },
    children: [
      {
        path: '/myMark',
        name: 'myMark',
        component: () => import('@views/my_mark/index.vue'),
        meta: {
          title: '我的成绩',
          hiddenBack: true
        }
      }
    ]
  },
  {
    path: '/internship',
    name: 'internship',
    redirect: '/myInternship',
    component: Layout,
    hidden: false,
    meta: {
      title: '实习申请',
      hiddenBack: true
    },
    children: [
      {
        path: '/myInternship',
        name: 'myInternship',
        component: () => import('@views/my_internship/index.vue'),
        meta: {
          title: '实习申请',
          hiddenBack: true
        }
      }
    ]
  },
  {
    path: '/list',
    name: 'list',
    redirect: '/userList',
    component: Layout,
    hidden: false,
    meta: {
      title: '用户管理',
      limits: ['1'],
    },
    children: [
      {
        path: '/userList',
        name: 'userList',
        component: () => import('@views/user_list/index.vue'),
        meta: {
          title: '用户管理',
          limits: ['1'],
          hiddenBack: true
        }
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@views/login/index.vue'),
    hidden: true,
    meta: {
      title: '登录'
    }
  }
];
const router = new Router({
  // mode: 'history',
  // scrollBehavior: () => ({ y: 0 }),
  routes: routeMaps // 抛出路由数组
});
router.beforeEach((to, from, next) => {
  const isLogin = sessionStorage.getItem('useID');
  console.log(to.path);
  if (to.path !== '/login') {
    if (!isLogin) {
      next('/login');
    } else {
      next();
    }
  }
  next()
});
export default router;
