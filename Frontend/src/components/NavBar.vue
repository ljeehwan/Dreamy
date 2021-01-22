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
                <!-- <div class="v-input--dense v-input--hide-details theme--light v-text-field v-text-field--single-line v-text-field--solo 
                v-text-field--solo-inverted v-text-field--solo-flat v-text-field--is-booted v-text-field--enclosed rounded-lg" style="max-width: 300px;">
                    <div class="v-input__control">
                        <div class="v-input__slot">
                            <div class="v-text-field__slot">
                                <input type="text" v-model="searchWord" placeholder="Search">
                                </div>
                                <div class="v-input__append-inner">
                                    <div class="v-input__icon v-input__icon--append">
                                        <i aria-hidden="true" class="v-icon notranslate mdi mdi-magnify theme--light">
                                            </i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>     -->
            </v-col>
            </v-row>
        </v-spacer>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">

    <v-btn target="_blank" text style="width:120px;">
        <span class="mr-2"><router-link to="/" style="color:black; text-decoration:none; font-size: 13px;">HOME</router-link></span>
    </v-btn>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">

    <v-btn target="_blank" text style="width:120px;">
        <span class="mr-2"><router-link to="/user/join" style="color:black; text-decoration:none; font-size: 13px;">SIGNUP</router-link></span>
    </v-btn>
    <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">

   <v-menu open-on-hover bottom offset-y  >
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
        <hr role="separator" aria-orientation="vertical" class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light" style="height: 20px;">
    <loginComponent />
    
    </v-app-bar>
</template>

<script>
import loginComponent from './Login.vue'
export default {
    data :()=>({
        searchWord:"",
    }),
    components:{
        loginComponent
    }
   
}
</script>

<style>

input::placeholder{
    font-size: 13px;
}

</style>