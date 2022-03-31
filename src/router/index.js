import Vue from 'vue';
import Router from 'vue-router';
import Layout from '@/layout/layout';
Vue.use(Router);

export const routeMaps = [
  {
    path: '/home',
    name: 'home',
    component: Layout,
    redirect: '/homePage',
    hidden: false,
    meta: {
      title: '首页'
    },
    children: [
      {
        path: '/homePage',
        name: 'homePage',
        component: () => import('@views/home_page/index.vue')
      },
    ]
  },
  {
    path: '/check',
    name: 'check',
    redirect: '/checkPage',
    component: Layout,
    hidden: false,
    meta: {
      title: '审核'
    },
    children: [
      {
        path: '/checkPage',
        name: 'checkPage',
        component: () => import('@views/check_page/index.vue')
      },
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
  },
];
const router = new Router({
  // mode: 'history',
  // scrollBehavior: () => ({ y: 0 }),
  routes: routeMaps // 抛出路由数组
});
// router.beforeEach((to, from, next) => {
//   const isLogin = sessionStorage.getItem('useID');
//   console.log(to.path);
//   if (to.path !== '/login') {
//     if (!isLogin) {
//       next('/login');
//     } else {
//       next();
//     }
//   }
//   next()
// });
export default router;
