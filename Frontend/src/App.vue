<template>
  <div id="app">
    <v-app>
      <NavBar/>
      
      <div class="body-component">
        
        <router-view></router-view>
        <Modal/>
        <SnackBar/>
        <Spinner/>
        
        <Upload v-if="getIsLogined"/>
      </div>
    </v-app>
  </div>
</template>


<script>
import "./assets/css/style.css"
import NavBar from '@/components/NavBar.vue'
import Modal from '@/components/Modal.vue'
import SnackBar from '@/components/SnackBar.vue'
import Spinner from '@/components/Spinner.vue'
import Upload from './components/upload.vue'

import { mapGetters } from 'vuex';

export default {
  name: "app",
  components:{
    NavBar,
    Modal,
    SnackBar,
    Spinner,
    Upload,
  },
  computed:{
        ...mapGetters(['getIsLogined'])
    },
};
</script>



<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>