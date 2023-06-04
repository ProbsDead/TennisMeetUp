<template>
  <div id="app">
    <div class="side-nav-bar">
      <side-nav-bar></side-nav-bar>
    </div>

    <page-header
      class="header-nav"
      v-bind:class="{
        'default-header': !sidebarIsOpen,
        'expanded-header': sidebarIsOpen,
      }"
    >
    </page-header>

    <router-view
      class="page-content"
      v-bind:class="{
        'default-content': !sidebarIsOpen,
        'expanded-content': sidebarIsOpen,
      }"
    />

    <page-footer
      class="footer"
      v-bind:class="{
        'default-footer': !sidebarIsOpen,
        'expanded-footer': sidebarIsOpen,
      }"
    ></page-footer>
  </div>
</template>

<script>
import PageHeader from "./components/PageHeader.vue";
import PageFooter from "./components/PageFooter.vue";
import SideNavBar from "./components/SideNavBar.vue";

export default {
  components: {
    PageHeader,
    PageFooter,
    SideNavBar,
  },
  computed: {
    sidebarIsOpen() {
      return this.$store.state.sideBarOpen;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

#app {
  font-family: "Poppins", sans-serif;
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
.page-content {
  grid-area: view;
}
.footer {
  grid-area: footer;
}

.page-content {
  padding-bottom: 1.5rem;
  padding-top: 1.5rem;
}

/* when sidebar is closed (default)*/
.default-content {
  position: relative;
  min-height: 90vh;
  width: calc(100%-250px);
  left: 78px;
  transition: all 0.5s ease;
}
.default-header {
  position: relative;
  min-height: 18vh;
  width: calc(100%-250px);
  left: 78px;
  transition: all 0.5s ease;
}

.default-footer {
  position: relative;
  min-height: 18vh;
  width: calc(100%-250px);
  left: 78px;
  transition: all 0.5s ease;
}

/* when sidebar is open */
.expanded-content {
  position: relative;
  min-height: 100vh;
  left: 250px;
  width: calc(100% - 250px);
  transition: all 0.5s ease;
}

.expanded-header {
  position: relative;
  min-height: 18vh;
  left: 250px;
  width: calc(100% - 250px);
  transition: all 0.5s ease;
}

.expanded-footer {
  position: relative;
  min-height: 18vh;
  left: 250px;
  width: calc(100% - 250px);
  transition: all 0.5s ease;
}

@media print {
  .noprint {
    display: none;
  }
}
</style>
