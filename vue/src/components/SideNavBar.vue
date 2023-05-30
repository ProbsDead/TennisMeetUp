<template>
  <div class="sidebar" v-bind:class="{ open: sideBarToggle }">
    <div class="logo-details">
      <i class="bx bxs-tennis-ball icon">
        <span class="logo-name"> Tennis Meetup</span>
      </i>
      <i class="bx bx-menu" id="btn" @click="expandOrCollapse"></i>
    </div>
    <ul class="nav-links">
      <li>
        <router-link v-bind:to="{ name: 'home' }">
          <i class="bx bx-grid-alt menu-icon"> </i
          ><span class="link_name">Dashboard</span></router-link
        >
        <span class="tooltip">Dashboard</span>
      </li>
      <li>
        <router-link v-bind:to="{ name: 'my-groups-list' }"
          ><i class="bx bxs-group menu-icon"></i>
          <span class="link_name">My Groups</span></router-link
        >
        <span class="tooltip">My Groups</span>
      </li>
      <li>
        <!-- change the link to viewing all public groups page (not present yet)-->
        <router-link v-bind:to="{ name: 'my-groups-list' }">
          <i class="bx bx-search-alt menu-icon"> </i
          ><span class="link_name">Find a Group</span></router-link
        >

        <span class="tooltip">Find a Group</span>
      </li>

      <!-- v-if for if admin, see admin page icon that links to admin page
     -->

      <li id="log-out-link">
        <!-- Change router rink once logout page
     -->
        <router-link v-bind:to="{ name: '/login' }">
          <i class="bx bx-log-out menu-icon" id="log_out"></i>
          <span class="link_name">Logout </span></router-link
        >
        <span class="tooltip">Logout</span>
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
        /* id btn refers to the toggle icon */
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

.sidebar .logo-details {
  /* font-size: 20px;
  height: 50px;
  min-width: 60px;
  text-align: center;
  line-height: 50px;
  padding-left: 20px;
  margin-bottom: 60px; */
  height: 60px;
  display: flex;
  align-items: center;
  position: relative;
}
.sidebar .logo-details .icon {
  opacity: 0;
  transition: all 0.5s ease;
}

.sidebar .logo-details .logo_name {
  color: #fff;
  font-size: 1em;
  font-weight: 100;
  transition: all 0.5s ease;
}

.sidebar .logo-details #btn {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  font-size: 25px;
  transition: all 0.4s ease;
  text-align: center;
  cursor: pointer;
}

.sidebar i.menu-icon,
.sidebar i {
  color: #fff;
  height: 60px;
  min-width: 50px;
  font-size: 23px;
  text-align: center;
  line-height: 60px;
  margin-left: 15px;
}

.sidebar .nav-links {
  margin-top: 20px;
  height: 100%;
}

.sidebar li {
  position: relative;
  margin: 15px 0;
  list-style: none;
}

.sidebar li .tooltip {
  position: absolute;
  top: -20px;
  left: calc(100%+ 15px);
  background: #fff;
  box-shadow: 0 5px 10px rgba(140, 117, 117, 0.3);
  padding: 6px 12px;
  font-size: 15px;
  font-weight: 400;
  opacity: 0;
  white-space: nowrap;
  pointer-events: none;
  color: black;
}

.sidebar li:hover .tooltip {
  opacity: 1;
  transition: all 0.4s ease;
  top: 50%;
  left: 100%;
  transform: translateY(-50%);
}

.sidebar li a {
  display: flex;
  height: 100%;
  width: 100%;
  border-radius: 12px;
  align-items: center;
  text-decoration: none;
  transition: all 0.4s ease;
  background: #11101d;
}

.sidebar li a:hover {
  background: #ebe2e2;
}

.sidebar li a .link_name {
  color: #fff;
  font-size: 15px;
  font-weight: 400;
  white-space: nowrap;
  opacity: 0;
  pointer-events: none;
  transition: 0.4s;
}
.sidebar.open li a .link_name {
  opacity: 1;
  pointer-events: auto;
}
.sidebar li a:hover .link_name,
.sidebar li a:hover i {
  transition: all 0.5s ease;
  color: #11101d;
}

.sidebar li i {
  height: 50px;
  line-height: 50px;
  font-size: 18px;
  border-radius: 50%;
}

#log-out-link {
  position: relative;
  top: 40%;
}

/* expanded sidebar */
.sidebar.open {
  width: 250px;
}

.sidebar.open li .tooltip {
  display: none;
}

.sidebar.open .logo-details .icon,
.sidebar.open .logo-details .logo_name {
  opacity: 1;
}

.sidebar.open .logo-details #btn {
  text-align: right;
}
@media (max-width: 420px) {
  .sidebar li .tooltip {
    display: none;
  }
}
</style>
