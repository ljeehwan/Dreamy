import axios from "axios"
// import {router} from "@/routes.js"

const SERVER_URL="http://localhost:8080";

const boardStore={
    namespaced:true,

    state:{
    },
    getters:{
      
    },
    mutation:{
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

    }
}

export default boardStore