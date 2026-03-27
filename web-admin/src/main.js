import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import store from './store'
import './styles/theme.css'

Vue.config.productionTip = false
Vue.use(ElementUI)

new Vue({
  // 路由（页面切换）。
  router,
  // store：Vuex（登录态、用户信息）。
  store,
  render: (h) => h(App)
}).$mount('#app')
