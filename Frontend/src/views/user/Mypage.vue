<template>
  <div>
    <h3>MY PAGE</h3>
      <div class="user-info">
        <UserInfo/>
        <!-- 그리드 쓸때 
        https://vuetifyjs.com/en/components/images/#height
        Misc grid 참고하기 -->
      </div>
  </div>
</template>

<script>
import UserInfo from '@/components/UserInfo.vue'
export default {
  name: 'Mypage',
  components: {
    UserInfo,
  },
}
</script>

<style>

</style>