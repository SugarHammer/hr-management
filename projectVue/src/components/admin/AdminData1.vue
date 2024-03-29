<template>
  <div class="box">
    <div id="myChart" class="item"></div>
    <div id="myChart1" class="item item1"></div>
  </div>
</template>

<script>
  import * as echarts from 'echarts';
  export default {
    name: "AdminData1",
    mounted() {
      //饼图
      this.axios.get('http://localhost:9000/emp/pieVO').then(res => {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('myChart'));
        myChart.setOption({
          title: {
            text: '员工男女比例统计',
            left: 'center',
            textStyle: {
              color: '#333'
            }
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          series: [
            {
              name: '性别',
              type: 'pie',
              radius: '50%',
              data: res.data,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        });
      })

      //树状图
      this.axios.get('http://localhost:9000/dept/barVO').then(res => {
        let myChart1 = echarts.init(document.getElementById('myChart1'));
        myChart1.setOption({
          title: {
            text: '部门人数统计',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#333'
            }
          },
          tooltip: {},
          xAxis: {
            data: res.data.names
          },
          yAxis: {},
          series: [{
            name: '人数',
            type: 'bar',
            data: res.data.values
          }]
        });
      })
    }
  }
</script>

<style scoped>
  .box {
    display: flex;
    flex: 1;
  }
  .item {
    width: 600px;
    height: 300px;
  }
  .item1 {
    margin-top: -20px;
    height: 320px !important;
  }
</style>
