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
          <h2 class="greeting">Meet your</h2> &nbsp;
          <h2 id="type"> {{ this.typedText }} </h2>
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
      type1: "new practice partner.",
      typedText: "",
      index: 0,
    }
  },
  computed: {
    messageComplete() {
      return this.index >= this.type1.length;
    }
  },
  mounted() {
    this.writer();
  },
  methods: {
    writer() {
      while (!this.messageComplete) {
        for (let char of this.type1) {
          this.typedText += char;
          this.index++;           
        }

      }
    }
  }
};
</script>

<style scoped>
.image-container {
  border: 1px solid black;
  height: 100vh;
  background-image: url("../assets/home-page-image.jpg");
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
