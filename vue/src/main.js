import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'




import './assets/global.css'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app =createApp(App).use(store).use(router).use(ElementPlus,{  size: 'small'}).mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
