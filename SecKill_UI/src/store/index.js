import Vue from 'vue'
import Vuex from 'vuex'
import login from './modules/login'
import good from './modules/good'
import seckill from './modules/seckill'
import order from './modules/order'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    salt: "1a2b3c4d"
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    login, good, seckill, order
  }
})
