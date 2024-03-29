<template>
    <div>
      <el-table ref="filterTable" :data="tableData" style="width: 100%" stripe size="small">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="username" label="姓名" width="60"></el-table-column>
        <el-table-column prop="sex" label="性别" width="50"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column prop="university" label="毕业院校"></el-table-column>
        <el-table-column prop="major" label="所学专业" width="120"></el-table-column>
        <el-table-column prop="education" label="学历" width="60"></el-table-column>
        <el-table-column prop="name" label="应聘职位"></el-table-column>
        <el-table-column prop="address" label="家庭住址"></el-table-column>
        <el-table-column prop="nature" label="招聘性质"
                         :filters="[{ text: '校园招聘', value: '校园招聘' }, { text: '社会招聘', value: '社会招聘' }]"
                         :filter-method="filterNature" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag :type="scope.row.nature === '社会招聘' ? 'primary' : 'success'"
                    disable-transitions>{{scope.row.nature}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="state" label="状态"></el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button @click="handleResume(scope.$index, scope.row)" type="success" size="small">查看</el-button>
            <el-button @click="handleEdit(scope.$index, scope.row)" type="primary" size="small">选择</el-button>
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

      <!--编辑投递状态-->
      <el-dialog title="编辑状态" :visible.sync="dialogVisible"
                 width="35%" showClose :before-close="handleClose">
        <el-form ref="recruitUser" :model="recruitUser" label-width="80px" size="small">
          <el-form-item label="姓名" prop="username">
            <el-input v-model="recruitUser.username"></el-input>
          </el-form-item>
          <el-form-item label="学历" prop="education">
            <el-input v-model="recruitUser.education"></el-input>
          </el-form-item>
          <el-form-item label="投递状态" prop="state">
            <el-radio-group v-model="recruitUser.state">
              <el-radio label="已通过"></el-radio>
              <el-radio label="未通过"></el-radio>
              <el-radio label="正在进行中"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editRecruitUser('recruitUser')" size="small">确 定</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      name: "RecruitUser",
      data() {
        return {
          pageInfo:{
            pageIndex: 1,
          },
          pageTotal: 0,
          dialogVisible: false,
          tableData: [],
          recruitUser: {
            username: '',
            education: '',
            state: ''
          }
        }
      },
      methods: {
        //分页
        handleCurrentChange(value){
          this.pageInfo.pageIndex = value;//获得当前页码
          this.findAll(this.pageInfo.pageIndex);
        },
        //自定义index
        indexMethod(index) {
          return index+(this.pageInfo.pageIndex-1)*8+1;
        },
        //筛选
        filterNature(value, row) {
          return row.nature === value;
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
        //预览简历
        handleResume(index, row) {
          //this.$router.replace(row.fileurl);
          window.open(row.fileurl);
        },
        //编辑
        handleEdit(index, row) {
          //console.log(index, row);
          this.dialogVisible = true;
          this.recruitUser = row
        },
        //更新
        editRecruitUser(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              console.log(this.recruitUser)
              this.axios.post('http://localhost:9000/user/recruit/edit', this.recruitUser).then(res => {
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
        findAll(pageNum) {
          this.axios.get('http://localhost:9000/user/recruit/list?pageNum=' + pageNum).then(res => {
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
  .pageBox {
    margin-top: 15px;
  }
</style>
