<template>
  <div>
    <!--搜索框-->
    <el-input v-model="search" placeholder="请输入要搜索的同事姓名" class="search" size="small" suffix-icon="el-icon-search" clearable
              :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
    </el-input>

    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
      <el-table-column type="index" :index="indexMethod"></el-table-column>
      <el-table-column prop="name" label="姓名" width="100"></el-table-column>
      <el-table-column prop="sex" label="性别" width="90"></el-table-column>
      <el-table-column prop="phone" label="手机号"></el-table-column>
      <el-table-column prop="university" label="毕业院校"></el-table-column>
      <!--<el-table-column prop="major" label="专业"></el-table-column>
      <el-table-column prop="education" label="学历"></el-table-column>-->
      <el-table-column prop="position" label="职位"></el-table-column>
      <el-table-column prop="dept" label="所属部门"></el-table-column>
      <el-table-column prop="create" label="入职日期"></el-table-column>
    </el-table>

    <!--分页-->
    <div class="pageBox">
      <el-pagination background layout="total, prev, pager, next" align="center"
                     :current-page="pageInfo.pageIndex" :page-size="10" :total="pageTotal"
                     @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    export default {
      name: "EmpInfo",
      data() {
        return {
          pageInfo:{
            pageIndex: 1,
          },
          pageTotal: 0,
          search: '',
          tableData: [],
          username: ''
        }
      },
      methods: {
        //分页
        handleCurrentChange(value){
          this.pageInfo.pageIndex = value;//获得当前页码
          this.findAll(this.pageInfo.pageIndex);
        },
        //自定义编号
        indexMethod(index) {
          return index+(this.pageInfo.pageIndex-1)*8+1;
        },
        findAll(pageNum) {
          this.username = localStorage.getItem("username");
          this.axios.get('http://localhost:9000/emp/listByName?pageNum=' + pageNum).then(res => {
            this.tableData = res.data.list
            this.pageTotal = res.data.total
          })
        }
      },
      created() {
        //调用findAll
        this.findAll(this.pageInfo.pageIndex);
      }
    }
</script>

<style scoped>
  .search {
    width: 30%;
  }
  .pageBox {
    margin-top: 15px;
  }
</style>
