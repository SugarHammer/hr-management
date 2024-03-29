<template>
  <div>
    <!--搜索框-->
    <el-input v-model="search" placeholder="请输入要搜索的活动名称" class="search" size="small" suffix-icon="el-icon-search"
              :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
    </el-input>
    <el-button @click="toAdd" type="primary" size="small" icon="el-icon-plus">添加培训活动</el-button>

    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
      <el-table-column type="index" :index="indexMethod"></el-table-column>
      <el-table-column prop="name" label="活动名称"></el-table-column>
      <el-table-column prop="introduce" label="活动介绍"></el-table-column>
      <el-table-column prop="start" label="开始时间"></el-table-column>
      <el-table-column prop="end" label="结束时间"></el-table-column>
      <el-table-column prop="count" label="报名人数"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="small" icon="el-icon-edit"
                     @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="small" type="danger" icon="el-icon-delete"
                     @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--修改培训活动信息-->
    <el-dialog title="修改活动信息" :visible.sync="dialogVisible"
               width="35%" :showClose="false" :before-close="handleClose">
      <el-form ref="trainInfo" :model="trainInfo" label-width="80px" size="small">
        <el-form-item label="活动名称" prop="name">
          <el-input v-model="trainInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="活动介绍" prop="introduce">
          <el-input v-model="trainInfo.introduce"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="start">
          <!--<el-input v-model="trainInfo.start"></el-input>-->
          <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                          v-model="trainInfo.start" style="width: 100%;">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="end">
          <!--<el-input v-model="trainInfo.end"></el-input>-->
          <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                          v-model="trainInfo.end" style="width: 100%;">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editInfo('trainInfo')" size="small">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "TrainList",
      data() {
        return {
          search: '',
          dialogVisible: false,
          tableData: [],
          trainInfo: {
            name: '',
            introduce: '',
            start: '',
            end: '',
            count: ''
          }
        }
      },
      methods: {
        //跳转到添加页面
        toAdd() {
          this.$router.push('/train/add')
        },
        //自定义编号
        indexMethod(index) {
          return index+1;
        },
        //关闭弹窗
        handleClose(done) {
          this.$confirm('确认关闭？')
            .then(_ => {
              done();
            })
            .catch(_ => {});
        },
        remove() {
          this.dialogVisible = false
          this.findAll();
        },
        //编辑
        handleEdit(index, row) {
          //console.log(index, row);
          this.dialogVisible = true;
          this.trainInfo = row
        },
        //更新
        editInfo(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/train/edit', this.trainInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.findAll();
                  this.dialogVisible = false;
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
        //删除
        handleDelete(index, row) {
          this.$confirm('此操作将删除' + row.name + '的信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.axios.get('http://localhost:9000/train/delete?id=' + row.id).then(res => {
              if (res.data.status) {
                this.$message.success(res.data.msg);
                //刷新表单
                this.findAll();
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
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
