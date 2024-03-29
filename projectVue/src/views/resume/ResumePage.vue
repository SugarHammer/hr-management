<template>
  <div>
    <el-container>
      <el-header>
        <IndexHeader></IndexHeader>
      </el-header>
      <el-main style="height: 750px">
        <div class="container">
          <!--<div style="margin-bottom: 20px">
            <el-select v-model="value" clearable placeholder="请选择职位族" style="width: 315px; margin-right: 15px">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
            <el-select v-model="value" clearable placeholder="请选择工作地点" style="width: 315px; margin-right: 15px">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
            <el-input v-model="input" placeholder="请输入关键字" style="width: 315px; margin-right: 15px"></el-input>
            <el-button type="primary"><i class="el-icon-search"></i>查询</el-button>
          </div>-->

          <el-table :data="tableData" style="width: 100%; border-radius: 5px; margin-top: 20px">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="top" inline class="demo-table-expand">
                  <el-form-item>
                    <div style="font-weight: 700">职位描述:</div>
                    <span>{{ props.row.describe }}</span>
                  </el-form-item>
                  <el-form-item>
                    <div style="font-weight: 700">任职要求:</div>
                    <span>{{ props.row.require }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="职位名称" prop="name"></el-table-column>
            <el-table-column label="职位族" prop="type"></el-table-column>
            <el-table-column label="工作地点" prop="address"></el-table-column>
            <el-table-column label="招聘人数" prop="number"></el-table-column>
            <el-table-column label="招聘性质" prop="nature"
              :filters="[{ text: '校园招聘', value: '校园招聘' }, { text: '社会招聘', value: '社会招聘' }]"
              :filter-method="filterNature" filter-placement="bottom-end">
              <template slot-scope="scope">
                <el-tag :type="scope.row.nature === '校园招聘' ? 'success' : 'primary'"
                  disable-transitions>{{scope.row.nature}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" size="small"
                           @click="handleUserRecruit(scope.$index, scope.row)">立即申请</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
      <el-footer style="height: 40px">
        <IndexFooter></IndexFooter>
      </el-footer>
      <router-view/>
    </el-container>

    <!--申请-->
    <el-dialog title="我的简历" :visible.sync="dialogVisible" :showClose="false"
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
        <el-form-item label="附件简历" prop="address">
          <el-upload class="upload-demo" action="http://localhost:9000/user/recruit/upload" :on-success="onSuccess"
            :on-preview="handlePreview" accept=".pdf" :limit="10">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">请上传pdf格式的简历</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="addUserRecruit('userRecruit')" size="small">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
  import IndexHeader from "../../components/index/IndexHeader";
  import IndexFooter from "../../components/index/IndexFooter";
    export default {
      name: "ResumePage",
      components: {
        IndexHeader,
        IndexFooter
      },
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
          options: [{
            value: '技术族',
            label: '技术族'
          }, {
            value: '产品族',
            label: '产品族'
          }, {
            value: '职能族',
            label: '职能族'
          }],
          userRecruit: {
            username: '',
            sex: '男',
            university: '',
            major: '',
            education: '',
            phone: '',
            address: '',
            rid: '',
            filename: '',
            fileurl: ''
          },
          value: '',
          input: ''
        }
      },
      methods: {
        //文件上传
        onSuccess(response, file, fileList) {
          //console.log(response);
          //console.log(file);
          //console.log(fileList);
          this.userRecruit.filename = file.response.name;
          this.userRecruit.fileurl = file.response.url;
        },
        handlePreview(file) {
          window.open(file.response.url);
        },
        //过滤
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
        //申请 获取岗位id
        handleUserRecruit(index, row) {
          this.dialogVisible = true;
          this.userRecruit.rid = row.id;
        },
        //添加
        addUserRecruit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/user/recruit/add', this.userRecruit).then(res => {
                if (res.data.status) {
                  this.$message.success({
                    message: res.data.msg,
                    showClose: true
                  });
                  this.findAll();
                  this.dialogVisible = false;
                  this.userRecruit = {}
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
        findAll() {
          this.axios.get('http://localhost:9000/recruit/list').then(res => {
            this.tableData = res.data
          })
        }
      },
      created() {
        //调用findAll
        this.findAll();
        //获取登录人的名字
        this.userRecruit.username = window.localStorage.getItem("username");
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
    text-align: center;
    line-height: 40px;
  }

  .demo-table-expand {
    margin-left: 50px;
  }
  .demo-table-expand label {
    font-weight: 700;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .container {
    width: 1100px;
    margin: 0 auto;
  }
</style>
