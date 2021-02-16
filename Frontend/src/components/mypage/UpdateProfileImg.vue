<template>
  <div>
      <v-toolbar color="primary" dark>프로필 사진 수정</v-toolbar>
      <div v-if="isMyself">
        <div class="text-h5 pa-10">사진을 선택해주세요</div>
        <v-card-title class="pa-7">
            <v-file-input
              label="File input"
              filled v-model="image"
              prepend-icon="mdi-camera"
            ></v-file-input>
        </v-card-title>
      </div>
      <div v-if="!isMyself" class="pa-3 mt-4">
        <p><b>회원님의 프로필 사진만 변경 가능합니다</b></p>
      </div>
      <v-card-actions class="justify-end" v-if="isMyself">
        <v-btn text color="info" @click="onSubmit">변경</v-btn>
      </v-card-actions>
      <v-card-actions class="justify-end" v-if="!isMyself">
        <v-btn text color="red" @click="onCancel">취소</v-btn>
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
  computed: {
    isMyself() {
      return this.$store.getters["userStore/getMyself"]
    }
  },
  methods: {
    onSubmit () {
      // const uid = this.$store.getters['userStore/getUserId']
      this.$store.dispatch("userStore/UPDATE_MEMBER_IMG", this.image)
      this.$emit('modal')
    },
    onCancel () {
      this.$emit('modal')
    },
  },
}
</script>

<style>

</style>