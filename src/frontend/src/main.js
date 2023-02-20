import Vue from 'vue'
import { createPinia, PiniaVuePlugin } from 'pinia'

import App from './App.vue'
import router from './router'
import Vuetify from 'vuetify'
import vuetify from './plugins/vuetify'
import 'vuetify/dist/vuetify.min.css'
import '@mdi/font/css/materialdesignicons.css'
import VueRouter from 'vue-router'
import './assets/main.css'


Vue.use(PiniaVuePlugin)
const pinia = createPinia()

Vue.use(Vuetify)
Vue.use(VueRouter)

new Vue({
  router,
  pinia,
  vuetify,
  render: (h) => h(App)
}).$mount('#app')
