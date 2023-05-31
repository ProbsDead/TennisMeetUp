<template>
  <div>
    <h3>{{ about }}</h3>
  </div>
</template>

<script>
import GroupService from '../services/GroupService';
export default {
  data() {
    return {
      about: {}
    };
  },
  created(){
    console.log("created starts");
    GroupService.getGroupDetails(this.$route.params.groupId).then(
      (response) => {
        console.log("before group assignment")
        this.about = response.data.about;
      }
    )
  }
};
</script>

<style scoped></style>
