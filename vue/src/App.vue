<template>
  <div id="app">
    <div class="side-nav-bar">
      <!-- <side-nav-bar></side-nav-bar> -->
    </div>
    <div class="header-nav">
      <page-header></page-header>
    </div>

    <router-view />

    <div class="footer">
      <page-footer></page-footer>
    </div>
  </div>
</template>

<script>
import PageHeader from "./components/PageHeader.vue";
import PageFooter from "./components/PageFooter.vue";
// import SideNavBar from "./components/SideNavBar.vue";

export default {
  components: {
    PageHeader,
    PageFooter,
    // SideNavBar,
  },
};
</script>

<style>
#app {
  display: grid;
  grid-template-columns: 1fr;

  grid-template-areas:
    "nav"
    "view"
    "footer";

  gap: 10px;
}

.header-nav {
  grid-area: nav;
}
router-view {
  grid-area: view;
}
.footer {
  grid-area: footer;
  bottom: 0;
}
</style>
