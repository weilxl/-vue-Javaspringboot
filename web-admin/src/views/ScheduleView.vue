<template>
  <div class="page-wrap">
    <el-card class="panel-card">
      <div slot="header" class="card-header">
        <span class="header-title">日程安排管理</span>
        <el-button type="primary" size="small" @click="openCreate">新增日程</el-button>
      </div>
      <el-table :data="tableData" border class="beauty-table">
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="startTime" label="开始时间" width="170" />
        <el-table-column prop="endTime" label="结束时间" width="170" />
        <el-table-column prop="location" label="地点" width="120" />
        <el-table-column prop="remark" label="备注" show-overflow-tooltip />
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="text" @click="openEdit(scope.row)">编辑</el-button>
            <el-button type="text" class="danger-btn" @click="remove(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog :title="form.id ? '编辑日程' : '新增日程'" :visible.sync="visible" width="520px">
      <el-form :model="form" label-width="90px">
        <el-form-item label="标题"><el-input v-model="form.title" placeholder="请输入标题" /></el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="form.startTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择开始时间" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="form.endTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%" placeholder="选择结束时间" />
        </el-form-item>
        <el-form-item label="地点"><el-input v-model="form.location" placeholder="选填" /></el-form-item>
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
import { addScheduleApi, deleteScheduleApi, getScheduleListApi, updateScheduleApi } from '@/api/modules'

export default {
  name: 'ScheduleView',
  data() {
    return {
      tableData: [],
      visible: false,
      form: {}
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    async load() {
      const res = await getScheduleListApi()
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
    async submit() {
      if (this.form.id) {
        await updateScheduleApi(this.form)
      } else {
        await addScheduleApi(this.form)
      }
      this.visible = false
      this.$message.success('操作成功')
      this.load()
    },
    async remove(id) {
      await deleteScheduleApi(id)
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
