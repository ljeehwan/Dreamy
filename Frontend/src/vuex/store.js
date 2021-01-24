import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"
import {router} from "@/routes.js"
import {requestJoinMember, setSnackBarInfo} from "../apis/accounts_api.js"

Vue.use(Vuex)

const SERVER_URL="http://localhost:8080";

export default new Vuex.Store({
    state:{
        isLogined:false,
        isSign:false,
        user:{
            uid:"",
            email:"",
            name:"",
            phone:""
        },
        // 로딩 관련~~~~~~
        loadingState: false,
        spinnerLoading: false,
        snackbar: {open: false, text: '', location: 'top',},
        modal: {open: false, title: '', content: '', option1: '', option2: '',},
        errorMessage: '잘못된 요청입니다'
    },
    mutations:{
        setUserinfo(state,payload){
            state.isLogined=true;
            state.user.uid=payload["user"].uid;
            state.user.email=payload["user"].email;
            state.user.name=payload["user"].name;
            state.user.phone=payload["user"].phone;
        },
        setKakaoUser(state,payload){
            state.user.email=payload.email;
            state.user.name=payload.name;
        },
        logout(state){
            state.accessToken = null;
            state.user.uid = "";
            state.user.email = "";
            state.user.name="";
            state.user.phone= "";
        },
        setIsSign(state,payload){
            state.isSign=payload;
        },
        // COMMON적으로 이용할 것들. -> loading
        CLOSE_MODAL(state) {
            state.modal.open = false
        },
        OPEN_MODAL(state, modalTexts) {
            state.loadingState = false,
            state.modal.title = modalTexts.title
            state.modal.content = modalTexts.content
            state.modal.option1 = modalTexts.option1
            state.modal.option2 = modalTexts.option2 ? modalTexts.option2 : null;
            state.modal.open = true
        },
        START_LOADING(state) {
            state.loadingState = true
        },
        END_LOADING(state) {
            state.loadingState = false
        },
        SET_SNACKBAR(state, snackbarInfo) {
            state.snackbar.open = true;
            state.snackbar.text = snackbarInfo.text
            state.snackbar.color = snackbarInfo.color
            state.snackbar.location = snackbarInfo.location
        },
        START_SPINNER(state) {
            state.spinnerLoading = true
        },
        END_SPINNER(state) {
            state.spinnerLoading = false
        },

    },
    getters:{
        getIsLogined(state){
            return state.isLogined;
        },
        getUsername(state){
            return state.user.name;
        },
        getEmail(state){
            return state.user.email;
        }
    },
    actions:{
        //로그인성공시 토큰 정보 받기
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
            localStorage.setItem("isLogin", true)
            axios.defaults.headers.common["access-token"]=`${response.data["access-token"]}`;
            router.go(router.currentRoute);
            context.dispatch("getUserinfo");
          }).catch((error) => {
            alert("이메일과 비밀번호를 확인하세요");
            console.log(error);
          })
        },

        // 받은 토큰으로 유저 정보 가져오기
        getUserinfo(context){
            let token=localStorage.getItem("access_token")
            axios({
                method: "post",
                url: `${SERVER_URL}/account/checkJwt`,
                data : token,
            }).then((response)=>{
                context.commit("setUserinfo",response.data);
                // console.log(response.data);
            }).catch(()=>{
                alert("jwt 인증 오류");
            })
        },

        getkakaoUserinfo(context,user){
            axios({
                method:"post",
                url:`${SERVER_URL}/account/checkUser`,
                data: {
                    email: user.email,
                }
              }).then((res)=>{
                  context.commit("setKakaoUser",user);
                  console.log(res);
                  if(res.data["message"]==="needLogin"){
                    alert("계정이 이미 존재합니다! 로그인해주세요")
                  }
                  else if(res.data["message"]==="needSignup"){
                    alert("해당 소셜로 등록된 계정이 존재하지 않습니다! 회원가입해주세요")
                    router.push("/user/join");
                  }
              }).catch((error)=>{
                  console.log(error.message)
              })
        },

        logout(context){
            localStorage.removeItem("access_token");
            localStorage.removeItem("isLogin");
            location.reload();
            context.commit("logout");
            axios.defaults.headers.common["auth-token"] = undefined;
        },

        async SIGNUP(context, credentials){
            try {
                context.commit('START_LOADING')
                context.commit('START_SPINNER')
                const response = await requestJoinMember(credentials)  
                setTimeout(function () {
                    context.commit('SET_SNACKBAR', setSnackBarInfo('회원가입이 완료되었습니다.', 'info', 'top'))
                    context.commit('END_SPINNER')
                    context.commit('END_LOADING')
                    router.push('/')
                    return response                    
                }, 2000)
            } catch (e) {
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원가입 실패', content: e.response.data.message, option1: '닫기',})
            }
        },


    }
})