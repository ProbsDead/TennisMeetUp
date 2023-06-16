import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Logout from "../views/Logout.vue";
import Register from "../views/Register.vue";
import store from "../store/index";
import GroupMainPage from "../views/GroupMainPage.vue";
import MyGroups from "../views/MyGroups.vue";
import AboutUs from "../views/AboutUs.vue";
import SearchGroups from "../views/SearchGroups.vue";
import CreateNewGroup from "../views/CreateNewGroup.vue";
import UserMainPage from "../views/UserMainPage.vue";
import ImageUploadTest from "../components/ImageUploadTest.vue";

Vue.use(Router);

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/about-us",
      name: "about-us",
      component: AboutUs,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/group/:groupId",
      name: "group-mainpage",
      component: GroupMainPage,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/my-groups",
      name: "my-groups-list",
      component: MyGroups,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/group",
      name: "search-groups",
      component: SearchGroups,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/create-group",
      name: "create-group",
      component: CreateNewGroup,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/user/:userId",
      name: "user-main",
      component: UserMainPage,
      meta: {
        requiresAuth: true,
      },
    },
    {
      // temporary path for testing uploading images
      path: "/test-image",
      name: "test-image",
      component: ImageUploadTest,
      meta: {
        requiresAuth: false,
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === "") {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
