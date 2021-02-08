<template>
  <v-content>
      <div class="user-info">
        <MyInfo v-bind="{info:targetInfo}" />
      </div>
      <v-card class="overflow-hidden mx-auto
       my-10" max-width="1155">
        <MypageMenu />
      </v-card>
      <v-layout class="overflow-hidden mx-auto my-10 
       align-center justify-center"
       max-width="1155">
        <MyFeed @myMenu="myMenu"/>
      </v-layout>

  </v-content>
</template>

<script>
import MyInfo from '@/components/MyInfo.vue'
import MypageMenu from '@/components/mypage/MypageMenu.vue'
import MyFeed from '@/components/mypage/MyFeed.vue'

export default {
  name: 'Mypage',
  components: {
    MyInfo,
    MypageMenu,
    MyFeed,
  },
  data: function () {
    return {
      menu: '',
    }
  },
  methods: {
    myMenu: function (menu) {
      this.menu = menu
      console.log(this.menu)
    },
  },
  watch: {
    targetInfo() {
      return this.$store.getters["userStore/getTargetInfo"];
    },
  },
  computed: {
    targetInfo() {
      return this.$store.getters["userStore/getTargetInfo"];
    },
  },
  created: function () {
    // 뷰엑스로 받은 이름을 타겟에 넣어서
    const targetUid = this.$store.getters['userStore/getTargetUid']
    //1. 스토어에 있는 GET_MEMBER dispatch한다. (네브 바의 마이 페이지 버튼은 어차피 
    // 자기 자신이기 때문에 이름을 내이름을 내려보내줌)
    // 응답을 변수에 담아서 저장
    this.$store.dispatch('userStore/GET_MEMBER', targetUid)
    //vuex에서 정보 가져오기
    // 저장한 변수에서 data의 email, username, phone으로 이름을 할당해주고 브라우저에 출력해준다
    // 2. 응답받은(위에서 출력한) 유저의 name과 로그인한 유저의 이름이 같으면 수정 버튼 활성화
    // 타겟과 로그인한 이름이 일치하면 수정 버튼을 보여준다.
    if (targetUid === this.$store.getters["userStore/getUserId"]) {
      console.log('이름이 일치합니당')
      this.$store.commit('userStore/MYSELF')
      console.log(`뷰엑스의 불린값 ${this.$store.getters["userStore/getMyself"]}`)
    } else {
      this.$store.commit('userStore/NOT_ME')
    }
    // 팔로우 정보 요청
    this.$store.dispatch('userStore/GET_FOLLOWER_NUM')
    this.$store.dispatch('userStore/GET_FOLLOWING_NUM')
    // 뷰엑스에서 컴퓨티드로 보여줌
    
    // follow 상태 확인 api 수정중..
    this.$store.dispatch('userStore/CHECK_FOLLOW')
  },

}
</script>

<style scoped>
  .user-info {
    margin-top: 60px;
  }

</style>