<template>
  <div class="joinForm">
    <h2>환영합니다</h2>
    <h4>드루와 드루와</h4>
    <h4>드루와 드루와</h4>

    <br>
    <div class="input-label">
      <label for="email"><b>Email</b></label>
      <input v-model="email" id="email" class="inputs" type="text" placeholder="ssafy@example.com" >
    </div>
    <p v-if="!availableEmail"  class="join-error-msg">{{errorEmail}}</p>

    <div class="input-label">
      <label for="nickname"><b>닉네임</b></label>
      <input v-model="name" id="nickname" class="inputs" type="text" placeholder="드리미" >
    </div>
    <p v-if="!availablename" class="join-error-msg"> {{errorname}} </p>

    <div class="input-label">
      <label for="password"><b>비밀번호</b></label>
      <input v-model="password" id="password" class="inputs" type="password" placeholder="영어, 숫자 8자이상" >
    </div>

    <p v-if="!availablePwd" class="join-error-msg">{{errorPwd}}</p>

    <div class="input-label">
      <label for="password-confirm"><b>비밀번호 확인</b></label>
      <input v-model="passwordConfirm" id="password-confirm" class="inputs" type="password" placeholder="비밀번호 확인">        
    </div>

    <p v-if="!unmatchPassword" class="join-error-msg"><b>{{errorUnmatch}}</b></p>

    <div class="input-label">
      <label for="phone"><b>핸드폰 번호</b></label>
      <input v-model="phone" @keypress.enter="onJoin" id="phone" class="inputs" type="text" placeholder="01043218765">
    </div>
    <p v-if="!availablePhone" class="join-error-msg">{{errorPhone}}</p>

    <div class="btn-container">
      <button class="join-button cancel">취소</button>
      <button @click="onJoin" class="join-button confirm">확인</button>
    </div>
    
  </div>
</template>

<script>

import * as EmailValidator from "email-validator"
import PV from "password-validator";

export default {
    name: "Join",
    data: function () {
      return {
        credentials: {
          email: null,
          name: null,
          password: null,
          phone: null
        },
        email:"",
        name:"",
        password:"",
        phone: "",

        errorEmail: null,
        errorPwd: null,
        errorname:null,
        errorPhone: null,
        errorUnmatch: null,

        passwordSchema: new PV(),

        passwordConfirm: "",

        availablename: false,
        availableEmail: false,
        availablePwd: false,
        availablePhone: false,
        unmatchPassword: false
      }
    },
    created() {
      this.component = this;

      this.passwordSchema
        .is()
        .min(8)
        .is()
        .max(100)
        .has()
        .digits()
        .has()
        .letters();
    },
    watch: {
      email: function() {
        this.checkForm()
      },
      password: function() {
        this.checkForm()
      },
      name: function () {
        this.checkForm()
      },
      phone: function () {
        this.checkForm()
      },
      passwordConfirm: function () {
        this.checkForm()
      }
    },

    methods: {
      checkForm: function () {
        this.availableEmail = true
        this.availablePwd = true
        this.availablename = true
        this.availablePhone = true
        this.unmatchPassword = true

        if (this.email.length >= 0 && !EmailValidator.validate(this.email)) {
          this.errorEmail = "이메일 형식에 맞지 않습니다."
          this.availableEmail = false
        } 
        if (this.password.length >= 0 && !this.passwordSchema.validate(this.password)) {
          this.errorPwd = "최소 8자 이상, 영어와 숫자 조합이여야 합니다."
          this.availablePwd = false
        } 
        if (this.name.length < 2) {
          this.errorname = "최소 한 글자 이상이여야 합니다."
          this.availablename = false
        }
        if (this.phone.length !== 11) {
          this.errorPhone = "핸드폰 번호 숫자 11자만 적어야합니다."
          this.availablePhone = false
        }
        if (this.password !== this.passwordConfirm) {
          this.errorUnmatch = "비밀번호와 비밀번호 확인이 일치하지 않습니다."
          this.unmatchPassword = false
        }

      },
      onJoin: function () {
        // 비밀번호와 비밀번호 확인이 같지 않으면
        if (this.availablename === false || this.availableEmail === false || this.availablePwd === false || this.availablePhone === false
        || this.unmatchPassword === false) {
          alert("입력란을 확인해 수정해주세요.")
        } else {
          // 비밀번호와 비밀번호 확인이 같고 모든 항목이 true면 가입
          this.errorUnmatch = null
          // 전송할 크레덴셜 입력
          this.credentials.email = this.email
          this.credentials.name = this.name
          this.credentials.password = this.password
          this.credentials.phone = this.phone
          // JSON 화 하기
          // const sendingData = JSON.stringify(this.credentials)

          //입력
          // axios.post(`${SERVER_URL}/account/signup/`, this.credentials)
          this.$store.dispatch('signup',this.credentials)
            .then(() => {
              this.$router.push("/user/login")
            })
        }
      }
    },
}
</script>

<style>

</style>