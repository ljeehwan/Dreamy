<template>
<div id="bar">
      <NavBar2/>

  <div class="img-container-l">

    <!-- <div class="logo">
      <div class="header-container">
        <div class="content">
          <h2>DREAMY</h2>
          <h2>DREAMY</h2>
        </div>
      </div>
    </div> -->
    <router-link to="/user/join">
      <div id=container-l>
      make 
      <div id=flip-l>
        <div><div>friends</div></div>
        <div><div>bucket list</div></div>
        <div><div>challenge</div></div>
      </div>
      now !
      </div>
      <p class="p-l">|| DREAMY ||</p>
    
  </router-link>
  </div>
  </div>
</template>

<script>
import NavBar2 from '@/components/NavBar2.vue'
export default {
    components:{
    NavBar2,
  },
}
</script>

<style>

</style>

