<template>
  <div>
    <v-row class="my-10">
      <!-- <FeedMain /> -->
      <!--     <v-col sm="3">
            <FeedMain/>
        </v-col>
        <v-col sm="3">
            <FeedMain/>
        </v-col>
        <v-col sm="3">
            <FeedMain/>
        </v-col>
        <v-col sm="3">
            <FeedMain/>
        </v-col>
        -->
        <feed-item/>
        <feed-item/>
        <feed-item/>
    </v-row>
    <Upload v-if="getIsLogined" />
  </div>
</template>
<script>
import FeedItem from "./feedItem.vue";
import Upload from "@/components/upload.vue";

import { mapGetters } from "vuex";

export default {
  components: {
    FeedItem,
    Upload,
  },
  computed: {
    ...mapGetters(["getIsLogined"]),
  },
};
</script>

<style></style>
