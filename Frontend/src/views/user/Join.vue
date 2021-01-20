<template>
  <v-container>
    <v-row align="center" class="fill-height" justify="center">
      <div class="register elevation-12">

          <h3 class="font-weight-bold text-center py-3 black--text">
          회 원 가 입
          </h3>
          <v-form class="pa-3 text-center" ref="form" lazy-validation>
            <v-alert v-if="!isCorrect" type="error">
            비밀번호가 일치하지 않습니다.
            </v-alert>

            <!-- 이메일 -->
            <v-text-field class="pl-3 pr-3" v-model="credentials.email" label="E-mail"
            prepend-icon="mdi-email" required type="email" :rules="emailRules"
            placeholder="ssafy@example.com"
            ></v-text-field>
            <!-- 닉네임 -->
            <v-text-field class="pl-3 par-3" v-model="credentials.name" 
            :counter="10" :rules="nameRules"
            label="Nickname" required prepend-icon="mdi-account"
            ></v-text-field>

            <!-- 비밀번호 -->
            <v-text-field :rules="passwordRules" 
            class="pl-3 pr-3" v-model="credentials.password"
            label="비밀번호" prepend-icon="mdi-lock"
            required type="password">
            </v-text-field>
            <!-- 비밀번호 확인 -->
            <v-text-field :rules="validatePasswordRules" class="pl-3 pr-3" 
            label="비밀번호 확인"
            prepend-icon="mdi-lock"
            required type="password" v-model="validatePassword">
            </v-text-field>
            
            <!-- 이용 약관 -->
            <v-checkbox
            class="pr-3 pl-3"
            v-model="checkbox"
            :rules="[v => !!v || '약관에 동의하셔야 합니다!']"
            label="약관에 동의하십니까?"
            required
            ></v-checkbox>
            <!-- :disabled="!valid" -->
            <v-btn  color="success" class="mr-4" depressed
            @click="onSignup">
            가입하기
            </v-btn>
            
          </v-form>
      </div>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    data:() => {
      return {
        // valid: true,
        credentials: {
          name: '',
          email: '',
          password: '',
        },
        nameRules: [
          v => !!v || '닉네임을 작성해주세요',
          v => (v && v.length <= 10) || '10자 이내로 작성해주세요',
        ],

        emailRules: [
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
            v => (v && v.length >= 8) || '8자 이상으로로 작성해주세요',
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
            console.log(this.credentials)
            this.$store.dispatch('SIGNUP', this.credentials)
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