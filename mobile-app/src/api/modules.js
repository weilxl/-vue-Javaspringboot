import request from './request'

export function loginApi(data) {
  return request({ url: '/auth/login', method: 'post', data })
}

export function registerApi(data) {
  return request({ url: '/auth/register', method: 'post', data })
}

export function getTodoTaskListApi() {
  return request({ url: '/todo-task/list', method: 'get' })
}

export function addTodoTaskApi(data) {
  return request({ url: '/todo-task/add', method: 'post', data })
}

export function updateTodoTaskApi(data) {
  return request({ url: '/todo-task/update', method: 'put', data })
}

export function deleteTodoTaskApi(id) {
  return request({ url: `/todo-task/delete/${id}`, method: 'delete' })
}

export function getScheduleListApi() {
  return request({ url: '/schedule/list', method: 'get' })
}

export function addScheduleApi(data) {
  return request({ url: '/schedule/add', method: 'post', data })
}

export function updateScheduleApi(data) {
  return request({ url: '/schedule/update', method: 'put', data })
}

export function deleteScheduleApi(id) {
  return request({ url: `/schedule/delete/${id}`, method: 'delete' })
}
