import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
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
        doSecKill(state, goodId) {
            return axios({
                url: '/doSecKill',
                method: 'POST',
                // headers:{
                //     "Content-Type":"application/json"
                // },
                data: qs.stringify({
                    goodId: goodId
                })
            })
        }
    },
    modules: {
    }
})