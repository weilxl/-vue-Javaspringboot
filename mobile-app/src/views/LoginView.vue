<template>
  <div class="wrap">
    <div class="card">
      <div class="title">个人效率管理</div>
      <el-input v-model="form.username" placeholder="请输入账号啊" class="input-item" />
      <el-input v-model="form.password" type="password" placeholder="请输入密码" show-password class="input-item" />
      <el-button type="primary" @click="login" :loading="loading" class="btn">登录</el-button>
      <div class="link" @click="goRegister">没有账号？去注册</div>
      <div class="tips">测试账号：admin / admin123</div>
    </div>
  </div>
</template>

<script>
import { loginApi } from '@/api/modules'

export default {
  name: 'LoginView',
  data() {
    return {
      loading: false,
      form: {
        username: 'admin',
        password: 'admin123'
      }
    }
  },
  methods: {
    async login() {
      if (!this.form.username || !this.form.password) {
        this.$message.warning('请输入账号和密码')
        return
      }
      this.loading = true
      try {
        const res = await loginApi(this.form)
        this.$store.commit('setAuth', {
          token: res.data.token,
          userInfo: {
            username: res.data.username,
            realName: res.data.realName,
            roleCode: res.data.roleCode
          }
        })
        this.$message.success('登录成功')
        this.$router.replace('/todo-task')
      } catch (e) {
        this.$message.error(e.message || '登录失败')
      } finally {
        this.loading = false
      }
    },
    goRegister() {
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>
.wrap {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #bfdfd2, #7bc0c0);
  padding: 20px;
}

.card {
  width: 100%;
  max-width: 400px;
  background: #fff;
  border-radius: 16px;
  padding: 40px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 30px;
  color: #4198ac;
}

.input-item {
  margin-bottom: 20px;
}

.btn {
  width: 100%;
  margin-top: 10px;
  background: #51999f;
  border-color: #51999f;
}

.btn:hover {
  background: #4198ac;
  border-color: #4198ac;
}

.link {
  margin-top: 20px;
  text-align: center;
  color: #4198ac;
  cursor: pointer;
  font-size: 14px;
}

.tips {
  margin-top: 16px;
  text-align: center;
  color: #64748b;
  font-size: 12px;
}
</style>
