<template>
  <div style="width: 50%">
    <el-form :model="positionInfo" :rules="rules" ref="positionInfo" label-width="100px" size="small" hide-required-asterisk>
      <el-form-item label="职位名称" prop="name">
        <el-input v-model="positionInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="所属部门" prop="deptname">
        <el-select v-model="positionInfo.deptname" clearable placeholder="请选择">
          <el-option v-for="item in depts" :key="item.id"
                     :value="item.name" :label="item.name">
          </el-option>
        </el-select>
        <!--<el-input v-model="positionInfo.deptname"></el-input>-->
      </el-form-item>
      <el-form-item label="职位介绍" prop="introduce">
        <el-input v-model="positionInfo.introduce"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('positionInfo')" size="small">添加</el-button>
        <el-button @click="reset('positionInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddPosition",
      data() {
        return {
          depts: [],
          positionInfo: {
            name: '',
            deptname: '',
            introduce: ''
          },
          rules: {
            name: [{required: true, message: '请输入职位名称', trigger: 'blur'}],
            deptname: [{required: true, message: '请输入所属部门', trigger: 'blur'}],
            introduce: [{required: true, message: '请输入职位介绍', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/position/add', this.positionInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.deptInfo = {}
                  this.$router.replace('/position/list')
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
      }
    }
</script>

<style scoped>

</style>
