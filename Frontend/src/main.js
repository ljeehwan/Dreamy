import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {router} from './routes'
import store from './vuex/store'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

Vue.use(VueRouter)

// const router = new VueRouter({
//   mode:'history',
//   routes,
// });

window.Kakao.init('d047127b3a3904b500f8dcdf1e91a71d');

new Vue({
  router,
  store,
  vuetify,
  beforeCreate(){
    if(localStorage.getItem("isLogin")=="true")
      this.$store.dispatch("userStore/getUserinfo")
  },
  render: h => h(App)
}).$mount('#app')
