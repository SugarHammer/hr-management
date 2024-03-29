<template>
    <div>
      <el-table :data="tableData" ref="filterTable" style="width: 100%" stripe size="small">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <!--<el-table-column prop="name" label="姓名"></el-table-column>-->
        <el-table-column prop="position" label="职位"></el-table-column>
        <el-table-column prop="dept" label="所属部门"></el-table-column>
        <el-table-column prop="pay" label="基本工资"></el-table-column>
        <el-table-column prop="bonus" label="绩效奖金"></el-table-column>
        <el-table-column prop="sum" label="总工资"></el-table-column>
        <el-table-column prop="tax" label="个人所得税"></el-table-column>
        <el-table-column prop="insurance" label="五险一金"></el-table-column>
        <el-table-column prop="salary" label="到手工资"></el-table-column>
        <el-table-column prop="release" label="发放时间" width="150"></el-table-column>
      </el-table>
    </div>
</template>

<script>
    export default {
      name: "ProfileSalary",
      data() {
        return {
          username: '',
          tableData: []
        }
      },
      methods: {
        //自定义编号
        indexMethod(index) {
          return index+1;
        },
        findAll() {
          this.username = localStorage.getItem("username");
          this.axios.get('http://localhost:9000/salary/queryByName?name=' + this.username).then(res => {
            this.tableData = res.data
          })
        }
      },
      created() {
        //调用findAll
        this.findAll();
      }
    }
</script>

<style scoped>

</style>
