<template>
  <div class="all-groups">
    <div>
      <div class="search-bar">
        <i class='bx bx-search'></i> <span> search a group</span>
        <br>
        <div class="search-expand">
          <label for="search-name"> Search by group name: </label>
        <input type="text" v-model="searchByName"/>
        <label for="search-city"> Search by city: </label>
        <input type="text" v-model="searchByCity"/>
        <button></button>
        </div>
        
      </div>
      <div v-if="!filtered">
        <h1>Browse all public groups</h1>
        <section
          class="group-box"
          v-for="item in groups"
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

      <div v-else></div>
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
      searchByName: "",
      searchByCity:"",
      filtered: false,
      filteredGroups: [],
    };
  },
  created() {
    GroupService.getAllPublicGroups().then((response) => {
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
