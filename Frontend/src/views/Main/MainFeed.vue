<template>
  <div class="mb-10">
    <v-layout row wrap class="ma-10 align-center justify-center">
    <feed-item v-for="(item,index) in list" v-bind:key="item.pid" v-bind:item="list[index]" />
    </v-layout>
    <infinite-loading
      @infinite="infiniteHandler"
      spinner="circles"
    ></infinite-loading>
    <Upload v-if="getIsLogined" />
  </div>
</template>
<script>
import FeedItem from "@/components/FeedItem.vue";
import Upload from "@/components/uploadItem.vue";
import InfiniteLoading from "vue-infinite-loading";
import axios from "axios";
import { mapGetters } from "vuex";

const SERVER_URL = "http://localhost:8080";

export default {
  components: {
    FeedItem,
    Upload,
    InfiniteLoading,
  },
  data() {
    return {
      list: [],
      limit: 0,
      uid:0,
    };
  },

  methods: {
    infiniteHandler($state) {
      this.uid=this.getUserId;
      axios({
        method: "get",
        url: `${SERVER_URL}/board/list/${this.limit}`,
        params:{
          uid:this.uid,
        }
      })
        .then((res) => {
          console.log(this.list);
          setTimeout(()=>{
            if (res.data.totalSize > this.limit) {
              const data = res.data.list;
              for (let key in data) {
                this.list.push(data[key]);
              }
              this.limit += 3;
              $state.loaded();
            } else {
              $state.complete();
            }
          },1000)
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    ...mapGetters({
      getIsLogined: "userStore/getIsLogined",
      getUserId:"userStore/getUserId"
    }),
  },
  watch:{
    list(){
      return this.list;
    }
  }
};
</script>

<style></style>
