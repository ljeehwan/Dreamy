<template>
  <div>
    <v-bottom-navigation
    v-model="value" :background-color="color"
    dark shift >

    <v-btn class="mx-auto" @click="onClick('getMyBoardList')">
      <span>내 드림</span>

      <v-icon>mdi-image</v-icon>
    </v-btn>

    <v-btn class="mx-auto" @click="onClick('getMyParticipateList')">
      <span>참여중 드림</span>

      <v-icon>mdi-book</v-icon>
    </v-btn>

    <!-- 성공, 완료한 목록 -->
    <v-btn class="mx-auto" @click="onClick('getMySuccessList')">
      <span>성공한 드림</span>

      <v-icon>mdi-check-bold</v-icon>
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
       menus: 'getMyBoardList',
    }
  },
  computed: {
      color () {
        switch (this.value) {
          case 0: return 'amber'
          case 1: return '#EF5350'
          case 2: return 'teal'
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