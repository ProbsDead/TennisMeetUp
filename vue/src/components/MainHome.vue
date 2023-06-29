<template>
  <div class="image-container">

    <div class="boxes">
      <!-- <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div>
      <div class="box"></div> -->
      <div class="options">
        <div class="typewriters">
          <h2 class="greeting">Meet your</h2>
          <h2 id="type"></h2>
        </div>
        <div class="buttons">
          <button>Browse Groups</button>
          <button>Register</button>
        </div>
      </div>
    </div>

    
  </div>
</template>

<script>
export default {
  data() {
    return {
      type1: " new practice partner.",
      index: 0,
      speed: 100
    }
  },
  methods: {
    writer() {
      const container = document.getElementById("type");
      if (this.index < this.type1.length) {
        container.innerHTML += this.type1.charAt(this.index);
        this.index++;
        setTimeout(this.writer, this.speed);
      }
    }
  }
};
</script>

<style scoped>
.image-container {
  border: 1px solid black;
  height: 100vh;
}
.boxes {
  display: grid;
  grid-template: repeat(6, 1fr) / repeat(6, 1fr);
  height: 100%;
  gap: 2px;
}
.box {
  border: 1px solid black;
  grid-template-rows: auto;
}
.options {
  background-color: white;
  grid-area: 2 / 2 / 2 / 6;
  opacity: 0.7;
}
.typewriters {
  display: flex;
  justify-content: center;
}
.greeting {
  /* padding-left: 5%; */
}
.buttons {
  display: flex;
  justify-content: center;
  gap: 15px;
}

</style>
