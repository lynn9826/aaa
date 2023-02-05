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
      <el-input v-model="search" placeholder="输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load" >查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="username" label="用户名" sortable  />
      <el-table-column prop="password" label="密码"  />
      <el-table-column prop="address" label="地址" />
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
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
          v-model:page-size="pageSize"
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
          <el-form-item label="id">
            <el-input v-model="form.id" style="width: 80%" disable/>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%" />
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%" />
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%" />
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
        <el-button type="primary" @click="save">Confirm</el-button>
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
      dialogVisible: false,
      search:'',
      currentPage: 1,
      pageSize: 10,
      total:0,
      tableData: [

      ]
    }
  },
  created() {                  //加载
    this.load()
  },
  methods:{
    load(){
      request.get("http://dpy472.natappfree.cc/user",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }

      }).then(res=>{
        console.log(res)
        this.tableData = res.data.records          //赋值
        this.total = res.data.total
      })
    },
    add(){
      this.dialogVisible = true   //打开弹窗
      this.form = {}   //清空表单
    },
    save(){
      //
      // 判断什么时候调用 新增：没有id
      // 判断什么时候调用 编辑：有id
      // 如果有id 调用编辑接口
      // 如果没有id 调用新增接口
      if (this.form.id) {
        request.put("http://dpy472.natappfree.cc/user", this.form).then(res => {
          console.log(res)
          if (res.code === 0){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()   //刷新表格的数据
        })
      } else {
        request.post("http://dpy472.natappfree.cc/user", this.form).then(res => {
          console.log(res)
          if (res.code === 0){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }else{
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()   //刷新表格的数据
        })
      }
      // ctrl shift r
      this.dialogVisible = false



    },
    handleEdit(row){
      this.form = row
      console.log(this.form)
      this.dialogVisible = true
    },
    handleDelete(id){
      console.log(id)
      request.delete("http://dpy472.natappfree.cc/user/" + id).then(res =>{
        console.log(res)
        if (res.code === 0){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()   //刷新表格的数据
      })
    },
    handleSizeChange(){        //改变当前每页的个数触发
      this.load()
    },
    handleCurrentChange(){     //改变当前页码触发
      this.load()
    }
  }
}
</script>
