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
          style="height:100%; vertical-align: top; width:100%; 
                position: absolute; top: 0;
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
            <v-btn id="detail" class="mx-2" outlined>
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
                <span style="color:white">댓글 보기</span>
              </span>

            </v-row>
          </v-card-text>
        </v-img>
      </div>
    </v-card>

    <v-dialog class="ma-3" v-model="detail" max-width="1000">
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
                  <v-card-subtitle style="color:black">
                    <span>
                      <v-btn text rounded
                       color="purple" @click="moveToPage(item.uid)">{{item.name}}</v-btn>
                    </span>
                  </v-card-subtitle>
                </v-row>
                <v-card-text>{{ item.content }}</v-card-text>
                <v-row class="mr-3 my-2 align-center justify-center">
                  <v-spacer></v-spacer>
                  <div class="mx-1">
                  <v-btn
                    v-if="isLiked"
                    class="mr-1"
                    small
                    icon="icon"
                    color="red"
                    @click="deleteLike"
                  >
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <v-btn
                    v-if="!isLiked"
                    class="mr-1"
                    small
                    icon="icon"
                    color="red"
                    @click="addLike"
                  >
                    <v-icon>mdi-heart-outline</v-icon>
                  </v-btn>
                  <span>{{ likes }}<small>명</small></span>
                  </div>
                  <v-btn v-if="!this.isParticipate" id="add" class="ml-5" outlined @click="addParticipant">
                      참여하기
                    </v-btn>
                  <v-btn v-else id="isOn" class="ml-3" outlined @click="cancel=true">
                      참여 중
                    </v-btn>
                  <v-btn v-if="item.boardType==2&&this.isParticipate" id="accepted" class="ml-1" text color="blue" @click="done">
                      <v-icon class="mr-1">mdi-checkbox-marked-outline</v-icon>
                      완료하기
                    </v-btn>
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
                      <Participate :type="item.boardType"
                       @exitModal="detail=false"
                      />
                    </v-tab-item>
                    <v-tab-item>
                      <Reply :pid="this.data.pid" :uid="this.data.uid"/>
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
        <v-dialog v-model="cancel" max-width="400">
      <v-card class="py-5">
        <v-card-subtitle style="color:black">
          정말 해당 {{item.boardType|typeFilter}} 진행을 취소 하시겠습니까? 
        </v-card-subtitle>
        <v-row justify="center">
          <v-btn text @click="cancel = false">
            아니요
          </v-btn>
          <v-btn color="error" text @click="delParticipate">
            취소
          </v-btn>
        </v-row>
      </v-card>
    </v-dialog>

        <v-dialog v-model="success" max-width="300">
      <v-card class="py-5">
        <v-card-subtitle style="color:black">
          해당 챌린지가 완료되었습니다. 
        </v-card-subtitle>
        <v-row justify="center">
          <v-btn color="primary" text @click="success=false">
            OK
          </v-btn>
        </v-row>
      </v-card>
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
import {router} from "@/routes.js"

const SERVER_URL = "http://localhost:8080";

export default {
  data() {
    return {
      detail: false,
      likes: 0,
      isLiked: null,
      isParticipate:null,
      delflag: false,
      data: {
        uid: parseInt(localStorage.getItem("uid")),
        pid: this.item.pid,
      },
      success:false,
      cancel:false,
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
      this.$store.commit("boardStore/setReply");
      this.$store.commit("boardStore/setParticipate");
      this.$store.dispatch("boardStore/getReply", this.data.pid);
      this.$store.dispatch("boardStore/getParticipate", this.data.pid);
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
      axios({
        method: "get",
        url: `${SERVER_URL}/likes/checklikes/${this.data.uid}/${this.data.pid}`,
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
    checkParticipate(){
      axios({
        method:"get",
        url:`${SERVER_URL}/participate/checkParticipant/${this.data.uid}/${this.data.pid}`,
      })
      .then((res)=>{
          if(res.data.message==="success"){
            this.isParticipate=true;
          }else{
            this.isParticipate=false;
          }
      })
    },
    addParticipant(){
       axios({
        method:"post",
        url:`${SERVER_URL}/participate/addParticipant`,
        data:{
          uid:this.data.uid,
          pid:this.data.pid,
          successDate:0,
        }
      })
      .then((res)=>{
          if(res.data.message==="success"){
            this.isParticipate=true;
            this.$store.commit("boardStore/setParticipate");
            this.$store.dispatch("boardStore/getParticipate", this.data.pid);
          }else{
            this.isParticipate=false;
          }
      })
    },
    delParticipate(){
        axios({
        method:"delete",
        url:`${SERVER_URL}/participate/deleteParticipant/${this.data.uid}/${this.data.pid}`,
      })
      .then((res)=>{
          if(res.data.message==="success"){
            this.$store.commit("boardStore/setParticipate");
            this.$store.dispatch("boardStore/getParticipate", this.data.pid);
            this.cancel = false
          }
      })
  
    },
    deleteBoard() {
      this.$store.dispatch("boardStore/deleteBoard", this.data.pid);
    },
    done(){
      this.$store.dispatch("boardStore/addSuccess",this.data);
      this.success=true;
      this.isParticipate=false;
    },
    moveToPage(uid) {
      this.detail = false
      router.push(`/user/mypage/${uid}`)
      this.$store.dispatch("userStore/GET_MEMBER", uid)
    },
  },
  created() {
    this.checkLike();
    this.checkParticipate();
    this.countLike();
  },
  filters: {
    dateFilter: function(date) {
      return moment(date).fromNow();
    },
     typeFilter: function(type) {
      if(type==1){
          return "버킷리스트"
      }else if(type==2){
          return "챌린지"
      }
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Gothic:wght@700;800&display=swap");
</style>
