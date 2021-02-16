<template>
  <div>
    <v-card-title>팔로잉 목록</v-card-title>
    <v-divider></v-divider>
    
    <v-card-text style="height: 300px;"  class="pa-2">
        <v-list dense>
            <v-subheader>
                 팔로우하고 있는 회원들입니다</v-subheader>
            <v-list-item-group v-model="selectedFollowing" color="teal">
                <v-list-item v-for="(item, idx) in followingList"
                 :key="idx" style="height: 20px;"
                 class="px-8 ma-2"
                 @click="moveToPage(item.uid)"
                >   
                    <v-list-item-avatar >
                        <v-img :src="item.profileUrl"></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title
                         v-text="item.name" style="font-size: medium">
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </v-card-text>

    <v-divider></v-divider>
    <v-card-actions>
    <v-btn color="orange darken-1" text @click="onClose" class="mx-auto">
        닫기
    </v-btn>
    </v-card-actions>
  </div>
</template>

<script>
import {router} from "@/routes.js"
export default {
    name : "FollowingList",
    data: function() {
        return {
            selectedFollowing : 1,

        }
    },
    computed: {
        followingList() {
            return this.$store.getters["userStore/getFollowingList"]
        },
    },
    methods: {
        onClose () {
            this.$emit('followinglistClose')
        },
        moveToPage (uid) {
            console.log(uid)
            this.$emit('followinglistClose')
            router.push(`/user/mypage/${uid}`)
            this.$store.dispatch("userStore/GET_MEMBER", uid)
        },
    },
}
</script>

<style>

</style>