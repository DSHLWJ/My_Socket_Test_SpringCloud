<template>
  <div>
    <h2>实时更新折线图（ECharts）</h2>
    <!-- 使用 ref 引用 DOM -->
    <div ref="lineChart" style="width: 1000px; height: 600px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { ref, onMounted, nextTick, onBeforeUnmount } from 'vue';

export default {
  setup() {
    const chartData = ref({
      xData: [],
      yData: [],
    });

    const lineChart = ref(null); // Vue ref 引用 DOM
    let myChart = null;

    const eventSource = ref(null);
    const loading = ref(true);


    // 定义一个方法来获取最新值
    const getLatestValue = (data)=> {
      console.log("11111")
      return {
        coord: [data.yData.length - 1, data.yData[data.yData.length - 1]], // 最新值的坐标
        value: data.yData[data.yData.length - 1], // 最新值
      };
    };

    // 创建图表
    const createChart = () => {
      if (lineChart.value) {
        myChart = echarts.init(lineChart.value);

        // 实时动态显示平均值跟坐标线
        // const option = {
        //   title: {
        //     text: '实时数据折线图',
        //   },
        //   tooltip: {
        //     trigger: 'axis' // 使用坐标轴触发
        //   },
        //   xAxis: {
        //     type: 'category',
        //     boundaryGap: false,
        //     data: chartData.value.xData,
        //   },
        //   yAxis: {
        //     type: 'value',
        //     axisLabel: {
        //       formatter: '{value} °C'
        //     }
        //   },
        //   series: [
        //     {
        //       data: chartData.value.yData,
        //       type: 'line',
        //       lineStyle: {
        //         color: '#336CCF',
        //       },
        //       markLine: {
        //         data: [{ type: 'average', name: 'Avg' }]
        //       }
        //     },
        //   ],
        // };


        const option = {
          title: {
            text: '实时数据折线图',
          },
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            data: chartData.value.xData,
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: chartData.value.yData,
              type: 'line',
              smooth: true,
              lineStyle: {
                color: '#336CCF',
              },
              markPoint: {
                data: [
                  { type: 'max' },
                  { type: 'min' },
                ],
                symbol: 'circle', // 设置为圆形点
                symbolSize: 8, // 设置点的大小
                label: {
                  position: 'right',
                  fontWeight: 'bold', // 标签字体加粗
                  fontSize: 12, // 标签字体大小
                },
              },

            },
          ],
        };


        myChart.setOption(option);
      } else {
        console.error('Canvas element not found');
      }
    };

    // 销毁图表
    const destroyChart = () => {
      if (myChart) {
        myChart.dispose();
      }
    };

    // 开始监听 SSE
    const startListening = () => {
      const userId = Math.random().toString(36).substr(2, 9); // 生成一个随机字符串作为 userId

      if (window.EventSource) {
        // 创建 EventSource 实例，建立 SSE 连接
        eventSource.value = new EventSource(`http://localhost:9685/sse/testSse?clientId=${userId}`);

        // 连接成功事件
        eventSource.value.addEventListener('open', (e) => {
          console.log('SSE 连接成功');
          loading.value = false; // 连接成功后隐藏加载状态
        });

        // 接收到消息时的事件
        eventSource.value.addEventListener('message', (event) => {
          const data = JSON.parse(event.data); // 假设后端推送的是 JSON 数据

          let date_time = new Date(data.time)
          // 获取年、月、日等
          const year = date_time.getFullYear();
          const month = date_time.getMonth() + 1;  // 月份从0开始，需要加1
          const day = date_time.getDate();

          // 获取时、分、秒
          const hours = date_time.getHours();
          const minutes = date_time.getMinutes();
          const seconds = date_time.getSeconds();

          // 格式化日期为字符串
          const formattedDate = `${month}月-${day}日 ${hours}:${minutes}:${seconds}`;

          console.log(formattedDate)

          chartData.value.xData.push(formattedDate);
          chartData.value.yData.push(data.value);

          if (chartData.value.xData.length > 100) {
            chartData.value.xData.shift();
            chartData.value.yData.shift();
          }

          // 更新图表
          // if (myChart) {
          //   myChart.setOption({
          //     xAxis: {
          //       data: chartData.value.xData,
          //     },
          //     series: [
          //       {
          //         data: chartData.value.yData,
          //       },
          //     ],
          //   });
          // }

          //   样式2
          if (myChart) {
            myChart.setOption({
              xAxis: {
                data: chartData.value.xData,
              },
              series: [
                {
                  data: chartData.value.yData, // Y 轴数据
                  markPoint: {
                    data: [
                      { type: 'max' },            // 最大值标记
                      { type: 'min' },            // 最小值标记
                      getLatestValue(chartData.value), // 动态添加最新的标记点
                    ],
                    symbol: 'circle',           // 设置标记点为圆形
                    symbolSize: 8,              // 设置标记点的大小
                    label: {
                      position: 'right',
                      fontWeight: 'bold',       // 标签字体加粗
                      fontSize: 12,             // 标签字体大小
                    },
                  },
                },
              ],

            });
          }


        });

        // 完成事件
        eventSource.value.addEventListener('finish', (e) => {
          console.log('当前 SSE 事件推送完毕...');
        });

        // 错误事件处理
        eventSource.value.addEventListener('error', (e) => {
          if (e.readyState === EventSource.CLOSED) {
            console.log('SSE 连接已关闭');
          } else {
            console.log('发生错误...');
          }
        });
      } else {
        console.log('您的浏览器不支持 SSE');
      }
    };

    onMounted(async () => {
      // 使用 nextTick 确保 DOM 已渲染完成
      await nextTick();
      createChart();
      startListening();
    });

    onBeforeUnmount(() => {
      destroyChart();

      // 关闭 SSE 连接
      if (eventSource.value) {
        eventSource.value.close();
      }
    });

    return {
      lineChart,
    };
  },
};
</script>

<style scoped>
/* 样式根据需要自定义 */
</style>
