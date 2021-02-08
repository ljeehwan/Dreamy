<template>
  <div>
    <v-bottom-navigation
    v-model="value" :background-color="color"
    dark shift >

    <v-btn class="mx-auto" @click="onClick('myFeed')">
      <span>내 피드</span>

      <v-icon>mdi-image</v-icon>
    </v-btn>

    <v-btn class="mx-auto" @click="onClick('participated')">
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
      this.$emit('myMenu', menu)
      // console.log(menu)
    },
  },
}
</script>

<style coped>

</style>