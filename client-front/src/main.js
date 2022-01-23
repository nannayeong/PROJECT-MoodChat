import { createApp } from 'vue'
import App from './App.vue'
import router from './plugins/router'

import('./assets/css/app.scss')

const app = createApp(App)

app.use(router).mount('#app')
