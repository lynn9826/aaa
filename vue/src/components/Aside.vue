<template>
  <div>
    <el-row class="tac">
      <el-col :span="12">

        <el-menu
            style="width: 200px; min-height: calc(100vh - 50px)"
            default-active="user"
            router
            :default-openeds="['1']"
            :unique-opened="true"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon><location /></el-icon>
              <span>系统管理</span>
            </template>

              <el-menu-item index="user">
                <el-icon><Document /></el-icon>
                <span>用户管理</span>
              </el-menu-item>
              <el-menu-item index="book">
                <el-icon><Document /></el-icon>
                <span>s=书籍管理</span>
              </el-menu-item>

          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>
              <el-icon><location /></el-icon>
              <span>系统管理2</span>
            </template>

            <el-menu-item index="user2">
              <el-icon><Document /></el-icon>
              <span>用户管理2</span>
            </el-menu-item>

          </el-sub-menu>



        </el-menu>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
export default {
  name: "Aside",
  components: {
    Document,
    Location,
    Setting,
  }, methods: {
    handleOpen(key, keyPath) {
      console.log('handleOpen: key = ', key, 'keyPath = ', keyPath);
    },
    handleClose(key, keyPath) {
      console.log('handleClose: key = ', key, 'keyPath = ', keyPath);
    }
  }
}
</script>

<style scoped>

</style>