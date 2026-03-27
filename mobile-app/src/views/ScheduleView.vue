<template>
  <div class="page">
    <div class="header">日程安排</div>
    <el-button type="primary" @click="showAdd = true" class="add-btn">新增</el-button>

    <div v-for="item in list" :key="item.id" class="card">
      <div class="title">{{ item.title }}</div>
      <div class="sub">{{ item.startTime }} 至 {{ item.endTime }}</div>
      <div class="sub" v-if="item.location">地点：{{ item.location }}</div>
    </div>

    <el-dialog title="新增日程" :visible.sync="showAdd" width="90%" :close-on-click-modal="false">
      <el-form :model="form" label-width="90px">
        <el-form-item label="标题"><el-input v-model="form.title" placeholder="请输入标题" /></el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="form.startTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择开始时间" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="form.endTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择结束时间" />
        </el-form-item>
        <el-form-item label="地点"><el-input v-model="form.location" placeholder="选填" /></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" type="textarea" placeholder="选填" /></el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="showAdd = false">取消</el-button>
        <el-button type="primary" @click="add" :loading="loading">提交</el-button>
      </div>
    </el-dialog>

    <TextTabBar active="schedule" />
  </div>
</template>

<script>
import { getScheduleListApi, addScheduleApi } from '@/api/modules'
import TextTabBar from '@/components/TextTabBar.vue'

export default {
  name: 'ScheduleView',
  components: { TextTabBar },
  data() {
    return {
      list: [],
      showAdd: false,
      loading: false,
      form: { title: '', startTime: '', endTime: '', location: '', remark: '' }
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    async load() {
      try {
        const res = await getScheduleListApi()
        this.list = res.data || []
      } catch (e) {
        this.$message.error(e.message || '加载失败')
      }
    },
    async add() {
      if (!this.form.title || !this.form.startTime || !this.form.endTime) {
        this.$message.warning('请填写标题、开始时间和结束时间')
        return
      }
      this.loading = true
      try {
        await addScheduleApi(this.form)
        this.$message.success('添加成功')
        this.showAdd = false
        this.form = { title: '', startTime: '', endTime: '', location: '', remark: '' }
        this.load()
      } catch (e) {
        this.$message.error(e.message || '添加失败')
      } finally {
        this.loading = false
      }
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

.header {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 20px;
  color: #2f3b4a;
}

.add-btn {
  width: 100%;
  margin-bottom: 20px;
  background: #51999f;
  border-color: #51999f;
}

.add-btn:hover {
  background: #4198ac;
  border-color: #4198ac;
}

.card {
  background: #fff;
  padding: 20px;
  border-radius: 12px;
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.card .title {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 8px;
  color: #2f3b4a;
}

.sub {
  color: #64748b;
  margin-top: 8px;
  font-size: 14px;
}
</style>
