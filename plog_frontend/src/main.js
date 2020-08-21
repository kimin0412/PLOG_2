import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import VuetifyDialog from 'vuetify-dialog'
import 'vuetify-dialog/dist/vuetify-dialog.css'

import Carousel3d from 'vue-carousel-3d'

import VeeValidate from 'vee-validate';
import VuePageTransition from 'vue-page-transition'

import store from './store';
import Vuex from 'vuex';
Vue.config.productionTip = false


Vue.use(VeeValidate);
Vue.use(Vuex);

Vue.use(VuetifyDialog, {
  context: {
    vuetify
  }
})

Vue.use(Carousel3d)
Vue.use(VuePageTransition)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
