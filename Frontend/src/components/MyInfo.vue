<template>
  <div>
  <v-container class="pa-6">
    <v-row>
      <v-col cols="6" sm="4" md="4" elevation="0">
        <v-card class="pa-2 user-img rounded-circle" elevation="0" outlined >
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" md="8">
        <v-card class="pa-2" height="200" elevation="0">
          <!-- 회원 탈퇴 -->
          <div class="d-flex justify-end">
            <v-btn color="orange" dark  @click="sheet = !sheet"
            small elevation="2" v-if="isMyself">
              회원 탈퇴 <v-icon right dark>mdi-exit-to-app</v-icon>
            </v-btn>
          </div>
          <v-bottom-sheet v-model="sheet">
            <v-sheet
            rounded
            class="text-center"
            height="340px" >
              <v-btn class="mt-6" text color="red"
              @click="sheet = !sheet" >
                취소
              </v-btn>
              <div class="py-3">
                <v-container class="exit-modal">
                <v-alert
                  dense 
                  outlined
                  type="warning"
                >
                한번 탈퇴하시면 드리미에서 계정 정보가 모두 삭제됩니다.
                </v-alert>
                <p>탈퇴하시려면 <b>"탈퇴하겠습니다"</b> 라고 치셔야합니다.</p>
                    <v-text-field
                      :rules="exitRules"
                      color="orange" background-color="#fbceb1"
                      solo v-model="exitMsg"
                      placeholder="탈퇴하겠습니다"
                      required rounded
                      @input="onExit"
                    ></v-text-field>
                </v-container>
                <v-btn color="red white--text" :disabled="!exitPass"
                @click="onDelete"
                >
                  회원 탈퇴
                </v-btn>
              </div>
            </v-sheet>
          </v-bottom-sheet>
          <!-- 회원 상세 정보 -->
          <div>
            <!-- email -->
            <div class="d-flex justify-start font-weight-bold">
              <p>
                <v-icon left>mdi-email</v-icon>
                E-mail : {{targetInfo.email}}
                  <v-dialog v-model="dialog" width="400" height="800">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn class="pa-0 ma-0" rounded fab d-inline-block v-if="isMyself"
                      text small v-bind="attrs" v-on="on">
                        <v-icon>mdi-cog-outline</v-icon>
                      </v-btn>
                    </template>
                    <v-card>
                      <UserInfoUpdate v-bind="{targetInfo: targetInfo}"
                      @complete="dialog = false"/>
                    </v-card>
                  </v-dialog>
              </p>
            </div>
            <!-- 닉네임 -->
            <div class="d-flex justify-start font-weight-bold">
              <p>
                <v-icon left>mdi-account</v-icon>
                닉네임 : {{targetInfo.name}}
              </p>
            </div>
          </div>
          <!-- 핸드폰 번호 -->
          <div class="d-flex justify-start font-weight-bold">
            <p>
              <v-icon left>mdi-phone</v-icon>
              핸드폰 번호 : {{targetInfo.phone}}
            </p>
          </div>
          <div class="d-flex justify-start font-weight-bold">
            <p>
               <span class="follow-m">
                 <v-icon left>mdi-account-supervisor-circle</v-icon>
                 팔로잉 :  {{targetInfo.following}}
              </span>
              <span class="follower-m">
                 <v-icon left>mdi-account-group-outline</v-icon>
                 팔로워 :  {{targetInfo.follower}}
              </span>

              <span v-if="isMyself">
                <!-- 버튼도 두개를 -->
                <v-btn @click="requestFollow">
                  <!-- 컴퓨티드로 하자 (뷰엑스에서 state값까지 바꿔서)  -->
                  <span>
                    <v-icon left>mdi-account-multiple-plus</v-icon>
                    팔로우
                  </span>
                </v-btn>

                <v-btn @click="requestUnfollow">
                  <!-- 컴퓨티드로 하자 (뷰엑스에서 state값까지 바꿔서)  -->
                  <span>
                    <v-icon left>mdi-account-multiple-minus</v-icon>
                    팔로우 취소
                  </span>
                </v-btn>
                
              </span>
            </p>
          </div>
        </v-card>
      </v-col>

    </v-row>
    <hr class="mt-7">
  </v-container>

  </div>
</template>

<script>
import UserInfoUpdate from "./UserInfoUpdate.vue"
import {router} from "@/routes"

export default {
  components: {
    UserInfoUpdate,
  },
  data: function () {
    return {
      sheet: false,
      dialog: false,
      uid: '',
      email : '',
      name : '',
      phone: '',
      exitPass: false,
      exitRules: [
        v => v === "탈퇴하겠습니다" || '탈퇴 문구를 정확히 입력하셔야합니다',
      ],
      exitMsg: '',
    }
  },

  updated: function () {
    if (this.sheet === false) {
      this.exitMsg = ""
    }
    
  },
  methods: {
    onExit () {
      if (this.exitMsg === "탈퇴하겠습니다") {
        this.exitPass = true
      } else {
        this.exitPass = false
      }
    },

    onDelete () {
      this.$store.dispatch('userStore/DELETE_MEMBER');
      this.sheet = false;
      router.push('/')
    },
    requestFollow() {
      this.$store.dispatch('userStore/REQUEST_FOLLOW')
    },
    // unfollow 하는 메소드
    requestUnfollow() {
      this.$store.dispatch('userStore/REQUEST_UNFOLLOW')
    },
  },
  watch: {
    isMyself() {
      return this.$store.getters["userStore/getMyself"];
    },
    
  },
  computed: {
    isMyself() {
      return this.$store.getters["userStore/getMyself"];
    },
    targetInfo() {
      return this.$store.getters["userStore/getTargetInfo"];
    },
  },
}
</script>

<style>
.user-img {
    height: 200px;
    max-height: 200px;
    max-width: 200px;
}
.exit-modal {
  max-width: 510px;
}
.follow-m {
  margin-right: 40px;
}
.follower-m {
  margin-right: 100px;
}


</style>