CREATE DATABASE IF NOT EXISTS efficiency_system DEFAULT CHARACTER SET utf8mb4;
USE efficiency_system;

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(64) NOT NULL,
  real_name VARCHAR(50) NOT NULL,
  phone VARCHAR(20) DEFAULT NULL,
  role_code VARCHAR(30) NOT NULL,
  status TINYINT NOT NULL DEFAULT 1,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_code VARCHAR(30) NOT NULL,
  role_name VARCHAR(50) NOT NULL,
  permissions VARCHAR(255) DEFAULT NULL,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS todo_task;
CREATE TABLE todo_task (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  priority VARCHAR(20) DEFAULT '普通',
  status VARCHAR(20) DEFAULT '待办',
  due_date DATE DEFAULT NULL,
  remark VARCHAR(500) DEFAULT NULL,
  owner_username VARCHAR(50) DEFAULT NULL,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS schedule;
CREATE TABLE schedule (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  location VARCHAR(200) DEFAULT NULL,
  remark VARCHAR(500) DEFAULT NULL,
  owner_username VARCHAR(50) DEFAULT NULL,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO sys_role (role_code, role_name, permissions) VALUES
('ADMIN', '系统管理员', 'todo:add,todo:edit,todo:delete,schedule:add,schedule:edit,schedule:delete,role:view'),
('USER', '普通用户', 'todo:view,todo:add,schedule:view,schedule:add');

INSERT INTO sys_user (username, password, real_name, phone, role_code, status) VALUES
('admin', '0192023a7bbd73250516f069df18b500', '系统管理员', '13800001111', 'ADMIN', 1),
('zhangsan', '0192023a7bbd73250516f069df18b500', '张三', '13800002222', 'USER', 1),
('lisi', '0192023a7bbd73250516f069df18b500', '李四', '13800003333', 'USER', 1),
('wangwu', '0192023a7bbd73250516f069df18b500', '王五', '13800004444', 'USER', 1);

INSERT INTO todo_task (title, priority, status, due_date, remark, owner_username) VALUES
('完成周报', '高', '待办', '2026-03-10', '周五前提交', 'admin'),
('复习Vue基础', '中', '进行中', '2026-03-12', '面试准备', 'zhangsan'),
('预约体检', '高', '待办', '2026-03-15', '公司年度体检', 'lisi'),
('整理学习笔记', '低', '已完成', '2026-03-08', '个人效率系统笔记', 'admin'),
('购买日用品', '中', '待办', '2026-03-11', '周末超市', 'wangwu');

INSERT INTO schedule (title, start_time, end_time, location, remark, owner_username) VALUES
('部门周会', '2026-03-10 09:00:00', '2026-03-10 10:00:00', '会议室A', '汇报本周进度', 'admin'),
('技术分享', '2026-03-11 14:00:00', '2026-03-11 15:30:00', '大会议室', 'Vue3 新特性', 'zhangsan'),
('面试', '2026-03-12 10:00:00', '2026-03-12 11:00:00', '线上', '某公司一面', 'lisi'),
('健身', '2026-03-13 18:00:00', '2026-03-13 19:00:00', '健身房', '每周固定', 'wangwu'),
('读书会', '2026-03-14 19:00:00', '2026-03-14 20:30:00', '图书馆', '《高效能人士》', 'admin');
