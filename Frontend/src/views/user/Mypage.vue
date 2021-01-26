<template>
  <div>
      <div class="user-info">
        <MyInfo v-bind="name"/>
        <!-- 그리드 쓸때 
        https://vuetifyjs.com/en/components/images/#height
        Misc grid 참고하기 -->
      </div>
  </div>
</template>

<script>
import MyInfo from '@/components/MyInfo.vue'
export default {
  name: 'Mypage',
  components: {
    MyInfo,
  },
  props: {
    name: {type: String}
  },
  

}
</script>

<style>

</style>