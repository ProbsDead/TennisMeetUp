<template>
  <div class="all-groups">
    <div>
      <div class="search-bar">
        <i class="bx bx-search"></i>
        <span class="search-by" @click="filterType = 'name'">
          search by group name</span
        >
        |

        <span class="search-by" @click="filterType = 'city'">
          search by city</span
        >
        <br />
        <div class="search-expand-name" v-show="filterType === 'name'">
          <label for="search-name"> Group name: </label>
          <input type="text" v-model="searchByName" />

          <button @click.prevent="searchGroupByCity">Search</button>
        </div>

        <div class="search-expand-city" v-show="filterType === 'city'">
          <label for="search-city"> City </label>
          <input type="text" v-model="searchByCity" />
          <select name="state" id="state">
            <option
              v-for="(stateAbbrev, index) in this.$store.state.stateAbbrev"
              v-bind:key="index"
            >
              {{ stateAbbrev }}
            </option>
          </select>
          <button @click.prevent="searchGroupByCity">Search</button>
        </div>
      </div>

      <div v-if="!filtered">
        <list-groups
          v-bind:allGroups="groups"
          v-bind:title="`Browse All Groups`"
        ></list-groups>
        <!-- <h1>Browse all public groups</h1>
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
        </section> -->
      </div>

      <div></div>
    </div>
  </div>
</template>

<script>
import GroupService from "../services/GroupService.js";
import ListGroups from "./ListGroups.vue";

export default {
  name: "group-list",
  components: {
    ListGroups,
  },
  data() {
    return {
      groups: [],
      searchName: "",
      searchCity: "",
      filtered: false,
      filterType: "",
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

.search-by:hover {
  cursor: pointer;
  border-bottom: 1px solid #2a9d8f;
}
</style>
