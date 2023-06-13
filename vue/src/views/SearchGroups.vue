<template>
  <div class="all-groups">
    <search-and-list-groups></search-and-list-groups>
  </div>
</template>

<script>
import SearchAndListGroups from "../components/all-groups/SearchAndListGroups.vue";

export default {
  name: "search-groups",
  components: {
    SearchAndListGroups,
  },
};
</script>

<style scoped></style>
