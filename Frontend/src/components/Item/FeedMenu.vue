<template>
<div>
   <v-row class="mx-10 mb-5 mt-10 align-center justify-center">
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" outlined icon @click="onclick(0)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>All</p>
      </div>
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" fab icon @click="onclick(1)"
          ><v-icon>mdi-weight-lifter</v-icon></v-btn
        >
        <p>운동</p>
      </div>
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" fab icon @click="onclick(2)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>음식</p>
      </div>
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" fab icon  @click="onclick(3)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>여행</p>
      </div>
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" fab icon  @click="onclick(4)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>학습</p>
      </div>
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" fab icon  @click="onclick(5)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>문화/생활</p>
      </div>
      <div class="mx-3">
        <v-btn id="menu" height="58" width="58" fab icon  @click="onclick(6)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>기타</p>
      </div>
    </v-row>
      <v-divider></v-divider>
</div>
</template>

<script>
export default {
    methods:{
        onclick(type){
            this.$emit("clickType",type);
        }
    }
}
</script>

<style scoped>
#menu {
  border: solid 1px currentColor;
}
</style>