import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token') || '',
    userInfo: JSON.parse(localStorage.getItem('userInfo') || '{}')
  },
  mutations: {
    // 该 setAuth 函数在登录页面（如 LoginView.vue）登录成功后被调用，
    // 用于存储 token 和用户信息到 Vuex 状态管理和 localStorage 持久化
    setAuth(state, payload) {
      state.token = payload.token
      state.userInfo = payload.userInfo
      localStorage.setItem('token', payload.token)
      localStorage.setItem('userInfo', JSON.stringify(payload.userInfo))
    },
    clearAuth(state) {
      state.token = ''
      state.userInfo = {}
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
    }
  }
})
