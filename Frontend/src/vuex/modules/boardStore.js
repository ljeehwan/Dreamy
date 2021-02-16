import axios from "axios"
// import {router} from "@/routes.js"

const SERVER_URL="http://localhost:8080";

const boardStore={
    namespaced:true,

    state:{
        replyList:[],
        partiList:[],
        partitotal:null,
    },
    getters:{
       getReply(state){
        return state.replyList;
       },
       getParticipate(state){
        return state.partiList;
       },
       getPartiTotal(state){
           return state.partitotal;
       }
    },
    mutations:{
        setReply(state){
            state.replyList=[];
        },
        setParticipate(state){
            state.partiList=[];
            state.partitotal=0;
        },
        setReplyList(state,payload){
            for (let key in payload.list) {
                state.replyList.push(payload.list[key]);
            }
        },
        setParticipateList(state,payload){
            for(let key in payload.userList){
            state.partiList.push(payload.userList[key])
            }
            state.partitotal=payload.userTotal;
        }
    },
    actions:{
        insertBoard(context,card){
            axios({
                method:"post",
                url:`${SERVER_URL}/board/insert`,
                data:card
            })
            .then((res)=>{
               console.log(res);
               alert("등록 성공");
            //    router.go(router.currentRoute);
               location.reload();
            }).catch((error)=>{
                console.log(error);
            })
        },
        deleteBoard(context,pid){
            axios({
                method:"delete",
                url:`${SERVER_URL}/board/delete/${pid}`,
            })
            .then((res)=>{
                if(res.data.message==="success")
                    console.log(pid+" "+"삭제 성공")
                window.location.reload();
            })
            .catch((error)=>{
                console.log(error);
            })
        },

        addLike(context,data){
            axios({
                method:"post",
                url:`${SERVER_URL}/likes/addlikes`,
                data:{
                    userid:data.uid,
                    postid:data.pid
                }
            })
            .then((res)=>{
                if(res.data.message==="success")
                    console.log(data.uid+" "+data.pid+" "+"좋아요 성공")
            })
            .catch((error)=>{
                console.log(error);
            })
        },
        deleteLike(context,data){
            axios({
                method:"delete",
                url:`${SERVER_URL}/likes/unlikes/${data.uid}/${data.pid}`,
            }).then((res)=>{
                if(res.data.message==="success")
                    console.log(data.uid+" "+data.pid+" "+"좋아요 취소")
            }).catch((error)=>{
                console.log(error);
            })
        },

        addReply(context,data){
            axios({
                method:"post",
                url:`${SERVER_URL}/reply/insert`,
                data
            }).then((res)=>{
                if(res.data.message==="success"){
                    context.commit('setReply');
                    context.dispatch('getReply',data.pid);
                }
            }).catch((error)=>{
                console.log(error);
            })
        },
        deleteReply(context,data){
            axios({
                method:"delete",
                url:`${SERVER_URL}/reply/delete/${data.rid}`,
            }).then((res)=>{
                if(res.data.message==="success"){
                    context.commit('setReply');
                    context.dispatch('getReply',data.pid);
                }
            }).catch((error)=>{
                console.log(error);
            })
        },
        getReply(context,pid){
            axios({
                method:"get",
                url:`${SERVER_URL}/reply/list/${pid}`,
            })
            .then((res)=>{
                // console.log(res.data);
                context.commit('setReplyList',res.data)
            })
            .catch((error)=>{
                console.log(error);
            })
        },

        getParticipate(context,pid){
            axios({
                method:"get",
                url:`${SERVER_URL}/participate/getUserList/${pid}`,
            })
            .then((res)=>{
                // console.log(res.data);
                context.commit('setParticipateList',res.data);
            })
            .catch((error)=>{
                console.log(error);
            })
        },
        addSuccess(context,data){
            axios({
                method:"put",
                url:`${SERVER_URL}/participate/addSuccess/${data.uid}/${data.pid}`,
            })
            .then((res)=>{
                if(res.data.message==="fail"){
                     alert("성공 체크 실패")
                }
            })
        }

    }
}

export default boardStore