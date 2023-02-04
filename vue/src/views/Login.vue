<template>
  <div style="width: 100%; height: 100vh; background-color: darkseagreen; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
      <!-- :model ref 中的值需要相等   -->
      <el-form :model="form" size="normal" :rules="rules" ref="form">
        <el-form-item prop="username">
          <el-input prefix-icon="User" v-model="form.username" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="Lock" v-model="form.password" show-password />
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import request from "../utils/request";

export default {
  name: "Login",

  data(){
    return{
      form:{

      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
      }
    }

  },

  methods:{
    login(){
      this.$refs['form'].validate((valid) => {
        console.log('valid = ', valid)
        if (valid) {
          request.post("http://dpy472.natappfree.cc/user/login",this.form).then(res =>{
            if (res.code === 0){
              this.$message({
                type: "success",
                message: "登录成功"
              })
              this.$router.push("/")      //登录成功后的页面跳转
            }else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })


    }
  }
}
</script>

<style scoped>

</style>