<template>
  <div>
    <h1>{{event.event_name}}</h1>
    <h4>{{players}}</h4>
    <div class="event-info">
        <div class=""></div>
    </div>
  </div>
</template>

<script>
import EventService from "../../services/EventService";
export default {
    data() {
        return{
            event:{},
            players:[]
        }
    },
    created(){
        EventService.getEventDetails(this.$route.params.eventId).then((response) =>{
            this.event = response.data;
        }).catch((err) => {
            this.handleError(err);
        });

        EventService.getPlayersByEventId(this.$route.params.eventId).then((response) => {
            this.players = response.data;
        }).catch((err) =>{
            this.handleError(err);
        })
    },
    methods:{
          handleError(error) {
      //A reusable error function to be used in the catch statements
      if (error.request) {
        this.errorMsg =
          "Error submitting request. Server could not be reached.";
      } else {
        this.errorMsg = "An error occurred, please try again later.";
      }
    },
    }
}
</script>

<style scoped>

</style>