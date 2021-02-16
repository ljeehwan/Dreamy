<template>
   <v-sheet max-height="270" width="460"> 
       <div class="px-5"  v-if="getPartiTotal==0">
           <v-row class="my-8 align-center justify-center">현재 참가자가 없습니다!</v-row>
       </div>
       <div class="px-5" v-else>
           <p class="my-8 align-center justify-center">현재 {{getPartiTotal}}명이 해당 <b>{{type|typeFilter}}</b>를 함께하고 있습니다!</p>
           <v-row class="my-5 align-center justify-center">
           <div class="ma-5" v-for="part in getParticipate" v-bind:key="part.uid">
               <v-tooltip bottom>
                    <template v-slot:activator="{ on }">
                        <v-avatar v-on="on" size="60">
                            <!-- <span> -->
                                <v-btn text
                                class="my-8 align-center justify-center"
                                @click="moveToPage(part.uid)">
                                    <img alt :src="part.profileUrl" />
                                </v-btn>
                            <!-- </span> -->
                        </v-avatar>
                    </template>
                    <span>{{part.name}}</span>
                </v-tooltip>
           </div>
           </v-row>
       </div>
   </v-sheet>
</template>

<script>
import {router} from "@/routes.js"


export default {
props:{
    type:Number
},
computed: {
    getParticipate() {
      return this.$store.getters["boardStore/getParticipate"];
    },
     getPartiTotal() {
      return this.$store.getters["boardStore/getPartiTotal"];
    },
  },
  methods: {
      moveToPage(uid) {
        this.$emit('exitModal')
        router.push(`/user/mypage/${uid}`)
        this.$store.dispatch("userStore/GET_MEMBER", uid)
      },
  },
  filters: {
    typeFilter: function(num) {
      if(num==1){
          return "버킷리스트"
      }else if(num==2){
          return "챌린지"
      }
    },
  },
}
</script>

<style>

</style>