<template>
    <div>
      <!--搜索框-->
      <el-input v-model="search" placeholder="请输入要搜索的员工姓名" class="search" size="small" suffix-icon="el-icon-search"
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
      </el-input>
      <el-button @click="toAdd" type="primary" size="small" icon="el-icon-plus">发放薪资</el-button>

      <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="position" label="职位"></el-table-column>
        <el-table-column prop="dept" label="所属部门"></el-table-column>
        <el-table-column prop="pay" label="基本工资"></el-table-column>
        <el-table-column prop="bonus" label="绩效奖金"></el-table-column>
        <el-table-column prop="sum" label="总工资"></el-table-column>
        <el-table-column prop="release" label="发放时间" width="150"></el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" icon="el-icon-edit"
                       @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="small" type="danger" icon="el-icon-delete"
                       @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--分页-->
      <div class="pageBox">
        <el-pagination background layout="total, prev, pager, next" align="center"
                       :current-page="pageInfo.pageIndex" :page-size="8" :total="pageTotal"
                       @current-change="handleCurrentChange">
        </el-pagination>
      </div>

      <!--修改员工薪资信息-->
      <el-dialog title="修改员工薪资信息" :visible.sync="dialogVisible"
                 width="35%" showClose :before-close="handleClose">
        <el-form ref="salaryInfo" :model="salaryInfo" label-width="80px" size="small">
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
          <el-form-item label="个人税" prop="tax">
            <el-input v-model="salaryInfo.tax"></el-input>
          </el-form-item>
          <el-form-item label="五险一金" prop="insurance">
            <el-input v-model="salaryInfo.insurance"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editInfo('salaryInfo')" size="small">确 定</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      name: "SalaryList",
      data() {
        return {
          pageInfo:{
            pageIndex: 1,
          },
          pageTotal: 0,
          depts: [],
          positions: [],
          search: '',
          dialogVisible: false,
          tableData: [],
          salaryInfo: {
            name: '',
            position: '',
            dept: '',
            pay: '',
            bonus: '',
            tax: '',
            insurance: '',
            release: ''
          }
        }
      },
      methods: {
        //跳转到添加页面
        toAdd() {
          this.$router.push('/salary/add')
        },
        //分页
        handleCurrentChange(value){
          this.pageInfo.pageIndex = value;//获得当前页码
          this.findAll(this.pageInfo.pageIndex);
        },
        //自定义index
        indexMethod(index) {
          return index+(this.pageInfo.pageIndex-1)*8+1;
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
          this.findAll(this.pageInfo.pageIndex);
        },
        //编辑
        handleEdit(index, row) {
          //console.log(index, row);
          this.dialogVisible = true;
          this.salaryInfo = row
        },
        //更新
        editInfo(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/salary/edit', this.salaryInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.findAll(this.pageInfo.pageIndex);
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
            this.axios.get('http://localhost:9000/salary/delete?id=' + row.id).then(res => {
              if (res.data.status) {
                this.$message.success(res.data.msg);
                //刷新表单
                this.findAll(this.pageInfo.pageIndex);
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        findAll(pageNum) {
          this.axios.get('http://localhost:9000/salary/list?pageNum=' + pageNum).then(res => {
            this.tableData = res.data.list
            this.pageTotal = res.data.total
          })
        }
      },
      created() {
        //调用findAll
        this.findAll(this.pageInfo.pageIndex);
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
  .search {
    width: 30%;
  }
  .pageBox {
    margin-top: 15px;
  }
</style>
