<template>
<div id="bar">
      <NavBar2/>
  <div v-if="getIsLogined" class="img-container-l">
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
  </div>

    <div v-if="!getIsLogined" class="img-container-l">
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
  computed: {
    getIsLogined() {
      return this.$store.getters["userStore/getIsLogined"];
    },
  },
}
</script>

<style>

</style>

