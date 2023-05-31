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
    GroupService.getGroupDetails(this.$route.params.groupId).then(
      (response) => {
        this.about = response.data.about;
      }
    )
  }
};
</script>

<style scoped>
div{
  height: 55vh;
  border-radius: 8px;
  background-color: rgb(166, 230, 230);
}
h3{

  
}
</style>
