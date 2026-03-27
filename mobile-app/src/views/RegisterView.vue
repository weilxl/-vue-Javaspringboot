<template>
  <div class="wrap">
    <div class="card">
      <div class="title">用户注册</div>
      <el-input v-model="form.username" placeholder="请输入账号" class="input-item" />
      <el-input v-model="form.password" type="password" placeholder="请输入密码" show-password class="input-item" />
      <el-input v-model="form.realName" placeholder="请输入真实姓名" class="input-item" />
      <el-input v-model="form.phone" placeholder="请输入手机号" class="input-item" />
      <el-button type="primary" @click="register" :loading="loading" class="btn">注册</el-button>
      <div class="link" @click="goLogin">已有账号？去登录</div>
    </div>
  </div>
</template>

<script>
import { registerApi } from '@/api/modules'

export default {
  name: 'RegisterView',
  data() {
    return {
      loading: false,
      form: {
        username: '',
        password: '',
        realName: '',
        phone: ''
      }
    }
  },
  methods: {
    async register() {
      if (!this.form.username || !this.form.password || !this.form.realName) {
        this.$message.warning('请填写账号、密码和姓名')
        return
      }
      this.loading = true
      try {
        await registerApi(this.form)
        this.$message.success('注册成功，请登录')
        this.$router.push('/login')
      } catch (e) {
        this.$message.error(e.message || '注册失败')
      } finally {
        this.loading = false
      }
    },
    goLogin() {
      this.$router.push('/login')
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
</style>
