<template>
  <div style="width: 50%">
    <el-form :model="userInfo" :rules="rules" ref="userInfo" label-width="100px" size="small" hide-required-asterisk>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="userInfo.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="userInfo.phone"  placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userInfo.password" show-password  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="用户角色" prop="role">
        <el-radio-group v-model="userInfo.role">
          <el-radio label="普通用户"></el-radio>
          <el-radio label="员工"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('userInfo')" size="small">添加</el-button>
        <el-button @click="reset('userInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddUser",
      data() {
        return {
          userInfo: {
            username: '',
            phone: '',
            password: '',
            role: '普通用户'
          },
          rules: {
            username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
            phone: [
              { required: true, message: '请输入手机号', trigger: 'blur'},
              { min: 11, max: 11, message: '手机号长度为11位数字', trigger: 'blur'}
            ],
            password: [{required: true, message: '请输入密码', trigger: 'blur'}],
            role: [{required: true, message: '请选择角色信息', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/user/addUser', this.userInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.userInfo = {}
                  this.$router.replace('/admin/user')
                } else {
                  this.$message.error(res.data.msg);
                }
              })
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        reset(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>

</style>
