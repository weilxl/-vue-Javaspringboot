import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import LoginView from '@/views/LoginView.vue'
import LayoutView from '@/views/LayoutView.vue'
import DashboardView from '@/views/DashboardView.vue'
import TodoTaskView from '@/views/TodoTaskView.vue'
import ScheduleView from '@/views/ScheduleView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/',
    component: LayoutView,
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        name: 'DashboardView',
        component: DashboardView,
        meta: { title: '首页仪表盘', icon: 'el-icon-s-home' }
      },
      {
        path: '/todo-task',
        name: 'TodoTaskView',
        component: TodoTaskView,
        meta: { title: '待办任务管理', icon: 'el-icon-document' }
      },
      {
        path: '/schedule',
        name: 'ScheduleView',
        component: ScheduleView,
        meta: { title: '日程安排管理', icon: 'el-icon-s-data' }
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 这是全局路由守卫（导航守卫），用于控制用户访问权限和登录流程。
// 每次路由跳转前都会执行这里的函数：
// 1. 如果用户未登录（没有 token），并且访问的不是登录页，
// 则强制跳转到登录页。
// 2. 如果用户已登录（有 token），还想再去登录页，
// 则自动跳转到首页仪表盘。
// 3. 其他情况（如目标页面不受限制或已登录访问正常页面），直接放行。

router.beforeEach((to, from, next) => {
  const token = store.state.token
  if (to.path !== '/login' && !token) {
    next('/login')
    return
  }
  if (to.path === '/login' && token) {
    next('/dashboard')
    return
  }
  next()
})

export default router
