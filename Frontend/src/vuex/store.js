import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"

Vue.use(Vuex)

const SERVER_URL="http://localhost:8080";

export default new Vuex.Store({
    state:{
        isUser:false,
        isSign:false,
        user:{
            email:"",
            password:""
        }
    },
    mutations:{
        setUser(state,payload){
            state.setUser=payload;
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
                method: "get",
                url: `${SERVER_URL}/account/login`,
                params: {
                   email: user.email,
                   password: user.password,
                },
           })
          .then((response) => {
            console.log(response.data.status);
            context.commit("setUser",response.data.status);
          }).catch((error) => {
            console.log(error);
          })
        },

        signup(context,credentials){
            axios({
                method:"post",
                url:`${SERVER_URL}/account/signup`,
                data:credentials
            }).then((response)=>{
                console.log(response.data.message);
                context.commit("setIsSign",response.data.message);
            })
            .catch((err) => {
                console.log(err)
              })
        }
    }
})