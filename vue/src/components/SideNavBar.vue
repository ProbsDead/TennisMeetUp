<template>
  <div class="sidebar">
    <div class="logo-details">
      <i class="bx bxs-tennis-ball">
        <span class="logo-name"> Tennis Meetup</span>
      </i>
    </div>
    <ul class="nav-links">
      <li>
        <i class="bx bx-grid-alt">
          <span class="link_name"
            ><router-link v-bind:to="{ name: 'home' }"
              >Dashboard</router-link
            ></span
          >
        </i>
      </li>
      <li>
        <i class="bx bxs-group">
          <span class="link_name"
            ><router-link v-bind:to="{ name: 'group-mainpage' }"
              >My Groups</router-link
            ></span
          >
        </i>
      </li>
      <li>
        <i class="bx bxs-group">
          <span class="link_name"
            ><router-link v-bind:to="{ name: 'group-mainpage' }"
              >My Groups</router-link
            ></span
          >
        </i>
      </li>

      <!-- v-if for if admin, see admin page icon that links to admin page
     -->
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
@import url("https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css");
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 260px;
  background: #8a84cd;
  font-family: "Poppins", sans-serif;
}

.nav-links {
  list-style: none;
}

a,
.logo-name {
  font-family: "Poppins", sans-serif;
  text-decoration: none;
}
</style>
