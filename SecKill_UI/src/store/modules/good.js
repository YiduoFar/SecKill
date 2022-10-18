import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
// import request from '../../../request'
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
        queryAllSecKillGood(state, QueryVo) {
            return axios({
                url: '/queryAllSecKillGood',
                method: 'POST',
                data: qs.stringify(QueryVo)
            })
        }
    },
    modules: {
    }
})