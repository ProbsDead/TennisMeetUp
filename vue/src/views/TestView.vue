<template>
  <div>
    <div class="side-bar">
      <side-nav-bar></side-nav-bar>
    </div>
    <div v-bind:class="{ default: !sidebarIsOpen, expanded: sidebarIsOpen }">
      <side-bar-test></side-bar-test>
    </div>
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
  computed: {
    sidebarIsOpen() {
      return this.$store.state.sideBarOpen;
    },
  },
};
</script>

<style scoped>
/* when sidebar is closed (default)*/
.default {
  position: relative;
  background: #e0e6f7;
  min-height: 100vh;
  width: calc(100%-78px);
  top: 0;
  left: 78px;
  transition: all 0.5s ease;
  z-index: 2;
}

/* when sidebar is open */
.expanded {
  position: relative;
  background: #e0e6f7;
  min-height: 100vh;
  top: 0;
  left: 250px;
  width: calc(100% - 250px);
  transition: all 0.5s ease;
  z-index: 2;
}
</style>
