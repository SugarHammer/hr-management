<template>
  <div style="width: 50%">
    <el-form :model="salaryInfo" :rules="rules" ref="salaryInfo" label-width="100px" size="small" hide-required-asterisk>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="salaryInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="职位" prop="position">
        <el-select v-model="salaryInfo.position" clearable placeholder="请选择">
          <el-option v-for="item in positions" :key="item.id"
                     :value="item.name" :label="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="部门" prop="dept">
        <el-select v-model="salaryInfo.dept" clearable placeholder="请选择">
          <el-option v-for="item in depts" :key="item.id"
                     :value="item.name" :label="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="基本工资" prop="pay">
        <el-input v-model="salaryInfo.pay"></el-input>
      </el-form-item>
      <el-form-item label="绩效奖金" prop="bonus">
        <el-input v-model="salaryInfo.bonus"></el-input>
      </el-form-item>
      <el-form-item label="个人所得税" prop="tax">
        <el-input v-model="salaryInfo.tax"></el-input>
      </el-form-item>
      <el-form-item label="五险一金" prop="insurance">
        <el-input v-model="salaryInfo.insurance"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('salaryInfo')" size="small">添加</el-button>
        <el-button @click="reset('salaryInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddSalary",
      data() {
        return {
          depts: [],
          positions: [],
          salaryInfo: {
            name: '',
            position: '',
            dept: '',
            pay: '',
            bonus: '',
            tax: '',
            insurance: '',
            release: ''
          },
          rules: {
            name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
            position: [{required: true, message: '请选择职位', trigger: 'blur'}],
            dept: [{required: true, message: '请选择所属部门', trigger: 'blur'}],
            pay: [{required: true, message: '请输入基本工资', trigger: 'blur'}],
            bonus: [{required: true, message: '请输入绩效奖金', trigger: 'blur'}],
            tax: [{required: true, message: '请输入扣除的个人所得税', trigger: 'blur'}],
            insurance: [{required: true, message: '请输入扣除的五险一金', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/salary/add', this.salaryInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.salaryInfo = {}
                  this.$router.replace('/salary/list')
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
      },
      created() {
        //查询所有部门
        this.axios.get('http://localhost:9000/dept/list').then(res => {
          //console.log(res.data)
          this.depts = res.data
        })
        //查询所有职位
        this.axios.get('http://localhost:9000/position/list').then(res => {
          this.positions = res.data
        })
      }
    }
</script>

<style scoped>

</style>
