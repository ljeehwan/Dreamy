<template>
  <v-hover v-slot:default="{ hover }">
    <v-card class="my-10 mx-2" color="grey lighten-4" width="350" max-height="400">
      <v-img :aspect-ratio="16 / 9" :src="item.imageUrl">
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal"
            style="height: 50%;"
          >
            {{ item.content }}
          </div>
        </v-expand-transition>
      </v-img>
      <v-row class="mt-3 px-5">
        <v-card-title id="title">{{ item.title }}</v-card-title>
        <v-spacer></v-spacer>
        <span>
          <small>{{ item.writtenDate | dateFilter }}</small>
        </span>
      </v-row>
      <v-card-text>
        <v-row class="my-2">
          <v-btn id="name" color="black" text>
            {{ item.name }}
          </v-btn>
          <v-spacer></v-spacer>
          <v-icon left="left">
            mdi-label
          </v-icon>
          <span id="category" class="mt-2 mr-2">
            {{ item.category | category }}
          </span></v-row
        >
        <v-divider></v-divider>
        <v-row class="mt-3">
          <v-btn id="detail" color="black" text @click="detail_view">
            자세히보기
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn class="mx-2" small icon="icon" color="red">
            <v-icon>mdi-heart</v-icon>
          </v-btn>
          <span class="ma-1">10</span>
          <v-btn class="mx-2" small icon="icon" color="blue">
            <v-icon>mdi-comment-outline</v-icon>
          </v-btn>
          <span class="ma-1">22</span>
          <v-btn class="mx-2" small="small" icon="icon" color="green">
            <v-icon>mdi-bookmark</v-icon>
          </v-btn>
          <span class="ma-1">33</span>
        </v-row>
        <v-dialog v-model="detail" max-width="1100">
          <v-card>
           
          </v-card>
        </v-dialog>
        <!-- <h3 class="display-1 font-weight-light orange--text mb-2">QW cooking utensils</h3>
                    <div class="font-weight-light title mb-2"> Our Vintage kitchen utensils delight any chef.<br> Made of bamboo by hand </div> -->
      </v-card-text>
    </v-card>
  </v-hover>
</template>

<script>
import moment from "moment";
import "moment/locale/ko";

export default {
  data() {
    return {
      detail: false,
    };
  },
  methods: {
    detail_view() {
      this.detail = true;
    },
  },
  props: {
    item: Object,
  },
  filters: {
    category: function(value) {
      if (!value) return "";
      else if (value === 1) {
        return "운동";
      } else if (value === 2) {
        return "음식";
      } else if (value === 3) {
        return "여행";
      } else if (value === 4) {
        return "학습";
      } else if (value === 5) {
        return "문화/생활";
      } else {
        return "기타";
      }
    },
    dateFilter: function(date) {
      // var form=moment(date).format('lll');// 연월일 오전/오후 시간
      return moment(date).fromNow();
    },
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Gothic:wght@700;800&display=swap');
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

#title, #name, #category, #detail{
font-family:  'Nanum Gothic', sans-serif;
}
</style>
