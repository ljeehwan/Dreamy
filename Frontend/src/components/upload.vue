<template>
   <v-btn  class="btn"
      fab
      dark
      bottom
      right
      color="indigo"
      elevation="8"
      @click="dialog = !dialog"
    >
      <v-icon dark>
        mdi-plus
      </v-icon>
      <v-dialog v-model="dialog" max-width="800" height="400">
        <v-card>
            <v-card-text>
              <v-text-field>등록</v-text-field>
              <small class="grey--text">* This doesn't actually save.</small>
            </v-card-text>
            <v-card-text>
              <v-text-field>머시가</v-text-field>
              <small class="grey--text">* This doesn't actually save.</small>
            </v-card-text>
                        <v-card-text>
              <v-text-field>머시기</v-text-field>
              <small class="grey--text">* This doesn't actually save.</small>
            </v-card-text>
                        <v-card-text>
              <v-text-field>머시기</v-text-field>
              <small class="grey--text">* This doesn't actually save.</small>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn
                text
                color="primary"
                @click="dialog = false"
              >
                Submit
              </v-btn>
            </v-card-actions>
          </v-card>
    </v-dialog>
    </v-btn>
</template>

<script>
//카테고리 1: 운동, 2:음식 3: 여행, 4:학슴, 5:문화/생활, 6:기타
// date_type: 1:일, 2:주 3:개월
export default {
  data(){
    return{
      dialog:false,
  }
}
}
</script>

<style>
.btn{
    position: fixed;
    right:30px;
    bottom:30px
}
</style>