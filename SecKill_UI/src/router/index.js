import Vue from 'vue'
import VueRouter from 'vue-router'
// import axios from 'axios'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/index',
    name: 'Index',
    component: () => import('../views/Index.vue')
  }
]

const router = new VueRouter({
  routes
})


router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/') {
    window.localStorage.removeItem('access-admin')
    next()
  } else {
    next()
  }
})
// } else if (to.path === '/reg') {
//   next()
// }
// } else {
//   let token = JSON.parse(window.localStorage.getItem('access-admin'))
//   if (!token) {
//     next({ path: '/login' })
//   } else {
//     // 校验token合法性
//     axios({
//       url: '/checkToken',
//       method: 'GET',
//       headers: {
//         Authorization: token
//       }
//     }).then((res) => {
//       console.log(res);
//       if (res.data.code === '500212') {
//         console.log('token失效');
//         next({ path: '/login' })
//       }
//     })
//     next()
//   }
// }
// })


export default router
