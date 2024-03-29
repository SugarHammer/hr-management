<template>
  <div style="margin: -8px">
    <el-container>
      <el-header>
        <AdminHeader></AdminHeader>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <StaffAside></StaffAside>
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right" class="homeBread"
                         v-if="this.$router.currentRoute.path != '/staff/index'">
            <el-breadcrumb-item :to="{ path: '/staff/index' }"><i class="el-icon-s-home"></i> 首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <div v-if="this.$router.currentRoute.path == '/staff/index'">欢迎您</div>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import AdminHeader from "../../components/admin/AdminHeader";
  import StaffAside from "../../components/staff/StaffAside";
  export default {
    name: "StaffIndex",
    components: {
      AdminHeader,
      StaffAside
    }
  }
</script>

<style scoped>
  .homeBread {
    font-size: 16px;
    margin-bottom: 20px;
  }
  .el-header {
    background-color: #272a34;
    line-height: 60px;
  }
  .el-main {
    background-color: #f5f6fa;
    /*background-color: #b2bec3;*/
  }
</style>
