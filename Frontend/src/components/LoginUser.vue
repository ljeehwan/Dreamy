<template>
   <v-menu offset-y>
    <template v-slot:activator="{on}">
        <v-btn icon v-on="on">
            <v-avatar class="mx-1" size="34">  
            <v-icon size="34">   <!--프로필 이미지 가져오기-->
                mdi-account-circle
            </v-icon>
        </v-avatar>
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
            <v-btn target="_blank" text @click="logout" style="width:120px; color:red;">Logout</v-btn>
        </v-container>
    </v-card>
    </v-menu>
    
</template>

<script>
export default {
    computed:{
        getEmail(){         // 렌더링 시점때문에 mapgetters 사용시 오류가 나므로 이렇게 쓰기
            return this.$store.getters.getEmail;
        },
        getUsername(){
            return this.$store.getters.getUsername;
        },
    },

    methods:{
        logout(){
        this.$store.dispatch('logout')
        .then(() => this.$router.replace('/').catch(() => {}));
        }
    }
}
</script>

<style>

</style>