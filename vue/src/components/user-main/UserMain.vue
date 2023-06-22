<template>
  <div class="user-main">
    <h1>Welcome back, {{ user.first_name }}! &#128075;</h1>
    <section class="goal-and-stats">
      <div class="goal">
        <h3 class="section-title">My Goal</h3>
        <div class="goal-box">
          <div>{{ user.goal ? user.goal : `No current goals` }}</div>
          <br />
          <div class="update-goal-text" @click="updateText = !updateText">
            Update goal
          </div>
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

        <div class="total-matches">
          {{ matches.length }} <span>Total Matches</span>
        </div>

        <div class="wins">
          {{ matches.length ? (winsTotal / matches.length) * 100 : 0 }}%
          <span>Wins</span>
        </div>
      </div>
    </section>
    <h2>My Groups ({{ userGroups.length }})</h2>
    <section class="my-groups">
      <div v-if="!userGroups.length">
        You are not a member of any group. Join new groups!
      </div>
      <div v-else class="group-cards">
        <div
          class="ui four cards"
          v-for="group in userGroups"
          v-bind:key="group.group_id"
        >
          <my-group v-bind:group-box="group"></my-group>
        </div>
      </div>
    </section>
    <br />

    <div>
      <span class="link"
        ><router-link
          v-bind:to="{
            name: 'search-groups',
          }"
          >Browse other groups</router-link
        >
      </span>
      <span class="link"
        ><router-link
          v-bind:to="{
            name: 'create-group',
          }"
          >Create a new group</router-link
        ></span
      >
    </div>

    <h2>Upcoming Events</h2>
    <section class="upcoming-events">
      <div v-for="event in upcomingEvents" :key="event.event_id">
        <user-event v-bind:eventInfo="event"></user-event>
      </div>
    </section>
    <section class="previous-events"></section>
  </div>
</template>

<script>
import UserService from "../../services/UserService.js";
import GroupService from "../../services/GroupService.js";
import MyGroup from "./MyGroup.vue";
import UserEvent from "./UserEvent.vue";

export default {
  name: "user-main",
  components: {
    MyGroup,
    UserEvent,
  },
  data() {
    return {
      user: this.$store.state.user,
      userGroups: [],
      matches: [],
      winsTotal: 0,
      newGoal: "",
      updateText: false,
      upcomingEvents: [],
    };
  },
  created() {
    // retrieve all match history for stats calculation
    UserService.getUserMatches(this.user.id)
      .then((response) => {
        this.matches = response.data;
        this.matches.forEach((match) => {
          if (
            // was the user the winner?
            match.winner === this.user.id ||
            match.winner_two === this.user.id
          )
            this.winsTotal++;
        });
      })
      .catch((error) => {
        this.handleError(error);
      });

    // retrieve all of the groups the USER is part of
    GroupService.getGroupsByUser(this.user.id).then((response) => {
      this.userGroups = response.data;
    });

    // retrieve user events
    UserService.getUpcomingUserEvents(this.user.id).then((response) => {
      this.upcomingEvents = response.data;
      console.log(this.upcomingEvents);
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
      const updatedUser = UserService.updateUserGoal(this.user.id, this.user);
      this.$store.state.user = updatedUser;
      this.updateText = false;
    },
  },
};
</script>

<style scoped>
section.goal-and-stats {
  display: flex;
  justify-content: flex-start;
  background-color: #f5f29e;
  min-height: 25vw;
  padding-left: 30px;
}

.stats {
  padding-right: 60px;
}

div.goal-box,
div.stats-box {
  min-width: 40vw;
  min-height: 15vw;
  font-family: "Poppins", sans-serif;
  /* padding-left: 20px; */
  /* border: 1px solid rgb(183, 183, 183); */
  border-radius: 5px;
}

div.goal-box div,
div.stats-box div {
  font-size: 16px;
}
.update-goal-text:hover {
  cursor: pointer;
  color: #2a9d8f;
}

.section-title {
  /* text-align: center; */
  color: #264653;
  font-size: 1.5em;
}

h1,
h2 {
  color: #264653;
  font-size: 2em;
  margin: 0.1em 0.6em 0.2em;
}

h2 {
  font-size: 1.5em;
  margin: 0.8em;
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

section {
  padding-left: 20px;
}

.group-cards {
  display: flex;
  gap: 20px;
}

a {
  text-decoration: none;
  padding-left: 20px;
}
</style>
