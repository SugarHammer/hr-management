<template>
  <div>
    <!--搜索框-->
    <el-input v-model="search" placeholder="请输入要搜索的报名人员姓名" class="search" size="small" suffix-icon="el-icon-search"
              :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
    </el-input>

    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
      <el-table-column type="index" :index="indexMethod"></el-table-column>
      <el-table-column prop="username" label="姓名"></el-table-column>
      <el-table-column prop="position" label="职位"></el-table-column>
      <el-table-column prop="dept" label="所属部门"></el-table-column>
      <el-table-column prop="name" label="报名活动"></el-table-column>
      <el-table-column prop="introduce" label="活动介绍"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="danger"
                     @click="handleDelete(scope.$index, scope.row)">取消其报名</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
    export default {
      name: "TrainUser",
      data() {
        return {
          search: '',
          tableData: []
        }
      },
      methods: {
        //自定义编号
        indexMethod(index) {
          return index+1;
        },
        //取消其报名
        handleDelete(index, row) {
          this.$confirm('此操作将取消' + row.username + '的报名信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.axios.get('http://localhost:9000/user/train/delete?id=' + row.id).then(res => {
              if (res.data.status) {
                this.$message.success(res.data.msg);
                //刷新表单
                this.findAll();
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消此操作'
            });
          });
        },
        findAll() {
          this.axios.get('http://localhost:9000/user/train/list').then(res => {
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
  .search {
    width: 30%;
  }
</style>
