<template>
  <div class="all-groups">
    <div>
      <section
        class="group-box"
        v-for="item in groups"
        v-bind:key="item.groupId">
        <div class="headline">
          <h2 class="group-name"> {{ item.group_name }} </h2>
          <h4 class="group-city"> {{ item.city }} </h4> 
        </div>
        <div class="group-description"> 
          <p> {{ item.about }} </p>
        </div>
        <router-link v-bind:to="{
          name:'group-mainpage',
          params: { groupId: item.group_id } }" >Learn More</router-link>

      </section> 
    </div>
  </div>
</template>

<script>
import GroupService from "../services/GroupService.js";

export default {
  name: "group-list",
  data() {
    return {
      groups: [],
      // searchTerm: ""
    };
  },
  created() {
    GroupService.getAllGroups().then((response) => {
      this.groups = response.data;
    });
  },
  // computed: {
  //     filteredGroups() {
  //         return this.groups.filter( (item) => {
  //             return item.name.toLowerCase().includes(this.searchTerm.toLowerCase());
  //         })
  //     }
  // }
};
</script>

<style scoped>
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
