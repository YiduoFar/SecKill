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
      <div class="goodListDiv l">
        <!-- 表头 -->
        <!-- <div class="list-head">
        </div> -->
        <div class="goodDiv" v-for="(good, index) in goodList" :key="index">
        </div>
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
      }
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
    }
  },
  mounted: function () {
    this.goodList = this.queryAllSecKillGood(this.QueryVo)
    // this.goodList = this.queryAllSecKillGood({queryVo: this.QueryVo})
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
  }

  .pageBlock{
    position: absolute;
    top: 93%;
    left: 30%;
  }
}
</style>