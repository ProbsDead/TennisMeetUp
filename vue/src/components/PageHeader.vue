<template>
  <div class="header">
    <div class="logo">
      <img src="../assets/tennis meetup-1.png" 
           alt="tennis meetup logo" 
           id="logo-image"
           v-show="!isSmallScreen"/>
      <img src="../assets/tennis meetup-letters.png"
           alt="TM"
           id="logo-image"
           v-show="isSmallScreen"/>
    </div>
    <div class="links-container">
      <ul class="nav-links">
        <li id="my-page">
          <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        </li>

        <li id="logout-login">
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''">Logout
          </router-link>
        <!-- </li>
        <li id="login"> -->
          <router-link
            v-bind:to="{ name: 'login' }"
            v-if="$store.state.token == ''">Login
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      windowWidth: window.innerWidth
    }
  },
  computed: {
    isSmallScreen() {
      return this.windowWidth < 554;
    }
  }
};
</script>

<style scoped>
.header {
  display: grid;
  grid-template-columns: 1fr 2fr;
  background-color: #264653;
  border-bottom: 1.5px solid #f2ede2;
}

#logo-image {
  max-width: 250px;
  height: auto;
}
.links-container {
  margin-top: auto;
  margin-bottom: auto; 
  margin-right: 10px;
}
.router-link-exact-active {
  text-decoration: underline;
}
.nav-links {
  display: flex;
  justify-content: flex-end;
}
li {
  list-style: none;
  padding: 7px;
}
a {
  text-decoration: none;
  color: white;
}

@media (max-width: 554px) {
  .header{
    display: block;
  }
  .logo {
    text-align: center;
  }
  .nav-links {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  li {
    text-align: center;
  }
}
</style>
