<template>
  <div>
    <dropdown v-bind:players="players"/>
    <div>Input matches:</div>
    <div><span> Single </span> | <span>Double</span></div>
    <form class="single-form">
      <div class="winner">
        <label for="winner-one"> 
        Won </label>
      <input type="search" id="winner-one"/>
      </div>
      <div class="loser">
          <label for="lost"> Lost </label>
      <input type="search" id="lost"/>
      </div>

     <div class="score">
       <label for="score"> Score </label>
      <input type="number" id="score"/>
      :
        <input type="number" id="score"/>
     </div>
      <button>Submit</button>

    </form>


    <form class="double-form">
      <button>Submit</button>
    </form>
  </div>
</template>

<script>
import Dropdown from './Dropdown.vue';

export default {
  components:{
    Dropdown
  },
  props:{
    players: Array
  },
  data() {
    return {
      single: {
        "winner-one": 0,
        "winner-two": 0,
        "lost-one": 0,
        "lost-two": 0,
        score: '',
        length:0
      },
      double: {
        "winner-one": 0,
        "winner-two": 0,
        "lost-one": 0,
        "lost-two": 0,
         score: '',
        length:0
      },
      singleOrDouble: ''
    };
  },
};
</script>

<style scoped>
.single-form{
  display: flex;
  justify-content: space-evenly;
}
</style>
