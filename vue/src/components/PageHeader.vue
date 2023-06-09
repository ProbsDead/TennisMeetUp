<template>
  <div class="header">
    <div>Logo + app name</div>
    <div class="links-container">
      <ul class="nav-links">
        <li id="my-page">
          <router-link v-bind:to="{ name: 'home' }">My Page</router-link>
        </li>
        <li id="aboutUs">
          <router-link v-bind:to="{ name: 'about-us' }">About Us</router-link>
        </li>
        <li id="logout">
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.header {
  display: inline;
  border-bottom: 1.5px solid #f2ede2;
}
.header div {
  display: inline-block;
}
.nav-links {
  display: flex;
}
li {
  list-style: none;
}
a {
  text-decoration: none;
}
</style>
