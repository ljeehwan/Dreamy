<template>
  <v-app-bar color="white" app rounded single-line elevation="0">
    <router-link to="/" style="text-decoration:none;">
    <div class="logo" transition="scale-transition">
      <div class="header-container" transition="scale-transition">
        <div class="content" transition="scale-transition">
          <h2>DREAMY</h2>
          <h2>DREAMY</h2>
        </div>
      </div>
    </div>
    </router-link>

    <v-spacer> </v-spacer>

      <!-- 검색 인풋 창 -->
      <v-flex shrink>
        <v-expand-x-transition>
          <div v-show="expand" class="search-box">
          <input type="text" class="search-txt" name="" placeholder="Type to search"
          @keydown.enter="onSearch" v-model="searchMsg">
          </div>
        </v-expand-x-transition>
      </v-flex>

   <v-btn id="search-icon" @click="expand=!expand" icon class="mx-2 my-auto">
      <v-icon size="26">
        mdi-magnify
      </v-icon>
    </v-btn>
    
    <hr
      role="separator"
      aria-orientation="vertical"
      class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light"
      style="height: 20px;"
    />


    <div v-if="getIsLogined">
      <span
        ><router-link to="/mainfeed" style="text-decoration:none;">
          <v-btn target="_blank" text style="width:120px;"
            >feed
          </v-btn></router-link
        ></span
      >
    </div>
   
    <div id="right" v-if="!getIsLogined">
      <span
        ><router-link to="/user/join" style="text-decoration:none;">
          <v-btn target="_blank" text style="width:120px;"
            >SIGNUP
          </v-btn></router-link
        ></span
      >
      <hr
        role="separator"
        aria-orientation="vertical"
        class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light"
        style="height: 20px;"
      />

      <loginComponent></loginComponent>
    </div>

    <div id="right" v-if="getIsLogined">
         
    <hr
      role="separator"
      aria-orientation="vertical"
      class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light"
      style="height: 20px;"
    />
   <loginUser />
    </div>
  </v-app-bar>
</template>

<script>
import loginComponent from "./Login/Login.vue";
import LoginUser from "./Login/LoginUser.vue";

export default {
  data: () => ({
    expand:false,
    searchMsg: "",
  }),
  components: {
    loginComponent,
    LoginUser,
  },
  computed: {
    getIsLogined() {
      return this.$store.getters["userStore/getIsLogined"];
    },
  },
  methods: {
    onSearch () {
      // console.log(this.searchMsg)
      this.$emit("searchMsg", this.searchMsg)
      this.searchMsg = ""
    },
  },
};
</script>

<style>
input::placeholder {
  font-size: 13px;
}
.search-box{
  padding: 10px;
  position: relative;
  transform: translate(0%,15%);
  height: 30px;
  background-color: lightgrey;
  border: 1px solid grey;
  border-radius: 30px;
  transition: 0.4s;
  width:220px;
}
.search-txt{
  position: relative;
  bottom: 10px;
  display: flex;
  border:none;
  background: none;
  outline: none;
  float: left;
  font-size: 1rem;
  line-height: 30px;
  transition: .4s;
  width: 220px;
  padding: 0 6px;
}
</style>
