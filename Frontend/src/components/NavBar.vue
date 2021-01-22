<template>
    <v-app-bar app color="white" rounded single-line elevation="1">
        <v-img
          alt="Dreamy Logo"
          class="shrink"
          contain
          src="http://placehold.it/50"
          transition="scale-transition"
        />

        <v-img
          alt="Dreamy Name"
          class="shrink"
          contain
         src="http://placehold.it/150x50"
        />
        <v-spacer>
            <v-row>
            <v-col md="4">
            </v-col>    
            <v-col md="4">
            </v-col>
            <v-col md="4">
                <div class="v-input-slot grey lighten-3 rounded-lg" style="height:40px;">
                    <div class="v-input__prepend-inner rounded">
                        <span aria-hidden="true" class="v-icon notranslate ml-1 mr-2 theme--light grey--text">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="v-icon__svg">
                            <path d="M9.5,3A6.5,6.5 0 0,1 16,9.5C16,11.11 15.41,12.59 14.44,13.73L14.71,14H15.5L20.5,19L19,20.5L14,15.5V14.71L13.73,14.44C12.59,15.41 11.11,16 9.5,16A6.5,6.5 0 0,1 3,9.5A6.5,6.5 0 0,1 9.5,3M9.5,5C7,5 5,7 5,9.5C5,12 7,14 9.5,14C12,14 14,12 14,9.5C14,7 12,5 9.5,5Z"></path>
                            </svg>
                            <div class="v-text-field__slot">
                                <input autocomplete="off" spellcheck="false" placeholder="Search" type="text" style="height:40px; font-size:14px">
                            </div>
                        </span>
                    </div>
                </div>
            </v-col>
            </v-row>
        </v-spacer>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">

    <v-btn target="_blank" text style="width:120px;">
        <span class="mr-2"><router-link to="/" style="color:black; text-decoration:none; font-size: 13px;">HOME</router-link></span>
    </v-btn>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">
     
    <v-btn target="_blank" text style="width:120px;">
        <span class="mr-2"><router-link to="/user/login" style="color:black; text-decoration:none; font-size: 13px;">LOGIN</router-link></span>
    </v-btn>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">
    <!-- my page 잠깐 넣었습니다 -->
    <v-btn target="_blank" text style="width:120px;">
        <span class="mr-2"><router-link to="/user/mypage" style="color:black; text-decoration:none; font-size: 13px;">MYPAGE</router-link></span>
    </v-btn>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">

    <v-btn target="_blank" text style="width:120px;">
        <span class="mr-2"><router-link to="/user/join" style="color:black; text-decoration:none; font-size: 13px;">SIGNUP</router-link></span>
    </v-btn>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">

    <v-menu open-on-hover bottom offset-y>
        <template v-slot:activator="{on}">
            <v-btn target="_blank" v-on="on" text style="width:120px;">
                <span class="mr-2 v-icon notranslate hidden-sm-and-down theme--light" style="font-size: 13px;">MORE
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="v-icon__svg" style="height: 14px; width: 14px;">
                <path d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z"></path></svg>
                </span>
            </v-btn>
        </template>
        <v-list>
            <v-list-item>
                <v-btn target="_blank" text style="width:120px;">
                <router-link to="/community/Bucket" style="color:black; text-decoration:none; font-size: 13px;">Bucket List</router-link>
                </v-btn>
            </v-list-item>
            

            <v-list-item>
                <v-btn target="_blank" text style="width:120px;">
                <router-link to="/community/challenge" style="color:black; text-decoration:none; font-size: 13px;">Challenge</router-link>
                </v-btn>
            </v-list-item>
            
            
            <v-list-item>
                <v-btn target="_blank" text style="width:120px;">
                <router-link to="/community/ranking" style="color:black; text-decoration:none; font-size: 13px;">Ranking</router-link>
                </v-btn>
            </v-list-item>
        </v-list>
    </v-menu>
    </v-app-bar>
</template>

<script>
export default {
    data :()=>({
        searchWord:"",
    }),
    methods:()=>({
        
    })
}
</script>

<style>

input::placeholder{
    font-weight: lighter;
    font-size: 15px;
}

</style>