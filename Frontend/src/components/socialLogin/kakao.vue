<template>
    <div id="kakao-login">
        <img src="@/assets/snsLogin/kakao.png" @click="Kakaologin"/>
    </div>
</template>

<script>

export default {
    name: "kakaoLogin",
    methods: {
        Kakaologin() {
            window.Kakao.Auth.login({
                    scope : 'profile, account_email',
                    success: this.kakaogetUserinfo,
                });
        },

        kakaogetUserinfo(){
                window.Kakao.API.request({
                url:'/v2/user/me',
                 success: ((response) =>{
                    const user={
                        email:response.kakao_account.email,
                        name:response.kakao_account.profile.nickname,
                        logintype:"kakao"
                    }
                    this.$store.dispatch("getSocialUserinfo",user)
                }),
                  fail: function(error) {
                  console.log(error.message);
               }
           })
        },
    },
    }
</script>
<style scoped>
img{
    width:90%;
    height:90%;
    cursor: pointer;
}
</style>
