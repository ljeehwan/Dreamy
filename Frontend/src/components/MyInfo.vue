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
            small elevation="2" >
              회원 탈퇴 <v-icon right dark>mdi-exit-to-app</v-icon>
            </v-btn>
          </div>
          <br>
          <v-bottom-sheet v-model="sheet">
            <v-sheet
            class="text-center"
            height="240px" >
              <v-btn class="mt-6" text color="red"
              @click="sheet = !sheet" >
                취소
              </v-btn>
              <div class="py-3">
                <p>한번 탈퇴하시면 드리미에서 계정 정보가 모두 삭제됩니다.</p>
                <p>정말로 탈퇴하시겠습니까?</p>
                <v-btn color="red white--text mt-4" >
                  회원 탈퇴
                </v-btn>
              </div>
            </v-sheet>
          </v-bottom-sheet>
          <!-- 회원 상세 정보 -->
          <div >
            <!-- email -->
            <div class="d-flex justify-start font-weight-bold">
              <p class="mr-6">
                <v-icon left>mdi-email</v-icon>
                E-mail : {{getEmail}}
              </p>
                <!-- 회원 정보 수정 -->
              <v-dialog v-model="dialog" max-width="400px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn v-bind="attrs" v-on="on" text x-small>
                    <v-icon class="grey--text">mdi-cog</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">회원 정보 수정</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-alert v-if="!isCorrect" type="error">
                        비밀번호가 일치하지 않습니다.
                      </v-alert>
                      <v-form class="pa-3 text-center" ref="form" lazy-validation>
                        <!-- 비밀번호 입력 -->
                        <v-text-field :rules="passwordRules" loading
                        class="pl-3 pr-3" v-model="credentials.password"
                        label="비밀번호" prepend-icon="mdi-lock"
                        required type="password">
                        </v-text-field>
                        <!-- 비밀번호 확인 -->
                        <v-text-field :rules="validatePasswordRules" class="pl-3 pr-3" 
                        label="비밀번호 확인" loading
                        prepend-icon="mdi-lock"
                        required type="password" v-model="validatePassword">
                        </v-text-field>
                        <!-- 핸드폰 번호 -->
                        <v-text-field loading
                        class="pr-3 pl-3 loading" v-model="credentials.phone"
                        prepend-icon="mdi-cellphone-information" :counter="11"
                        :label="getPhone" :rules="phoneNumberRules"
                        required >
                        </v-text-field>
                        <div>
                          <!-- dialog = false만들어줘야함 성공시;; -->
                          <v-btn  color="error" class="mr-4 mt-3 mr-9" depressed @click="dialog=false">
                          취소
                          </v-btn>
                          <v-btn @click="onUpdate" color="success" class="mt-3 ml-9" depressed>
                          가입하기
                          </v-btn>
                        </div>
                      </v-form>
                    </v-container>
                  </v-card-text>
                </v-card>
              </v-dialog>
            </div>

            <!-- 닉네임 -->
            <div class="d-flex justify-start font-weight-bold">
              <p>
                <v-icon left>mdi-account</v-icon>
                닉네임 : {{getName}}
                </p>
            </div>
          </div>
          <!-- 핸드폰 번호 -->
          <div class="d-flex justify-start font-weight-bold">
            <p>
              <v-icon left>mdi-phone</v-icon>
              핸드폰 번호 : {{getPhone}}
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
export default {
  data: function () {
    return {
      sheet: false,
      dialog: false,
      isCorrect: true,
      credentials: {
        uid: '',
        email: '',
        name: '',
        password: '',
        phone: '',
      },
      passwordRules: [
        v => !!v || '비밀번호를 작성해주세요',
        v => (v && v.length >= 8) || '8자 이상으로로 작성해주세요',
        v => v.search(/\s/) === -1 || '공백을 제거해주세요!'
      ],
      validatePassword: '',
      validatePasswordRules: [
        v => v.search(/\s/) === -1 || '공백이 포함되어있습니다',
        v => (v && v.length >= 8) || '8자 이상으로로 작성해주세요',
      ],
      phoneNumberRules: [
        v => (v && v.length === 11) || '숫자 11자로 작성해주세요',
        v => v.search(/\s/) === -1 || '공백을 제거해주세요!'
      ],
    }
  },
  computed: {
    getEmail() {
      return this.$store.getters.getEmail
    },
    getName() {
      return this.$store.getters.getUsername
    },
    getPhone() {
      return this.$store.getters.getPhone
    },
  },
  methods: {
    onUpdate: function () {
      this.isCorrect = true
      if (this.$refs.form.validate()) {
        if (this.validatePassword === this.credentials.password) {
          this.credentials.uid = this.$store.state.user.uid
          this.credentials.email = this.$store.state.user.email
          this.credentials.name = this.$store.state.user.name
          console.log(this.credentials)
          console.log('마이 인포 는 오케이')
          this.$store.dispatch('UPDATE_MEMBER', this.credentials)
          // this.dialog = false
        } else {
          this.isCorrect = false
        }
      }
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

</style>