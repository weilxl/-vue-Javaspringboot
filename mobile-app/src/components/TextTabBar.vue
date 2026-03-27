<template>
  <div class="tab-bar">
    <div class="tab-item" :class="{ active: active === 'todo' }" @click="go('/todo-task')">待办任务</div>
    <div class="tab-item" :class="{ active: active === 'schedule' }" @click="go('/schedule')">日程安排</div>
    <div class="tab-item" :class="{ active: active === 'mine' }" @click="go('/mine')">我的</div>
  </div>
</template>

<script>
export default {
  name: 'TextTabBar',
  props: {
    active: {
      type: String,
      default: 'todo'
    }
  },
  methods: {
    go(path) {
      this.$router.push(path)
    }
  }
}
</script>

<style scoped>
.tab-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  display: flex;
  background: #fff;
  border-top: 1px solid #e5e7eb;
  z-index: 1000;
}

.tab-item {
  flex: 1;
  text-align: center;
  padding: 18px 0;
  color: #64748b;
  cursor: pointer;
  font-size: 14px;
}

.tab-item.active {
  color: #4198ac;
  font-weight: 700;
}
</style>
