<template>
    <GoogleLogin
      class="google-login-button"
      :params="params"
      :onSuccess="onSuccess"
      :onFailure="onFailure"
    >
    <v-btn icon elevation="3" outlined>
         <img src="@/assets/snsLogin/google.png" alt="google"/>
    </v-btn>
    </GoogleLogin>
</template>

<script>
import GoogleLogin from "vue-google-login";
export default {
  components: {
    GoogleLogin,
  },
  data() {
    return {
      params: {
        client_id:
          "59282748193-2ftph4gilb0g6vfnuentcfkr1c2oiirk.apps.googleusercontent.com",
      },
    };
  },
  methods: {
    onSuccess(googleUser) {
      var auth = window.gapi.auth2.getAuthInstance();
      if (auth.isSignedIn.get()) {
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId());
        console.log("Name: " + profile.getName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        const user = {
          email: profile.getEmail(),
          name: profile.getName(),
          logintype: "google",
        };
        this.$store.dispatch("userStore/getSocialUserinfo", user);
      }
    },
    onFailure() {
      console.log("login fail");
    },
  },
};
</script>

<style>
.v-btn{
border:1px
}
</style>
