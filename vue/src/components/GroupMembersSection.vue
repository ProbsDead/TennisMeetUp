<template>
  <div class="members-list">
    <div>Admin: {{ groupAdmin }}</div>
    <br />
    <div class="group-members">
      Group Members:
      <div
        class="each-member"
        v-for="(name, index) in memberNames"
        :key="index"
      >
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
.members-list {
  margin: 40px;
}
</style>
