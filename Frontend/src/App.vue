<template>
<v-app>
  <div id="app">
    <NavBar/>
    <div class="body-component">
       <router-view></router-view>
    </div>
  </div>
</v-app>
</template>


<script>
import "./assets/css/style.css"
import NavBar from '@/components/NavBar.vue'
export default {
  name: "app",
  components:{
    NavBar
  }
};
</script>



<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>