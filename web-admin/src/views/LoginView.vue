<template>
  <div class="login-wrap">
    <el-card class="login-card">
      <div slot="header" class="login-header">
        <div class="title">个人效率管理系统</div>
        <div class="sub-title">欢迎回来，请登录后继续使用</div>
      </div>
      <el-form :model="form" :rules="rules" ref="loginForm" label-width="70px">
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" :loading="loading" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
      <div class="tips">测试账号：admin / admin123</div>
    </el-card>
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
      },
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(async (valid) => {
        if (!valid) return
        this.loading = true
        try {
          // token 是后端返回的一段用于身份验证的字符串（类似于“通行证”），
          // 前端每次访问需要登录权限的接口时，都需要带上 token，以证明自己的身份。
          // 下面这些代码的作用是：发送登录请求并获取 token 和用户信息，将这些信息保存到 Vuex 全局状态（持久化到本地），
          // 然后给出登录成功提示，最后跳转到首页仪表盘。

          // 这里调用 loginApi 是对后端的登录接口发起请求
          // loginApi 需要你在 @/api/modules.js 或类似的文件里定义对应的接口方法，比如用 axios 实现：
          // export function loginApi(data) { return axios.post('/your-backend-url/login', data) }
          // 如果你的项目目前还没对接真实后端，也可以先用下面这种 mock 写法：
          // const res = { data: { token: 'mock_token', username: this.form.username, realName: '演示用户', roleCode: 'admin' } }
          // 真实项目请把上面 mock 部分去掉，使用实际后端 api。
          const res = await loginApi(this.form)
          this.$store.commit('setAuth', {
            token: res.data.token, // 保存登录后返回的 token
            userInfo: {
              username: res.data.username,
              realName: res.data.realName,
              roleCode: res.data.roleCode
            }
          })
          this.$message.success('登录成功')
          this.$router.replace('/dashboard')
        } catch (e) {
          this.$message.error(e.message || '登录失败')
        } finally {
          this.loading = false
        }
      })
    }
  }
}
</script>

<style scoped>
.login-wrap {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: radial-gradient(circle at 20% 20%, #d8f0eb 0%, #a0ccd0 35%, #7ba9bf 100%);
}

.login-card {
  width: 420px;
  border-radius: 16px;
  border: none;
  box-shadow: 0 20px 45px rgba(15, 23, 42, 0.18);
}

.login-header {
  text-align: center;
}

.title {
  font-size: 22px;
  font-weight: 700;
  color: #0f172a;
}

.sub-title {
  margin-top: 6px;
  color: #64748b;
  font-size: 13px;
}

.login-card ::v-deep .el-input__inner {
  border-radius: 10px;
}

.login-card ::v-deep .el-button--primary {
  border-radius: 10px;
}

.tips {
  text-align: center;
  color: #6b7280;
  font-size: 12px;
}
</style>
