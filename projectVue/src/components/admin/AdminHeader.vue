<template>
  <div class="homeHeader">
    <div class="title">人力资源管理平台</div>
    <div class="headerTime">当前时间 : {{dateFormat(date)}}</div>
    <el-dropdown @command="commandHeader">
      <span class="el-dropdown-link">欢迎您 , {{this.username}}<i class="el-icon-arrow-down el-icon--right"></i></span>
      <el-dropdown-menu slot="dropdown">
        <!--<el-dropdown-item command="userInfo">个人中心</el-dropdown-item>-->
        <el-dropdown-item command="logout">注销登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
    export default {
      name: "AdminHeader",
      data() {
        return {
          username: '',
          date: new Date()
        }
      },
      methods: {
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
                  localStorage.removeItem("username")
                  localStorage.removeItem("userId")
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
        //日期
        dateFormat(time) {
          let date=new Date(time);
          let year=date.getFullYear();

          let month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
          let day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
          let hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
          let minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
          let seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
          let week = new Date(time).getDay();
          let weeks = ["日","一","二","三","四","五","六"];
          let getWeek = "星期" + weeks[week];
          // 拼接
          return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds + "  " + getWeek;
        }
      },
      mounted() {
        //显示当前日期时间
        let _this = this// 声明一个变量指向Vue实例this，保证作用域一致
        this.timer = setInterval(() => {
          _this.date = new Date(); // 修改数据date
        }, 1000)
      },
      beforeDestroy() {
        if (this.timer) {
          clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
        }
      },
      created() {
        this.username = window.localStorage.getItem("username");
      }
    }
</script>

<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #fff;
  }
  .el-icon-arrow-down {
    font-size: 16px;
  }
  .homeHeader .title {
    font-size: 30px;
    color: #fff;
    font-family: 华文行楷;
  }
  .headerTime {
    color: #fff;
    font-size: 16px;
  }
  .homeHeader {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 15px;
    box-sizing: border-box;
  }
</style>
