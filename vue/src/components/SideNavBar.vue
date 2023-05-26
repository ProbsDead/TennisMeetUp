<template>
  <div class="sidebar" v-bind:class="{ open: sideBarToggle }">
    <div class="logo-details">
      <i class="bx bxs-tennis-ball">
        <span class="logo-name"> Tennis Meetup</span>
        <i class="bx bx-menu" id="btn" @click="expandOrCollapse"></i>
      </i>
    </div>
    <ul class="nav-links">
      <li>
        <i class="bx bx-grid-alt"> </i
        ><span class="link_name"
          ><router-link v-bind:to="{ name: 'home' }"
            >Dashboard</router-link
          ></span
        >
        <i class="bx bxs-chevron-down"></i>
      </li>
      <li>
        <i class="bx bxs-group"></i>
        <span class="link_name">
          <router-link v-bind:to="{ name: 'group-list-page' }"
            >My Groups</router-link
          ></span
        >
      </li>
      <li>
        <i class="bx bx-search-alt"> </i>
        <span class="link_name"
          ><router-link v-bind:to="{ name: 'group-mainpage' }"
            >Find a Group</router-link
          ></span
        >
      </li>

      <!-- v-if for if admin, see admin page icon that links to admin page
     -->

      <li id="log-out-link">
        <i class="bx bx-log-out" id="log_out"></i>

        <!-- Change router rink once logout page
     -->
        <span class="link_name"
          ><router-link v-bind:to="{ name: '/login' }">Logout</router-link>
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sideBarToggle: false,
    };
  },
  methods: {
    expandOrCollapse() {
      this.sideBarToggle = !this.sideBarToggle;
      this.$store.commit("TOGGLE_SIDEBAR");
      if (document.querySelector(".sidebar").classList.contains("open")) {
        /* replacing the icons class with expanding */
        document
          .querySelector("#btn")
          .classList.replace("bx-menu", "bx-menu-alt-right");
      } else {
        /* replacing the icons with collapsing */
        document
          .querySelector("#btn")
          .classList.replace("bx-menu-alt-right", "bx-menu");
      }
    },
  },
};
</script>

<style scoped>
@import url("https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css");
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
  color: rgb(240, 230, 230);
}

/* collapsed (default) sidebar */
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 75px;
  background: #11101d;
  transition: all 0.5s ease;
}

.logo-details {
  font-size: 20px;
  height: 50px;
  min-width: 60px;
  text-align: center;
  line-height: 50px;
  padding-left: 20px;
  margin-bottom: 60px;
}

li {
  padding-left: 20px;
  margin: 15px;
}

.nav-links li {
  position: relative;
  list-style: none;
}

.sidebar .nav-links li i {
  height: 45px;
  min-width: 50px;
  text-align: center;
  line-height: 50px;
}

a,
i {
  text-decoration: none;
  color: white;
}

.sidebar li:hover {
  background: #f6efef;
  border-radius: 4px;
  margin: 10px;
}

.sidebar li:hover a,
.sidebar li:hover i {
  transition: all 0.5s ease;
  color: #11101d;
}

#log-out-link {
  position: fixed;
  bottom: 10px;
}

/* expanded sidebar */
.open {
  width: 250px;
}
</style>
