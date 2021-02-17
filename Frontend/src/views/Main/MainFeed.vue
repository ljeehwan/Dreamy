<template>

  <div class="mt-15">
    <NavBar @searchMsg="requestSearch" />
    <feed-menu id="menubar" @clickType="changeType"/>
    <v-divider></v-divider>
    <v-layout row wrap class="mb-10 mx-10 align-center justify-center">
      <feed-item
        v-for="(item, index) in list"
        v-bind:key="item.pid"
        v-bind:item="list[index]"
      />
    </v-layout>
    <infinite-loading
      @infinite="infiniteHandler"
      spinner="circles"
      ref="infiniteLoading"
    ></infinite-loading>
    <Upload v-if="getIsLogined" />
  </div>
</template>
<script>
import FeedMenu from "@/components/Item/FeedMenu.vue";
import Upload from "@/components/Item/UploadItem.vue";
import axios from "axios";
import InfiniteLoading from "vue-infinite-loading";
import FeedItem from "@/components/Item/FeedItem.vue";
// import FeedList from "./feedList";
import { mapGetters } from "vuex";
import NavBar from '@/components/NavBar.vue'

const SERVER_URL = "http://localhost:8080";
export default {
  components: {
    // FeedMenu,
    Upload,
    // FeedList
    InfiniteLoading,
    FeedItem,
    FeedMenu,
    NavBar,
  },
  data() {
    return {
      list: [],
      limit: 0,
      uid: 0,
      type: '',
      searchingStatus : false,
    };
  },
  methods: {
    infiniteHandler($state) {
      let id = localStorage.getItem("uid");
      if (this.searchingStatus === false) {
        axios({
          method: "get",
          url: `${SERVER_URL}/board/list/${this.type}/${this.limit}`,
          params: {
            uid: id,
          },
        })
          .then((res) => {
            // console.log(res)
            console.log(this.list);
            setTimeout(() => {
              if (res.data.totalSize>this.limit) {
              let data=res.data.list
              for (let key in data) {
                  this.list.push(data[key]);
              }
              this.limit+=3;
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 1000);
          })
          .catch((error) => {
            console.log(error);
          }) 
      } else {
          axios({
            method: "get",
            url: `${SERVER_URL}/board/search/${this.type}/${this.limit}`,
            params: {
              uid: id,
            },
          })
          
          .then((res) => {
            console.log(res)
            console.log(this.list);
            setTimeout(() => {
              if (res.data.totalSize>this.limit) {
              let data=res.data.list
              for (let key in data) {
                  this.list.push(data[key]);
              }
              this.limit+=3;
                $state.loaded();
              } else {
                $state.complete();
                this.searchingStatus = false
              }
            }, 1000);
            })
          .catch((error) => {
            console.log(error);
          }) 
      }
    },
    changeType(val) {
      this.type = val;
    },
    requestSearch (searchText) {
      // console.log(searchText)
      this.searchingStatus = true
      this.type = searchText
      console.log(this.type)
    },
  },
  computed: {
    ...mapGetters({
      getIsLogined: "userStore/getIsLogined",
    }),
  },
  watch: {
    type() {
      this.list=[];
      this.limit=0;
      this.$refs.infiniteLoading.stateChanger.reset();
    },
  },
};
</script>

<style scoped>
#menubar{
  /* position: fixed; */
  z-index: 1;
  background-color: white;
  width:100%;
}
</style>
