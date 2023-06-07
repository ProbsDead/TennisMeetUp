<template>
  <div class="user-main">
    <h1>Welcome back, {{ user.first_name }}! &#128075;</h1>
    <section class="goal-and-stats">
      <div class="goal">
        <h3>My Goal</h3>
        <div class="goal-box">
          <p>{{ user.goal ? user.goal : `No current goals` }}</p>
          <p>Update goals</p>
        </div>
      </div>
      <div class="stats">
        <h3>Stats</h3>
        <div class="stats-box">
            <div>Total matches</div>
        <div>Winning</div>
        <div>View all match history with scores</div>
        </div>  
      </div>
    </section>
    <section class="my-groups"></section>
    <section class="upcoming-events"></section>
    <section class="previous-events"></section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: this.$store.state.user,
    };
  },
};
</script>

<style scoped>
section.goal-and-stats {
  display: flex;
  justify-content: flex-start;
  gap: 100px;
}
div.goal-box,
div.stats-box {
  min-width: 35vw;
  font-family: "Poppins", sans-serif;
  padding-left: 20px;
  padding-right: 20px;
  border: 1px solid black;
  border-radius: 5px;
}
</style>
