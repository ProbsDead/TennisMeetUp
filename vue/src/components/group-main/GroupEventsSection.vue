<template>
  <div class="all-events">
    <div class="event" v-for="event in events" :key="event.event_id">
      <h3>{{ event.event_name }}</h3>
      <h4>{{ event.description }}</h4>
      <h4>{{ event.start_time }} - {{ event.end_time }}</h4>
      <h4>{{ event.location }}</h4>
    </div>
  </div>
</template>

<script>
import 
export default {
  name:"group-events",
  components:{},
  data() {
    return{
      events: []
    }
  },
  created(){

  }
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
</style>
