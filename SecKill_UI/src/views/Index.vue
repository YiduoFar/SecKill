<template>
  <div class="main">
    <!-- <div class="l" @click="hello">{{ data }}</div>
    <div class="l" @click="logout">LOGOUT</div> -->
    <div class="middleDiv ">
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
            <img class="img-css" :src="loadImage(g.good_img)" alt="">
          </div>
          <!-- 名字、标题 -->
          <div class="good-name ">
            <span style="font-weight: bold;">{{g.good_name}}</span>
            <br>
            {{g.good_title}}
          </div>
          <!-- 库存 -->
          <div class="good_stock ">
            库存量: <span style="color:tomato;">{{g.good_stock}}</span> 件
          </div>
          <!-- 付款人数 -->
          <div class="good_pay_num">
            <span style="color:#5a5a5a">{{(g.good_pay_num)}}+ 人付款</span>
          </div>
          <!-- 秒杀价、原价 -->
          <div class="good_price">
            <div style="color:#f30505;font-weight: bold;font-size:17px">秒杀价:{{g.seckill_price}}￥</div>
            <div style="color:#5a5a5a;margin-top: 5px;text-decoration: line-through;">原价:{{g.good_price}}￥</div>
          </div>
          <!-- 跳转详情 -->
          <div class="jump">
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
      QueryVo: {
        current: 1,
        size: 20,
        word: ''
      },
    };
  },
  computed: {
  },
  methods: {
    ...mapActions("good", ["queryAllSecKillGood"]),
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
    }
  },
  mounted: async function () {
    this.goodList = await this.queryAllSecKillGood(this.QueryVo)
    console.log('goodlist:', this.goodList);
  },
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
      .jump{
        position: absolute;
        left: 90%;
        top: 14%;
        width: 10%;
        height: 15%;
        color: #217eb4;
        cursor: pointer;
        transition: all 0.5s;
      }
      .jump:hover{
        color: #217eb4;
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

.el-pager li:hover{
  color: #aa0000;
}

.el-pager li.active{
  color: #aa0000;
}

.el-pagination .btn-next:hover, .el-pagination .btn-prev:hover{
  color: #aa0000;
}

.el-pagination__sizes .el-input .el-input__inner:hover{
  border: #aa0000 1px solid;
}
.el-select .el-input__inner:focus{
  border: #aa0000 1px solid;
}

.el-range-editor.is-active, .el-range-editor.is-active:hover, .el-select .el-input.is-focus .el-input__inner{
  border: #aa0000 1px solid;
}

.el-select-dropdown__item.selected{
  color: #aa0000;
}

.el-input.is-active .el-input__inner, .el-input__inner:focus{
  border: #aa0000 1px solid;
}
// .el-pager li.btn-quicknext:hover, .el-pager li.btn-quickprev:hover{
//   color: #aa0000;
// }
</style>