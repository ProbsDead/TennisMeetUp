<template>
  <div>
    <h3>{{ group.about }}</h3>
  </div>
</template>

<script>
import GroupService from '../services/GroupService';
export default {
  data() {
    return {
      group: {}
    };
  },
  created(){
    console.log("created starts");
    GroupService.getGroupDetails(this.$route.params.groupId).then(
      (response) => {
        console.log("before group assignment")
        this.group = response.data;
      }
    )
  }
};
</script>

<style scoped></style>
