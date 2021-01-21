import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

const SERVER_URL="http://localhost:8080";

export default new Vuex.Store({
    state:{
        accessToken:null,
        isLogined:false,
        isSign:false,
        user:{
            uid:"",
            email:"",
            name:"",
            phone:""
        }
    },
    mutations:{
        setUser(state,payload){
            state.isLogined=true;
            state.accessToken=payload["access-token"];
            state.user.uid=payload["user"].uid;
            state.user.email=payload["user"].email;
            state.user.name=payload["user"].name;
            state.user.phone=payload["user"].phone;
        },
        setIsSign(state,payload){
            state.isSign=payload;
        }

    },
    getters:{
        getIsuser(state){
            return state.isUser;
        },
    },
    actions:{
        login(context,user){
            axios({
                method: "post",
                url: `${SERVER_URL}/account/login`,
                data: {
                   email: user.email,
                   password: user.password,
                },
           })
          .then((response) => {
            localStorage.setItem("access_token", response.data["access-token"])
            localStorage.setItem("access_id", response.data["user"].uid)
            localStorage.setItem("access_name", response.data["user"].name)
            context.commit("setUser",response.data);
            axios.defaults.headers.common["auth-token"]=`${response.data["access-token"]}`;
            console.log(response.data["user"]);
          }).catch((error) => {
            alert("로그인 실패");
            console.log(error);
          })
        },

        SIGNUP(context, credentials){
            axios.post(`${SERVER_URL}/account/signup`, credentials)
              .then((response)=>{
                console.log(response.data.message);
                context.commit("setIsSign",response.data.message);
                alert('회원가입 성공')
                .then(()=>this.$router.push("/user/login"))
              })
              .catch((err) => {
                alert("회원가입 실패")
                console.log(err)
              })
        }
    }
})