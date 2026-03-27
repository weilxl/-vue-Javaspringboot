<template>
  <div class="page">
    <div class="card">
      <div class="title">我的信息</div>
      <div class="info-item">
        <span class="label">账号：</span>
        <span>{{ userInfo.username || '-' }}</span>
      </div>
      <div class="info-item">
        <span class="label">姓名：</span>
        <span>{{ userInfo.realName || '-' }}</span>
      </div>
      <div class="info-item">
        <span class="label">角色：</span>
        <span>{{ userInfo.roleCode || '-' }}</span>
      </div>
    </div>
    <el-button type="danger" @click="logout" class="logout-btn">退出登录</el-button>
    <TextTabBar active="mine" />
  </div>
</template>

<script>
import TextTabBar from '@/components/TextTabBar.vue'

export default {
  name: 'MineView',
  components: { TextTabBar },
  computed: {
    userInfo() {
      return this.$store.state.userInfo
    }
  },
  methods: {
    logout() {
      this.$confirm('确定要退出登录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.commit('clearAuth')
        this.$message.success('已退出登录')
        this.$router.replace('/login')
      }).catch(() => {})
    }
  }
}
</script>

<style scoped>
.page {
  padding: 20px;
  padding-bottom: 80px;
  min-height: 100vh;
}

.card {
  background: #fff;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.title {
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 16px;
  color: #4198ac;
}

.info-item {
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
  font-size: 14px;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  color: #64748b;
  margin-right: 8px;
}

.logout-btn {
  width: 100%;
  background: #ed8d5a;
  border-color: #ed8d5a;
}

.logout-btn:hover {
  background: #ea9e58;
  border-color: #ea9e58;
}
</style>
