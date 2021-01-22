
// user
// import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import myPage from './views/user/Mypage.vue'
//Main
import Main from './views/Main/Main.vue'
// Community
import Bucket from './views/Community/Bucket'
import Challenge from './views/Community/Challenge'
import Ranking from './views/Community/Ranking'
// import { userSetter } from 'core-js/fn/symbol'


export default [

    {
        path : '/',
        name : 'Main',
        component : Main
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    // {
    //     path : '/user/login',
    //     name : 'Login',
    //     component : Login
    // },
    {
        path: '/user/mypage',
        name: 'myPage',
        component: myPage
    },

    {
        path: '/community/bucket',
        name: 'Bucket',
        component: Bucket
    },
    {
        path: '/community/challenge',
        name: 'Challenge',
        component: Challenge
    },
    {
        path: '/community/ranking',
        name: 'Ranking',
        component: Ranking
    }

]