<template>
  <div style="width: 50%">
    <el-form :model="recruitInfo" :rules="rules" ref="recruitInfo" label-width="100px" size="small" hide-required-asterisk>
      <el-form-item label="职位名称" prop="name">
        <el-input v-model="recruitInfo.name"></el-input>
      </el-form-item>
      <el-form-item label="职位族" prop="type">
        <el-select v-model="recruitInfo.type" clearable placeholder="请选择">
          <el-option v-for="item in options" :key="item.value"
            :label="item.value" :value="item.value">
          </el-option>
        </el-select>
        <!--<el-input v-model="recruitInfo.type"></el-input>-->
      </el-form-item>
      <el-form-item label="工作地点" prop="address">
        <el-input v-model="recruitInfo.address"></el-input>
      </el-form-item>
      <el-form-item label="招聘人数" prop="number">
        <el-input v-model="recruitInfo.number"></el-input>
      </el-form-item>
      <el-form-item label="招聘性质" prop="nature">
        <el-radio-group v-model="recruitInfo.nature">
          <el-radio label="校园招聘"></el-radio>
          <el-radio label="社会招聘"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="职位描述" prop="describe">
        <el-input type="textarea" v-model="recruitInfo.describe"></el-input>
      </el-form-item>
      <el-form-item label="任职要求" prop="require">
        <el-input type="textarea" v-model="recruitInfo.require"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('recruitInfo')" size="small">添加</el-button>
        <el-button @click="reset('recruitInfo')" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddRecruit",
      data() {
        return {
          options: [{
            value: '技术族'
          }, {
            value: '产品族'
          }, {
            value: '职能族'
          }],
          recruitInfo: {
            name: '',
            type: '',
            address: '',
            number: '',
            nature: '',
            describe: '',
            require: ''
          },
          rules: {
            name: [{required: true, message: '请输入职位名称', trigger: 'blur'}],
            type: [{required: true, message: '请输入职位族', trigger: 'blur'}],
            address: [{required: true, message: '请输入工作地点', trigger: 'blur'}],
            number: [{required: true, message: '请输入招聘人数', trigger: 'blur'}],
            nature: [{required: true, message: '请输入职位性质', trigger: 'blur'}],
            describe: [{required: true, message: '请输入职位描述', trigger: 'blur'}],
            require: [{required: true, message: '请输入任职要求', trigger: 'blur'}]
          }
        }
      },
      methods: {
        submit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/recruit/add', this.recruitInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.recruitInfo = {}
                  this.$router.replace('/admin/recruit/list')
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
