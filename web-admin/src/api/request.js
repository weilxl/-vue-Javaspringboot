import axios from 'axios'
import store from '@/store'
import router from '@/router'

const service = axios.create({
  baseURL: process.env.NODE_ENV === 'development' ? '/api' : '/api',
  timeout: 10000
})

service.interceptors.request.use((config) => {
  const token = store.state.token
  if (token) {
    config.headers.Authorization = token.startsWith('Bearer ') ? token : 'Bearer ' + token
  }
  return config
})

service.interceptors.response.use((response) => {
  const res = response.data
  if (res.code !== 200) {
    return Promise.reject(new Error(res.message || '请求失败'))
  }
  return res
}, (error) => {
  if (error.response && error.response.status === 401) {
    store.commit('clearAuth')
    router.replace('/login')
  }
  return Promise.reject(error)
})

export default service
