<template>
  <el-container class="layout-wrap">
    <el-aside width="210px" class="aside">
      <div class="logo"><i class="el-icon-office-building"></i> 个人效率管理</div>
      <el-menu :default-active="$route.path" router class="side-menu" background-color="#2f6068" text-color="#c8e0df" active-text-color="#ffffff">
        <el-menu-item index="/dashboard"><i class="el-icon-s-home"></i>首页仪表盘</el-menu-item>
        <el-menu-item index="/todo-task"><i class="el-icon-document"></i>待办任务管理</el-menu-item>
        <el-menu-item index="/schedule"><i class="el-icon-s-data"></i>日程安排管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <div class="title">个人效率管理系统</div>
        <div class="user-box">
          <i class="el-icon-user"></i>
          {{ userInfo.realName || userInfo.username }}
          <el-button type="text" class="logout-btn" @click="logout">退出</el-button>
        </div>
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'LayoutView',
  computed: {
    userInfo() {
      return this.$store.state.userInfo
    }
  },
  methods: {
    logout() {
      this.$store.commit('clearAuth')
      this.$router.replace('/login')
    }
  }
}
</script>

<style scoped>
.layout-wrap {
  height: 100vh;
}

.aside {
  background: linear-gradient(180deg, #346d76 0%, #2f6068 100%);
  box-shadow: 4px 0 16px rgba(28, 63, 66, 0.18);
}

.logo {
  color: #fff;
  padding: 20px 16px;
  font-weight: 700;
  font-size: 16px;
  letter-spacing: 0.4px;
}

.side-menu {
  border-right: none;
}

.side-menu ::v-deep .el-menu-item {
  margin: 6px 10px;
  border-radius: 10px;
}

.side-menu ::v-deep .el-menu-item.is-active {
  background: rgba(255, 255, 255, 0.16) !important;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #fff;
  border-bottom: 1px solid #e8ecef;
  box-shadow: 0 2px 10px rgba(15, 23, 42, 0.06);
}

.title {
  font-size: 17px;
  font-weight: 600;
  color: #1f2937;
}

.user-box {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #334155;
  font-weight: 500;
}

.logout-btn {
  color: #3a8f99;
}

.main-content {
  background: #f5f8fb;
  padding: 20px;
}
</style>
