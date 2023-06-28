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
        <h2 class="greeting">Meet your new</h2>
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
.greeting {
  padding-left: 5%;
}
.buttons {
  display: flex;
  justify-content: center;
  gap: 15px;
}

</style>
