import { createRouter, createWebHistory } from 'vue-router'

import chatRoom from '../view/ChatRoom'

const routes = [
  {
    path: '/',
    component: chatRoom,
  },
]

const router = createRouter({
  history: createWebHistory('/moodchat/'),
  routes
})


export default router