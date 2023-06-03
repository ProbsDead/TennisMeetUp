<template>
  <div class="all-groups">
    <list-all-groups></list-all-groups>
  </div>
</template>

<script>
import ListAllGroups from "../components/ListAllGroups.vue";

export default {
  name: "search-groups",
  components: {
    ListAllGroups,
  },
};
</script>

<style scoped></style>
