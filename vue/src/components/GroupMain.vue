<template>
  <div>
    <section class="description">
      <div class="group-info">
        <div class="split-view">
          <img src="../assets/tennis-court.jpg" alt="meetup-image" />
          <div class="details">
            <h1>{{ group.group_name }}</h1>
            <div class="container">
              <div><i class="bx bxs-map"></i> {{ group.city }}</div>
              <div>
                <i class="bx bxs-map-pin"></i><span>{{ group.location }}</span>
              </div>
              <div>
                <i class="bx bxs-user-account"></i
                ><span>{{ groupMembers.length }} members</span>
              </div>
              <div>
                <i class="bx bx-glasses"></i
                >{{ group.is_public ? `Public` : `Private` }}
              </div>
              <div><i class="bx bx-user"></i> Organized by {{}}</div>
            </div>
          </div>
        </div>

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
    </section>

    <section class="group-details"></section>
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

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Assistant&display=swap");
* {
  font-family: "Assistant", sans-serif;
}

.description h1 {
  font-size: 2rem;
}
img {
  border-radius: 8px;
  width: 45%;
  height: 300px;
}
.split-view {
  display: flex;
  gap: 30px;
}
.container {
  display: flex;
  flex-direction: column;
}
group-members {
  display: flex;
}
</style>
