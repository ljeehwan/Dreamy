<template>
  <div>
    <!-- <v-hover v-slot:default="{ hover }"> -->
    <v-card
      class="mt-10 mx-3"
      color="white lighten-4"
      width="350"
      elevation="3"
      id="card"
    >
      <div id="imgsection">
        <img
          src="@/assets/empty.png"
          class="cardbg"
          style="height:100%; vertical-align: top; width:100%; position: absolute; top: 0;
  left: 0;"
          alt="bg"
        />
        <v-img
          :aspect-ratio="1 / 1"
          :src="item.imageUrl"
          @click="detail_view"
          id="card_img"
          alt
        >
          <img
            src="@/assets/shadow.png"
            class="cardbg"
            style="height:100%; vertical-align: top; width:100%; position: absolute; top: 0;
  left: 0;"
            alt="bg2"
          />

          <div id="category">
            <span v-if="item.category == 1" id="sport">
              Sport
            </span>
            <span v-else-if="item.category == 2" id="food">
              Food
            </span>
            <span v-else-if="item.category == 3" id="travel">
              Travel
            </span>
            <span v-else-if="item.category == 4" id="NewSkill">
              New Skill
            </span>
            <span v-else-if="item.category == 5" id="lifestyle">
              Life Style
            </span>
            <span v-else-if="item.category == 6" id="etc">
              Etc
            </span>
          </div>
          <div id="cardTitle">{{ item.title }}</div>
          <div id="writtenDate">
            <span>{{ item.writtenDate | dateFilter }}</span>
          </div>
          <div></div>
          <v-row class="align-center justify-center">
            <v-btn id="add" class="mx-2" outlined>
              <v-icon>mdi-plus</v-icon>
              자세히보기
            </v-btn>
          </v-row>
          <!-- <div class="typeName" v-if="item.boardType==1" style="color:green">B<span id="bucket"></span></div>
      <div class="typeName" v-if="item.boardType==2" style="color:red">C<span id="challenge"></span></div> -->
          <v-card-text id="card_contents">
            <v-row class="align-center justify-center">
              <span class="mx-3">
                <v-btn
                  v-if="isLiked"
                  class=""
                  small
                  icon="icon"
                  color="red"
                  @click="deleteLike"
                >
                  <v-icon>mdi-heart</v-icon>
                </v-btn>
                <v-btn
                  v-if="!isLiked"
                  class=""
                  small
                  icon="icon"
                  color="white"
                  @click="addLike"
                >
                  <v-icon>mdi-heart-outline</v-icon>
                </v-btn>
                <span style="color:white">{{ likes }}</span>
              </span>
              <span class="mx-3">
                <v-btn class="" small icon="icon" color="blue">
                  <v-icon>mdi-comment-outline</v-icon>
                </v-btn>
                <span style="color:white">22</span>
              </span>

              <span class="mx-3">
                <v-btn small="small" icon="icon" color="green">
                  <v-icon>mdi-bookmark</v-icon>
                </v-btn>
                <span style="color:white">33</span>
              </span>
            </v-row>
          </v-card-text>
        </v-img>
      </div>
    </v-card>

    <v-dialog v-model="detail" max-width="1000" max-height="800">
      <v-layout style="background-color:black">
        <v-row class="align-center justify-center">
          <v-col class="pr-0">
            <v-img
              :aspect-ratio="16 / 9"
              max-height="650"
              :src="item.imageUrl"
            ></v-img>
          </v-col>
          <v-col class="pl-0">
            <v-flex>
              <v-card class="pa-5">
                <v-row>
                  <v-card-title>{{ item.title }}</v-card-title>
                  <v-spacer></v-spacer>
                  <v-btn
                    v-if="data.uid == item.uid"
                    class="my-4"
                    icon="icon"
                    @click="delflag = true"
                  >
                    <v-icon>mdi-trash-can-outline</v-icon>
                  </v-btn>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-card-subtitle style="color:black">{{
                    item.name
                  }}</v-card-subtitle>
                </v-row>
                <v-card-text>{{ item.content }}</v-card-text>
                <v-row class="mr-3 my-2">
                  <v-spacer></v-spacer>
                  <v-btn
                    v-if="isLiked"
                    class="mx-2"
                    small
                    icon="icon"
                    color="red"
                    @click="deleteLike"
                  >
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <v-btn
                    v-if="!isLiked"
                    class="mx-2"
                    small
                    icon="icon"
                    color="red"
                    @click="addLike"
                  >
                    <v-icon>mdi-heart-outline</v-icon>
                  </v-btn>
                  <p>{{ likes }}<small>명</small></p>
                </v-row>
                <v-divider></v-divider>
                <template>
                  <v-tabs
                    color="deep-purple accent-4"
                    fixed-tabs
                    slider-color="block"
                   
                  >
                    <v-tab>
                      참가자
                    </v-tab>
                    <v-tab>댓글</v-tab>
                    <v-tab-item>
                      <Participate/>
                    </v-tab-item>
                    <v-tab-item>
                      <Reply :pid="item.pid" :uid="this.data.uid"/>
                    </v-tab-item>
                  </v-tabs>
                </template>
                <!-- <Reply v-for="(reply,index) in getReply" v-bind:key="reply.rid" v-bind:reply="getReply[index]"/> -->
              </v-card>
            </v-flex>
          </v-col>
        </v-row>
      </v-layout>
    </v-dialog>
    <v-dialog v-model="delflag" max-width="300">
      <v-card class="py-5">
        <v-card-subtitle style="color:black">
          정말 해당 게시물을 삭제하겠습니까?
        </v-card-subtitle>
        <v-row justify="center">
          <v-btn text @click="delflag = false">
            취소
          </v-btn>
          <v-btn color="error" text @click="deleteBoard">
            삭제
          </v-btn>
        </v-row>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import moment from "moment";
import axios from "axios";
import Reply from "./ItemReply";
import Participate from "./ParticipateList";
import "moment/locale/ko";
import "@/assets/css/style.css";

const SERVER_URL = "http://localhost:8080";

export default {
  data() {
    return {
      detail: false,
      likes: 0,
      isLiked: null,
      delflag: false,
      data: {
        uid: parseInt(localStorage.getItem("uid")),
        pid: this.item.pid,
      },
    };
  },
  components: {
    Reply,
    Participate
  },
  props: {
    item: Object,
  },
  methods: {
    detail_view() {
      this.detail = true;
      this.$store.commit("boardStore/setDefault");
      this.$store.dispatch("boardStore/getReply", this.data.pid);
    },
    addLike() {
      this.$store.dispatch("boardStore/addLike", this.data);
      this.likes += 1;
      this.isLiked = true;
    },
    deleteLike() {
      this.$store.dispatch("boardStore/deleteLike", this.data);
      this.likes -= 1;
      this.isLiked = false;
    },
    checkLike() {
      let uid = localStorage.getItem("uid");
      let pid = this.item.pid;
      axios({
        method: "get",
        url: `${SERVER_URL}/likes/checklikes/${uid}/${pid}`,
      })
        .then((res) => {
          if (res.data.message == "success") {
            this.isLiked = true;
          } else if (res.data.message == "fail") {
            this.isLiked = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    countLike() {
      axios({
        method: "get",
        url: `${SERVER_URL}/likes/countlikes/${this.item.pid}`,
      })
        .then((res) => {
          this.likes = res.data.count;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteBoard() {
      this.$store.dispatch("boardStore/deleteBoard", this.data.pid);
    },
  },
  created() {
    this.countLike();
    this.checkLike();
  },
  filters: {
    dateFilter: function(date) {
      return moment(date).fromNow();
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Gothic:wght@700;800&display=swap");
</style>
