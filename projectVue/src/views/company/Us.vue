<template>
  <!--我的应聘-->
  <div>
    <el-container>
      <el-header>
        <IndexHeader></IndexHeader>
      </el-header>
      <el-main style="height: 621px">
        <div style="width: 1100px; margin: 0 auto">
          <el-table ref="filterTable" :data="tableData" style="width: 100%; border-radius: 5px" stripe size="small">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-descriptions :column="3" size="small" border style="margin: 5px">
                  <el-descriptions-item>
                    <template slot="label"><i class="el-icon-user"></i>用户名</template>
                    {{ props.row.username }}
                  </el-descriptions-item>
                  <el-descriptions-item>
                    <template slot="label"><i class="el-icon-mobile-phone"></i>手机号</template>
                    {{ props.row.phone }}
                  </el-descriptions-item>
                  <el-descriptions-item>
                    <template slot="label"><i class="el-icon-school"></i>毕业院校</template>
                    {{ props.row.university }}
                  </el-descriptions-item>
                  <el-descriptions-item>
                    <template slot="label"><i class="el-icon-reading"></i>所学专业</template>
                    {{ props.row.major }}
                  </el-descriptions-item>
                  <el-descriptions-item>
                    <template slot="label"><i class="el-icon-tickets"></i>学历</template>
                    {{ props.row.education }}
                  </el-descriptions-item>
                  <el-descriptions-item>
                    <template slot="label">
                      <i class="el-icon-location-outline"></i>家庭住址</template>
                    {{ props.row.address }}
                  </el-descriptions-item>
                </el-descriptions>
              </template>
            </el-table-column>
            <el-table-column type="index" :index="indexMethod"></el-table-column>
            <el-table-column prop="name" label="岗位名称"></el-table-column>
            <el-table-column label="职位族" prop="type"></el-table-column>
            <el-table-column label="工作地点" prop="gaddress"></el-table-column>
            <el-table-column prop="nature" label="招聘性质"
                             :filters="[{ text: '校园招聘', value: '校园招聘' }, { text: '社会招聘', value: '社会招聘' }]"
                             :filter-method="filterNature" filter-placement="bottom-end">
              <template slot-scope="scope">
                <el-tag :type="scope.row.nature === '社会招聘' ? 'primary' : 'success'"
                        disable-transitions>{{scope.row.nature}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="state" label="状态"></el-table-column>
            <el-table-column label="操作" width="200">
              <template slot-scope="scope">
                <el-button icon="el-icon-edit" size="small"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="small" type="danger"
                       @click="handleDelete(scope.$index, scope.row)">取消申请</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!--修改我的信息-->
        <div>
          <el-dialog title="修改我的信息" :visible.sync="dialogVisible" :showClose="false"
                     width="40%" :before-close="handleClose">
            <el-form ref="userRecruit" :model="userRecruit" label-width="80px" size="small">
              <el-form-item label="姓名" prop="username">
                <el-input v-model="userRecruit.username"></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="sex">
                <el-radio-group v-model="userRecruit.sex">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="毕业院校" prop="university">
                <el-input v-model="userRecruit.university"></el-input>
              </el-form-item>
              <el-form-item label="所学专业" prop="major">
                <el-input v-model="userRecruit.major"></el-input>
              </el-form-item>
              <el-form-item label="学历" prop="education">
                <el-select v-model="userRecruit.education" clearable placeholder="请选择">
                  <el-option v-for="item in educations" :key="item.value"
                             :label="item.value" :value="item.value">
                  </el-option>
                </el-select>
                <!--<el-input v-model="userRecruit.education"></el-input>-->
              </el-form-item>
              <el-form-item label="手机号" prop="phone">
                <el-input v-model="userRecruit.phone"></el-input>
              </el-form-item>
              <el-form-item label="家庭住址" prop="address">
                <el-input v-model="userRecruit.address"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editUserRecruit('userRecruit')" size="small">确 定</el-button>
        </span>
          </el-dialog>
        </div>
      </el-main>
      <el-footer style="height: 40px">
        <IndexFooter></IndexFooter>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  import IndexHeader from "../../components/index/IndexHeader";
  import IndexFooter from "../../components/index/IndexFooter";
  export default {
    name: "Us",
    data() {
      return {
        educations: [{
          value: '本科'
        }, {
          value: '硕士'
        }, {
          value: '博士'
        }],
        dialogVisible: false,
        tableData: [],
        userRecruit: {
          username: '',
          sex: '',
          university: '',
          major: '',
          education: '',
          phone: '',
          address: '',
          rid: ''
        },
        username: ''
      }
    },
    methods: {
      //自定义编号
      indexMethod(index) {
        return index+1;
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
        this.findAll();
      },
      //编辑
      handleEdit(index, row) {
        this.dialogVisible = true;
        this.userRecruit = row
      },
      //更新
      editUserRecruit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('http://localhost:9000/user/recruit/edit', this.userRecruit).then(res => {
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
      //取消
      handleDelete(index, row) {
        this.$confirm('此操作将取消申请' + row.name + ', 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios.get('http://localhost:9000/user/recruit/delete?id=' + row.id).then(res => {
            if (res.data.status) {
              this.$message.success(res.data.msg);
              //刷新表单
              this.findAll();
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已放弃取消申请'
          });
        });
      },
      findAll() {
        this.username = localStorage.getItem("username");
        this.axios.get('http://localhost:9000/user/recruit/queryByName?username=' + this.username).then(res => {
          this.tableData = res.data
        })
      }
    },
    created() {
      //调用findAll
      this.findAll();
    },
    components: {
      IndexHeader,
      IndexFooter
    }
  }
</script>

<style scoped>
  .el-container {
    margin: -8px;
  }
  .el-header {
    background-color: #272a34;
    color: white;
    line-height: 60px;
  }

  .el-main {
    background-color: #b2bec3;
  }

  .el-footer {
    background-color: #272a34;
    color: white;
    text-align: center;
    line-height: 40px;
  }
</style>
