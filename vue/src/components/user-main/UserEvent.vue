<template>
  <div>
    <h2>{{ formattedDate }}</h2>
    <hr />
    <div class="event-box">
      <div class='left'> 
      <div> <span>{{ formattedTime(eventInfo.start_time) }} - {{ formattedTime(eventInfo.end_time) }}</span></div>
    <div><span class="event-name">{{ eventInfo.event_name }}</span> <span> &#8226; {{ eventGroup.group_name }}</span></div>
    <div>
    {{ eventInfo.location }}
    </div>
    </div>
    <div class="right">
       <div>{{ eventInfo.description }}</div>
    </div>
    </div>
  </div>
</template>

<script>
import GroupService from "../../services/GroupService";
export default {
  props: {
    eventInfo: Object,
  },
  data() {
    return {
      eventGroup: {},
    };
  },
  created() {
    GroupService.getGroupByEventId(this.eventInfo.event_id).then((response) => {
      this.eventGroup = response.data;
    });
  },
  methods: {
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
  },
  computed: {
    formattedDate() {
      const dateObject = new Date(this.eventInfo.start_time);
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
h2{
  margin: 0px;
  font-size: 1.2em;
}
.event-box{
  display: flex;
 justify-content: flex-start;
box-shadow: rgba(0, 0, 0, 0.04) 0px 3px 5px;
}
div.left{
  padding-right: 30px;
}

div.right{
  text-align: center;
    padding: 25px 0px 25px 25px;
}

span.event-name{
  color:#1c736a;
}


</style>
