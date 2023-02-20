import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../pages/LoginPage.vue'
import RegisterPage from '../pages/RegisterPage.vue'
import HomePage from '../pages/HomePage.vue'
import {useUserStore} from '../stores/UserStore';

const router = new VueRouter({
  mode: 'history',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginPage,
      meta: {availableWithoutAuth: true}
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterPage,
      meta: {availableWithoutAuth: true},
    },
    {
      path: '/',
      name: 'home',
      component: HomePage,
      meta: {requiresAuth: true}
    }
  ]
})

router.beforeResolve(async (to, from, next) => {
  const userStore = useUserStore();
  const sessionToken = localStorage.getItem("user");
  const tokenValid = await userStore.validateToken(sessionToken).status === 200;
  if((!sessionToken && !tokenValid) && to.name !== "login") {
    to.name === "register" ? next() : router.push("/login")
    return false;
  }
  else if(tokenValid && to.name !== "home"){
    router.push("/")
    return;
  }
  else {
    next();
    return;
  }

}) 

export default router
