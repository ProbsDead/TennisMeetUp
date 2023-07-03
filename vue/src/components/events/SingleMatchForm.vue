<template>
  <div>
    <div>Input matches:</div>
    <div><span> Single </span> | <span>Double</span></div>
    <form class="single-form" type="disabled">
      <div class="winner">
        <label for="winner-one"> 
        Won: </label>
      <dropdown v-bind:players="players"/>
      </div>
      <div class="loser">
          <label for="lost"> Lost: </label>
       <dropdown v-bind:players="players"/>
      </div>

     <div class="score-input">
       <label for="score"> Score </label>
      <input type="number" id="score" />
      :
        <input type="number" id="score"/>
     </div>
      <button @click.prevent="">Submit</button>

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
  methods: {
    // once submit: show a success/failure message on whether it was added correctly.
  }
};
</script>

<style scoped>
.single-form{
  display: flex;
  /* justify-content: space-between; */
  gap: 50px;
  font-size: 14px;
}

label {
  color:rgb(13, 32, 78);
  font-weight: 900;
}
.score-input input{
  width: 35px;
  text-align: center;
}

.winner, .loser, .score-input{
  display: flex;
  gap: 10px;
  /* align-items: center; */
}

.score-input, button{
  max-height: 25px;
}
</style>
