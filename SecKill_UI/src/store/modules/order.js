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
        queryOrderById(state, orderId) {
            console.log('axios-orderId:', orderId);
            return axios({
                url: '/queryOrderById',
                method: 'POST',
                data: qs.stringify({
                    orderId: orderId
                })
            })
        }
    },
    modules: {
    }
})