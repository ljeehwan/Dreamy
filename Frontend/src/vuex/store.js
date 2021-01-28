import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"
import {router} from "@/routes.js"
import {requestJoinMember, setSnackBarInfo, requestUpdateMember,
        requestDeleteMember,} from "../apis/accounts_api.js"
// requestMemberInfo,
Vue.use(Vuex)

const SERVER_URL="http://localhost:8080";

export default new Vuex.Store({
    state:{
        targetName: '',
        targetUser: {
            uid: '',
            name: '',
            email: '',
            phone: '',
        },
        isMyself: false,
        isLogined:false,
        isSign:false,
        user:{
            uid:"",
            email:"",
            name:"",
            phone:"",
            logintype:"",
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
            state.user.logintype=payload["user"].loginType;
        },
        setSocialUser(state,payload){
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
        TARGET_NAME(state, name) {
            state.targetName = name
        },
        MYSELF(state) {
            state.isMyself = true
        },
        NOT_ME(state){
            state.isMyself = false
        },
        PUT_TARGET_INFO(state, targetInfo) {
            state.targetUser.uid = targetInfo.uid
            state.targetUser.email = targetInfo.email
            state.targetUser.name = targetInfo.name
            state.targetUser.phone = targetInfo.phone
        },
    },
    getters:{
        getIsLogined(state){
            return state.isLogined;
        },
        getUserId(state) {
            return state.user.uid
        },
        getUsername(state){
            return state.user.name;
        },
        getEmail(state){
            return state.user.email;
        },
        getPhone(state){
            return state.user.phone;
        },
        getLogintype(state){
            return state.user.logintype;
        },
        getTargetName(state){
            return state.targetName;
        },
        getTargetInfo(state){
            return state.targetUser;
        },
        getMyself(state) {
            return state.isMyself;
        },
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
                console.log(response.data);
                context.commit("setUserinfo",response.data);
            }).catch(()=>{
                alert("jwt 만료");
            })
        },

        getSocialUserinfo(context,user){
            axios({
                method:"post",
                url:`${SERVER_URL}/account/checkUser`,
                data: {
                    email: user.email,
                    name:user.name,
                    loginType:user.logintype
                }
              }).then((response)=>{
                  if(response.data["message"]=="otherSocialLogin"){
                    alert("해당 계정이 이미 다른 플랫폼으로 가입되어있습니다")
                  }
                  else if(response.data["message"]=='needSignup'){
                    context.commit("setSocialUser",user);
                    context.dispatch("socialSignup",user.logintype);
                    alert("자동 회원가입 완료! 초기 비밀번호를 꼭 수정해주세요");
                  }
                  //자동 로그인
                  else if(response.data["message"]=="success"){ 
                    localStorage.setItem("access_token", response.data["access-token"])
                    localStorage.setItem("isLogin", true)
                    axios.defaults.headers.common["access-token"]=`${response.data["access-token"]}`;
                    context.dispatch("getUserinfo");
                  }
              }).catch((error)=>{
                  console.log(error.message)
              })
        },

        socialSignup(context,type){
            let user={
                email:this.state.user.email,
                password:"",
                name:this.state.user.name,
            }
            axios({
                method:"post",
                url:`${SERVER_URL}/account/signup`,
                data:{
                    email:user.email,
                    name:user.name,
                    loginType:type,
                }
            }).then((res)=>{
                user.password=res.data["userInfo"].password;
                context.dispatch("login",user);
            }).catch((error)=>{
                console.log(error);
                alert(error.data["message"]);
            })
        },

        logout(context){
            localStorage.clear();
            context.commit("logout");
            axios.defaults.headers.common["access-token"] = undefined;
            window.location.reload();
        },  

        insertBoard(context,card){
            axios({
                method:"post",
                url:`${SERVER_URL}/board/insert`,
                data:card
            })
            .then((res)=>{
               console.log(res);
               alert("등록 성공");
               window.location.reload();
            }).catch((error)=>{
                console.log(error);
            })
        },


        async SIGNUP(context, credentials){
            try {
                context.commit('START_LOADING')
                context.commit('START_SPINNER')
                const response = await requestJoinMember(credentials)  
                setTimeout(function () {
                    context.commit('SET_SNACKBAR', setSnackBarInfo('회원가입이 완료되었습니다.', 'primary', 'top'))
                    context.commit('END_SPINNER')
                    context.commit('END_LOADING')
                    router.push('/main')
                    return response                    
                }, 2000)
            } catch (e) {
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원가입 실패', content: e.response.data.message, option1: '닫기',})
            }
        },
        async UPDATE_MEMBER(context, credentials){
            try {
                context.commit('START_LOADING')
                context.commit('START_SPINNER')
                const userId = this.state.user.uid
                console.log(`스토어 진입성공 : ${userId}`)
                const response = await requestUpdateMember(credentials, userId)
                console.log(response)
                setTimeout(function () {
                    console.log('셋 타임아웃 시작')
                    context.commit('END_LOADING')
                    context.commit('END_SPINNER')
                    context.commit('SET_SNACKBAR', setSnackBarInfo('수정이 완료되었습니다.', 'primary', 'top'))
                    // 어디로 보낼지 다시 정해야함
                    
                    return response                    
                }, 500)
            } catch (e) {
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원 수정 실패', content: e.response.data.message, option1: '닫기',})
            }
        },
        // 회원 상세 정보 요청 
        GET_MEMBER(context, targetName) {
            // 일단 요청하는 방식 uid 말고 name으로 바꿔달라고 해서 요청보내서 응답을 받아온다.
            console.log(`${SERVER_URL}/account/user/${targetName}`)
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.get(`${SERVER_URL}/account/user/${targetName}`)
            .then(res => {
                console.log(res)
                context.commit('END_SPINNER')
                context.commit('END_LOADING')
                context.commit('SET_SNACKBAR', setSnackBarInfo('상세 정보 요청이 완료 되었습니다.', 'primary', 'top'))
                const targetInfo = {uid: res.data.userInfo.uid, email: res.data.userInfo.email,
                name: res.data.userInfo.name, phone: res.data.userInfo.phone}
                context.commit('PUT_TARGET_INFO', targetInfo)
            })
            .catch(err => {
                context.commit('END_SPINNER')
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원 정보 요청 실패', content: err, option1: '닫기',})
            })
        },
        // 회원 상세 정보 요청
        GET_TARGET(context, name) {
            context.commit('TARGET_NAME', name)
        },
        async DELETE_MEMBER(context) {
            try {
                context.commit('START_LOADING')
                context.commit('START_SPINNER')
                const userId = this.state.user.uid
                console.log(`삭제 스토어 진입성공 : ${userId}`)
                const response = await requestDeleteMember(userId)
                console.log(`회원 삭제 리스폰스 ${response}`)
                setTimeout(function () {
                    console.log('셋 타임아웃 시작')
                    context.commit('END_LOADING')
                    context.commit('END_SPINNER')
                    context.commit('SET_SNACKBAR', setSnackBarInfo('회원 탈퇴가 완료되었습니다.', 'primary', 'top'))
                    // 어디로 보낼지 다시 정해야함
                    
                    return response                    
                }, 500)
            } catch (e) {
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원 수정 실패', content: e.response.data.message, option1: '닫기',})
            }
            
        },
    }
})