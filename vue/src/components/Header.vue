<template xmlns:padding-left="http://www.w3.org/1999/xhtml">
<div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
  <div style="width: 200px; padding-left:30px; font-weight: bold; color: blueviolet">后台管理</div>
  <div style="flex: 1"></div>
  <div style="width: 100px">
    <el-dropdown>
      <el-button type="primary">
        张三<el-icon class="el-icon--right"><arrow-down /></el-icon>
      </el-button>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>个人信息</el-dropdown-item>
          <el-dropdown-item>退出</el-dropdown-item>
          <el-dropdown-item>Action 3</el-dropdown-item>
          <el-dropdown-item>Action 4</el-dropdown-item>
          <el-dropdown-item>Action 5</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>

</div>
</template>

<script>
export default {
  name: "Header",
  components:{
    ArrowDown
  }
}
import { ArrowDown } from '@element-plus/icons-vue'

const handleClick = () => {
  // eslint-disable-next-line no-alert
  alert('button click')
}
</script>

<style scoped>
.example-showcase .el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>