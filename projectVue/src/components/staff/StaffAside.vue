<template>
  <div>
    <el-menu @select="menuClick" class="el-menu-vertical-demo">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-user"></i>我的信息</template>
        <el-menu-item-group>
          <el-menu-item index="/profile/info">我的资料</el-menu-item>
          <el-menu-item index="/profile/salary">我的工资</el-menu-item>
          <el-menu-item index="/profile/train">我的报名</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/staff/emp">
        <i class="el-icon-postcard"></i>
        <span slot="title">同事信息</span>
      </el-menu-item>
      <el-menu-item index="/staff/train">
        <i class="el-icon-guide"></i>
        <span slot="title">培训活动</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
    export default {
      name: "StaffAside",
      methods: {
        menuClick(index) {
          this.$router.push(index)
        }
      }
    }
</script>

<style scoped>
  .el-menu-vertical-demo {
    height: 658px;
    background-color: #dcdde1;
  }
</style>
