<template>
   <v-menu offset-y>
    <template v-slot:activator="{on}">
        <v-btn icon v-on="on" class="mx-3 my-auto">
            <v-avatar size="34">  
            <v-icon size="34">   <!--프로필 이미지 가져오기-->
                mdi-account-circle
            </v-icon>
        </v-avatar>
        </v-btn>
        <v-btn icon class="mx-3 my-auto">
        <v-icon size="26">   <!--프로필 이미지 가져오기-->
                mdi-bell
        </v-icon>
        </v-btn>
    </template>
    <v-card width="300">
        <v-container grid-list-md>
            <v-layout row wrap>
                <v-flex xs4>
                    <v-avatar class="mx-1" size="64">  
                    <v-icon size="64"> 
                            mdi-account-circle
                        </v-icon>
                    </v-avatar>
                </v-flex>
                <v-flex xs8>
                    <v-card-text>
                    {{getUsername}} 님<br><hr>
                    {{getEmail}} </v-card-text>
                </v-flex>
            </v-layout>
            
            <v-btn @click="moveToMyPage" text style="width:180px;">MyPage</v-btn>

            <br>
            <v-btn target="_blank" text @click="logout" style="width:180px; color:red;">Logout</v-btn>
        </v-container>
    </v-card>
    </v-menu>
    
</template>

<script>
import {router} from "../routes"

export default {
    computed:{
        getEmail(){         // 렌더링 시점때문에 mapgetters 사용시 오류가 나므로 이렇게 쓰기
            return this.$store.getters.getEmail;
        },
        getUsername(){
            return this.$store.getters.getUsername;
        },
        getLogintype(){
             return this.$store.getters.getLogintype;
        }
    },

    methods:{
        logout(){
        this.$store.dispatch('logout')
        if(this.getLogintype=="kakao"){
            window.Kakao.API.request({
                url: '/v1/user/unlink',
                 success:((res)=>{
                     console.log(res);
                     this.$store.state.user.logintype="default"
                     window.location.reload();
                 }) 
            })
            }
        },
        moveToMyPage () {
            const name = this.$store.state.getters.getUsername
            // this.$store.dispatch('GET_TARGET', name)
            router.push({name: 'myPage', params: {name}})
        },
    }
}
</script>

<style>

</style>