<template>
  <div id="app">
    <v-app>
      
      <!--<NavBar/>-->
 
        <router-view></router-view>
        <Modal/>
        <SnackBar/>
        <Spinner/>
      <v-footer></v-footer>
    </v-app>
  </div>
</template>


<script>
import "./assets/css/style.css"
//import NavBar from '@/components/NavBar.vue'
import Modal from '@/components/Modal.vue'
import SnackBar from '@/components/SnackBar.vue'
import Spinner from '@/components/Spinner.vue'

export default {
  name: "app",
  components:{
  //  NavBar,
    Modal,
    SnackBar,
    Spinner,
  },
 
};
</script>



<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>