<template>
  <div class="list-groups">
    <section class="card">
        <img class="card-image" src="" alt="">
      <div class="headline">
        {{ groupBox.group_name }}
      </div>
     <div class="meta">{{ groupBox.city }}, {{groupBox.state}}</div>
    <div class="extra-content">
      <span class="position"></span>
      <span class="link">
        <router-link
        v-bind:to="{
          name: 'group-mainpage',
          params: { groupId: groupBox.group_id },
        }"
        >view group page</router-link
      >
  
      </span>
    </div>
    </section>
  </div>
</template>

<script>
export default {
  props: {
    groupBox: Object,
  },
};
</script>

<style scoped>
.card{
  width: 200px;
  height: 274px;
  background: white;
  padding: .4em;
  border-radius: 6px;
  border-inline-end: 3px solid rgb(226, 226, 226);
  border-bottom: 3px solid rgb(226, 226, 226);
}

.card-image{
   background-color: rgb(236, 236, 236);
  width: 100%;
  height: 60%;
  border-radius: 6px 6px 0 0;
}

.headline{
  text-transform: uppercase;
  font-size: 1.2em;
  font-weight: 800;
  color: #264653;
  padding: 10px 7px 0;
}

.meta {
  font-weight: 600;
  color: rgb(88, 87, 87);
  padding: 7px;
}

a{
  padding: 7px;
}

</style>
