import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import qs from 'qs'
import md5 from 'js-md5'
import './assets/css/global.css'
import './globle/globleApi'

const TOKEN_INVALID = '认证失败, 请重新登录';
const NETWORK_ERROR = '网络请求异常，请稍后再试'

Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.prototype.$qs = qs
Vue.prototype.$md5 = md5
Vue.prototype.$img_src = 'http://localhost:8088/static'
// Vue.prototype.global = global // 不生效
axios.defaults.baseURL = "http://localhost:8088"

Vue.config.productionTip = false

//请求拦截
axios.interceptors.request.use((req) => {
    //TODO: 用正则 匹配更多的开放接口
    if (req.url == '/login' || req.url == '/reg') {
        return req
    }
    const headers = req.headers;
    if (!headers.Authorization) {
        headers.Authorization = JSON.parse(window.localStorage.getItem("access-admin"));
    }
    return req;
})

//响应拦截
axios.interceptors.response.use((res) => {
    //TODO:
    const { code, message, obj } = res.data;
    console.log('code-->', code);
    console.log('message-->', message);
    console.log('data-->', obj);
    if (code === 200) {
        if (res.config.url == '/login') {
            return {code, obj}
        }
        if (res.config.url == '/reg') {
            return code
        }
        return obj;
    } else if (code === 500213) {
        ElementUI.Message.error(TOKEN_INVALID)
        setTimeout(() => {
            router.replace({ path: '/' });
        }, 1500)
        return Promise.reject(TOKEN_INVALID);
    } else {
        ElementUI.Message.error(message || NETWORK_ERROR)
        if (code == 403002) {
            setTimeout(() => {
                router.replace({ path: '/' });
            }, 1500)
        }
        return Promise.reject(message || NETWORK_ERROR);
    }
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')



