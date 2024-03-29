<template>
    <div>
      <!--搜索框-->
      <el-input v-model="search" placeholder="请输入要搜索的用户名" class="search" size="small" suffix-icon="el-icon-search" clearable
                :data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))">
      </el-input>
      <el-button @click="toAdd" type="primary" size="small" icon="el-icon-plus">添加用户</el-button>

      <el-table :data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"
        ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <!--<el-table-column prop="password" label="密码"></el-table-column>-->
        <el-table-column prop="create" label="创建时间"></el-table-column>
        <el-table-column prop="role" label="用户角色"
          :filters="[{ text: '员工', value: '员工' }, { text: '普通用户', value: '普通用户' }]"
          :filter-method="filterRole" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag :type="scope.row.role === '员工' ? 'primary' : 'success'"
              disable-transitions>{{scope.row.role}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" icon="el-icon-edit"
                       @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <!--<el-button size="small" type="danger" icon="el-icon-delete"
                       @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
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

      <!--修改用户信息-->
      <el-dialog title="修改用户信息" :visible.sync="dialogVisible" :showClose="false"
                 width="35%" :before-close="handleClose">
        <el-form ref="userInfo" :model="userInfo" label-width="80px" size="small">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userInfo.username"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="userInfo.phone"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="userInfo.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="用户角色" prop="role">
            <el-radio-group v-model="userInfo.role">
              <!--<el-radio label="管理员"></el-radio>-->
              <el-radio label="普通用户"></el-radio>
              <el-radio label="员工"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editInfo('userInfo')" size="small">确 定</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      name: "User",
      data() {
        return {
          pageInfo:{
            pageIndex: 1,
          },
          pageTotal: 0,
          search: '',
          dialogVisible: false,
          tableData: [],
          userInfo: {
            username: '',
            phone: '',
            password: '',
            role: ''
          }
        }
      },
      methods: {
        //跳转到添加页面
        toAdd() {
          this.$router.push('/admin/user/add')
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
        //筛选
        filterRole(value, row) {
          return row.role === value;
        },
        //关闭弹窗
        handleClose(done) {
          this.$confirm('确认关闭？')
            .then(_ => {
              done();
            })
            .catch(_ => {});
        },
        findAll(pageNum) {
          this.axios.get('http://localhost:9000/user/list?pageNum=' + pageNum).then(res => {
            this.tableData = res.data.list
            this.pageTotal = res.data.total
          })
        },
        remove() {
          this.dialogVisible = false
          this.findAll(this.pageInfo.pageIndex);
        },
        //编辑
        handleEdit(index, row) {
          //console.log(index, row);
          this.dialogVisible = true;
          this.userInfo = row
        },
        //更新
        editInfo(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/user/edit', this.userInfo).then(res => {
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
