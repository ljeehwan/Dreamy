<template>
   <v-btn  class="btn"
      fab
      dark
      bottom
      right
      color="indigo"
      elevation="8"
    >
      <v-icon dark>
        mdi-plus
      </v-icon>
    </v-btn>
</template>

<script>
//카테고리 1: 운동, 2:음식 3: 여행, 4:학슴, 5:문화/생활, 6:기타
// date_type: 1:일, 2:주 3:개월
export default {

}
</script>

<style>
.btn{
    position: fixed;
    right:30px;
    bottom:30px
}
</style>