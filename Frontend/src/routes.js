import Vue from 'vue'
import VueRouter from 'vue-router'

// user
// import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import myPage from './views/user/Mypage.vue'
//Main
import Landing from './views/Main/Landing.vue'
import MainFeed from './views/Main/MainFeed.vue'
// Community
import Bucket from './views/Community/Bucket'
import Challenge from './views/Community/Challenge'
import Ranking from './views/Community/Ranking'
// import { userSetter } from 'core-js/fn/symbol'

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',   // <- '/landing'
            name: 'Landing',
            component: Landing
        },
    
        {
            path : '/mainfeed',        // <- '/'
            name : 'MainFeed',
            component : MainFeed
        },
        {
            path : '/user/join',
            name : 'Join',
            component : Join
        },
        {
            path: '/user/mypage/',
            name: 'myPage',
            component: myPage,
            props: true
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
})

export {router}