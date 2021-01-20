<template>
  <v-row>
      <v-col sm="3">
          <FeedMain/>
      </v-col>
      <v-col sm="3">
          <FeedMain/>
      </v-col>
      <v-col sm="3">
          <FeedMain/>
      </v-col>
        <v-col sm="3">
          <FeedMain/>
      </v-col>
  </v-row>
</template>

<script>
import FeedMain from './feedMain.vue'
export default {
    components:{
    FeedMain
  }
}
</script>

<style>

</style>