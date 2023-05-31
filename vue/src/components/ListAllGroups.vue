<template>
  <div class="all-groups">
    <div
      class="group-box"
      v-for="item in groups"
      v-bind:key="item.groupId"
    >
    {{item.group_id}}
    {{item.group_name}}
    {{item.about}}</div>
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

<style scoped></style>
