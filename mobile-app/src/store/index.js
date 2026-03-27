import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token') || '',
    userInfo: JSON.parse(localStorage.getItem('userInfo') || '{}')
  },
  mutations: {
    setAuth(state, payload) {
      state.token = payload.token
      state.userInfo = payload.userInfo || payload
      localStorage.setItem('token', payload.token)
      localStorage.setItem('userInfo', JSON.stringify(state.userInfo))
    },
    clearAuth(state) {
      state.token = ''
      state.userInfo = {}
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
    }
  }
})
