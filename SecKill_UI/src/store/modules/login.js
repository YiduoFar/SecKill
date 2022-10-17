import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import qs from 'qs'

Vue.use(Vuex)

// 导出方式区分index.js
export default ({
  // 声明命名空间
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    async login(state, user) {
      return await axios({
        url: '/login',
        method: 'POST',
        data: qs.stringify(user)
      })
    },
    async register(state, user) {
      return await axios({
        url: '/reg',
        method: 'POST',
        data: qs.stringify(user)
      })
    }
  },
  modules: {
  }
})