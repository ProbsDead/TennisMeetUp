<template>
  <div class="user-main-page">
    <user-main></user-main>
  </div>
</template>

<script>
import UserMain from "../components/user-main/UserMain.vue";
export default {
  components: {
    UserMain,
  },
};
</script>

<style scoped>
  .user-main-page{
    padding: 0;
  }
</style>
