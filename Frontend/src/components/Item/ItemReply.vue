<template>
  <v-sheet max-height="600" width="460">

    <!-- 댓글 리스트 시작 -->
      <v-row class="ma-2">
      <v-text-field
        label="댓글 작성"
        dense
        v-model="content"
        :append-icon="content ? 'mdi-send' : 'mdi-message-text-outline'"
        clear-icon="mdi-close-circle"
        @click:append="addReply"
        @click:clear="this.content=''"
        clearable
        text
        :rules="[v => (v && v.length <= 50) || '50자 이내로 작성해주세요']"
        required
        @keyup.enter="addReply"
      >
      </v-text-field>
      </v-row>
      <div id="fonttest" v-if="getReply.length===0">
          등록된 댓글이 없습니다!
      </div>
      <!-- 댓글 내용 리스트 ~~~~~~ -->
    <v-list class="my-2">

        <v-list-item
          dense
          v-for="reply in pagingList"
          v-bind:key="reply.rid"
        >
          <v-item-content style="width: 100%;" class="pa-1">

            <v-list-item-title class="d-flex justify-space-between mb-1">
              <span>
                <b>{{ reply.name }}</b> 
                <v-icon right class="ml-1">mdi-thought-bubble-outline</v-icon>
                 <v-icon right color="red darken-1 ml-9" 
                @click="deleteReply(reply.rid)"
                v-if="uid == reply.uid"
                >
                  mdi-delete-empty
                </v-icon>
              </span>
              
              <span class=" font-weight-light " color="grey">
                {{reply.writtenDate | replyFilter}}
              </span>
            </v-list-item-title>

              <span class="d-flex justify-start ">
                {{ reply.content }}
                
              </span>

          </v-item-content>

        </v-list-item>

    </v-list>
    
    <!-- 페이지네이션 시작 -->
     <v-pagination v-model="curPage" :length="pages" circle></v-pagination>
  </v-sheet>
</template>

<script>
import moment from "moment";
import "moment/locale/ko";
export default {
  data() {
    return {
      content:"",
      dataPerPage:4,
      replyList:[],
      curPage:1,
    };
  },
  props:{
    pid:Number,
    uid:Number,
  },
  methods: {
    addReply() {
      let data={
        pid:this.pid,
        uid:this.uid,
        name:this.getUsername,
        content:this.content,
      }
    this.$store.dispatch("boardStore/addReply", data);
    this.content='';
    },
    deleteReply(rid) {
      let data={
        pid:this.pid,
        rid:rid
      }
      this.$store.dispatch("boardStore/deleteReply",data);
    },
  },
  filters: {
    replyFilter: function(date) {
      return moment(date).fromNow();
    },
  },
  computed: {
    getReply() {
      return this.$store.getters["boardStore/getReply"];
    },
    getUsername(){
      return this.$store.getters["userStore/getUsername"]
    },
    startOffset() {
        return ((this.curPage - 1) * this.dataPerPage);
    },
    endOffset() {
        return (this.startOffset + this.dataPerPage);
    },
    pages(){
         return Math.ceil(this.getReply.length / this.dataPerPage);
    },
    pagingList(){
        return this.getReply.slice(this.startOffset,this.endOffset);
    },
  },
  
};
</script>

<style>
#reply {
  font-size: 12px;
}
#reply:hover {
  background-color: #eeeeee;
}
</style>
