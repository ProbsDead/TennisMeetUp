<template>
  <div>
    <div class="title-and-btn">
      <h1>{{ event.event_name }}</h1>
      <div v-if="attending" class="button">Attending</div>
      <div v-else class="button btn-join">Join Event</div>
    </div>
    <section class="top">
      <hr />
      <div class="event-info">
        <div class="group">
          <i class="bx bxs-group"></i>Hosted by {{ eventGroup.group_name }}
        </div>
        <h3>When and Where</h3>
        <div class="when">
          <i class="bx bx-calendar-star"></i> Date and Time
          <div>{{ formattedDate(event.start_time) }}</div>
          <div>
            {{ formattedTime(event.start_time) }} -
            {{ formattedTime(event.end_time) }}
          </div>
        </div>

        <div class="where"><i class="bx bx-map" /> {{ event.location }}</div>
        <h3>About this event</h3>
        <div class="about">{{ event.description }}</div>
        <hr />
      </div>
    </section>
    <section class="bottom">
        <h3>Attending ({{players.length}})</h3>
    </section>
  </div>
</template>

<script>
import EventService from "../../services/EventService";
import GroupService from "../../services/GroupService";
export default {
  data() {
    return {
      event: {},
      eventGroup: {},
      players: [],
      attending: false,
    };
  },
  created() {
    EventService.getEventDetails(this.$route.params.eventId)
      .then((response) => {
        this.event = response.data;
      })
      .catch((err) => {
        this.handleError(err);
      });

    EventService.getPlayersByEventId(this.$route.params.eventId)
      .then((response) => {
        this.players = response.data;
        response.data.forEach((player) => {
          if (player.id === this.$store.state.user.id) {
            this.attending = true;
          }
        });
      })
      .catch((err) => {
        this.handleError(err);
      });
    GroupService.getGroupByEventId(this.$route.params.eventId).then(
      (response) => {
        this.eventGroup = response.data;
      }
    );
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
    formattedTime(timeStamp) {
      const dateObject = new Date(timeStamp);
      const hours = dateObject.getHours();
      const minutes = dateObject.getMinutes();

      // converting to the 12-hour clock format with AM/PM
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
    formattedDate(timeStamp) {
      const dateObject = new Date(timeStamp);
      const month = dateObject.getMonth() + 1;
      const day = dateObject.getDate();
      const dayOfWeek = dateObject.getDay();

      const daysOfWeek = [
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
      ];
      const dayName = daysOfWeek[dayOfWeek];

      return `${dayName}, ${month < 10 ? "0" + month : month}-${
        day < 10 ? "0" + day : day
      }`;
    },
  },
};
</script>

<style scoped>
.top {
  background-color: rgb(251, 249, 197);
}
h1 {
  display: inline;
  margin-right: 150px;
}

hr {
  border: 1px solid rgb(232, 229, 229);
}

.button {
  background-color: rgba(51, 51, 51, 0.05);
  border-radius: 8px;
  border-width: 0;
  color: #333333;
  display: inline-block;
  font-family: "Haas Grot Text R Web", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 14px;
  font-weight: 500;
  line-height: 20px;
  list-style: none;
  margin: 0;
  padding: 10px 12px;
  text-align: center;
  transition: all 200ms;
  vertical-align: baseline;
  white-space: nowrap;
}

.btn-join{
      cursor: pointer;
}
</style>
