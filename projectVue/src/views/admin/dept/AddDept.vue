<template>
  <div style="width: 50%">
    <el-form :model="deptInfo" :rules="rules" ref="deptInfo" label-width="100px" size="small" hide-required-asterisk>
      <el-form-item label="部门编号" prop="deptno">
        <el-input v-model="deptInfo.deptno"></el-input>
      </el-form-item>
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="deptInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="部门介绍" prop="introduce">
        <el-input v-model="deptInfo.introduce"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('deptInfo')" size="small">添加</el-button>
        <el-button @click="reset('deptInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddDept",
      data() {
        return {
          deptInfo: {
            deptno: '',
            name: '',
            introduce: ''
          },
          rules: {
            deptno: [{required: true, message: '请输入部门编号', trigger: 'blur'}],
            name: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
            introduce: [{required: true, message: '请输入部门介绍', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/dept/add', this.deptInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.deptInfo = {}
                  this.$router.replace('/dept/list')
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
