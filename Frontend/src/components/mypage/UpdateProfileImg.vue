<template>
  <div>

      <v-toolbar color="primary" dark>프로필 사진 수정</v-toolbar>
      <div class="text-h5 pa-10">사진을 선택해주세요</div>
      <v-card-title class="pa-7">
          <v-file-input
            label="File input"
            filled v-model="image"
            prepend-icon="mdi-camera"
          ></v-file-input>
      </v-card-title>
      <v-card-actions class="justify-end">
        <v-btn text color="info" @click="onSubmit">변경</v-btn>
      </v-card-actions>

  </div>
</template>

<script>
// import axios from 'axios'
// const SERVER_URL = "http://localhost:8080";

export default {
  name: 'UpdateProfileImg',
  data: function () {
    return {
      modal: false,
      image: [],
      profileImg: '',
    }
  },
  methods: {
    onSubmit () {
      // const uid = this.$store.getters['userStore/getUserId']
      this.$store.dispatch("userStore/UPDATE_MEMBER_IMG", this.image)
      this.$emit('modal')
    },
  },
}
</script>

<style>

</style>