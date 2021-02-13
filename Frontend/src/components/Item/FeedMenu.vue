<template>
  <div>
    <v-row class="mx-10 mb-5 mt-10 align-center justify-center">
      <div class="mx-5" v-if="select === 0">
        <v-btn
          class="menu"
          id="allsel"
          height="58"
          width="58"
          fab
          icon
          ref="default"
          style="background-color: #40b2a8;
          border-color: white;
          color: white;"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>All</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="all"
          height="58"
          width="58"
          fab
          icon
          ref="default"
          @click="onclick(0)"
          ><v-icon>mdi-arrange-send-backward</v-icon></v-btn
        >
        <p>All</p>
      </div>
      <div class="mx-5" v-if="select === 1">
        <v-btn
          class="menu"
          id="sport"
          height="58"
          width="58"
          fab
          icon
          style="background-color: #b39ddb;
  border-color: white;
  color: white;"
          ><v-icon>mdi-weight-lifter</v-icon></v-btn
        >
        <p>운동</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="sport"
          height="58"
          width="58"
          fab
          icon
          @click="onclick(1)"
          ><v-icon>mdi-weight-lifter</v-icon></v-btn
        >
        <p>운동</p>
      </div>
      <div class="mx-5" v-if="select === 2">
        <v-btn
          class="menu"
          id="food"
          height="58"
          width="58"
          fab
          icon
          style="background-color: #f48fb1;
  border-color: white;
  color: white;"
          ><v-icon>mdi-food</v-icon></v-btn
        >
        <p>음식</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="food"
          height="58"
          width="58"
          fab
          icon
          @click="onclick(2)"
          ><v-icon>mdi-food</v-icon></v-btn
        >
        <p>음식</p>
      </div>
      <div class="mx-5" v-if="select === 3">
        <v-btn
          class="menu"
          id="travel"
          height="58"
          width="58"
          fab
          icon
          style="background-color: #90caf9;
  border-color: white;
  color: white;"
          ><v-icon>mdi-airplane</v-icon></v-btn
        >
        <p>여행</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="travel"
          height="58"
          width="58"
          fab
          icon
          @click="onclick(3)"
          ><v-icon>mdi-airplane</v-icon></v-btn
        >
        <p>여행</p>
      </div>
      <div class="mx-5" v-if="select === 4">
        <v-btn
          class="menu"
          id="skill"
          height="58"
          width="58"
          fab
          icon
          style="background-color: #ffcc80;
  border-color: white;
  color: white;"
          ><v-icon>mdi-book-open-page-variant-outline</v-icon></v-btn
        >
        <p>학습</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="skill"
          height="58"
          width="58"
          fab
          icon
          @click="onclick(4)"
          ><v-icon>mdi-book-open-page-variant-outline</v-icon></v-btn
        >
        <p>학습</p>
      </div>
      <div class="mx-5" v-if="select === 5">
        <v-btn
          class="menu"
          id="lifestyle"
          height="58"
          width="58"
          fab
          icon
          style="background-color: #81c784;
  border-color: white;
  color: white;"
          ><v-icon>mdi-palette-swatch</v-icon></v-btn
        >
        <p>문화/생활</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="lifestyle"
          height="58"
          width="58"
          fab
          icon
          @click="onclick(5)"
          ><v-icon>mdi-palette-swatch</v-icon></v-btn
        >
        <p>문화/생활</p>
      </div>
      <div class="mx-5" v-if="select===6">
        <v-btn
          class="menu"
          id="etc"
          height="58"
          width="58"
          fab
          icon
          style="background-color: #80deea;
  border-color: white;
  color: white;"
          ><v-icon>mdi-dots-horizontal</v-icon></v-btn
        >
        <p>기타</p>
      </div>
      <div class="mx-5" v-else>
        <v-btn
          class="menu"
          id="etc"
          height="58"
          width="58"
          fab
          icon
          @click="onclick(6)"
          ><v-icon>mdi-dots-horizontal</v-icon></v-btn
        >
        <p>기타</p>
      </div>
    </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      select: null,
    };
  },
  mounted() {
    this.select = 0;
    this.$emit("clickType", this.select);
  },
  watch: {
    select() {
      return this.select;
    },
  },
  methods: {
    onclick(type) {
      this.select = type;
      this.$emit("clickType", this.select);
    },
  },
};
</script>

<style scoped>
.menu {
  border: solid 1px currentColor;
}
#all:hover,
#all:active,
#all:visited,
#all:focus {
  background-color: #40b2a8;
  border-color: white;
  color: white;
}
#sport:hover,
#sport:active,
#sport:visited,
#sport:focus {
  background-color: #b39ddb;
  border-color: white;
  color: white;
}
#food:hover,
#food:active,
#food:visited,
#food:focus {
  background-color: #f48fb1;
  border-color: white;
  color: white;
}
#travel:hover,
#travel:active,
#travel:visited,
#travel:focus {
  background-color: #90caf9;
  border-color: white;
  color: white;
}
#skill:hover,
#skill:active,
#skill:visited,
#skill:focus {
  background-color: #ffcc80;
  border-color: white;
  color: white;
}
#lifestyle:hover,
#lifestyle:active,
#lifestyle:visited,
#lifestyle:focus {
  background-color: #81c784;
  border-color: white;
  color: white;
}
#etc:hover,
#etc:active,
#etc:visited,
#etc:focus {
  background-color: #80deea;
  border-color: white;
  color: white;
}
p {
  margin-top: 6px;
  font-size: 14px;
}
</style>
