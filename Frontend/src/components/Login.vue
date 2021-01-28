<template>
  <v-dialog transition="dialog-top-transition" max-width="600">
    <template v-slot:activator="{ on }">
      <v-btn
        target="_blank"
        text
        style="width:120px"
        v-on="on"
        id="loginbtn"
        >
        <span>Login</span></v-btn
      >
    </template>

    <template v-slot:default="dialog">
      <v-card sm5 class="hidden-xs-only">
        <v-card-title>
          <v-layout align-center justify-center class="my-4">
            <h2>DREAMY</h2>
          </v-layout>
        </v-card-title>
        <v-layout  align-center justify-center>
        <v-form>
          <v-text-field
            outlined
            label="E-mail"
            append-icon="mdi-email-outline"
            v-model="user.email"
          >
          </v-text-field>
          <v-text-field
            outlined
            label="Password"
            append-icon="mdi-lock"
            type="password"
            v-model="user.password"
          >
          </v-text-field>
        </v-form>
        </v-layout>
        <v-row  class="mx-9">
            <v-spacer></v-spacer>
            <v-btn text color="red" @click="dialog.value = false" class="mx-3" :ripple="{ class: 'red--text' }">Close</v-btn>            
              <v-btn text color="blue" class="mx-3" @click="login"  :ripple="{ class: 'blue--text' }">
                Login
              </v-btn>
        </v-row>
            <v-container grid-list-md fluid>
                    <v-layout row wrap>
                        <v-flex xs5 class="mt-3">
                              <v-divider></v-divider>
                          </v-flex>
                        <v-flex xs2>
                              or
                          </v-flex>
                        <v-flex xs5 class="mt-3">
                              <v-divider></v-divider>
                          </v-flex>
                    </v-layout>

            <v-layout align-center justify-center>
              <v-col class="sm-6">
                <!-- <kakaoLogin :component="component" /> -->
                <kakaoLogin /> 
                 </v-col>
                 <v-col class="sm-6">
                 <!-- <googleLogin :component="component" /> -->
                <googleLogin />
                 </v-col>
              
            </v-layout>
          </v-container>

             <v-container fluid>
                    <v-layout row wrap>
                        <v-flex xs2>
                              hoxy                          
                        </v-flex>
                        <v-flex xs10 class="mt-3">
                              <v-divider></v-divider>
                          </v-flex>
                    </v-layout>
                    <v-row>
                        <v-spacer></v-spacer>
                        <v-btn class="mx-2 my-2" color="blue" text>비밀번호를 잊으셨나요?</v-btn>
                        <v-btn class="mx-2 my-2" color="blue" text href="/user/join">아직 회원이 아니신가요?</v-btn>
                    </v-row>
              </v-container>
      </v-card>
    </template>
  </v-dialog>
</template>
<script>
import kakaoLogin from "@/components/socialLogin/kakao.vue";
import googleLogin from "@/components/socialLogin/google.vue";


export default {
   components: {
    kakaoLogin,
    googleLogin
  },
  created(){
    this.component = this;
  },
  data: () => {
    return {
      user: {
        email: "",
        password: "",
      },
    };
  },
  methods:{
    login:function(){
      this.$store.dispatch('login',this.user);
    }, 
  },
};
</script>

<style scoped>
.v-card::-webkit-scrollbar {
    display: none;
}

.v-text-field{
    width:500px;
}

#loginbtn:hover{
  color:blue;
}

</style>
