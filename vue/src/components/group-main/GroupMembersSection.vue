<template>
  <div class="members-list">
    <div>
      <span class="admin-title">Admin </span>
      <div class="each-member">
        <i class="bx bx-user-check"></i>{{ groupAdmin }}
      </div>
    </div>
    <br />
    <div class="group-members">
      <span class="members-title"> Group Members</span>

      <div
        class="each-member"
        v-for="(name, index) in memberNames"
        :key="index"
      >
        <i class="bx bxs-tennis-ball"></i>
        {{ name }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    members: Array,
    groupAdmin: String,
  },
  data() {
    return {
      adminName: this.groupAdmin,
      memberNames: this.members,
    };
  },
};
</script>

<style scoped>
@import url("https://fonts.cdnfonts.com/css/graphik?styles=44656");
.members-list {
  height: 55vh;
  border-radius: 8px;
  padding: 30px 90px 0px 40px;
  background-color: rgb(251, 253, 246);
  font-family: "Graphik", sans-serif;
}
.admin-title,
.members-title {
  font-size: 1.3rem;
  font-weight: bold;
}

.admin-member,
.each-member {
  margin: 10px;
}

i {
  font-size: 22px;
  text-align: center;
}
</style>
