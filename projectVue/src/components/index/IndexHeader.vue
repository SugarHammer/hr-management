<template>
  <div>
    <el-row :gutter="24">
      <el-col :span="9" style="font-size: 30px; color: #fff; font-family: 华文行楷;">人力资源管理平台</el-col>
      <el-col :span="12">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#272a34"
          text-color="#fff"
          active-text-color="#67C23A">
          <el-menu-item index="/">首页</el-menu-item>
          <div>

          </div>
          <el-menu-item index="/resume" v-if="username !== null">人才招聘</el-menu-item>
          <el-menu-item index="/data" v-if="username !== null">数据可视化</el-menu-item>
          <el-menu-item index="/us" v-if="username !== null">我的应聘</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="3">
        <div v-if="username === null">
          <span class="toLogin" @click="$router.replace('/login')">登录</span>
          <el-divider direction="vertical"></el-divider>
          <span class="toLogin" @click="$router.replace('/register')">注册</span>
        </div>
        <div v-else>
          <el-dropdown @command="commandHeader">
            <span class="el-dropdown-link">欢迎您 , {{this.username}}<i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu slot="dropdown">
              <!--<el-dropdown-item command="userInfo">个人中心</el-dropdown-item>-->
              <el-dropdown-item command="logout">注销登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "IndexHeader",
      data() {
        return {
          activeIndex: this.$route.path,
          username: ''
        }
      },
      methods: {
        //跳转
        handleSelect(key, keyPath) {
          //console.log(key, keyPath);
          this.$router.push(key);
        },
        //注销
        commandHeader(command) {
          if (command == 'logout') {
            this.$confirm('此操作将退出系统, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.axios.get('http://localhost:9000/user/logout').then(res => {
                if (res.data.status) {
                  this.$message.success('注销成功!');
                  localStorage.removeItem("username");
                  localStorage.removeItem("userId");
                  localStorage.removeItem("token")
                  this.$router.replace('/login')
                }
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消'
              });
            });
          }
        },
      },
      created() {
        this.username = window.localStorage.getItem("username");
      }
    }
</script>

<style scoped>
  .el-menu-item {
    margin-right: 30px;
  }
  .toLogin {
    cursor: pointer;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #fff;
  }
</style>
