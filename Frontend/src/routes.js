import Vue from 'vue'
import VueRouter from 'vue-router'

// user
// import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import myPage from './views/user/Mypage.vue'
import FindPassword from './views/user/FindPassword.vue'
//Main
import Landing from './views/Main/Landing.vue'
import MainFeed from './views/Main/MainFeed.vue'
// Community
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
            path: '/user/mypage/:id',
            name: 'myPage',
            component: myPage,
            // props: true
        },
        {
            path : '/user/findpassword',
            name : 'FindPassword',
            component : FindPassword
        },
    
    ]
})

export {router}