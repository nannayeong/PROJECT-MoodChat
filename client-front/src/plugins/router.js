import { createRouter, createWebHistory } from 'vue-router'

import chat from '../view/Chat'

const routes = [
  {
    path: '/',
    component: chat,
  },
]

const router = createRouter({
  history: createWebHistory('/awd/'),
  routes
})


export default router