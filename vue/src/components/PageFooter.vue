<template>
  <div class="footer">
    <h3 id="aboutUs">
          <router-link v-bind:to="{ name: 'about-us' }">About Us</router-link>
    </h3>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.footer {
  background-color: #cddfd6;
}
</style>
