<template>
  <div class="list-groups">
    <h2>{{title}}</h2>
    <section
      class="group-box"
      v-for="item in allGroups"
      v-bind:key="item.groupId"
    >
      <div class="headline">
        <h2 class="group-name">{{ item.group_name }}</h2>
        <h4 class="group-city">{{ item.city }}</h4>
      </div>
      <div class="group-description">
        <p>{{ item.about }}</p>
      </div>
      <router-link
        v-bind:to="{
          name: 'group-mainpage',
          params: { groupId: item.group_id },
        }"
        >Learn More</router-link
      >
    </section>
  </div>
</template>

<script>
export default {
    props: {
        allGroups: Array,
        title: String
    },
  data() {
    return {
      filteredGroupsByName: [],
      filteredGroupsByCity: [],
    };
  },
};
</script>

<style scoped>
div.list-groups > h2 {
  padding-left:20px;
}
.group-box {
  width: 80vw;
  font-family: "Poppins", sans-serif;
  padding-left: 20px;
  padding-right: 20px;
  border: 1px solid black;
  border-radius: 5px;
  margin: 10px;
}
.headline {
  display: inline-flex;
  width: 100%;
  justify-content: space-between;
}
</style>
