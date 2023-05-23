<template>
  <div>
    <section>
      <h1>Name: {{ group.group_name }}</h1>
      <h2>Group details</h2>
      <div>City: {{ group.city }}</div>
      <div>Location: {{ group.location }}</div>
      <div>{{ group.is_public ? `Public` : `Private` }}</div>
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
    </section>

    <section></section>
  </div>
</template>

<script>
import GroupService from "../services/GroupService.js";

export default {
  name: "group-main",
  data() {
    return {
      group: {},
      groupMembers: [],
      memberNames: [],
    };
  },
  created() {
    GroupService.getGroupDetails(this.$route.params.groupId).then(
      (response) => {
        this.group = response.data;
        //     response comes in this format:
        //  "city": "Burlington",
        // "location": "45 Tennis Ct, South Burlington, VT 05403",
        // "public": false,
        // "group_id": 1,
        // "group_name": "Volley Girls",
        // "created_by": 1,
        // "is_public": false
      }
    );

    GroupService.getGroupMembers(this.$route.params.groupId).then(
      (response) => {
        this.groupMembers = response.data;
        response.data.forEach((member) => {
          this.memberNames.push(`${member.first_name} ${member.last_name}`);
        });
        this.memberNames.sort();
      }
    );
  },
  methods() {},
};
</script>

<style>
group-members {
  display: flex;
}
</style>
