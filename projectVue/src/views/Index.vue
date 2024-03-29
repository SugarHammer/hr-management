<template>
  <div>
    <el-container>
      <el-header>
        <IndexHeader></IndexHeader>
      </el-header>
      <el-main style="height: 621px">
        <div>
          <el-carousel :interval="5000" height="621px" style="margin: -20px">
            <el-carousel-item v-for="item in img" :key="item">
              <el-image :src="item" style="height: 100%; width: 100%" fit="fill"></el-image>
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-main>
      <el-footer style="height: 40px">
        <IndexFooter></IndexFooter>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
    import IndexHeader from "../components/index/IndexHeader";
    import IndexFooter from "../components/index/IndexFooter";
    export default {
      name: "Index",
      components: {
        IndexHeader,
        IndexFooter
      },
      data() {
        return {
          img: [
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fe%2F573c191476fbf.jpg%3Fdown&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638537208&t=c51cda06ab00f01bb77d273e21804744',
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091320%2F0ghposv35jy0ghposv35jy.jpg&refer=http%3A%2F%2Fimg9.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638537272&t=b3cee39cd80371615916a2e852d1f549',
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.2008php.com%2F2011_Website_appreciate%2F2011-12-05%2F20111205010254.jpg&refer=http%3A%2F%2Fwww.2008php.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638537254&t=22d5c8ec2bdd62b9768c0fa4081b358d',
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091212%2F2do1zrvq5yv2do1zrvq5yv.jpg&refer=http%3A%2F%2Fimg9.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638537350&t=c525b94a1a82eded9798b5e732b0e524'
          ]
        }
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

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 500px;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

</style>
