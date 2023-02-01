<template>
  <div class="home" style="padding: 10px">
<!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>

    </div>
<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="输入关键字" style="width: 20%" ></el-input>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="date" label="日期" sortable  />
      <el-table-column prop="name" label="名字"  />
      <el-table-column prop="address" label="Address" />
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default>
          <el-button link type="primary" size="small" @click="handleEdit">编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?">
            <template #reference>
              <el-button link type="primary" size="small">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize4"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <el-dialog v-model="dialogVisible" title="Tips" width="30%" :before-close="handleClose">
        <el-form :modle="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%" />
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" style="width: 80%" />
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%" />
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男" size="small">男</el-radio>
            <el-radio v-model="form.sex" label="女" size="small">女</el-radio>
            <el-radio v-model="form.sex" label="外星人" size="small">外星人</el-radio>


          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%" />
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">
          Confirm
        </el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src


import request from "../utils/request";

export default {
  name: 'Home',
  components: {

  },
  data(){
    return {
      form:{},
      dialogVisible:false,
      search:'',
      currentPage: 1,
      total:10,
      tableData: [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        }
      ]
    }
  },
  methods:{
    add(){
      this.dialogVisible = true   //打开弹窗
      this.form = {}   //清空表单
    },
    save(){
      request.post("http://fgj9p5.natappfree.cc/user", this.form).then(res => {
        console.log(res)
      })
    },
    handEdit(){

    },
    handleSizeChange(){

    },
    handleCurrentChange(){

    }
  }
}
</script>
