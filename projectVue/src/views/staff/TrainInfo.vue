<template>
    <div>
      <!--搜索框-->
      <el-input v-model="search" placeholder="请输入要搜索的活动名称" class="search" size="small" suffix-icon="el-icon-search"
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
      </el-input>

      <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="name" label="活动名称"></el-table-column>
        <el-table-column prop="introduce" label="活动介绍"></el-table-column>
        <el-table-column prop="start" label="开始时间"></el-table-column>
        <el-table-column prop="end" label="结束时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" icon="el-icon-thumb" type="primary"
                       @click="handle(scope.$index, scope.row)">点击报名此活动</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
    export default {
      name: "TrainInfo",
      data() {
        return {
          search: '',
          tableData: [],
          userTrain: {
            username: '',
            tid: ''
          }
        }
      },
      methods: {
        //自定义编号
        indexMethod(index) {
          return index+1;
        },
        //参加培训活动
        handle(index, row) {
          this.userTrain.tid = row.id;
          this.userTrain.username = localStorage.getItem("username");
          this.axios.post('http://localhost:9000/user/train/add', this.userTrain).then(res => {
            if (res.data.status) {
              this.$message.success(res.data.msg);
            } else {
              this.$message.error(res.data.msg);
            }
          })
        },
        findAll() {
          this.axios.get('http://localhost:9000/train/list').then(res => {
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
