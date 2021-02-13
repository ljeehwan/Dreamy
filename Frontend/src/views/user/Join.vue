<template>
  <div>
    <NavBar/>
  <v-container fluid class="mb-7">
    <v-row align="center" class="fill-height" justify="center">
      <div class="register elevation-12">
  
          <h3 class="text-h6  text-center py-3 darkgrey--text">
          회 원 가 입
          </h3>
          <h4 class="darkgrey--text pt-2">당신의 꿈을 응원합니다</h4>
          <v-form class="pa-3 text-center" ref="form" lazy-validation>
            <v-alert v-if="!isCorrect" type="error">
            비밀번호가 일치하지 않습니다.
            </v-alert>

            <!-- 이메일 -->
            <v-text-field class="pl-3 pr-3" v-model="credentials.email" label="E-mail"
            prepend-icon="mdi-email" required type="email" :rules="emailRules"
            placeholder="ssafy@example.com" loading
            ></v-text-field>
            
            <!-- 닉네임 -->
            <v-text-field class="pl-3 pr-3" v-model="credentials.name" 
            :counter="10" :rules="nameRules" loading
            label="닉네임" required prepend-icon="mdi-account"
            ></v-text-field>

            <!-- 비밀번호 -->
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
            label="핸드폰 번호" :rules="phoneNumberRules"
            required >
            </v-text-field>

            <!-- 이용 약관 -->
            <v-checkbox
            class="pr-3 pl-3 my-0"
            v-model="checkbox"
            :rules="[v => !!v || '약관에 동의하셔야 합니다!']"
            label="약관에 동의하십니까?"
            required
            ></v-checkbox>
            <!-- 약관 -->
              <v-col class="shrink">
                <v-btn
                class="ma-1"
                color="grey lighten-3"
                @click="expand2 = !expand2">
                개인정보 수집 약관 보기
                </v-btn>
          
                <v-expand-x-transition>
                  <v-card
                    v-show="expand2"
                    height="300"
                    width="100%"
                    color="grey lighten-5"
                    class="mx-auto pa-3"
                  ><p class="text-caption" color="grey">수집된 정보를 타인에게 노출, 공개하지 아니한다.
                    개인정보 수집 약관이 길게 만들고 있기 때문에 아무 내용이 없어도 된다.
                    개인정보 수집 약관이 길게 만들고 있기 때문에 아무 내용이 없어도 된다.
                    개인정보 수집 약관이 길게 만들고 있기 때문에 아무 내용이 없어도 된다.
                    개인정보 수집 약관이 길게 만들고 있기 때문에 아무 내용이 없어도 된다.</p></v-card>

                </v-expand-x-transition>
              </v-col>

            <!-- 가입 버튼 -->
            <div>
              <v-btn  color="error" class="mr-4 mt-3 mr-9" depressed
              @click="onCancel">
              취소
              </v-btn>
              <v-btn  color="success" class="mt-3 ml-9" depressed
              @click="onSignup">
              가입하기
              </v-btn>
            </div>
          </v-form>
      </div>
    </v-row>
  </v-container>
  <Modal/>
  </div>
</template>

<script>
  import Modal from "@/components/Modal.vue"
  import {router} from "@/routes.js"
      import NavBar from '@/components/NavBar.vue'

  export default {
    components: {
      Modal,
      NavBar,
    },
    data:() => {
      return {
        expand2: false,
        credentials: {
          name: '',
          email: '',
          password: '',
          phone: '',
        },
        nameRules: [
          v => v.search(/\s/) === -1 || '공백을 제거해주세요!',
          v => !!v || '닉네임을 작성해주세요',
          v => (v && v.length <= 10) || '10자 이내로 작성해주세요',
        ],

        emailRules: [
          v => v.search(/\s/) === -1 || '공백을 제거해주세요!',
          v => !!v || 'E-mail을 작성해주세요',
          v => /.+@.+\..+/.test(v) || '올바른 E-mail을 작성해주세요',
        ],
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
        isCorrect : true,
        checkbox: false,
      }
    },
    methods: {
      onSignup () {
        this.isCorrect = true
        if (this.$refs.form.validate()) {
          if (this.validatePassword === this.credentials.password) {
            // console.log(this.credentials)
            this.$store.dispatch('userStore/SIGNUP', this.credentials)
          } else {
            this.isCorrect = false
          }
        } 
      },
      onCancel () {
        router.push('/')
      },
    },
  }
</script>

<style scoped>
@media (min-width: 700px) {
    .register {
        width: 400px !important;
    }
}
.register {
  background-color: white;
  border-radius: 8px;
  width: 80%;
  border: white 1px solid;
  padding: 20px;
}
</style>