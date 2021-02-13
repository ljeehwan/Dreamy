import axios from "axios"
// import {router} from "@/routes.js"

const SERVER_URL="http://localhost:8080";

const boardStore={
    namespaced:true,

    state:{
        replyList:[]
    },
    getters:{
       getReply(state){
        return state.replyList;
       }
    },
    mutations:{
        setDefault(state){
            state.replyList=[]
        },
        setReplyList(state,payload){
            let data=payload.list
            for (let key in data) {
                state.replyList.push(data[key]);
            }
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

        getReply(context,pid){
            axios({
                method:"get",
                url:`${SERVER_URL}/reply/list/${pid}`,
            })
            .then((res)=>{
                console.log(res.data);
                context.commit('setReplyList',res.data)
            })
            .catch((error)=>{
                console.log(error);
            })
        }
        

    }
}

export default boardStore