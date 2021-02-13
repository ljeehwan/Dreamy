<template>
  <v-sheet height="270" width="450">
    <v-list class="my-0">
      <v-text-field
        label="comment"
        dense
        append-icon="mdi-message-text-outline"
      >
      </v-text-field>
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
              <v-btn icon small @click="deleteReply"
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
      
    };
  },
  props:{
    pid:Number,
    uid:Number,
  },
  methods: {
    deleteReply() {},
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
  },
  watch: {
    getReply() {
      return this.$store.getters["boardStore/getReply"];
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
