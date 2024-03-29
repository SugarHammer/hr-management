<template>
  <div>
    <!--搜索框-->
    <el-input v-model="search" placeholder="请输入要搜索的职位名称" class="search" size="small" suffix-icon="el-icon-search"
              :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
    </el-input>
    <el-button @click="toAdd" type="primary" size="small" icon="el-icon-plus">添加职位</el-button>

    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              ref="filterTable" style="width: 100%; margin-top: 10px" stripe size="small">
      <el-table-column
        type="index"
        :index="indexMethod">
      </el-table-column>
      <el-table-column prop="name" label="职位名称"></el-table-column>
      <el-table-column prop="type" label="职位族"></el-table-column>
      <el-table-column prop="address" label="工作地点"></el-table-column>
      <el-table-column prop="number" label="招聘人数"></el-table-column>
      <el-table-column prop="nature" label="招聘性质"
                       :filters="[{ text: '校园招聘', value: '校园招聘' }, { text: '社会招聘', value: '社会招聘' }]"
                       :filter-method="filterNature" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag :type="scope.row.nature === '社会招聘' ? 'primary' : 'success'"
                  disable-transitions>{{scope.row.nature}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="280">
        <template slot-scope="scope">
          <el-button size="small" type="primary"
                     @click="handleSee(scope.$index, scope.row)">查看详情</el-button>
          <el-button size="small" icon="el-icon-edit"
                     @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="small" type="danger" icon="el-icon-delete"
                     @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--招聘信息详情-->
    <el-dialog title="招聘职位详细信息" :visible.sync="visible"
               showClose :before-close="handleClose">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="职位名称">{{recruitInfo.name}}</el-descriptions-item>
        <el-descriptions-item label="职位族">{{recruitInfo.type}}</el-descriptions-item>
        <el-descriptions-item label="工作地点">{{recruitInfo.address}}</el-descriptions-item>
        <el-descriptions-item label="招聘人数">{{recruitInfo.number}}</el-descriptions-item>
        <el-descriptions-item label="招聘性质">
          <el-tag :type="recruitInfo.nature === '校园招聘' ? 'success' : 'primary'">{{recruitInfo.nature}}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
      <el-descriptions :column="1" direction="vertical" border>
        <el-descriptions-item label="职位描述">{{recruitInfo.describe}}</el-descriptions-item>
        <el-descriptions-item label="任职要求">{{recruitInfo.require}}</el-descriptions-item>
      </el-descriptions>
      <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">关 闭</el-button>
      </span>
    </el-dialog>

    <!--分页-->
    <div class="pageBox">
      <el-pagination background layout="total, prev, pager, next" align="center"
                     :current-page="pageInfo.pageIndex" :page-size="8" :total="pageTotal"
                     @current-change="handleCurrentChange">
      </el-pagination>
    </div>

    <!--修改招聘信息-->
    <el-dialog title="修改招聘信息" :visible.sync="dialogVisible"
               showClose :before-close="handleClose">
      <el-form ref="recruitInfo" :model="recruitInfo" label-width="80px" size="small">
        <el-form-item label="职位名称" prop="name">
          <el-input v-model="recruitInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="职位族" prop="type">
          <el-select v-model="recruitInfo.type" clearable placeholder="请选择">
            <el-option v-for="item in options" :key="item.value"
                       :label="item.value" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工作地点" prop="address">
          <el-input v-model="recruitInfo.address"></el-input>
        </el-form-item>
        <el-form-item label="招聘人数" prop="number">
          <el-input v-model="recruitInfo.number"></el-input>
        </el-form-item>
        <el-form-item label="招聘性质" prop="nature">
          <el-radio-group v-model="recruitInfo.nature">
            <el-radio label="校园招聘"></el-radio>
            <el-radio label="社会招聘"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="职位描述" prop="describe">
          <el-input type="textarea" v-model="recruitInfo.describe"></el-input>
        </el-form-item>
        <el-form-item label="任职要求" prop="require">
          <el-input type="textarea" v-model="recruitInfo.require"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editInfo('recruitInfo')" size="small">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "RecruitList",
      data() {
          return {
            pageInfo:{
              pageIndex: 1,
            },
            pageTotal: 0,
            options: [{
              value: '技术族'
            }, {
              value: '产品族'
            }, {
              value: '职能族'
            }],
            search: '',
            visible: false,
            dialogVisible: false,
            tableData: [],
            recruitInfo: {
              name: '',
              type: '',
              address: '',
              number: '',
              nature: '',
              describe: '',
              require: ''
            }
          }
      },
      methods: {
        //跳转到添加页面
        toAdd() {
          this.$router.push('/admin/recruit/add')
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
          this.visible = false
          this.dialogVisible = false
          this.findAll(this.pageInfo.pageIndex);
        },
        //详情
        handleSee(index, row) {
          //console.log(index, row);
          this.visible = true;
          this.recruitInfo = row
        },
        //编辑
        handleEdit(index, row) {
          //console.log(index, row);
          this.dialogVisible = true;
          this.recruitInfo = row
        },
        //更新
        editInfo(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/recruit/edit', this.recruitInfo).then(res => {
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
            this.axios.get('http://localhost:9000/recruit/delete?id=' + row.id).then(res => {
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
          this.axios.get('http://localhost:9000/recruit/listAll?pageNum=' + pageNum).then(res => {
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
