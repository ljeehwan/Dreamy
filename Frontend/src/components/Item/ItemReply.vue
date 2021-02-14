<template>
  <v-sheet max-height="270" width="460">
    <v-list class="my-2">
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
        @keyup.enter="addReply"
      >
      </v-text-field>
      </v-row>
      <div v-if="getReply.length===0">
          등록된 댓글이 없습니다!
      </div>
      <v-list-item-group>
        <v-list-item
          dense
          v-for="reply in getReply"
          v-bind:key="reply.rid"
        >
          <v-list-item>
            <v-list-item-title>{{ reply.name }}</v-list-item-title>
            <v-list-item-subtitle>{{ reply.content }}</v-list-item-subtitle>
            <v-list-item-subtitle>{{
              reply.writtenDate | replyFilter
            }}</v-list-item-subtitle>
            <v-list-item-action v-if="uid == reply.uid">
              <v-btn icon small @click="deleteReply(reply.rid)"
                ><v-icon color="error">mdi-close</v-icon></v-btn
              >
            </v-list-item-action>
          </v-list-item>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-sheet>
</template>

<script>
import moment from "moment";
import "moment/locale/ko";
export default {
  data() {
    return {
      content:""
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
    }
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
