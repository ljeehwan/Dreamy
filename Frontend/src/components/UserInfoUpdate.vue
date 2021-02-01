<template>
  <div>
  <v-container >
    <v-row align="center" class="fill-height" justify="center">
      <div class="register elevation-12">
  
          <h3 class="text-h6  text-center py-3 darkgrey--text">
          회원 정보 수정
          </h3>
          <h4 class="darkgrey--text pt-2">당신의 꿈, 드리미와 함께하세요</h4>
          <v-form class="pa-3 text-center" ref="form" lazy-validation>
            <v-alert v-if="!isCorrect" type="error">
            비밀번호가 일치하지 않습니다.
            </v-alert>

            <!-- 이메일 -->
            <v-text-field class="pl-3 pr-3" v-model="credentials.email" :label="info.email"
            prepend-icon="mdi-email" required type="email"
            placeholder="ssafy@example.com" disabled  
            ></v-text-field>
            
            <!-- 닉네임 -->
            <v-text-field class="pl-3 pr-3" v-model="credentials.name" 
            disabled
            :label="info.name" required prepend-icon="mdi-account"
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

            <!-- 가입 버튼 -->
            <div>
              <v-btn  color="success" depressed
              @click="onUpdate" :disabled="complete">
              <span>수정 하기!</span>
              </v-btn>
            </div>
          </v-form>
      </div>
    </v-row>
  </v-container>

  </div>
</template>

<script>


  export default {
    components: {

    },
    data:() => {
      return {
       
        complete : false,
        credentials: {
          uid: '',
          name: '',
          email: '',
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
        isCorrect : true,
      }
    },
    props: {
        info: {
            type: Object
        }
    },
    methods: {
      onUpdate() {
          const uid = this.$store.state.userStore.user.uid
          this.credentials.uid = uid
          this.credentials.email = this.info.email
          this.credentials.name = this.info.name
          this.isCorrect = true
          if (this.$refs.form.validate()) {
              if (this.validatePassword === this.credentials.password) {
                this.$store.dispatch('userStore/UPDATE_MEMBER', this.credentials)
                // console.log(this.credentials)
                // 내정보를 바꿨을 때, 다시 emit해서 이벤트 주고 폰 바뀐거 다시 얻어야함
                this.$emit('complete')
              } else {
                  this.isCorrect = false
              }
          }
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