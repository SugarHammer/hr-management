<template>
  <div class="box">
    <div id="myChart2" class="item2"></div>
    <div id="myChart3" class="item2"></div>
  </div>
</template>

<script>
  import * as echarts from 'echarts';
  export default {
    name: "AdminData2",
    mounted(){

      let myChart2 = echarts.init(document.getElementById('myChart2'));
      // 绘制图表

      myChart2.setOption({
        title: {
          text: '近一周员工入职人数统计',
          left: 'center',
          top: 20,
          textStyle: {
            color: '#555555'
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
          data: [1, 0, 1, 2, 0, 1, 1],
          type: 'line',
          smooth: true
        }]
      });

      let myChart3 = echarts.init(document.getElementById('myChart3'));
      myChart3.setOption({
        title: {
          text: '薪资分布',
          subtext: '薪资范围'
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c}%"
        },
        toolbox: {
          feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
          }
        },
        legend: {
          data: ['6-10k','10-20k','20-30k','30k以上']
        },

        series: [
          {
            name:'薪资分布',
            type:'funnel',
            left: '10%',
            top: 60,
            //x2: 80,
            bottom: 60,
            width: '80%',
            // height: {totalHeight} - y - y2,
            min: 0,
            max: 100,
            minSize: '0%',
            maxSize: '100%',
            sort: 'descending',
            gap: 2,
            label: {
              show: true,
              position: 'inside'
            },
            labelLine: {
              length: 10,
              lineStyle: {
                width: 1,
                type: 'solid'
              }
            },
            itemStyle: {
              borderColor: '#fff',
              borderWidth: 1
            },
            emphasis: {
              label: {
                fontSize: 20
              }
            },
            data: [
              {value: 20, name: '6-10k'},
              {value: 50, name: '10-20k'},
              {value: 20, name: '20-30k'},
              {value: 10, name: '30k以上'}
            ]
          }
        ]
      });
    }
  }
</script>

<style scoped>
  .box {
    display: flex;
    flex: 1;
  }
  .item2 {
    width: 600px;
    height: 400px;
  }
</style>
