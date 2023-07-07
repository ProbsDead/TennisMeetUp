<template>
  <div class="all-events">
    <div class="event" v-for="event in events" :key="event.event_id">
      <u
        ><h3 class="name">
          <router-link
            v-bind:to="{
              name: 'event-details',
              params: { eventId: event.event_id },
            }"
            >{{ event.event_name }}
          </router-link>
        </h3></u
      >
      <h4 class="description">What: {{ event.description }}</h4>
      <h4 class="times">
        When: {{ formatDates(event.start_time) }} :
        {{ formatTimes(event.start_time) }} - {{ formatTimes(event.end_time) }}
      </h4>
      <h4 class="address">Where: {{ event.location }}</h4>
    </div>
  </div>
</template>

<script>
import EventService from "../../services/EventService";

export default {
  name: "group-events",
  components: {},
  data() {
    return {
      events: [],
      start: [],
      end: [],
    };
  },

  created() {
    EventService.getEventsByGroupId(this.$route.params.groupId)
      .then((response) => {
        this.events = response.data;
      })
      .catch((error) => {
        this.handleError(error);
      });
    console.log("created runs");
    this.formatTimes();
    console.log("created finishes");
  },

  methods: {
    formatTimes(timeStamp) {
      const dateObject = new Date(timeStamp);
      const hours = dateObject.getHours();
      const minutes = dateObject.getMinutes();

      let formattedTime;
      if (hours === 0) {
        formattedTime = `12:${minutes < 10 ? "0" + minutes : minutes} AM`;
      } else if (hours < 12) {
        formattedTime = `${hours}:${minutes < 10 ? "0" + minutes : minutes} AM`;
      } else if (hours === 12) {
        formattedTime = `12:${minutes < 10 ? "0" + minutes : minutes} PM`;
      } else {
        formattedTime = `${hours - 12}:${
          minutes < 10 ? "0" + minutes : minutes
        } PM`;
      }
      return formattedTime;
    },
    formatDates(timeStamp) {
      const dateObject = new Date(timeStamp);
      const month = dateObject.getMonth();
      const day = dateObject.getDate();
      const year = dateObject.getFullYear();

      let formattedDate = `${month < 10 ? "0" + month : month}-${
        day < 10 ? "0" + day : day
      }-${year}`;
      return formattedDate;
    },

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
@import url("https://fonts.cdnfonts.com/css/graphik?styles=44656");

div.events {
  height: 55vh;
  border-radius: 8px;
  padding: 30px 90px 0px 40px;
  background-color: rgb(251, 253, 246);
  font-family: "Graphik", sans-serif;
}
.event {
  background-color: rgba(244, 162, 97, 0.8);
  border-radius: 10px;
}
.name {
  color: rgb(38, 70, 83);
}
u {
  color: rgb(38, 70, 83);
}
</style>
