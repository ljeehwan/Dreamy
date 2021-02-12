<template>
  <div>
    <v-bottom-navigation
    v-model="value" :background-color="color"
    dark shift >

    <v-btn class="mx-auto" @click="onClick('getMyBoardList')">
      <span>내 피드</span>

      <v-icon>mdi-image</v-icon>
    </v-btn>

    <v-btn class="mx-auto" @click="onClick('getMyParticipateList')">
      <span>참여중</span>

      <v-icon>mdi-book</v-icon>
    </v-btn>

    </v-bottom-navigation>    
  </div>
</template>

<script>
export default {
  name: 'MypageMenu',
  data: function () {
    return {
       value: 0,
       menus: null,
    }
  },
  computed: {
      color () {
        switch (this.value) {
          case 0: return 'teal'
          case 1: return 'amber'
          default: return 'blue-grey'
        }
      },
  },
  methods: {
    onClick(menu) {
      this.menus = menu
      this.$emit('myMenu', this.menus)
    },
  },
  mounted() {
    this.menus = 'getMyBoardList'
    this.$emit("myMenu", this.menus)
  },
  watch: {
    menus() {
      return this.menus
    },
  },
}
</script>

<style coped>

</style>