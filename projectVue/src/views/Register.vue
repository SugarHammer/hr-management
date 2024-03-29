<template>
  <div class="login">
    <el-form ref="loginFrom" :model="loginFrom" hide-required-asterisk
             status-icon :rules="rules" class="loginContainer">
      <h2 class="login-title">人力资源管理平台</h2>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginFrom.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="loginFrom.phone" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="loginFrom.password" type="password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <!--<el-checkbox v-model="checked" class="loginRemember">记住我</el-checkbox>-->
        <el-button type="primary" @click="submitLogin('loginFrom')" style="width: 100%">注册</el-button>
      </el-form-item>
      <span class="toRegister" @click="$router.replace('/login')">已有账号？点击登录</span>
      <span class="toIndex" @click="$router.replace('/')">返回首页</span>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "Register",
      data() {
        return {
          checked: true,
          loginFrom: {
            username: '',
            phone: '',
            password: ''
          },
          rules: {
            username: [{ required: true, message: '请输入用户名', trigger: 'blur'}],
            phone: [
              { required: true, message: '请输入手机号', trigger: 'blur'},
              { min: 11, max: 11, message: '手机号长度为11位数字', trigger: 'blur'}
              ],
            password: [{ required: true, message: '请输入密码', trigger: 'blur'}],
          }
        }
      },
      methods: {
        submitLogin(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/user/addUser', this.loginFrom).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.$router.replace('/login');
                } else {
                  this.$message.error(res.data.msg);
                }
              })
            } else {
              return false;
            }
          });
        }
      }
    }
</script>

<style scoped>
  .login {
    margin: -8px;
    padding: 50px 20px;
    position: relative;
    box-sizing: border-box;
    background-color: rgba(0,0,0,.2);
    background-image: url("../assets/img/login.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    min-height: 100vh;
    background-size: 100% 100%;
  }
  .login:before {
    content: "";
    background-color: rgba(0,0,0,.2);
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }
  .loginContainer {
    position: relative;
    width: 400px;
    margin-left: 40%;
    margin-top: 40px;
    padding: 20px 30px;
    border: 1px solid #DCDFE6;
    border-radius: 15px;
    background-color: #fff;
    box-sizing: border-box;
    z-index: 2;
  }
  .login-title {
    text-align: center;
  }
  .loginRemember {
    text-align: left;
    margin-bottom: 5px;
  }

  .toRegister {
    font-size: 14px;
    color: #475669;
    cursor: pointer;
  }

  .toIndex {
    font-size: 14px;
    color: #475669;
    cursor: pointer;
    float: right;
  }
</style>
