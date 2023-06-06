<template>
  <div class="all-groups">
    <div>
      <div class="browse-all" @click="listAll">
        <i class="bx bx-sidebar"></i>
        <span> Browse all public groups</span>
      </div>
      <div class="search-bar">
        <div class="search-top">
          <i class="bx bx-search" @click="filterType = ''"></i>
          <span class="search-by" @click="filterType = 'name'">
            search by group name</span
          >
          <span> | </span>

          <span class="search-by" @click="filterType = 'city'">
            search by city</span
          >
        </div>

        <div class="search-expand-name" v-show="filterType === 'name'">
          <label for="search-name"> Group name: </label>
          <input type="text" v-model="searchName" />

          <button @click.prevent="searchGroupByName">Search</button>
        </div>

        <div class="search-expand-city" v-show="filterType === 'city'">
          <label for="search-city"> City: </label>
          <input type="text" v-model="searchCity" />
          <select name="state" id="state" v-model="searchState">
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
      </div>

      <div v-else-if="filtered && filteredGroup.length">
        <list-groups
          v-bind:allGroups="filteredGroup"
          v-bind:title="
            filterType === 'name'
              ? `Searched name: ${searchName}`
              : `Groups near ${searchCity}`
          "
        ></list-groups>
      </div>
      <div v-else>
        <p>No results based on search</p>
      </div>
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
      filteredGroup: [],
      searchName: "",
      searchCity: "",
      searchState: "",
      filtered: false,
      filterType: "",
    };
  },
  created() {
    GroupService.getAllPublicGroups().then((response) => {
      // sorting the array alphabetically by CITY and storing it in the groups array
      this.groups = response.data.sort(function (a, b) {
        let cityA = a.city.toLowerCase();
        let cityB = b.city.toLowerCase();
        return cityA < cityB ? -1 : cityA > cityB ? 1 : 0;
      });
    });
  },
  methods: {
    searchGroupByName() {
      // reset the filtered group each time they click on search
      this.filteredGroup = [];
      this.filtered = true;
      this.searchCity = "";

      // add to filteredGroup if the name they searched for is "included" (doesn't have to exactly match) in the name
      this.groups.forEach((group) => {
        if (
          group.group_name.toLowerCase().includes(this.searchName.toLowerCase())
        ) {
          this.filteredGroup.push(group);
        }
      });
    },
    searchGroupByCity() {
      this.filteredGroup = [];
      this.filtered = true;
      this.searchName = "";
      this.groups.forEach((group) => {
        if (
          group.city.toLowerCase() === this.searchCity.toLowerCase() &&
          group.state === this.searchState
        ) {
          this.filteredGroup.push(group);
        }
      });
    },
    listAll() {
      this.filtered = false;
      this.filteredGroup = [];
      this.searchName = "";
      this.searchCity = "";
      (this.searchState = ""), (this.filterType = "");
    },
  },
};
</script>

<style scoped>
* {
  margin-left: 5px;
}

.headline {
  display: inline-flex;
  width: 100%;
  justify-content: space-between;
}
div.search-top i,
div.browse-all i {
  height: 30px;
  font-size: 23px;
  text-align: center;
  margin-left: 15px;
}
.search-top,
.search-expand-name,
.search-expand-city, .browse-all {
  display: flex;
  justify-content: flex-start;
  gap: 10px;
}

.search-expand-name, .search-expand-city {
  margin-top: 5px;
  margin-left: 55px;
}
.search-by:hover {
  cursor: pointer;
  background-color: #e3faf7;
  border-radius: 10px;
}
</style>
