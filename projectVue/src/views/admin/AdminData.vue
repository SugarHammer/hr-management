<template>
  <div>
    <div class="title">
      <h1>数据统计报表</h1>
    </div>
    <AdminData1></AdminData1>
    <AdminData2></AdminData2>
  </div>
</template>

<script>
  import AdminData1 from "../../components/admin/AdminData1";
  import AdminData2 from "../../components/admin/AdminData2";
  export default {
    name: "AdminData",
    components: {
      AdminData1,
      AdminData2
    }
  }
</script>

<style scoped>
  .title {
    font-size: 20px;
    text-align: center;
  }
</style>
