<template>
  <div class="page-wrap">
    <el-card class="panel-card">
      <div slot="header" class="card-header">
        <span class="header-title">待办任务管理</span>
        <el-button type="primary" size="small" @click="openCreate">新增待办</el-button>
      </div>
      <el-table :data="tableData" border class="beauty-table">
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="priority" label="优先级" width="90">
          <template slot-scope="scope">
            <el-tag size="mini" effect="dark" :type="priorityTagType(scope.row.priority)">{{ scope.row.priority }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="90">
          <template slot-scope="scope">
            <el-tag size="mini" :type="statusTagType(scope.row.status)">{{ scope.row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="dueDate" label="截止日期" width="120" />
        <el-table-column prop="remark" label="备注" show-overflow-tooltip />
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="text" @click="openEdit(scope.row)">编辑</el-button>
            <el-button type="text" class="danger-btn" @click="remove(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog :title="form.id ? '编辑待办' : '新增待办'" :visible.sync="visible" width="520px">
      <el-form :model="form" label-width="90px">
        <el-form-item label="标题"><el-input v-model="form.title" placeholder="请输入标题" /></el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="form.priority" placeholder="请选择" style="width:100%">
            <el-option label="高" value="高" />
            <el-option label="中" value="中" />
            <el-option label="低" value="低" />
            <el-option label="普通" value="普通" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" placeholder="请选择" style="width:100%">
            <el-option label="待办" value="待办" />
            <el-option label="进行中" value="进行中" />
            <el-option label="已完成" value="已完成" />
          </el-select>
        </el-form-item>
        <el-form-item label="截止日期"><el-date-picker v-model="form.dueDate" type="date" value-format="yyyy-MM-dd" style="width:100%" /></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" type="textarea" /></el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="visible=false">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { addTodoTaskApi, deleteTodoTaskApi, getTodoTaskListApi, updateTodoTaskApi } from '@/api/modules'

export default {
  name: 'TodoTaskView',
  data() {
    return {
      tableData: [],
      visible: false,
      form: {}
    }
  },
  mounted() {//生命周期钩子函数
    this.load()
  },
  methods: {
    async load() {
      const res = await getTodoTaskListApi()
      this.tableData = res.data
    },
    openCreate() {
      this.form = {}
      this.visible = true
    },
    openEdit(row) {
      this.form = { ...row }
      this.visible = true
    },
    priorityTagType(priority) {
      const map = {
        高: 'danger',
        中: 'warning',
        低: 'success',
        普通: 'info'
      }
      return map[priority] || 'info'
    },
    statusTagType(status) {
      const map = {
        待办: 'info',
        进行中: 'warning',
        已完成: 'success'
      }
      return map[status] || 'info'
    },
    async submit() {
      if (this.form.id) {
        await updateTodoTaskApi(this.form)
      } else {
        await addTodoTaskApi(this.form)
      }
      this.visible = false
      this.$message.success('操作成功')
      this.load()
    },
    async remove(id) {
      await deleteTodoTaskApi(id)
      this.$message.success('删除成功')
      this.load()
    }
  }
}
</script>

<style scoped>
.page-wrap {
  min-height: 100%;
}

.panel-card {
  border-radius: 14px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-title {
  font-size: 16px;
  font-weight: 600;
  color: #1f2937;
}

.danger-btn {
  color: #ef4444;
}

.beauty-table ::v-deep .el-table__header th {
  background: #f1f5f9;
  color: #334155;
}
</style>
