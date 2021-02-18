<template>
  <v-content>
        <NavBar/>
      <div class="user-info">
        <MyInfo v-bind="{info:targetInfo}" />
      </div>
      <v-card class="overflow-hidden mx-auto
       my-10" max-width="1100">
        <MypageMenu @myMenu="myMenu" />
      </v-card>
      <v-layout row wrap class="overflow-hidden mx-10 my-10 
       align-center justify-center"
       max-width="1155">

        <feed-item  v-for="(item, idx) in list"
        v-bind:key="item.pid"
        v-bind:item="list[idx]" />

      </v-layout>

      <infinite-loading
        @infinite="infiniteHandler"
        spinner="circles"
        ref="infiniteLoading"
      >
        <div slot="no-more">목록의 끝입니다 :)</div>
        <div slot="no-results"> 목록이 비어있습니다 :(</div>
      </infinite-loading>

  </v-content>
</template>

<script>
import axios from "axios";
import InfiniteLoading from "vue-infinite-loading";

import MyInfo from '@/components/MyInfo.vue'
import MypageMenu from '@/components/mypage/MypageMenu.vue'
import FeedItem from "@/components/Item/FeedItem.vue";
import NavBar from '@/components/NavBar.vue'

const SERVER_URL = "http://localhost:8080";

export default {
  name: 'Mypage',
  components: {
    MyInfo,
    MypageMenu,
    FeedItem,
    InfiniteLoading,
    NavBar,
  },
  data: function () {
    return {
      menu: null,
      limit: 0,
      list: [],
    }
  },
  methods: {
    myMenu: function (menu) {
      this.menu = menu
    },
    // 마이피드 불러오기
    infiniteHandler($state) {
      // this.uid = Number(this.$route.params.id)
      axios.get(`${SERVER_URL}/mylist/${this.menu}/${this.uid}/${this.limit}`,
      {uid:`${this.uid}`})
        .then(res => {
          setTimeout(()=> {
            if(res.data.totalSize > this.limit) {
              let data=res.data.boardlist
              for (let key in data) {
                this.list.push(data[key])
              }
              this.limit += 3
                $state.loaded()
            } else {
              $state.complete()
            }
          }, 1000)
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  watch: {
    menu() {
      this.list=[];
      this.limit=0;
      this.$refs.infiniteLoading.stateChanger.reset();
    },
    uid() {
      this.list=[];
      this.limit=0;
      this.$refs.infiniteLoading.stateChanger.reset();
      const comparingData = {userUid : parseInt(localStorage.getItem("uid")),
      requestUid: Number(this.$route.params.id)}
      this.$store.dispatch('userStore/CHECK_FOLLOW', comparingData)
      },
  },
  computed: {
    targetInfo() {
      return this.$store.getters["userStore/getTargetInfo"];
    },
    uid() {
      return this.$route.params.id
    },
  },
  updated: function () {
    //동적 파라미터로 Uid받기 
    let requestUid = Number(this.$route.params.id);
    // 로그인 유저 정보 받기
    let userUid = parseInt(localStorage.getItem("uid"))
    // 회원 정보 받기
    this.$store.dispatch('userStore/GET_MEMBER', requestUid)
    // 2. 응답받은(위에서 출력한) 유저의 name과 로그인한 유저의 이름이 같으면 수정 버튼 활성화
    // 타겟과 로그인한 이름이 일치하면 수정 버튼을 보여준다.
    if (requestUid === parseInt(localStorage.getItem("uid"))) {
      this.$store.commit('userStore/MYSELF')
    } else {
      this.$store.commit('userStore/NOT_ME')
    }
    // 팔로우 정보 요청
    this.$store.dispatch('userStore/GET_FOLLOWER_NUM', requestUid)
    this.$store.dispatch('userStore/GET_FOLLOWING_NUM', requestUid)
    // 뷰엑스에서 컴퓨티드로 보여줌
    
    // follow 상태 확인 api 새로고침하면 user.uid가 안가져온당? 확인
    const comparingData = {userUid : userUid,
    requestUid: requestUid}
    this.$store.dispatch('userStore/CHECK_FOLLOW', comparingData)
    
    // 팔로우, 팔로워 리스트 요청해서 저장해놓기
    this.$store.dispatch('userStore/REQUEST_FOLLOWING_LIST', requestUid)
    this.$store.dispatch('userStore/REQUEST_FOLLOWER_LIST', requestUid)
  },

}
</script>

<style scoped>
  .user-info {
    margin-top: 60px;
  }

</style>