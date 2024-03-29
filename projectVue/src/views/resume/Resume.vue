<template>
  <!--投递简历首页-->
  <div>
    <el-container>
      <el-header>
        <IndexHeader></IndexHeader>
      </el-header>
      <el-main style="height: 621px">
        <h1 class="title"><i class="el-icon-zoom-in"></i> 诚聘英才 | 福利待遇</h1>
        <h1 class="title delivery" @click="$router.replace('/resumePage')">
          <i class="el-icon-position"></i> 点击投递简历</h1>
        <div class="container">
          <div class="item">
            <h2>技术族</h2>
            <p>Java开发工程师</p>
            <p>前端开发工程师</p>
            <p>测试工程师</p>
          </div>
          <div class="item">
            <h2>产品族</h2>
            <p>产品经理</p>
            <p>用户研究</p>
            <p>视觉设计</p>
          </div>
          <div class="item">
            <h2>职能族</h2>
            <p>HRBP</p>
            <p>行政管理</p>
            <p>运营管理</p>
          </div>
          <div class="item lastItem">
            <h2>九险一金</h2>
            <p>五险一金、补充医疗险</p>
            <p>意外险、重大疾病险、补充生育险</p>
          </div>
          <div class="item lastItem">
            <h2>年度福利</h2>
            <p>年终奖、年节礼物</p>
            <p>年度体检、生日礼物</p>
          </div>
          <div class="item lastItem">
            <h2>个人发展</h2>
            <p>一对一导师带教, 180天行动指导</p>
            <p>三年培养规划, 多通道跨地域的职业选择</p>
          </div>
        </div>
      </el-main>
      <el-footer style="height: 40px">
        <IndexFooter></IndexFooter>
      </el-footer>
      <router-view/>
    </el-container>
  </div>
</template>

<script>
    import IndexHeader from "../../components/index/IndexHeader";
    import IndexFooter from "../../components/index/IndexFooter";
    export default {
      name: "Resume",
      components: {
        IndexHeader,
        IndexFooter
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
    text-align: center;
    line-height: 40px;
  }

  .grid-content {
    width: 200px;
    height: 200px;
  }

  .container {
    width: 1000px;
    background-color: white;
    margin-left: 300px;
  }
  .container .item {
    float: left;
    width: 290px;
    height: 200px;
    margin-right: 10px;
    background-color: #fff;
    text-align: center;
    border-radius: 10px;
  }
  .container .item:hover {
    box-shadow: 0 2px 12px 0 rgb(0 0 0);
  }
  .container .lastItem {
    margin-top: 10px;
  }
  .title {
    display: inline-block;
    margin-left: 300px;
    font-size: 28px;
  }
  .delivery {
    margin-left: 390px;
    cursor: pointer;
  }
</style>
