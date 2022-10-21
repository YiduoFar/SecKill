<template>
  <div class="main">
    <!-- <div class="l" @click="hello">{{ data }}</div>
    <div class="l" @click="logout">LOGOUT</div> -->
    <transition name="fade" :appear="true">
      <div class="middleDiv " v-show="listToDetail">
        <img src="../assets/img/bg.jpg" alt="" class="logo ">
        <div class="queryDiv ">
          <div class="queryDiv-box">
            <input type="text" class="queryDiv-input" placeholder="搜索商品">
            <div class="queryDiv-btn ">搜索</div>
          </div>
        </div>
        <div class="goodListDiv">
          <div class="goodDiv" v-for="(g, index) in goodList" :key="index">
            <!-- 图片 -->
            <div class="good-img">
              <img class="img-css" :src="loadImage(g.goodImg)" alt="">
            </div>
            <!-- 名字、标题 -->
            <div class="good-name ">
              <span style="font-weight: bold;">{{g.goodName}}</span>
              <br>
              {{g.good_title}}
            </div>
            <!-- 库存 -->
            <div class="good_stock ">
              库存量: <span style="color:tomato;">{{g.goodStock}}</span> 件
            </div>
            <!-- 付款人数 -->
            <div class="good_pay_num">
              <span style="color:#5a5a5a">{{(g.goodPayNum)}}+ 人付款</span>
            </div>
            <!-- 秒杀价、原价 -->
            <div class="good_price">
              <div style="color:#f30505;font-weight: bold;font-size:17px">秒杀价:{{g.seckillPrice}}￥</div>
              <div style="color:#5a5a5a;margin-top: 5px;text-decoration: line-through;">原价:{{g.goodPrice}}￥</div>
            </div>
            <!-- 跳转详情 -->
            <div class="jump" @click="jumpToDetail(g.id)">
              详情页>
            </div>
            <div class="goodLine"></div>
          </div>
          <div class="goodLine"></div>
        </div>
        <div class="pageBlock ">
          <span class="demonstration"></span>
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="QueryVo.current" :page-sizes="[20, 50, 100, 200]" :page-size="QueryVo.size"
            layout="total, sizes, prev, pager, next, jumper" :total="400">
          </el-pagination>
        </div>
      </div>
    </transition>
    <transition name="fade2" :appear="true">
      <div class="middleDiv " v-show="!listToDetail">
        <div class="DetailDiv ">
          <div class="jumpBack " @click="jumpToList">&lt;- 返回</div>
          <div class="detailDiv__inner ">
            <!-- 详情图片 -->
            <div class="detail-img">
              <img class="detail-img-css" :src="loadImage(goodDetailFocus.goodImg)" alt="">
            </div>
            <!-- 名称 -->
            <div class="detail-name ">
              <span style="font-size: 25px;">{{goodDetailFocus.goodName}}</span>
              <br>
              <span style="font-size: 16px;">{{goodDetailFocus.goodTitle}}</span>
            </div>
            <!-- 价格 -->
            <div class="detail-price ">
              <span style="font-size: 25px;color: #f30505;font-weight: bold;">
                秒杀价：
                {{goodDetailFocus.seckillPrice}}.00￥
              </span>
              <br>
              <span style="text-decoration: line-through;">
                原价：
                {{goodDetailFocus.goodPrice}}.00￥
              </span>
            </div>
            <!-- 库存 -->
            <div class="detail-stock ">
              秒杀量:
              <span style="font-weight:bold">{{goodDetailFocus.stock_count}}</span>
              <br>
              <span style="text-decoration:underline;"> 库存量:{{goodDetailFocus.good_stock}}+</span>
            </div>
            <!-- 时间 -->
            <div class="detail-time ">
              <span style="color: #000;">秒杀时间</span>
              <br>
              {{goodDetailFocus.startDate|formatDate('yyyy-MM-dd HH:mm:ss')}}（开始）
              <br>
              {{goodDetailFocus.endDate|formatDate('yyyy-MM-dd HH:mm:ss')}}（结束）
            </div>
            <!-- 商品详情 -->
            <div class="detail-detail ">
              <span style="font-size: 18px;font-weight: bold;color: #4d4d4d;">详情:</span>
              {{goodDetailFocus.goodDetail}}
              <br>
              ......
            </div>
          </div>
          <!-- 秒杀状态 按钮 -->
          <div class="detail-status ">
            <div class="d-status " v-show="status == 1">
              <span style="font-weight:lighter">秒杀倒计时</span>
              <span style="font-weight:bold;color: #f30505;">{{dateRemainder}}</span>
              <span style="font-weight:lighter">后</span>
            </div>
            <div class="d-status ing" v-show="status == 2">
              <span style="font-weight:lighter">秒杀进行中</span>
            </div>
            <div class="d-status after" v-show="status == 3">
              秒杀已结束
            </div>
            <div class="d-btn" @click="buy()">
              立即抢购
            </div>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import { mapActions } from "vuex";
// import ElementUI from 'element-ui'
export default {
  data() {
    return {
      data: "123",
      goodList: [],
      goodDetailFocus: {},
      QueryVo: {
        current: 1,
        size: 20,
        word: ''
      },
      listToDetail: true,
      timeCounter: '',
      dateRemainder: '00天00时00分00秒',
      status: 1,
    };
  },
  components: {},
  computed: {
  },
  methods: {
    ...mapActions("good", ["queryAllSecKillGood"]),
    ...mapActions("good", ["querySecKillGoodById"]),
    async hello() {
      this.data = await this.$request.get('/hello')
    },
    logout() {
      this.$request.get('/userLogout')
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    loadImage(img) {
      return this.$img_src + img
    },
    async jumpToDetail(id) {
      this.goodDetailFocus = await this.querySecKillGoodById(id);

      // 1.首先先获取当前的时间戳
      var nowDate = new Date();
      // 2.再获取开始秒杀的时间戳
      var startDate = new Date(this.goodDetailFocus.startDate);
      // 3.再获取结束秒杀的时间戳
      var endDate = new Date(this.goodDetailFocus.endDate);
      if (startDate - nowDate > 0) {
        // 倒计时
        this.status = 1;
        // 4.得到时间差
        let count = (startDate - nowDate) / 1000
        // 5.计算天时分
        this.dateRemainder = this.getCountTime(count);
      } else if (nowDate - endDate > 0) {
        // 已结束
        this.status = 3
      } else {
        // 进行中
        this.status = 2
        // TODO:
      }

      this.listToDetail = !this.listToDetail
    },
    jumpToList() {
      clearInterval(this.timeCounter)
      this.listToDetail = !this.listToDetail
    },
    getCountTime(count) {

      // 5.拿到剩余时间后 获取天数 时分秒
      let d = parseInt(count / 60 / 60 / 24)  //天数
      d = d < 10 ? '0' + d : d;// 判断得出的结果小不小于10  自动补零
      let h = parseInt(count / 60 / 60 % 24) //小时
      h = h < 10 ? '0' + h : h;
      let m = parseInt(count / 60 % 60) //分钟
      m = m < 10 ? '0' + m : m;
      let s = parseInt(count % 60) //秒
      s = s < 10 ? '0' + s : s;

      // console.log(` ${d}天${h}时${m}分${s}秒 `);
      this.dateRemainder = ` ${d}天${h}时${m}分${s}秒 `

      // 定时器
      clearInterval(this.timeCounter)
      this.timeCounter = setInterval(() => {
        count = count - 1
        this.getCountTime(count)
      }, 1000)
    },
    buy() {
      console.log(1);
    }
  },
  mounted: async function () {
    this.goodList = await this.queryAllSecKillGood(this.QueryVo)
    console.log('goodlist:', this.goodList);
  },
  filters: {
    formatDate: function (value, args) {
      var dt = new Date(value);
      if (args == 'yyyy-M-d') {// yyyy-M-d
        let year = dt.getFullYear();
        let month = dt.getMonth() + 1;
        let date = dt.getDate();
        return `${year}-${month}-${date}`;
      } else if (args == 'yyyy-M-d H:m:s') {// yyyy-M-d H:m:s
        let year = dt.getFullYear();
        let month = dt.getMonth() + 1;
        let date = dt.getDate();
        let hour = dt.getHours();
        let minute = dt.getMinutes();
        let second = dt.getSeconds();
        return `${year}-${month}-${date} ${hour}:${minute}:${second}`;
      } else if (args == 'yyyy-MM-dd') {// yyyy-MM-dd
        let year = dt.getFullYear();
        let month = (dt.getMonth() + 1).toString().padStart(2, '0');
        let date = dt.getDate().toString().padStart(2, '0');
        return `${year}-${month}-${date}`;
      } else {// yyyy-MM-dd HH:mm:ss
        let year = dt.getFullYear();
        let month = (dt.getMonth() + 1).toString().padStart(2, '0');
        let date = dt.getDate().toString().padStart(2, '0');
        let hour = dt.getHours().toString().padStart(2, '0');
        let minute = dt.getMinutes().toString().padStart(2, '0');
        let second = dt.getSeconds().toString().padStart(2, '0');
        return `${year}-${month}-${date} ${hour}:${minute}:${second}`;
      }
    }
  }
};
</script>

<style lang="less">
.main {
  overflow: hidden;
  width: 100%;
  height: 100vh;

}

.middleDiv {
  position: relative;
  top: 2.5%;
  left: 8%;
  width: 84%;
  height: 95%;
  box-shadow: 5px 5px 20px gray;

  .logo {
    position: absolute;
    width: 12%;
    left: 10%;
    height: 14%;
    top: 4%;
  }

  .queryDiv {
    position: absolute;
    left: 22%;
    width: 68%;
    height: 14%;
    top: 4%;

    .queryDiv-box {
      margin: auto;
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      width: 90%;
      height: 40%;
      border: #aa0000 2px solid;
      border-radius: 10px;

      .queryDiv-input {
        position: absolute;
        outline: none;
        border: none;
        height: 90%;
        width: 82%;
        left: 2%;
        font-size: 20px;
      }

      .queryDiv-btn {
        position: absolute;
        height: 100%;
        width: 12%;
        right: 0;
        color: #fff;
        background-color: #aa0000;
        // border: #aa0000 1px solid;
        cursor: pointer;
        transition: all 0.3s;
        text-align: center;
        font-size: 20px;
        line-height: 250%;
      }

      .queryDiv-btn:hover {
        background-color: #c90000;
      }
    }
  }

  .goodListDiv {
    position: absolute;
    top: 20%;
    left: 10%;
    width: 80%;
    height: 72%;
    overflow-y: scroll;
    overflow-x: hidden;

    .goodDiv {
      position: relative;
      width: 100%;
      height: 160px;
      // margin-top: 10px;

      .good-img {
        position: absolute;
        // width: 9%;
        width: 110px;
        height: 110px;
        left: 1%;
        top: 14%;
        border: #5a5a5a 1px solid;

        .img-css {
          position: relative;
          width: 100%;
          height: 110px;
        }
      }

      .good-name {
        position: absolute;
        width: 20%;
        height: 40%;
        left: 11%;
        top: 14%;
        overflow: hidden;
      }

      .good_stock {
        position: absolute;
        left: 11%;
        top: 70%;
        width: 20%;
        height: 15%;
      }

      .good_pay_num {
        position: absolute;
        left: 43%;
        top: 18%;
        width: 15%;
        height: 15%;
      }

      .good_price {
        position: absolute;
        left: 70%;
        top: 14%;
        width: 12%;
        height: 15%;
      }

      .jump {
        position: absolute;
        left: 90%;
        top: 14%;
        width: 10%;
        height: 15%;
        color: #217eb4;
        cursor: pointer;
        transition: all 0.5s;
      }

      .jump:hover {
        color: #42a5df;
      }
    }

    .goodLine {
      position: absolute;
      border: #b1b1b1 1px solid;
      // border: #f30505 1px solid;
      border-top: none;
      width: 98%;
      left: 1%;
    }
  }

  .pageBlock {
    position: absolute;
    top: 93%;
    left: 30%;
  }
}

.DetailDiv {
  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  position: absolute;
  width: 90%;
  height: 90%;

  .jumpBack {
    width: 8%;
    height: 35px;
    font-size: 22px;
    text-align: center;
    line-height: 35px;
    position: absolute;
    color: #830000;
    transition: all 0.5s;
    right: 0;
    cursor: pointer;
  }

  .jumpBack:hover {
    color: #c50000;
  }

  .detailDiv__inner {
    position: relative;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    width: 70%;
    height: 100%;
    overflow-y: scroll;
    overflow-x: hidden;

    .detail-img {
      position: absolute;
      width: 450px;
      height: 450px;
      border: #5a5a5a 1px solid;

      .detail-img-css {
        position: absolute;
        width: 100%;
        height: 100%;
      }
    }

    .detail-name {
      position: absolute;
      top: 1%;
      left: 46%;
      width: 50%;
      height: 10%;
      overflow: hidden;
    }

    .detail-price {
      position: absolute;
      top: 13%;
      left: 46%;
      width: 30%;
      height: 10%;
      overflow: hidden;
    }

    .detail-stock {
      position: absolute;
      right: 4%;
      top: 13%;
      width: 15%;
      height: 10%;
      text-align: right;
      font-size: 16px;
      line-height: 28px;
    }

    .detail-time {
      position: absolute;
      top: 25%;
      left: 46%;
      width: 50%;
      height: 15%;
      overflow: hidden;
      font-size: 21px;
      line-height: 30px;
      color: #127dd4;
      // text-align: right;
    }

    .detail-detail {
      position: absolute;
      top: 56%;
      width: 100%;
      height: 30%;
      overflow: hidden;
      line-height: 25px;
      color: #868686;
    }
  }

  .detail-status {
    position: absolute;
    height: 10%;
    width: 70%;
    margin: auto;
    bottom: 0;
    top: 90%;
    left: 0;
    right: 0;

    // background: #000;
    .d-status {
      position: absolute;
      left: 0;
      width: 55%;
      height: 100%;
      font-size: 35px;
      text-align: center;
      line-height: 80px;
    }

    .ing {
      box-shadow: 2px 2px 20px #a54e4e;
      background-color: #ca3535;
      color: #fff;
      border-radius: 10px;
    }

    .after{
      box-shadow: 2px 2px 20px #6d6d6d;
      background-color: #696969;
      color: #fff;
      border-radius: 10px;
    }

    .d-btn {
      position: absolute;
      right: 0;
      width: 40%;
      height: 100%;
      box-shadow: 2px 2px 20px #57cefd;
      background-color: #25ade2;
      color: #fff;
      border-radius: 10px;
      font-size: 35px;
      text-align: center;
      line-height: 80px;
      cursor: pointer;
    }
  }
}

/* 定义滚动条样式 */
::-webkit-scrollbar {
  width: 4px;
  height: 6px;
  background-color: rgba(240, 240, 240, 0);
}

/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 0px rgba(240, 240, 240, 0);
  border-radius: 10px;
  background-color: rgba(240, 240, 240, 0);
}

/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 0px rgba(240, 240, 240, 0.5);
  background-color: rgba(158, 158, 158, 0.397);
}


/**element分页组件改变颜色 */
.el-pager li:hover {
  color: #aa0000;
}

.el-pager li.active {
  color: #aa0000;
}

.el-pagination .btn-next:hover,
.el-pagination .btn-prev:hover {
  color: #aa0000;
}

.el-pagination__sizes .el-input .el-input__inner:hover {
  border: #aa0000 1px solid;
}

.el-select .el-input__inner:focus {
  border: #aa0000 1px solid;
}

.el-range-editor.is-active,
.el-range-editor.is-active:hover,
.el-select .el-input.is-focus .el-input__inner {
  border: #aa0000 1px solid;
}

.el-select-dropdown__item.selected {
  color: #aa0000;
}

.el-input.is-active .el-input__inner,
.el-input__inner:focus {
  border: #aa0000 1px solid;
}

/**fade fade2 渐变效果 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}

.fade-leave-to,
.fade-enter {
  opacity: 0
}

.fade-enter-to,
.fade-leave {
  opacity: 1
}



.fade2-enter-active,
.fade2-leave-active {
  transition: opacity 0.5s;
}

.fade2-enter-to,
.fade2-leave {
  opacity: 1
}

.fade2-leave-to,
.fade2-enter {
  opacity: 0
}
</style>