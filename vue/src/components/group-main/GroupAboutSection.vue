<template>
  <div class="about">
    <h2>What We're About</h2>
    <h4>{{ about }}</h4>
  </div>
</template>

<script>
import GroupService from "../../services/GroupService";
export default {
  data() {
    return {
      about: {},
    };
  },
  created() {
    GroupService.getGroupDetails(this.$route.params.groupId).then(
      (response) => {
        this.about = response.data.about;
      }
    );
  },
};
</script>

<style scoped>
@import url('https://fonts.cdnfonts.com/css/graphik?styles=44656');
div.about {
  height: 55vh;
  border-radius: 8px;
  padding: 30px 90px 0px 40px;
  background-color: rgb(251, 253, 246);
  font-family: 'Graphik', sans-serif;
}
</style>
