<template>
  <div>
    <el-container>
      <el-header>
        <IndexHeader></IndexHeader>
      </el-header>
      <el-main style="height: 621px">
        <!--图-->
        <IndexPie></IndexPie>
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
  import IndexPie from "../../components/index/IndexPie";
  export default {
    name: "Data",
    components: {
      IndexHeader,
      IndexFooter,
      IndexPie,
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
