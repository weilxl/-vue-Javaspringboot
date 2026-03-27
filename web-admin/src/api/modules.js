import request from './request'

// 这里的 request 方法实际上是一个对 Axios 的封装，而不是 fetch。
// 你可以在 './request' 文件里找到 request 的定义，
// 通常类似于：import axios from 'axios'，然后 export default axios.create(...)。
// 所以这里底层使用的是 axios，不是 fetch。

export function loginApi(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

export function userInfoApi() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function getTodoTaskListApi() {
  return request({
    url: '/todo-task/list',
    method: 'get'
  })
}

export function addTodoTaskApi(data) {
  return request({
    url: '/todo-task/add',
    method: 'post',
    data
  })
}

export function updateTodoTaskApi(data) {
  return request({
    url: '/todo-task/update',
    method: 'put',
    data
  })
}

export function deleteTodoTaskApi(id) {
  return request({
    url: `/todo-task/delete/${id}`,
    method: 'delete'
  })
}

export function getScheduleListApi() {
  return request({
    url: '/schedule/list',
    method: 'get'
  })
}

export function addScheduleApi(data) {
  return request({
    url: '/schedule/add',
    method: 'post',
    data
  })
}

export function updateScheduleApi(data) {
  return request({
    url: '/schedule/update',
    method: 'put',
    data
  })
}

export function deleteScheduleApi(id) {
  return request({
    url: `/schedule/delete/${id}`,
    method: 'delete'
  })
}

export function getDashboardApi() {
  return request({
    url: '/stats/dashboard',
    method: 'get'
  })
}
