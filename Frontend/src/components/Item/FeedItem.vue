<template>
  <v-hover v-slot:default="{ hover }">
    <v-card
      class="my-10 mx-2"
      color="grey lighten-4"
      width="350"
      max-height="380"
    >
      <v-img :aspect-ratio="16 / 9" :src="item.imageUrl" @click="detail_view" style="cursor:pointer">
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal"
            style="height: 50%;"
          >
            {{ item.content }}
          </div>
        </v-expand-transition>
      </v-img>
      <v-row class="mt-3 px-5" @click="detail_view" style="cursor:pointer">
        <v-card-title id="title">{{ item.title }}</v-card-title>
        <v-spacer></v-spacer>
        <span>
          <small>{{ item.writtenDate | dateFilter }}</small>
        </span>
      </v-row>
      <v-card-text class="py-0">
        <v-row class="my-1" @click="detail_view" style="cursor:pointer">
          <v-btn id="name" color="black" text>
            {{ item.name }}
          </v-btn>
          <v-spacer></v-spacer>
          <v-icon left="left">
            mdi-label
          </v-icon>
          <span id="category" class="mt-2 mr-2">
            {{ item.category | category }}
          </span></v-row
        >
        <v-divider></v-divider>
        <v-row class="my-2 align-center justify-center">
          <v-btn v-if="isLiked" class="mx-2" small icon="icon" color="red">
            <v-icon>mdi-heart</v-icon>
          </v-btn>
          <v-btn v-if="!isLiked" class="mx-2" small icon="icon" color="red" @click="addLike">
            <v-icon>mdi-heart-outline</v-icon>
          </v-btn>
          <span class="mx-1">{{ likes }}</span>
          <v-btn class="mx-2" small icon="icon" color="blue">
            <v-icon>mdi-comment-outline</v-icon>
          </v-btn>
          <span class="mx-1">22</span>
          <v-btn class="mx-2" small="small" icon="icon" color="green">
            <v-icon>mdi-bookmark</v-icon>
          </v-btn>
          <span class="mx-1">33</span>
        </v-row>
        <v-dialog v-model="detail" width="1000">
          <v-layout style="background-color:black">
            <v-row class="align-center justify-center">
              <v-col class="pr-0">
                <v-card>
                  <v-img
                    :aspect-ratio="16 / 9"
                    max-height="650"
                    :src="item.imageUrl"
                  ></v-img>
                </v-card>
              </v-col>
              <v-col class="pl-0">
                <v-flex>
                  <v-card class="pa-5">
                    <v-row>
                      <v-card-title>{{ item.title }}</v-card-title>
                      <v-spacer></v-spacer>
                      <v-card-title>참가하기</v-card-title>
                    </v-row>
                    <v-row>
                      <v-spacer></v-spacer>
                      <v-card-subtitle>{{ item.name }}</v-card-subtitle>
                    </v-row>
                    <v-card-text>{{ item.content }}</v-card-text>
                    <v-row class="mr-3">
                      <v-spacer></v-spacer>
                      <v-btn
                        v-if="isLiked"
                        class="mx-2"
                        small
                        icon="icon"
                        color="red"
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
                    <v-card-text>
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                      sed do eiusmod tempor incididunt ut labore et dolore magna
                      aliqua. Nisl tincidunt eget nullam non. Quis hendrerit
                      dolor magna eget est lorem ipsum dolor sit. Volutpat odio
                      facilisis mauris sit amet massa. Commodo odio aenean sed
                      adipiscing diam donec adipiscing tristique. Mi eget mauris
                      pharetra et. Non tellus orci ac auctor augue. Elit at
                      imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu
                      felis. Egestas integer eget aliquet nibh praesent. In hac
                      habitasse plat
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-col>
            </v-row>
          </v-layout>
        </v-dialog>
      </v-card-text>
    </v-card>
  </v-hover>
</template>

<script>
import moment from "moment";
import { mapGetters } from "vuex";
import axios from "axios";
import "moment/locale/ko";
const SERVER_URL = "http://localhost:8080";

export default {
  data() {
    return {
      detail: false,
      likes: 0,
      isLiked:null
    };
  },
  props: {
    item: Object,
  },
  methods: {
    detail_view() {
      this.detail = true;
    },
    addLike() {
      let data={
        uid:localStorage.getItem("uid"),
        pid:this.item.pid,
      }
      this.likes+=1;
      this.isLiked=true;
      this.$store.dispatch('boardStore/addLike',data);
    },
    checkLike() {
      let uid=localStorage.getItem("uid");
      let pid=this.item.pid;
      axios({
        method: "get",
        url: `${SERVER_URL}/likes/checklikes/${uid}/${pid}`,
      })
        .then((res) => {
          if (res.data.message == "success") {
            this.isLiked=true;
          } else if (res.data.message == "fail") {
            this.isLiked=false;
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
  },
  created() {
    this.countLike();
    this.checkLike();
  },

  computed: {
    ...mapGetters({
      getIsLogined: "userStore/getIsLogined",
    }),
  },
  filters: {
    category: function(value) {
      if (!value) return "";
      else if (value === 1) {
        return "Sport";
      } else if (value === 2) {
        return "Cook";
      } else if (value === 3) {
        return "Travel";
      } else if (value === 4) {
        return "New Skill";
      } else if (value === 5) {
        return "Culture";
      } else {
        return "Etc";
      }
    },
    dateFilter: function(date) {
      return moment(date).fromNow();
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Gothic:wght@700;800&display=swap");
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.8;
  position: absolute;
  width: 100%;
}

.v-application--wrap {
  min-height: 100vh;
}

#title,
#name,
#category,
#detail {
  font-family: "Nanum Gothic", sans-serif;
}

.v-dialog::-webkit-scrollbar {
  display: none;
}
</style>
