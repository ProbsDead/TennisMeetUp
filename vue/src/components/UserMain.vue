<template>
  <div class="user-main">
    <h1>Welcome back, {{ user.first_name }}! &#128075;</h1>
    <section class="goal-and-stats">
      <div class="goal">
        <h3 class="section-title">My Goal</h3>
        <div class="goal-box">
          <p>{{ user.goal ? user.goal : `No current goals` }}</p>
          <p>Update goals</p>
        </div>
      </div>
      <div class="stats">
        <h3 class="section-title">Stats</h3>
        <div class="stats-box">
          <div>Total matches: {{ matches.length }}</div>
          <div>Total Wins: {{ (winsTotal / matches.length) * 100 }}%</div>
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
import UserService from "../services/UserService.js";
export default {
  name: "user-main",
  data() {
    return {
      user: this.$store.state.user,
      matches: [],
      winsTotal: 0,
    };
  },
  created() {
    UserService.getUserMatches(this.user.id)
      .then((response) => {
        this.matches = response.data;
        this.matches.forEach((match) => {
          if (
            match.winner === this.user.id ||
            match.winner_two === this.user.id
          )
            this.winsTotal++;
        });
      })
      .catch((error) => {
        this.handleError(error);
      });
  },
  methods: {
    handleError(error) {
      //A reusable error function to be used in the catch statements
      if (error.request) {
        this.errorMsg =
          "Error submitting request. Server could not be reached.";
      } else {
        this.errorMsg = "An error occurred, please try again later.";
      }
    },
  },
};
</script>

<style scoped>
section.goal-and-stats {
  display: flex;
  justify-content: space-evenly;
  background-color: #cddfd6;
  min-height: 35vw;
}
div.goal-box,
div.stats-box {
  min-width: 35vw;
  min-height: 30vw;
  font-family: "Poppins", sans-serif;
  padding-left: 20px;
  padding-right: 20px;
  border: 1px solid black;
  border-radius: 5px;
}

.section-title,
h1 {
  text-align: center;
}
</style>
