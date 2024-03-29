<template>
  <div style="width: 50%">
    <el-form :model="empInfo" :rules="rules" ref="empInfo" label-width="100px" size="small" hide-required-asterisk>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="empInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="empInfo.sex">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="身份证" prop="card">
        <el-input v-model="empInfo.card"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="empInfo.phone"></el-input>
      </el-form-item>
      <el-form-item label="毕业院校" prop="university">
        <el-input v-model="empInfo.university"></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="empInfo.major"></el-input>
      </el-form-item>
      <el-form-item label="学历" prop="education">
        <el-select v-model="empInfo.education" clearable placeholder="请选择">
          <el-option v-for="item in educations" :key="item.value"
                     :label="item.value" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="职位" prop="position">
        <el-select v-model="empInfo.position" clearable placeholder="请选择">
          <el-option v-for="item in positions" :key="item.id"
                     :value="item.name" :label="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="部门" prop="dept">
        <el-select v-model="empInfo.dept" clearable placeholder="请选择">
          <el-option v-for="item in depts" :key="item.id"
                     :value="item.name" :label="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="家庭住址" prop="address">
        <el-input v-model="empInfo.address"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('empInfo')" size="small">添加</el-button>
        <el-button @click="reset('empInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddEmp",
      data() {
        return {
          educations: [{
            value: '本科'
          }, {
            value: '硕士'
          }, {
            value: '博士'
          }],
          depts: [],
          positions: [],
          empInfo: {
            name: '',
            sex: '男',
            card: '',
            phone: '',
            university: '',
            major: '',
            education: '',
            address: '',
            position: '',
            dept: ''
          },
          rules: {
            name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
            dept: [{required: true, message: '请输入所属部门', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/emp/add', this.empInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.empInfo = {}
                  this.$router.replace('/emp/list')
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
