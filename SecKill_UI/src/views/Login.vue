<template>
  <div class="main ">
    <!-- <img src="../assets/img/bg0.jpg" alt="" class="bg"> -->
    <img src="../assets/img/bg.jpg" alt="" class="" style="position:absolute;width: 55%; right:7%;top:7%;" />

    <transition name="fade" :appear="true">
      <div class="middleDiv" v-show="loginOrRegister">
        <div class="loginText">账号登录</div>
        <div class="inputArr">
          <el-input v-model="user.mobile" placeholder="请输入手机号"></el-input>
        </div>
        <div class="inputArr">
          <el-input placeholder="请输入密码" v-model="user.password" show-password></el-input>
        </div>
        <div class="inputArr">
          <el-button type="primary" round style="width: 100%; font-size: 20px" @click="doLogin">登录</el-button>
        </div>
        <div class="tip" @click="changeloginOrRegister()">新用户注册</div>
      </div>
    </transition>
    <transition name="fade2" :appear="true">
      <div class="middleDiv" v-show="!loginOrRegister">
        <div class="loginText">注册用户</div>
        <div class="inputArr">
          <el-input v-model="user2.mobile" placeholder="请输入手机号"></el-input>
        </div>
        <div class="inputArr">
          <el-input placeholder="请输入密码" v-model="user2.password" show-password></el-input>
        </div>
        <div class="inputArr">
          <el-input placeholder="请再输入密码" v-model="user2.password2" show-password></el-input>
        </div>
        <div class="inputArr">
          <el-button type="primary" round style="width: 100%; font-size: 20px" @click="doReg">注册</el-button>
        </div>
        <div class="tip" @click="changeloginOrRegister()">已有账户，去登录</div>
      </div>
    </transition>
  </div>
</template>
  
<script>
import { mapActions, mapState } from "vuex";
import ElementUI from 'element-ui'
export default {
  data() {
    return {
      loginOrRegister: true,
      user: {
        mobile: "13168162412",
        password: "123456",
      },
      user2: {
        mobile: "13042001124",
        password: "123456",
        password2: "123456",
      },
    };
  },
  computed: {
    ...mapState(["salt"]),
  },
  methods: {
    ...mapActions("login", ["login"]),
    ...mapActions("login", ["register"]),
    /**
     * 登录
     */
    async doLogin() {
      const admin = { ...this.user }
      if (admin.mobile == '' || admin.password == '') {
        ElementUI.Message.error('请输入完整的账号密码')
        return
      }
      admin.password = this.$md5(
        this.salt.charAt(0) +
        this.salt.charAt(2) +
        this.user.password +
        this.salt.charAt(5) +
        this.salt.charAt(4)
      );
      console.log('md5', admin.password);
      const { code, obj } = await this.login(admin)
      if (code === 200) {
        this.user.mobile = this.user.password = ''
        localStorage.setItem("access-admin", JSON.stringify(obj.token));
        ElementUI.Message.success('登录成功！')
        this.$router.replace({ path: "/index" });
      }
    },
    /**
     * 注册
     */
    async doReg() {
      const admin = { ...this.user2 }
      const adminPrev = { ...this.user2 }
      if (admin.password !== admin.password2) {
        ElementUI.Message.error('请输入两次一致的密码')
        return;
      }
      admin.password = this.$md5(
        this.salt.charAt(0) +
        this.salt.charAt(2) +
        this.user2.password +
        this.salt.charAt(5) +
        this.salt.charAt(4)
      );
      const admin2 = {
        mobile: admin.mobile,
        password: admin.password,
      };
      let code = await this.register(admin2)
      if (code === 200) {
        ElementUI.Message.success('注册成功！')
        this.user.mobile = adminPrev.mobile;
        this.user.password = adminPrev.password;
        this.changeloginOrRegister();
      }
    },
    /**
     * 改变 登录或注册 表单
     */
    changeloginOrRegister() {
      this.loginOrRegister = !this.loginOrRegister;
    }
  },
  mounted: function () {
    console.log(this.user);
  },
};
</script>
  


<style lang="less" scoped>
// <style lang="less">
.main {
  overflow: hidden;
  width: 100%;
  height: 100vh;

  .bg {
    width: 100%;
    height: 100%;
    transform: scale(1.1);
  }
}

.middleDiv {
  height: 500px;
  width: 400px;
  text-align: center;
  /*让div内部文字居中*/
  background-color: #fffffff8;
  border-radius: 20px;
  box-shadow: 5px 5px 20px #c4c4c4;
  margin: auto;
  position: absolute;
  top: -150px;
  left: -900px;
  right: 0;
  bottom: 0;
  opacity: 1;

  .loginText {
    margin-bottom: 45px;
    margin-top: 45px;
    font-size: 25px;
  }

  .inputArr {
    position: relative;
    width: 80%;
    left: 10%;
    margin-top: 40px;
    margin-bottom: 40px;
  }

  .tip {
    position: absolute;
    width: 150px;
    margin-left: 240px;
    top: 450px;
    font-size: 15px;
    color: #afafaf;
    cursor: pointer;
    transition: color 0.5s;
  }

  .tip:hover {
    color: #b60c11;
  }
}

.el-input.isactive .el-input__inner,
::v-deep .el-input__inner:focus {
  border-color: #d40007;
}

::v-deep.el-input__inner {
  border-color: #d40007;
  // outline: none;
}

.el-button--primary {
  color: #FFF;
  background-color: #b60c11;
  border-color: #d40007;
}

.el-button--primary:hover {
  background-color: #df0008;
  border-color: #d40007;
}


.fade-enter-active,
.fade-leave-active {
  transition: opacity 1s;
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