import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';

import VeeValidate from 'vee-validate';

import store from './store';
import Vuex from 'vuex';

Vue.config.productionTip = false

Vue.use(VeeValidate);
Vue.use(Vuex);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
