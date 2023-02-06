<template>
  <div>
    <el-card style="width: 40%; margin: 10px">
      <el-form ref="form" :modle="form" label-width="80px">
        <el-form-item label="id">
          <el-input v-model="form.id" style="width: 80%" disable/>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username" disabled style="width: 80%" />
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickName" style="width: 80%" />
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" style="width: 80%" />
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex" style="width: 80%" />
        <el-form-item label="密码">
          <el-input v-model="form.password" show-password style="width: 80%" />
        </el-form-item>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="form.address" style="width: 80%" />
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary" @click="updata">保存</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
import request from "../utils/request";
export default {
  name: "Person",
  data(){
    return{
      form:{},
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    console.log()
    this.form = JSON.parse(str)
  },
  methods:{
    updata(){
      request.put("http://2stzeh.natappfree.cc/user",this.form).then(res=>{
        console.log(res)
        if (res.code === 0){
          this.$message({
            type: "success",
            message: "更新成功"
          })
          sessionStorage.setItem("user",JSON.stringify(this.form))
        }else{
          this.$message({
            type: "error",
            message: res.msg
          })
        }
          }
      )
    }
  }
}
</script>

<style scoped>

</style>