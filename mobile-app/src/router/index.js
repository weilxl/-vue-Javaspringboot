import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import TodoTaskView from '@/views/TodoTaskView.vue'
import ScheduleView from '@/views/ScheduleView.vue'
import MineView from '@/views/MineView.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'LoginView', component: LoginView },
  { path: '/register', name: 'RegisterView', component: RegisterView },
  { path: '/todo-task', name: 'TodoTaskView', component: TodoTaskView, meta: { requiresAuth: true } },
  { path: '/schedule', name: 'ScheduleView', component: ScheduleView, meta: { requiresAuth: true } },
  { path: '/mine', name: 'MineView', component: MineView, meta: { requiresAuth: true } }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
