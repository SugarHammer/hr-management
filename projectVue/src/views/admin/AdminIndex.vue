<template>
    <div style="margin: -8px">
      <el-container>
        <el-header>
          <AdminHeader></AdminHeader>
        </el-header>
        <el-container>
          <el-aside width="200px">
            <AdminAside></AdminAside>
          </el-aside>
          <el-main style="height: 650px;">
            <el-breadcrumb separator-class="el-icon-arrow-right" class="homeBread"
                           v-if="this.$router.currentRoute.path != '/admin/index'">
              <el-breadcrumb-item :to="{ path: '/admin/index' }"><i class="el-icon-s-home"></i> 首页</el-breadcrumb-item>
              <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
            </el-breadcrumb>
            <div v-if="this.$router.currentRoute.path == '/admin/index'">欢迎您</div>
            <router-view/>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>
  import AdminHeader from "../../components/admin/AdminHeader";
  import AdminAside from "../../components/admin/AdminAside";
  export default {
    name: "AdminIndex",
    components: {
      AdminHeader,
      AdminAside
    }
  }
</script>

<style>
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
