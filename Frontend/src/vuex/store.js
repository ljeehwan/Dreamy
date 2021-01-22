import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

import {router} from "@/routes.js"
import {requestJoinMember, setSnackBarInfo} from "../apis/accounts_api.js"

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
        },
        // 로딩 관련~~~~~~
        loadingState: false,
        spinnerLoading: false,
        snackbar: {open: false, text: '', location: 'top',},
        modal: {open: false, title: '', content: '', option1: '', option2: '',},
        errorMessage: '잘못된 요청입니다'
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