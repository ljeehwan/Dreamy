<template>
  <v-hover v-slot:default="{ hover }">
    <v-card class="ma-auto" color="grey lighten-4" width="450">
      <v-img
        :aspect-ratio="16 / 9"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal display-1"
            style="height: 50%;"
          >
            내용
          </div>
        </v-expand-transition>
      </v-img>
      <v-card-title class="my-2">서와니 100대 때리기</v-card-title>
      <v-card-text>
        <v-row class="my-2">
          <v-btn color="black" text>
            지허니
          </v-btn>
          <v-spacer></v-spacer>
          <v-icon left="left">
            mdi-label
          </v-icon>
          <span class="mt-2 mr-2">
            요리
          </span></v-row
        >
        <v-divider></v-divider>
        <v-row class="mt-3">
          <v-btn color="black" text>
            자세히보기
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn class="mx-2" small text icon="icon">
            <v-icon>mdi-heart</v-icon>
          </v-btn>
          <span class="ma-1">10</span>
          <v-btn class="mx-2" small text icon="icon">
            <v-icon>mdi-comment-outline</v-icon>
          </v-btn>
          <span class="ma-1">22</span>
          <v-btn class="mx-2" small="small" text icon="icon">
            <v-icon>mdi-bookmark</v-icon>
          </v-btn>
          <span class="ma-1">33</span>
        </v-row>
        <!-- <h3 class="display-1 font-weight-light orange--text mb-2">QW cooking utensils</h3>
                    <div class="font-weight-light title mb-2"> Our Vintage kitchen utensils delight any chef.<br> Made of bamboo by hand </div> -->
      </v-card-text>
    </v-card>
  </v-hover>
</template>

<script>
export default {};
</script>

<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.8;
  position: absolute;
  width: 100%;
}

.v-application--wrap {
  min-height: 100vh;
}
</style>
