<template>
  <div id="app">
    <div class="nav">
      <div class="nav-left">
        <div class="logo">
        </div>
      </div>

    <div class="nav-center">
      <div class="search-container">
        <input type="text" placeholder="Search">
        <div class="search-button">
        </div>
      </div>
    </div>

    <div class="nav-right">
      <p><router-link :to="{name:'Ranking'}">Ranking</router-link></p>
      <p><router-link :to="{name:'Bucket'}">Bucket-list</router-link></p>
      <p><router-link :to="{name:'Challenge'}">Challenge</router-link></p>
      <p><router-link :to="{name:'myPage'}">myPage</router-link></p>
      <p><router-link :to="{name:'Join'}">Join</router-link></p>
      <p><router-link :to="{name:'Login'}">login</router-link></p>
      <p>log-out</p>
    </div>
  </div>
    <div class="body-component">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import "./assets/css/style.css"
export default {
  name: "app"
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
