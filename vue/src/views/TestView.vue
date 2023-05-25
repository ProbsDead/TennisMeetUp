<template>
  <div>
    <side-nav-bar></side-nav-bar>
    <side-bar-test></side-bar-test>
  </div>
</template>

<script>
import SideBarTest from "../components/SideBarTest.vue";
import SideNavBar from "../components/SideNavBar.vue";

export default {
  components: {
    SideBarTest,
    SideNavBar,
  },
};
</script>

<style scoped></style>
