<template>
  <div>
    <el-table :data="tableData" ref="filterTable" style="width: 100%;" stripe size="small">
      <el-table-column type="index" :index="indexMethod"></el-table-column>
      <el-table-column prop="name" label="活动名称"></el-table-column>
      <el-table-column prop="introduce" label="活动介绍"></el-table-column>
      <el-table-column prop="start" label="开始时间"></el-table-column>
      <el-table-column prop="end" label="结束时间"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="danger"
                     @click="handleDelete(scope.$index, scope.row)">取消报名</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
    export default {
      name: "ProfileTrain",
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
        //删除
        handleDelete(index, row) {
          this.$confirm('此操作将取消' + row.name + '培训活动的报名, 是否继续?', '提示', {
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
          this.username = localStorage.getItem("username");
          this.axios.get('http://localhost:9000/user/train/queryByName?username=' + this.username).then(res => {
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
