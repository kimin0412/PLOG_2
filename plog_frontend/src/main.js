import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import VuetifyDialog from 'vuetify-dialog'
import 'vuetify-dialog/dist/vuetify-dialog.css'

import VeeValidate from 'vee-validate';

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

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
