<template>
  <div class="box">
    <div id="myChart" class="item"></div>
    <div id="myChart1" class="item1"></div>
    <div id="myChart2" class="item2"></div>
  </div>
</template>

<script>
  import * as echarts from 'echarts';
    export default {
      name: "IndexPie",
      mounted() {
        //饼图
        let myChart = echarts.init(document.getElementById('myChart'));
        myChart.setOption({
          title: {
            text: '各个大族正在招聘职位数量的比例',
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
              type: 'pie',
              radius: '50%',
              data: [
                { value: 5, name: '技术族' },
                { value: 1, name: '产品族' },
                { value: 1, name: '职能族' }
              ],
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

        //树状图
        let myChart1 = echarts.init(document.getElementById('myChart1'));
        myChart1.setOption({
          title: {
            text: '投递各个职位大族人数统计',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#333'
            }
          },
          tooltip: {},
          xAxis: {
            type: 'category',
            data: ['技术族', '产品族', '职能族']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            name: '人数',
            type: 'bar',
            data: [4, 2, 1]
          }]
        });

        //折线图
        let myChart2 = echarts.init(document.getElementById('myChart2'));
        myChart2.setOption({
          title: {
            text: '近一周投递简历人数统计',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#333'
            }
          },
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [1, 0, 2, 0, 1, 0, 2],
            type: 'line',
            smooth: true
          }]
        });
      }
    }
</script>

<style scoped>
  .item {
    margin-top: 50px;
    width: 500px;
    height: 420px;
  }
  .item1 {
    margin-top: 35px;
    width: 350px;
    height: 420px;
  }

  .item2 {
    margin-top: 35px;
    width: 500px;
    height: 420px;
  }
  .box {
    display: flex;
    flex: 1;
  }
</style>
