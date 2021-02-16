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
          버킷리스트 / 챌린지 등록
        </v-toolbar-title>
      </v-toolbar>
      <v-divider></v-divider>
      <v-stepper v-model="upload" vertical>
        <v-stepper-step step="1" v-bind:complete="upload > 1">
          타입 등록
          <small>등록할 게시물의 타입을 정해주세요</small>
        </v-stepper-step>
        <v-stepper-content step="1">
          <v-card class="ma-10">
            <v-form v-model="s1valid" ref="form">
              <v-row class="align-center">
                <v-col class="sm-6 pa-5">
                  <v-radio-group
                    :rules="[(v) => !!v || '타입을 선택해주세요!']"
                    v-model="card.boardType"
                  >
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
                    :rules="[(v) => !!v || '카테고리를 선택해주세요!']"
                    :items="items"
                    label="카테고리"
                    dense
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-spacer></v-spacer>
                <v-btn
                  class="mx-5 my-2"
                  color="primary"
                  :disabled="!s1valid"
                  @click.native="upload=2;"
                  >계속</v-btn
                >
              </v-row>
            </v-form>
          </v-card>
        </v-stepper-content>

        <v-stepper-step step="2" v-bind:complete="upload > 2"
          >날짜를 정해주세요</v-stepper-step
        >
        <v-stepper-content step="2">
          <v-card class="my-6 pa-5" v-if="upload == 2 && card.boardType == 1">
            <v-form v-model="s2valid" ref="form">
            <v-row class="align-center justify-center">
              <v-spacer></v-spacer>
              <v-tooltip left>
                <template v-slot:activator="{ on }">
                  <v-icon v-on="on">mdi-help-circle</v-icon>
                </template>
                <span
                  ><small>버킷리스트의 시작 날짜는 현재일 기준이며,<br />종료 날짜는
                  자유롭게 설정 가능합니다.</small></span
                >
              </v-tooltip>
            </v-row>
            <v-row class="align-center justify-center">
              <span><strong>기간 설정</strong></span>
            </v-row>
            <v-row
              ><v-spacer></v-spacer>
              <span>
                <small>작성일 : {{ this.writtenDate }}</small>
              </span></v-row
            >
            <v-row justify="space-around" class="mb-5">
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
                :rules="[(v) => !!v || '날짜를 선택해주세요!']"
                label="종료 날짜"
                prepend-icon="mdi-calendar"
              ></v-text-field>
              <v-spacer></v-spacer>
            </v-row>

            <v-row justify="space-around">
              <v-date-picker
                :min="card.startDate"
                v-model="card.startDate"
                readonly
                :show-current="true"
              ></v-date-picker>
              <v-date-picker
                v-model="card.endDate"
                :min="card.startDate"
                :rules="[(v) => !!v || '날짜를 선택해주세요!']"
                :show-current="true"
              ></v-date-picker>
            </v-row>
            <v-row class="ma-10">
            <v-btn icon @click="reset"><v-icon> mdi-refresh</v-icon></v-btn>
              <v-spacer></v-spacer>
              <v-btn class="mr-3" text @click.native="upload = 1">이전</v-btn>
              <v-btn color="primary" :disabled="!s2valid" @click.native="upload = 3">계속</v-btn>
            </v-row>
            </v-form>
          </v-card>
        </v-stepper-content>
        <!--첼린지 -->
        <v-stepper-content step="2">
          <v-card class="my-6 pa-5" v-if="upload == 2 && card.boardType == 2">
            <v-form v-model="s2valid" ref="form">
            <v-row class="align-center justify-center">
              <v-spacer></v-spacer>
              <v-tooltip left>
                <template v-slot:activator="{ on }">
                  <v-icon v-on="on">mdi-help-circle</v-icon>
                </template>
                <span
                  ><small>첼린지에서 시작 날짜와 종료 날짜는 <br />
                  자유롭게 설정 가능합니다.</small></span
                >
              </v-tooltip>
            </v-row>
            <v-row class="align-center justify-center">
              <span><strong>기간 설정</strong></span>
            </v-row>
            <v-row
              ><v-spacer></v-spacer>
              <span>
                <small>작성일 : {{ this.writtenDate }}</small>
              </span></v-row
            >

            <v-row class="mb-5">
              <v-text-field
                class="ml-10"
                v-model="dateRange[0]"
                label="시작 날짜"
                :rules="[(v) => !!v || '날짜를 선택해주세요!']"
                prepend-icon="mdi-calendar"
              >
              </v-text-field>
              <v-spacer></v-spacer>
              <v-text-field
                class="mr-10"
                v-model="dateRange[1]"
                label="종료 날짜"
                :rules="[(v) => !!v || '날짜를 선택해주세요!']"
                prepend-icon="mdi-calendar"
              ></v-text-field>
            </v-row>
            <v-row justify="center">
              <v-date-picker
                locale="ko-KR"
                class="mx-10"
                full-width
                :min="this.dateRange[0]"
                range
                v-model="dateRange"
                :rules="[(v) => dateRange[0]>dateRange[1] || '날짜를 다시 선택해주세요!']"
              ></v-date-picker>
            </v-row>
            <v-row class="ma-10">
              <v-btn icon @click="reset"><v-icon> mdi-refresh</v-icon></v-btn>
              <v-spacer></v-spacer>
              <v-btn class="mr-3" text @click.native="upload = 1">이전</v-btn>
              <v-btn color="primary" :disabled="!s2valid" @click.native="upload = 3">계속</v-btn>
            </v-row>
            </v-form>
          </v-card>
        </v-stepper-content>

        <v-stepper-step step="3" v-bind:complete="upload > 3"
          >내용을 입력해주세요</v-stepper-step
        >
        <v-card class="ma-7" v-if="upload == 3">
          <v-form>
            <v-row>
              <v-text-field
                v-model="card.title"
                label="제목 입력"
                clearable
                dense
                :rules="titleRules"
                :counter="15"
                class="ma-10"
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                clearable
                clear-icon="mdi-close-circle"
                outlined
                width="70%"
                height="100px"
                label="내용 입력"
                :rules="contentRules"
                :counter="100"
                class="mx-10"
                v-model="card.content"
              ></v-text-field>
            </v-row>
            <v-row>
              <v-col class="sm-6 mx-10">
                <v-file-input
                  show-size
                  label="이미지 등록"
                  dense
                  v-model="image"
                ></v-file-input>
              </v-col>
              <v-col class="sm-6" justify-center align-center>
                <v-row class="mx-10">
                  <v-checkbox
                    v-model="defaultfile"
                    label="기본 이미지로 등록"
                  ></v-checkbox>
                  <v-spacer></v-spacer>
                  <v-tooltip left>
                    <template v-slot:activator="{ on }">
                      <v-icon v-on="on">mdi-help-circle</v-icon>
                    </template>
                    <span
                      ><small>기본 이미지로 등록 시,<br />
                      카테고리와 연관된 사진이 등록됩니다.</small></span
                    >
                  </v-tooltip>
                </v-row>
              </v-col>
            </v-row>
          </v-form>
          <v-row class="mx-10 py-5">
            <v-spacer></v-spacer>
            <v-btn class="mr-3" text @click.native="upload = 2">이전</v-btn>
            <v-btn color="primary" @click="submit">등록</v-btn>
          </v-row>
        </v-card>
      </v-stepper>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";
import moment from "moment";
import "moment/locale/ko";
const SERVER_URL = "http://localhost:8080";
//카테고리 1: 운동, 2:음식 3: 여행, 4:학슴, 5:문화/생활, 6:기타
export default {
  data() {
    return {
      dialog: false,
      upload: 1,
      defaultfile: false,
      image: [],
      s1valid: true,
      s2valid: true,
      s3valid: true,
      writtenDate: moment().format("YYYY-MM-DD"),
      card: {
        uid: "",
        boardType: "",
        title: "",
        content: "",
        category: "",
        startDate: "",
        endDate: "",
        imageUrl: "",
      },
      titleRules: [v => !!v || '제목을 입력해주세요',
      v => v.length <= 15 || '글자수를 확인해주세요'
    ],
    contentRules:[v => !!v || '내용을 입력해주세요',
      v => v.length <= 100 || '글자수를 확인해주세요'],
      dateRange: [],
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
  computed: {
    getUserId() {
      return this.$store.getters["userStore/getUserId"];
    },
  },
  methods: {
    submit() {
      if (this.card.boardType == 2) {
        this.card.startDate = this.dateRange[0];
        this.card.endDate = this.dateRange[1];
      }
      this.card.uid = this.getUserId;

      if(this.defaultfile===true){
        this.card.imageUrl=null;
        this.$store.dispatch("boardStore/insertBoard", this.card);
      }
      else{
      let formData = new FormData();
      formData.append("files", this.image);
      axios
        .post(`${SERVER_URL}/board/imageupload`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          this.card.imageUrl = res.data["imgPath"];
          // console.log(this.card);
          this.$store.dispatch("boardStore/insertBoard", this.card);
        })
        .catch((error) => {
          console.log(error);
        });
      }
    },
    open() {
      this.dialog = true;
      this.upload = 1;
      this.card.startDate = moment().format("YYYY-MM-DD");
      this.image = [];
      this.defaultfile = false;
    },
    reset(){
      this.dateRange=[];
      this.card.endDate="";
    }
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
