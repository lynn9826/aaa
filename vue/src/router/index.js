import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/user",
    children:[
      {
        path: 'user',
        name: 'User',
        component:() => import("../views/User")
      },
      {
        path: 'book',
        name: 'Book',
        component:() => import("../views/Book")
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("../views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("../views/Register")
  },
  {
    path: '/person',
    name: 'Person',
    component: () => import("../views/Person")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
