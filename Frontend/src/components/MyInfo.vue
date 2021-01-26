<template>
  <div>
  <v-container class="pa-6">
    <v-row>
      <v-col cols="6" sm="4" md="4" elevation="0">
        <v-card class="pa-2 user-img rounded-circle" elevation="0" outlined >
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" md="8">
        <v-card class="pa-2" height="200" elevation="0">
          <!-- 회원 탈퇴 -->
          <div class="d-flex justify-end">
            <v-btn color="orange" dark  @click="sheet = !sheet"
            small elevation="2" >
              회원 탈퇴 <v-icon right dark>mdi-exit-to-app</v-icon>
            </v-btn>
          </div>
          <br>
          <v-bottom-sheet v-model="sheet">
            <v-sheet
            class="text-center"
            height="240px" >
              <v-btn class="mt-6" text color="red"
              @click="sheet = !sheet" >
                취소
              </v-btn>
              <div class="py-3">
                <p>한번 탈퇴하시면 드리미에서 계정 정보가 모두 삭제됩니다.</p>
                <p>정말로 탈퇴하시겠습니까?</p>
                <v-btn color="red white--text mt-4" >
                  회원 탈퇴
                </v-btn>
              </div>
            </v-sheet>
          </v-bottom-sheet>
          <!-- 회원 상세 정보 -->
          <div>
            <!-- email -->
            <div class="d-flex justify-start font-weight-bold">
              <p class="mr-6">
                <v-icon left>mdi-email</v-icon>
                E-mail : 
              </p>
            </div>
            <!-- 닉네임 -->
            <div class="d-flex justify-start font-weight-bold">
              <p>
                <v-icon left>mdi-account</v-icon>
                닉네임 :
                </p>
            </div>
          </div>
          <!-- 핸드폰 번호 -->
          <div class="d-flex justify-start font-weight-bold">
            <p>
              <v-icon left>mdi-phone</v-icon>
              핸드폰 번호 : 
            </p>
          </div>
        </v-card>
      </v-col>

    </v-row>
    <hr class="mt-7">
  </v-container>

  </div>
</template>

<script>
export default {
  data: function () {
    return {
      sheet: false,
      dialog: false,
      email : '',
      username : '',
      phone: '',
    }
  },
  props: {
    name: {type: String},
  },
  methods: {
  },
  created: function () {
    // 파람스로 받은 이름을 타겟에 넣어서
    const targetName = this.name
    //1. 스토어에 있는 GET_MEMBER dispatch한다. (네브 바의 마이 페이지 버튼은 어차피 
    // 자기 자신이기 때문에 이름을 내이름을 내려보내줌)
    // 응답을 변수에 담아서 저장
    const res = this.$store.dispatch('GET_MEMBER', targetName)
    // this.email = res.data.~~~
    // 저장한 변수에서 data의 email, username, phone으로 이름을 할당해주고 브라우저에 출력해준다

    // 2. 응답받은(위에서 출력한) 유저의 name과 로그인한 유저의 이름이 같으면 수정 버튼 활성화
    // 타겟과 로그인한 이름이 일치하면 수정 버튼을 보여준다.

  }
}
</script>

<style>
.user-img {
    height: 200px;
    max-height: 200px;
    max-width: 200px;
}

</style>