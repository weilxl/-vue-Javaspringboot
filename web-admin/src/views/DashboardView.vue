<template>
  <div class="dashboard-page">
    <el-row :gutter="16">
      <el-col :span="12">
        <el-card class="stat-card">
          <div class="stat-label">待办任务总数</div>
          <div class="stat-value">{{ stats.todoTotal }}</div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="stat-card">
          <div class="stat-label">日程安排总数</div>
          <div class="stat-value">{{ stats.scheduleTotal }}</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="16" class="chart-row">
      <el-col :span="12">
        <el-card class="chart-card">
          <div id="taskChart" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="chart-card">
          <div id="priorityChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import { getDashboardApi } from '@/api/modules'

export default {
  name: 'DashboardView',
  data() {
    return {
      stats: {
        todoTotal: 0,
        scheduleTotal: 0,
        taskStatusMap: {},
        taskPriorityMap: {}
      }
    }
  },
  mounted() {
    this.loadStats()
  },
  methods: {
    async loadStats() {
      const res = await getDashboardApi()
      this.stats = res.data
      this.$nextTick(() => {
        this.renderTaskChart()
        this.renderPriorityChart()
      })
    },
    renderTaskChart() {
      const chart = echarts.init(document.getElementById('taskChart'))
      const data = this.stats.taskStatusMap || {}
      chart.setOption({
        title: { text: '待办任务状态分布' },
        xAxis: { type: 'category', data: Object.keys(data) },
        yAxis: { type: 'value' },
        series: [{ type: 'bar', data: Object.values(data), itemStyle: { color: '#51999F' } }]
      })
    },
    renderPriorityChart() {
      const chart = echarts.init(document.getElementById('priorityChart'))
      const data = this.stats.taskPriorityMap || {}
      chart.setOption({
        title: { text: '待办任务优先级分布' },
        tooltip: { trigger: 'item' },
        series: [{
          type: 'pie',
          radius: '60%',
          data: Object.keys(data).map((key) => ({ name: key, value: data[key] }))
        }]
      })
    }
  }
}
</script>

<style scoped>
.dashboard-page {
  min-height: 100%;
}

.stat-card {
  border-radius: 14px;
}

.stat-label {
  color: #64748b;
  font-size: 14px;
  margin-bottom: 8px;
}

.stat-value {
  font-size: 32px;
  font-weight: 700;
  color: #0f766e;
  line-height: 1;
}

.chart-row {
  margin-top: 16px;
}

.chart-card {
  border-radius: 14px;
}

.chart-box {
  height: 320px;
}
</style>
