<template>
  <div class="user-main">
    <h1>Welcome back, {{ user.first_name }}! &#128075;</h1>
    <section class="goal-and-stats">
      <div class="goal">
        <h3 class="section-title">My Goal</h3>
        <div class="goal-box">
          <p>{{ user.goal ? user.goal : `No current goals` }}</p>
          <p class="update-goal-text" @click="updateText = !updateText">
            Update goals
          </p>
          <div class="input-form" v-if="updateText">
            <input
              type="textarea"
              class="free-text"
              v-model="newGoal"
              placeholder="Write a new goal"
            />
            <span class="btn" @click="updateGoal"> submit </span>
          </div>
        </div>
      </div>
      <div class="stats">
        <h3 class="section-title">Stats</h3>
        <div class="stats-box">
          <div>Total matches: {{ matches.length }}</div>
          <div>
            Total Wins:
            {{ matches.length ? (winsTotal / matches.length) * 100 : 0 }}%
          </div>
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
      newGoal: "",
      updateText: false,
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
    updateGoal() {
      this.user.goal = this.newGoal;
      UserService.updateUserGoal(this.user.id, this.user);

      this.updateText = false;
    },
  },
};
</script>

<style scoped>
section.goal-and-stats {
  display: flex;
  justify-content: center;
  background-color: #fef9e9;
  min-height: 30vw;
  padding: 30px;
  gap: 40px;
}

.stats {
  padding-right: 60px;
}

div.goal-box,
div.stats-box {
  min-width: 40vw;
  min-height: 30vw;
  font-family: "Poppins", sans-serif;
  padding-left: 20px;
  border: 1px solid rgb(183, 183, 183);
  border-radius: 5px;
}

.update-goal-text:hover {
  cursor: pointer;
  color: #2a9d8f;
}

.section-title,
h1 {
  text-align: center;
}

.btn {
  background-color: #5b919c;
  border-radius: 5px;
  border-style: none;
  box-shadow: none;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-weight: 500;
  height: 40px;
  letter-spacing: normal;
  line-height: 1;
  padding: 10px 12px;
  position: relative;
  text-align: center;
  text-decoration: none;
  vertical-align: top;
  white-space: nowrap;
}

.btn:hover {
  background-color: #3b6070;
  box-shadow: rgba(0, 0, 0, 0.05) 0 5px 30px, rgba(0, 0, 0, 0.05) 0 1px 4px;
  opacity: 1;
  transition-duration: 0.35s;
}

.input-form {
  display: inline-block;
}

.free-text {
  width: 80%;
  padding: 8px 16px;
  line-height: 25px;
  font-size: 14px;
  font-weight: 500;
  font-family: inherit;
  border-radius: 6px;
  color: #99a3ba;
  border: 1px solid #cdd9ed;
  background: #fff;
  transition: border 0.3s ease;
}
</style>
