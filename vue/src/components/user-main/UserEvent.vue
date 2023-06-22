<template>
  <div>
    <h2> {{formattedDate}}</h2>
    <hr>

    <div>{{eventInfo.event_name}}</div>
    <div>{{eventInfo.description}}</div>
    <div>{{eventInfo.start_time}}</div>
    <div>{{eventInfo.end_time}}</div>
    <div>{{location}}</div>
    
  </div>
</template>

<script>
export default {
  props: {
    eventInfo: Object
  },
  computed: {
    formattedDate() {
    const dateObject = new Date(this.eventInfo.start_time);
    const month = dateObject.getMonth() +1;
    const day = dateObject.getDate();
    const dayOfWeek = dateObject.getDay();

    const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const dayName = daysOfWeek[dayOfWeek];

   return `${dayName}, ${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`;

    }
    
  },
  
}
</script>

<style>

</style>