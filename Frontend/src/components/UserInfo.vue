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
          <div >
            <!-- email -->
            <div class="d-flex justify-start font-weight-bold">
              <p class="mr-8">
                <v-icon left>mdi-email</v-icon>
                E-mail : 
              </p>
                <!-- 회원 정보 수정 -->
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    text pa-0 ma-0 x-small
                    v-bind="attrs" v-on="on"
                  >
                    <v-icon class="grey--text">mdi-cog</v-icon>
                  </v-btn>
                </template>
                <span>회원 정보 수정하기</span>
              </v-tooltip>
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
    }
  },
}
</script>

<style>
.user-img {
    height: 200px;
    max-height: 200px;
    max-width: 200px;
}

</style>