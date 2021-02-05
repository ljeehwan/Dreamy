import axios from "axios"
import {router} from "@/routes.js"
import {requestJoinMember, setSnackBarInfo, requestUpdateMember,
        requestDeleteMember,} from "@/apis/accounts_api.js"
// requestMemberInfo,

const SERVER_URL="http://localhost:8080";

const userStore={
    namespaced:true,

    state:{
        followStatus: false,
        newRequest: false,
        requestUid: '',
        targetUser: {
            uid: '',
            name: '',
            email: '',
            phone: '',
            follower: '',
            following: '',
            profileUrl: '',
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
            follower: '',
            following: '',
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
            localStorage.setItem("uid",payload["user"].uid);
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
        PUT_REQUEST_UID(state, uid) {
            state.requestUid = uid
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
            state.targetUser.profileUrl = targetInfo.profileUrl
            console.log(state.targetUser)
        },
        PUT_TARGET_FOLLOWER(state, targetFollower) {
            state.targetUser.follower = targetFollower
        },
        PUT_TARGET_FOLLOWING(state, targetFollowing) {
            state.targetUser.following = targetFollowing
        },
        NEW_REQUEST(state) {
            console.log(state.newRequest)
            state.newRequest = !state.newRequest
        },
        TRUE_FOLLOW(state) {
            console.log(state.followStatus)
            state.followStatus = true
        },
        FALSE_FOLLOW(state) {
            state.followStatus = false
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
        getTargetUid(state){
            return state.requestUid;
        },
        getTargetInfo(state){
            return state.targetUser;
        },
        getMyself(state) {
            return state.isMyself;
        },
        getTargetFollower(state) {
            return state.targetUser.follower;
        },
        getNewRequest(state) {
            return state.newRequest;
        },
        getFollowStatus(state) {
            return state.followStatus
        },
        getFollower(state) {
            return state.targetUser.follower
        },
        getFollowing(state) {
            return state.targetUser.following
        },
        getTargetPhone(state) {
            return state.targetUser.phone
        },
        getTargetImg (state) {
            return state.targetUser.profileUrl
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
                    context.dispatch("socialSignup",user);
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

        socialSignup(context,user){
            axios({
                method:"post",
                url:`${SERVER_URL}/account/signup`,
                data:{
                    email:user.email,
                    name:user.name,
                    loginType:user.logintype,
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
            router.push('/');
            window.location.reload();
        },

        //비밀번호 찾기
        findpassword(context,user){
            axios({
                method: "post",
                url: `${SERVER_URL}/account/updatePassword`,
                data: {
                   email: user.email,
                   phone: user.phone,
                },
           })
          .then((response) => {
            if(response.data["message"]=="success"){
                alert("임시 비밀번호가 이메일로 발송되었습니다!")
              }
              else if(response.data["message"]=='fail'){
                alert("입력하신 정보를 다시 확인해주세요!");
              }
          }).catch((error)=>{
              console.log(error.message)
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
                    router.push('/')
                    return response                    
                }, 1000)
            } catch (e) {
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원가입 실패', content: e.response.data.message, option1: '닫기',})
            }
        },
        async UPDATE_MEMBER(context, credentials){
            try {
                context.commit('START_LOADING')
                context.commit('START_SPINNER')
                const userId = this.state.userStore.user.uid
                console.log(`스토어 진입성공 : ${userId}`)
                const response = await requestUpdateMember(credentials, userId)
                console.log(response)
                setTimeout(function () {
                    console.log('셋 타임아웃 시작')
                    context.commit('END_LOADING')
                    context.commit('END_SPINNER')
                    // 타겟 정보가 바꼈다는 표시
                    context.commit('NEW_REQUEST')
                    context.commit('SET_SNACKBAR', setSnackBarInfo('수정이 완료되었습니다.', 'primary', 'top'))
                    // 어디로 보낼지 다시 정해야함
                    
                    return response                    
                }, 500)
            } catch (e) {
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원 수정 실패', content: e.response.data.message, option1: '닫기',})
            }
        },
        // 프로필 사진을 클라우드에 등록 
        UPDATE_MEMBER_IMG(context, profileUrl) {
            const myUid = this.state.userStore.user.uid
            console.log(`${SERVER_URL}/user/imageupload/${myUid}`)
            //
            let formData = new FormData();
            formData.append("files", profileUrl);
            axios.post(`${SERVER_URL}/user/imageupload/${myUid}`, formData,
            {headers: {
                "Content-Type" : "multipart/form-data"
            }})
              .then(res => {
                  console.log(res.data.imgPath)
              })
              .catch(err => {
                  console.log(err)
                  console.log('클라우드 등록 실패')
              })
        },

        // 회원 상세 정보 요청 
        GET_MEMBER(context, targetUid) {
            // 일단 요청하는 방식 uid 말고 name으로 바꿔달라고 해서 요청보내서 응답을 받아온다.
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.get(`${SERVER_URL}/account/user/${targetUid}`)
            .then(res => {
                console.log(res)
                context.commit('END_SPINNER')
                context.commit('END_LOADING')
                context.commit('SET_SNACKBAR', setSnackBarInfo('상세 정보 요청이 완료 되었습니다.', 'info', 'bottom'))
                const targetInfo = {uid: res.data.userInfo.uid, email: res.data.userInfo.email,
                name: res.data.userInfo.name, phone: res.data.userInfo.phone,
                profileUrl: res.data.userInfo.profileUrl,}
                context.commit('PUT_TARGET_INFO', targetInfo)
            })
            .catch(err => {
                context.commit('END_SPINNER')
                context.commit('END_LOADING')
                context.commit('OPEN_MODAL', {title: '회원 정보 요청 실패', content: err, option1: '닫기',})
            })
        },



        async DELETE_MEMBER(context) {
            try {
                context.commit('START_LOADING')
                context.commit('START_SPINNER')
                const userId = this.state.userStore.user.uid
                console.log(`삭제 스토어 진입성공 : ${userId}`)
                const response = await requestDeleteMember(userId)
                console.log(`회원 삭제 리스폰스 ${response}`)
                context.dispatch('logout')
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
                context.commit('OPEN_MODAL', {title: '회원 수정 실패', content: e.response.data, option1: '닫기',})
            }
            
        },
        GET_FOLLOWER_NUM(context) {
            const targetUid = this.state.userStore.requestUid      
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.get(`${SERVER_URL}/follow/countfollower/${targetUid}`)
            .then(res => {
                context.commit('PUT_TARGET_FOLLOWER', res.data.count)
                context.commit('END_SPINNER')
                context.commit('END_LOADING')
                // 정보 받아와서 state에 저장하고 Mypage.vue의 computed에서
                // 보내준다
            })
            .catch(err => {
                console.log(err)
                context.commit('END_SPINNER')
                context.commit('END_LOADING')
            })
        },
        GET_FOLLOWING_NUM(context) {
            const targetUid = this.state.userStore.requestUid
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.get(`${SERVER_URL}/follow/countfollowing/${targetUid}`)
              .then(res => {
                  context.commit('PUT_TARGET_FOLLOWING', res.data.count)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
              })
              .catch(err => {
                  console.log(err)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
              })
        },
        REQUEST_FOLLOW(context) {
            const requestUid = this.state.userStore.requestUid
            const credentials= {
                'followingUid': this.state.userStore.user.uid,
                'followUid' : requestUid
            }
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.post(`${SERVER_URL}/follow/requestfollow`, credentials)
              .then(res => {
                  console.log(res)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
                  context.commit('TRUE_FOLLOW')
              })
              .catch(err => {
                  console.log(err)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
              })
        },
        REQUEST_UNFOLLOW(context) {
            const requestUid = this.state.userStore.requestUid
            const userId = this.state.userStore.user.uid
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.delete(`${SERVER_URL}/follow/unfollow/${userId}/${requestUid}`)
              .then(res => {
                  console.log(res)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
                  context.commit('FALSE_FOLLOW')
              })
              .catch(err => {
                  console.log(err)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
              })
        },
        // api 수정중...
        CHECK_FOLLOW(context) {
            const requestUid = this.state.userStore.requestUid
            const userId = this.state.userStore.user.uid
            context.commit('START_LOADING')
            context.commit('START_SPINNER')
            axios.get(`${SERVER_URL}/follow/checkfollow/${userId}/${requestUid}`)
              .then(res => {
                  console.log('관계 확인')
                  console.log(res.data.message)
                  if (res.data.message === 'success') {
                      context.commit('TRUE_FOLLOW')
                  } else {
                      context.commit('FALSE_FOLLOW')
                  }
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
              })
              .catch(err => {
                  console.log(err)
                  context.commit('END_SPINNER')
                  context.commit('END_LOADING')
              })
        },

    }
}
export default userStore