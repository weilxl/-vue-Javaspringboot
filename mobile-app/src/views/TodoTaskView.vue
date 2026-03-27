<template>
  <div class="page">
    <div class="header">待办任务</div>
    <el-button type="primary" @click="showAdd = true" class="add-btn">新增</el-button>

    <div v-for="item in list" :key="item.id" class="card">
      <div class="title">{{ item.title }}</div>
      <div class="sub">{{ item.priority || '普通' }} | {{ item.status || '待办' }}</div>
      <div class="sub" v-if="item.dueDate">截止：{{ item.dueDate }}</div>
    </div>

    <el-dialog title="新增待办" :visible.sync="showAdd" width="90%" :close-on-click-modal="false">
      <el-form :model="form" label-width="90px">
        <el-form-item label="标题"><el-input v-model="form.title" placeholder="请输入标题" /></el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="form.priority" placeholder="请选择" style="width:100%">
            <el-option label="高" value="高" /><el-option label="中" value="中" /><el-option label="低" value="低" /><el-option label="普通" value="普通" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" placeholder="请选择" style="width:100%">
            <el-option label="待办" value="待办" /><el-option label="进行中" value="进行中" /><el-option label="已完成" value="已完成" />
          </el-select>
        </el-form-item>
        <el-form-item label="截止日期"><el-date-picker v-model="form.dueDate" type="date" value-format="yyyy-MM-dd" style="width:100%" placeholder="选填" /></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" type="textarea" placeholder="选填" /></el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="showAdd = false">取消</el-button>
        <el-button type="primary" @click="add" :loading="loading">提交</el-button>
      </div>
    </el-dialog>

    <TextTabBar active="todo" />
  </div>
</template>

<script>
import { getTodoTaskListApi, addTodoTaskApi } from '@/api/modules'
import TextTabBar from '@/components/TextTabBar.vue'

export default {
  name: 'TodoTaskView',
  components: { TextTabBar },
  data() {
    return {
      list: [],
      showAdd: false,
      loading: false,
      form: { title: '', priority: '普通', status: '待办', dueDate: '', remark: '' }
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    async load() {
      try {
        const res = await getTodoTaskListApi()
        this.list = res.data || []
      } catch (e) {
        this.$message.error(e.message || '加载失败')
      }
    },
    async add() {
      if (!this.form.title) {
        this.$message.warning('请填写标题')
        return
      }
      this.loading = true
      try {
        await addTodoTaskApi(this.form)
        this.$message.success('添加成功')
        this.showAdd = false
        this.form = { title: '', priority: '普通', status: '待办', dueDate: '', remark: '' }
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
