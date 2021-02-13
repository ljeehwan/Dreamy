<template>
   <v-sheet height="270" width="450"> 
       <div class="my-2" v-if="getPartiTotal==0">
           현재 참가자가 없습니다!
       </div>
       <div v-else>
           
       </div>
   </v-sheet>
</template>

<script>
export default {

computed: {
    getParticipate() {
      return this.$store.getters["boardStore/getParticipate"];
    },
     getPartiTotal() {
      return this.$store.getters["boardStore/getPartiTotal"];
    },
  },
}
</script>

<style>

</style>