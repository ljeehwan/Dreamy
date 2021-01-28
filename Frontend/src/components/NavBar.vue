<template>
  <v-app-bar app color="white" rounded single-line elevation="1">
    <v-img
      alt="Dreamy Logo"
      class="shrink"
      contain
      src="http://placehold.it/50"
      transition="scale-transition"
    />

    <!-- <v-img
          alt="Dreamy Name"
          class="shrink"
          contain
         src="http://placehold.it/150x50"
        /> -->

    <div class="logo" transition="scale-transition">
      <div class="header-container" transition="scale-transition">
        <div class="content" transition="scale-transition">
          <h2>DREAMY</h2>
          <h2>DREAMY</h2>
        </div>
      </div>
    </div>

    <v-spacer> </v-spacer>
    <v-btn icon class="mx-2 my-auto">
      <v-icon size="26">
        <!--프로필 이미지 가져오기-->
        mdi-magnify
      </v-icon>
    </v-btn>

    <hr
      role="separator"
      aria-orientation="vertical"
      class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light"
      style="height: 20px;"
    />
    <span
      ><router-link to="/" style="text-decoration:none;">
        <v-btn target="_blank" text style="width:120px;"
          >HOME
        </v-btn></router-link
      ></span
    >
    <hr
        role="separator"
        aria-orientation="vertical"
        class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light"
        style="height: 20px;"
      />

    <div v-if="getIsLogined">
      <span
        ><router-link to="/main" style="text-decoration:none;">
          <v-btn target="_blank" text style="width:120px;"
            >feed
          </v-btn></router-link
        ></span
      >
    </div>
   
    <v-menu open-on-hover bottom offset-y>
      <template v-slot:activator="{ on }">
        <span class="v-icon notranslate hidden-sm-and-down theme--light">
          <v-btn target="_blank" v-on="on" text style="width:120px;"
            >MORE
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              role="img"
              aria-hidden="true"
              class="v-icon__svg"
              style="height: 14px; width: 14px;"
            >
              <path
                d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z"
              ></path>
            </svg>
          </v-btn>
        </span>
      </template>
      <v-list>
        <v-list-item>
          <span
            ><router-link to="/community/Bucket" style="text-decoration:none;">
              <v-btn target="_blank" text style="width:120px;"
                >Bucket List</v-btn
              ></router-link
            ></span
          >
        </v-list-item>

        <v-list-item>
          <span
            ><router-link
              to="/community/challenge"
              style="text-decoration:none;"
            >
              <v-btn target="_blank" text style="width:120px;"
                >Challenge</v-btn
              ></router-link
            ></span
          >
        </v-list-item>

        <v-list-item>
          <span
            ><router-link to="/community/Ranking" style="text-decoration:none;">
              <v-btn target="_blank" text style="width:120px;"
                >Ranking</v-btn
              ></router-link
            ></span
          >
        </v-list-item>
      </v-list>
    </v-menu>
    <hr
      role="separator"
      aria-orientation="vertical"
      class="mx-2 my-auto v-divider v-divider--inset v-divider--vertical theme--light"
      style="height: 20px;"
    />

    <div v-if="!getIsLogined">
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

    <div v-if="getIsLogined">
      <loginUser />
    </div>
  </v-app-bar>
</template>

<script>
import loginComponent from "./Login.vue";
import LoginUser from "./LoginUser.vue";

export default {
  data: () => ({
    searchWord: "",
  }),
  components: {
    loginComponent,
    LoginUser,
  },
  computed: {
    getIsLogined() {
      return this.$store.getters.getIsLogined;
    },
  },
};
</script>

<style>
input::placeholder {
  font-size: 13px;
}
</style>
