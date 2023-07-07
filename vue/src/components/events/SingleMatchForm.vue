<template>
  <div>
    <div>Input matches:</div>
    <div><span> Single </span> | <span>Double</span></div>
    <form class="single-form" type="disabled">
      <div class="winner-input">
        <label for="winner-one"> Won: </label>
        <dropdown v-bind:players="players" />
      </div>
      <div class="loser-input">
        <label for="lost"> Lost: </label>
        <dropdown v-bind:players="players" />
      </div>

      <div class="score-input">
        <label for="score"> Score </label>
        <input type="number" id="score" />
        :
        <input type="number" id="score" />
      </div>

      <div class="length-input">
        <label for="length"> Length: </label>
        <input type="number" id="length" /> <span> mins</span>
      </div>

      <button @click.prevent="">Submit</button>
    </form>
  </div>
</template>

<script>
import Dropdown from "./Dropdown.vue";

export default {
  components: {
    Dropdown,
  },
  props: {
    players: Array,
  },
  data() {
    return {
      single: {
        "winner": "",
        "lost": "",
        score: "",
        length: 0,
      },
      double: {
        "winner-one": 0,
        "winner-two": 0,
        "lost-one": 0,
        "lost-two": 0,
        score: "",
        length: 0,
      },
      singleOrDouble: "",
    };
  },
  methods: {
    // once submit: show a success/failure message on whether it was added correctly.
  },
};
</script>

<style scoped>
.single-form {
  display: flex;
  /* justify-content: space-between; */
  gap: 50px;
  font-size: 14px;
}

label {
  color: rgb(13, 32, 78);
  font-weight: 900;
}
.score-input input,
.length-input input {
  width: 50px;
  text-align: center;
}

.winner-input,
.loser-input,
.score-input,
.length-input {
  display: flex;
  gap: 10px;
  /* align-items: center; */
}

.score-input,
.length-input,
button {
  max-height: 28px;
}
</style>
