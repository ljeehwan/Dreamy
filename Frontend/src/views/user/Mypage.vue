<template>
  <div>
      <div class="user-info">
        <MyInfo v-bind="{info:targetInfo}" />
        
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
  computed: {
    targetInfo() {
      return this.$store.getters.getTargetInfo
    },
  },
  created: function () {
    // 뷰엑스로 받은 이름을 타겟에 넣어서
    const targetName = this.$store.state.targetName
    //1. 스토어에 있는 GET_MEMBER dispatch한다. (네브 바의 마이 페이지 버튼은 어차피 
    // 자기 자신이기 때문에 이름을 내이름을 내려보내줌)
    // 응답을 변수에 담아서 저장
    this.$store.dispatch('GET_MEMBER', targetName)
    //vuex에서 정보 가져오기
    // 저장한 변수에서 data의 email, username, phone으로 이름을 할당해주고 브라우저에 출력해준다
    // 2. 응답받은(위에서 출력한) 유저의 name과 로그인한 유저의 이름이 같으면 수정 버튼 활성화
    // 타겟과 로그인한 이름이 일치하면 수정 버튼을 보여준다.
    if (targetName === this.$store.getters.getUsername) {
      console.log('이름이 일치합니당')
      this.$store.commit('MYSELF')
      console.log(`뷰엑스의 불린값 ${this.$store.getters.getMyself}`)
    } else {
      this.$store.commit('NOT_ME')
    }
  },
  

}
</script>

<style>

</style>