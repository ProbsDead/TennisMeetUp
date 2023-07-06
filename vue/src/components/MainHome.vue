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
          <span class="greeting">Meet your
            <VueWriter class="greeting"
                :array="['new practice partner','weekend group','next tournament']">
            </VueWriter>
          </span>
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

import VueWriter from 'vue-writer'
export default {
  components: {
    VueWriter
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
.buttons {
  display: flex;
  justify-content: center;
  gap: 15px;
}

</style>
