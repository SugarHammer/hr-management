<template>
    <div>
      <!--我的账号信息-->
      <el-button type="primary" size="small" style="margin-bottom: 10px"
                 icon="el-icon-edit" @click="dialogFormVisible = true">修改我的账号信息</el-button>
      <el-descriptions class="margin-top" :column="3" border>
        <el-descriptions-item>
          <template slot="label">用户名</template>
          <span v-model="userInfo.username">{{userInfo.username}}</span>
        </el-descriptions-item>
        <!--<el-descriptions-item>
          <template slot="label">密码</template>
          <span v-model="userInfo.password">{{userInfo.password}}</span>
        </el-descriptions-item>-->
        <el-descriptions-item>
          <template slot="label">角色</template>
          <el-tag size="small" v-model="userInfo.role">{{userInfo.role}}</el-tag>
          <!--<span v-model="userInfo.role">{{userInfo.role}}</span>-->
        </el-descriptions-item>
      </el-descriptions>

      <!--修改我的账号信息-->
      <el-dialog title="修改我的账号信息" :visible.sync="dialogFormVisible"
                 width="35%" showClose :before-close="handleClose">
        <el-form ref="userInfo" :model="userInfo" label-width="80px" size="small">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userInfo.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input show-password v-model="userInfo.password"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editInfo('userInfo')" size="small">确 定</el-button>
        </span>
      </el-dialog>

      <!--我的基本信息-->
      <el-button type="primary" size="small" style="margin-bottom: 10px; margin-top: 20px"
                 icon="el-icon-edit" @click="handleEdit()">修改我的基本信息</el-button>
      <el-descriptions class="margin-top" :column="3" border>
        <el-descriptions-item>
          <template slot="label">姓名</template>
          <span v-model="profileInfo.name">{{profileInfo.name}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">性别</template>
          <span v-model="profileInfo.sex">{{profileInfo.sex}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">手机号</template>
          <span v-model="profileInfo.phone">{{profileInfo.phone}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">毕业院校</template>
          <span v-model="profileInfo.university">{{profileInfo.university}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">所学专业</template>
          <span v-model="profileInfo.major">{{profileInfo.major}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">学历</template>
          <span v-model="profileInfo.education">{{profileInfo.education}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">职位</template>
          <span v-model="profileInfo.position">{{profileInfo.position}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">所属部门</template>
          <span v-model="profileInfo.dept">{{profileInfo.dept}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">入职时间</template>
          <span v-model="profileInfo.create">{{profileInfo.create}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">身份证号码</template>
          <span v-model="profileInfo.card">{{profileInfo.card}}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">家庭住址</template>
          <span v-model="profileInfo.address">{{profileInfo.address}}</span>
        </el-descriptions-item>
      </el-descriptions>

      <!--修改我的信息-->
      <el-dialog title="修改我的信息" :visible.sync="dialogVisible"
                 width="45%" showClose :before-close="handleClose">
        <el-form ref="profileInfo" :model="profileInfo" label-width="80px" size="small">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="profileInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="profileInfo.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="身份证" prop="card">
            <el-input v-model="profileInfo.card"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="profileInfo.phone"></el-input>
          </el-form-item>
          <el-form-item label="毕业院校" prop="university">
            <el-input v-model="profileInfo.university"></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="major">
            <el-input v-model="profileInfo.major"></el-input>
          </el-form-item>
          <el-form-item label="学历" prop="education">
            <el-select v-model="profileInfo.education" clearable placeholder="请选择">
              <el-option v-for="item in educations" :key="item.value"
                         :label="item.value" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="职位" prop="position">
            <el-select v-model="profileInfo.position" clearable placeholder="请选择">
              <el-option v-for="item in positions" :key="item.id"
                         :value="item.name" :label="item.name">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="部门" prop="dept">
            <el-select v-model="profileInfo.dept" clearable placeholder="请选择">
              <el-option v-for="item in depts" :key="item.id"
                         :value="item.name" :label="item.name">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="家庭住址" prop="address">
            <el-input v-model="profileInfo.address"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="remove" size="small">取 消</el-button>
          <el-button type="primary" @click="editInfo('profileInfo')" size="small">确 定</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      name: "ProfileInfo",
      data() {
        return {
          dialogFormVisible: false,
          userInfo: {
            id: '',
            username: '',
            password: '',
            role: ''
          },
          educations: [{
            value: '本科'
          }, {
            value: '硕士'
          }, {
            value: '博士'
          }],
          depts: [],
          positions: [],
          dialogVisible: false,
          profileInfo: {
            id: '',
            name: '',
            sex: '',
            card: '',
            phone: '',
            university: '',
            major: '',
            education: '',
            address: '',
            position: '',
            dept: '',
            create: ''
          },
          username: ''
        }
      },
      methods: {
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
          this.dialogFormVisible = false
          this.findAll();
        },
        //编辑
        handleEdit() {
          this.dialogVisible = true;
        },
        //更新
        editInfo(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.axios.post('http://localhost:9000/emp/edit', this.profileInfo).then(res => {
                if (res.data.status) {
                  this.$message.success(res.data.msg);
                  this.findAll();
                  this.dialogFormVisible = false;
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
        findAll() {
          this.username = window.localStorage.getItem("username");
          this.axios.get('http://localhost:9000/emp/queryByName?name=' + this.username).then(res => {
            this.profileInfo = res.data;
          })
          this.axios.get('http://localhost:9000/user/queryByName?username=' + this.username).then(res => {
            this.userInfo = res.data;
          })
        }
      },
      created() {
        //调用findAll
        this.findAll();
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

</style>
