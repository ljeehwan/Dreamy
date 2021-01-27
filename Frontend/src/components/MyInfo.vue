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
            class="text-center"
            height="300px" >
              <v-btn class="mt-6" text color="red"
              @click="sheet = !sheet" >
                취소
              </v-btn>
              <div class="py-3">
                <p>한번 탈퇴하시면 드리미에서 계정 정보가 모두 삭제됩니다.</p>
                <p>탈퇴하시려면 <b>"탈퇴하겠습니다"</b> 라고 치셔야합니다.</p>
<!-- 
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="name"
      :counter="10"
      :rules="nameRules"
      label="Name"
      required
    ></v-text-field>

    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>

    <v-select
      v-model="select"
      :items="items"
      :rules="[v => !!v || 'Item is required']"
      label="Item"
      required
    ></v-select>

    <v-checkbox
      v-model="checkbox"
      :rules="[v => !!v || 'You must agree to continue!']"
      label="Do you agree?"
      required
    ></v-checkbox>

    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate"
    >
      Validate
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset Form
    </v-btn>

    <v-btn
      color="warning"
      @click="resetValidation"
    >
      Reset Validation
    </v-btn>
  </v-form> -->

                <v-btn color="red white--text" :disabled="!completeExit">
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
                E-mail : {{info.email}}
                  <v-dialog v-model="dialog" width="400" height="800">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn class="pa-0 ma-0" rounded fab d-inline-block v-if="isMyself"
                      text small v-bind="attrs" v-on="on">
                        <v-icon>mdi-cog-outline</v-icon>
                      </v-btn>
                    </template>
                    <v-card>
                      <UserInfoUpdate v-bind="{info: info}"
                      @complete="dialog = false"/>
                    </v-card>
                  </v-dialog>
              </p>
            </div>
            <!-- 닉네임 -->
            <div class="d-flex justify-start font-weight-bold">
              <p>
                <v-icon left>mdi-account</v-icon>
                닉네임 : {{info.name}}
                </p>
            </div>
          </div>
          <!-- 핸드폰 번호 -->
          <div class="d-flex justify-start font-weight-bold">
            <p>
              <v-icon left>mdi-phone</v-icon>
              핸드폰 번호 : {{info.phone}}
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
      exitText: '',
      exitRules: [
        v => v === "탈퇴하겠습니다" || '정확히 입력하셔야합니다',
      ],
    }
  },
  props:{
    info: {
      type: Object,
    }
  },
  methods: {
  },
  watch: {
    isMyself() {
      return this.$store.getters.getMyself
    },
    completeExit() {
      if (this.exitText === "탈퇴하겠습니다") {
        return true

      }
    },

  },
  computed: {
    isMyself() {
      return this.$store.getters.getMyself
    },
    targetInfo() {
      return this.$store.getters.getTargetInfo
    },

    
  },
  // created: function () {
  //   // 뷰엑스로 받은 이름을 타겟에 넣어서
  //   const targetName = this.$store.state.targetName
  //   //1. 스토어에 있는 GET_MEMBER dispatch한다. (네브 바의 마이 페이지 버튼은 어차피 
  //   // 자기 자신이기 때문에 이름을 내이름을 내려보내줌)
  //   // 응답을 변수에 담아서 저장
  //   this.$store.dispatch('GET_MEMBER', targetName)
  //   //vuex에서 정보 가져오기
  //   // const isMyself = this.$store.getters.getMyself
  //   const targetInfo = this.$store.getters.getTargetInfo
  //   // 저장한 변수에서 data의 email, username, phone으로 이름을 할당해주고 브라우저에 출력해준다
  //   // this.email = targetInfo.email
  //   // this.name = targetInfo.name
  //   // this.phone = targetInfo.phone
  //   // this.uid = targetInfo.uid
  //   console.log(this.name)
  //   // 2. 응답받은(위에서 출력한) 유저의 name과 로그인한 유저의 이름이 같으면 수정 버튼 활성화
  //   // 타겟과 로그인한 이름이 일치하면 수정 버튼을 보여준다.
  //     // console.log(this.name)
  //     // console.log(this.$store.getters.getUsername)
  //   if (this.name === this.$store.getters.getUsername) {
  //     console.log('이름이 일치합니당')
  //     // this.isMyself = true
  //     this.$store.commit('MYSELF')
  //     console.log(`뷰엑스의 불린값 ${this.$store.getters.getMyself}`)
  //     // console.log(`컴포넌트 안의 불린 값 : ${this.isMyself}`)
  //   } 

  // } 
}
</script>

<style>
.user-img {
    height: 200px;
    max-height: 200px;
    max-width: 200px;
}


</style>