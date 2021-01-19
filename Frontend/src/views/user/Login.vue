<template>
<div class="user" id="login">
      <div class="wrapC">
            <h1>LOGIN</h1>
            <h3>Lorem Ipsum is not simply random text</h3>
            <h3>Lorem Ipsum is not simply random text</h3>
      </div>
      <div class="input-with-label">
      <input v-model="user.email" id="email" class="login-input" placeholder="이메일을 입력하세요" type="text"/>
      <label for="email">이메일</label>
      </div>

      <div class="input-with-label">
      <input v-model="user.password" id="password" class="login-input" placeholder="비밀번호를 입력하세요" type="password"/>
      <label for="password">비밀번호</label>
      </div>

      <button class="btn--login" @click="onLogin">로그인</button>
      <hr/>
      <button class="btn--signup" @click="signup">회원가입</button>

</div>
</template>

<script>
export default {
data:() => {
      return {
            user:{            
                  email:"",
                  password:""
            }
      };
},
methods:{
      onLogin(){
            this.$store.dispatch('login',this.user)
            .then(this.$router.push("/"));
      },
      signup(){
            this.$router.push("/user/join");
      },
},
computed:{
      isUser(){
      return this.$store.getters.getIsuser();
      }
}
};


</script>

<style>

</style>