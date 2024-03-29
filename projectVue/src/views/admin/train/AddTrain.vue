<template>
  <div style="width: 50%">
    <el-form :model="trainInfo" :rules="rules" ref="trainInfo" label-width="100px"
             size="small" hide-required-asterisk>
      <el-form-item label="活动名称" prop="name">
        <el-input v-model="trainInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="活动介绍" prop="introduce">
        <el-input v-model="trainInfo.introduce"></el-input>
      </el-form-item>
      <el-form-item label="开始日期" prop="start">
        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                        v-model="trainInfo.start" style="width: 100%;">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="end">
        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                        v-model="trainInfo.end" style="width: 100%;">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('trainInfo')" size="small">添加</el-button>
        <el-button @click="reset('trainInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddTrain",
      data() {
        return {
          trainInfo: {
            name: '',
            introduce: '',
            start: '',
            end: ''
          },
          rules: {
            name: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
            introduce: [{required: true, message: '请输入部门介绍', trigger: 'blur'}],
            start: [{required: true, message: '请选择开始日期', trigger: 'blur'}],
            end: [{required: true, message: '请选择结束日期', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/train/add', this.trainInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.trainInfo = {}
                  this.$router.replace('/train/list')
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
