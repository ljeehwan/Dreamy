<template>
  <div>
    <v-card-title>팔로워</v-card-title>
    <v-divider></v-divider>
    <v-card-text style="height: 300px;">
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
export default {
    name : "FollowingList",
    data: function() {
        return {

        }
    },
    methods: {
        onClose () {
            this.$emit('followerlistClose')
        },
    },
}
</script>

<style>

</style>