<template>
  <v-dialog v-model="dialog" max-width="800">
    <template v-slot:activator="{ on }">
      <v-btn
        class="btn"
        fab
        dark
        bottom
        right
        v-on="on"
        color="indigo"
        elevation="8"
        transition="scale-transition"
        @click="open"
      >
        <v-icon dark>
          mdi-plus
        </v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-toolbar dark flat>
        <v-toolbar-title>
          버킷리스트 / 첼린지 등록
        </v-toolbar-title>
      </v-toolbar>
      <v-divider></v-divider>
      <v-stepper v-model="e1">
        <v-stepper-header>
          <template v-for="n in 3">
            <v-stepper-step
              :key="`${n}-step`"
              :complete="e1 > n"
              :step="n"
              editable
            >
              Step {{ n }}
            </v-stepper-step>

            <v-divider v-if="n !== steps" :key="n"></v-divider>
          </template>
        </v-stepper-header>

        <v-stepper-items>
          <v-stepper-content v-for="n in steps" :key="`${n}-content`" :step="n">
            <v-card class="ma-10" v-if="n == 1">
              <v-row class="align-center justify-center"
                ><p><strong>타입 설정</strong></p></v-row
              >
              <v-row class="align-center">
                <v-col class="sm-6 pa-5">
                  <v-radio-group v-model="card.type">
                    <template v-slot:label>
                      <div><strong>등록 타입</strong></div>
                    </template>
                    <v-radio label="Bucket List" value="1"> </v-radio>
                    <v-radio label="Challenge" value="2"> </v-radio>
                  </v-radio-group>
                </v-col>
                <v-col class="sm-6 pa-5">
                  <v-select
                    v-model="card.category"
                    :items="items"
                    label="카테고리"
                    dense
                  ></v-select>
                </v-col>
              </v-row>
            </v-card>

            <v-card class="my-6 pa-5" v-if="n == 2 && card.type == 1">
              <v-row class="align-center justify-center">
                <v-spacer></v-spacer>
                <v-tooltip left>
                  <template v-slot:activator="{ on }">
                    <v-icon v-on="on">mdi-help-circle</v-icon>
                  </template>
                  <span
                    >버킷리스트의 시작 날짜는 현재일 기준이며,<br />종료 날짜는
                    자유롭게 설정 가능합니다.</span
                  >
                </v-tooltip>
              </v-row>
              <v-row class="align-center justify-center">
                <p><strong>기간 설정</strong></p>
              </v-row>
              <v-row
                ><v-spacer></v-spacer>
                <p>
                  <small>작성일 : {{ card.writtenDate }}</small>
                </p></v-row
              >
              <v-row justify="space-around">
                <v-text-field
                  class="ml-10"
                  v-model="card.startDate"
                  readonly
                  label="시작 날짜"
                  prepend-icon="mdi-calendar"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-text-field
                  class="ml-10"
                  v-model="card.endDate"
                  label="종료 날짜"
                  prepend-icon="mdi-calendar"
                ></v-text-field>
                <v-spacer></v-spacer>
              </v-row>

              <v-row justify="space-around">
                <v-date-picker
                  v-model="card.startDate"
                  readonly
                  :show-current="true"
                ></v-date-picker>
                <v-date-picker
                  v-model="card.endDate"
                  :show-current="true"
                ></v-date-picker>
              </v-row>
            </v-card>
            <!--첼린지 -->
            <v-card class="my-6 pa-5" v-if="n == 2 && card.type == 2">
              <v-row class="align-center justify-center">
                <v-spacer></v-spacer>
                <v-tooltip left>
                  <template v-slot:activator="{ on }">
                    <v-icon v-on="on">mdi-help-circle</v-icon>
                  </template>
                  <span
                    >첼린지에서 시작 날짜와 종료 날짜는 <br />
                    자유롭게 설정 가능합니다.</span
                  >
                </v-tooltip>
              </v-row>
              <v-row class="align-center justify-center">
                <p><strong>기간 설정</strong></p>
              </v-row>
              <v-row
                ><v-spacer></v-spacer>
                <p>
                  <small>작성일 : {{ card.writtenDate }}</small>
                </p></v-row
              >

              <v-row>
                <v-text-field
                  class="ml-10"
                  v-model="dateRange[0]"
                  label="시작 날짜"
                  prepend-icon="mdi-calendar"
                >
                </v-text-field>
                <v-spacer></v-spacer>
                <v-text-field
                  class="mr-10"
                  v-model="dateRange[1]"
                  label="종료 날짜"
                  prepend-icon="mdi-calendar"
                ></v-text-field>
              </v-row>
              <v-row justify="center">
                <v-date-picker
                  locale="ko-KR"
                  class="mx-10"
                  full-width
                  range
                  v-model="dateRange"
                ></v-date-picker>
              </v-row>
            </v-card>

            <v-card class="my-6" v-if="n == 3">
              <v-row class="align-center justify-center">
                <p><strong>내용 입력</strong></p>
              </v-row>
              <v-row>
                  <v-text-field
                    v-model="card.title"
                    label="제목 입력"
                    clearable
                    outlined
                    dense
                    class="mx-5"
                  ></v-text-field>
                  <v-file-input
                  show-size
                  label="이미지 등록"
                  class="mx-5"
                  ></v-file-input>
              </v-row>
              <v-row>
                  <v-textarea
                    clearable
                    clear-icon="mdi-close-circle"
                    outlined
                    label="내용 입력"
                    class="ma-5"
                    v-model="card.content"
                  ></v-textarea>
              </v-row>
            </v-card>

            <v-btn text @click="dialog = false">
              취소
            </v-btn>

            <v-btn color="success" @click="submit" v-if="n == 3">등록</v-btn>

            <v-btn color="primary" @click="nextStep(n)" v-if="n != 3">
              다음
            </v-btn>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </v-card>
  </v-dialog>
</template>

<script>
//카테고리 1: 운동, 2:음식 3: 여행, 4:학슴, 5:문화/생활, 6:기타
export default {
  data() {
    return {
      dialog: false,
      e1: 1,
      steps: 3,
      bucketHelpMsg: "",
      card: {
        type: "",
        title: "",
        content: "",
        category: "",
        writtenDate: new Date().toISOString().substr(0, 10),
        startDate: "",
        endDate: "",
        imageUrl:""
      },
      dateRange: ["", ""],
      items: [
        { text: "1. 운동", value: 1 },
        { text: "2. 음식", value: 2 },
        { text: "3. 여행", value: 3 },
        { text: "4. 학습", value: 4 },
        { text: "5. 문화/생활", value: 5 },
        { text: "6. 기타", value: 6 },
      ],
    };
  },
  watch: {
    steps(val) {
      if (this.e1 > val) {
        this.e1 = val;
      }
    },
  },
  methods: {
    nextStep(n) {
      if (n === this.steps) {
        this.e1 = 1;
      } else {
        this.e1 = n + 1;
      }
    },
    submit() {
      if(this.card.type==2){
        this.card.startDate=this.dateRange[0];
        this.card.endDate=this.dateRange[1];
      }
      this.$store.dispatch('insertBoard',this.card);
    },
    open() {
      this.dialog = true;
      this.e1 = 1;
      this.card.type = "";
      this.card.category = "";
      this.card.startDate = new Date().toISOString().substr(0, 10);
      this.card.endDate = "";
      this.dateRange[0] = "";
      this.dateRange[1] = "";
    },
  },
};
</script>

<style>
.btn {
  position: fixed;
  right: 30px;
  bottom: 30px;
}
</style>
