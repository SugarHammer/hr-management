<template>
  <div>
    <el-menu @select="menuClick" class="el-menu-vertical-demo">
      <el-menu-item index="/admin/user">
        <i class="el-icon-user-solid"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-s-platform"></i>招聘管理</template>
        <el-menu-item-group>
          <el-menu-item index="/admin/recruit/list">职位信息</el-menu-item>
          <el-menu-item index="/admin/recruit/user">报名信息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/dept/list">
        <i class="el-icon-notebook-1"></i>
        <span slot="title">部门管理</span>
      </el-menu-item>
      <el-menu-item index="/position/list">
        <i class="el-icon-notebook-2"></i>
        <span slot="title">职位管理</span>
      </el-menu-item>
      <el-menu-item index="/emp/list">
        <i class="el-icon-postcard"></i>
        <span slot="title">员工管理</span>
      </el-menu-item>
      <el-menu-item index="/salary/list">
        <i class="el-icon-money"></i>
        <span slot="title">薪资管理</span>
      </el-menu-item>
      <el-submenu index="7">
        <template slot="title"><i class="el-icon-guide"></i>培训管理</template>
        <el-menu-item-group>
          <el-menu-item index="/train/list">培训活动</el-menu-item>
          <el-menu-item index="/train/user">报名信息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/admin/data">
        <i class="el-icon-s-data"></i>
        <span slot="title">数据可视化</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
    export default {
      name: "AdminAside",
      methods: {
        menuClick(index) {
          this.$router.push(index)
        }
      }
    }
</script>

<style>
  .el-menu-vertical-demo {
    height: 658px;
    background-color: #dcdde1;
  }
</style>
