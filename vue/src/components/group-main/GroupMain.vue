<template>
  <div>
    <section class="description">
      <!-- <div class="group-info"> -->
        <div class="split-view">
          <!-- <div class="image-box"> -->
            <img :src="getImage" 
                alt="meetup-image" 
                class="title-image"/>
          <!-- </div> -->
          <div class="details">
            <h1>{{ group.group_name }}</h1>
            <!-- <button class="request">Request Membership</button> -->
            <div class="container">
              <div>
                <i class="bx bxs-map"></i> <span>{{ group.city }}</span>
              </div>
              <div>
                <i class="bx bxs-map-pin"></i><span>{{ group.location }}</span>
              </div>
              <div>
                <i class="bx bxs-user-account"></i
                ><span>{{ groupMembers.length }} members</span>
              </div>
              <div>
                <i class="bx bx-glasses"></i
                ><span>{{
                  group.is_public ? `Public group` : `Private group`
                }}</span>
              </div>
              <div>
                <i class="bx bx-user"></i>
                <span>Organized by {{ adminName }}</span>
              </div>
            </div>
          </div>
        </div>
      <!-- </div> -->
    </section>
    <hr />

    <section class="tabs">
      <span
        @click="renderSection"
        v-bind:class="{ highlight: currentTab === 'About' }"
        >About</span
      >
      <span
        @click="renderSection"
        v-bind:class="{ highlight: currentTab === 'Events' }"
        >Events</span
      >
      <span
        @click="renderSection"
        v-bind:class="{ highlight: currentTab === 'Members' }"
        >Members</span
      >
      <span> Photos </span>
      <button :disabled="isDisabled" @click.prevent="createRequest()">
        {{ buttonText }}
      </button>
      <button v-if="currentTab === 'Events' && $store.state.user.id === group.created_by" 
        @click="() => TogglePopup('buttonTrigger')">
        Create Event
      </button>
      <create-event v-if="popupTrigger.buttonTrigger"
        :TogglePopup="() => TogglePopup('buttonTrigger')">
      </create-event>
    </section>

    <section class="group-details">
      <group-about-section v-if="currentTab === 'About'"></group-about-section>
      <group-events-section
        v-else-if="currentTab === 'Events'"
      ></group-events-section>
      <group-members-section
        v-else-if="currentTab === 'Members'"
        :members="memberNames"
        :groupAdmin="adminName"
      ></group-members-section>
    </section>
  </div>
</template>

<script>
import GroupService from "../../services/GroupService.js";
import GroupAboutSection from "./GroupAboutSection.vue";
import GroupEventsSection from "./GroupEventsSection.vue";
import GroupMembersSection from "./GroupMembersSection.vue";
import RequestService from "../../services/RequestService";
import CreateEvent from "../events/CreateEvent.vue";
import { ref } from "vue";

export default {
  name: "group-main",
  components: {
    GroupAboutSection,
    GroupEventsSection,
    GroupMembersSection,
    CreateEvent
  },
  setup() {
    const popupTrigger = ref({
      buttonTrigger: false,
    });
    const TogglePopup = (trigger) => {
      popupTrigger.value[trigger] = !popupTrigger.value[trigger];
    };
    return {
      CreateEvent,
      popupTrigger,
      TogglePopup,
    };
  },
  data() {
    return {
      group: {},
      groupMembers: [],
      adminName: "",
      memberNames: [],
      // currentTab indicates what the user clicked on: about / events / members
      currentTab: "About",
      request: {
        groupId: "",
        joiningUserId: "",
        status: "Pending",
        inviteOrRequest: "Request",
      },
      buttonText: "Join this Group",
      isDisabled: false,
      allRequests: {},
      imgSrc: require("../../assets/court-hills.jpg"),
    };
  },
  computed: {
    getImage() {
      return this.imgSrc;
    },
  },
  created() {
    GroupService.getGroupDetails(this.$route.params.groupId).then(
      (response) => {
        this.group = response.data;
        const imageData = response.data.group_image;
        const imageType = response.data.image_type;

        // if image is not null, Convert the byte array to a data URL
        if (imageData != null) {
          // const base64Img = btoa(String.fromCharCode.apply(null, imageData));
          const dataURL = `data:${imageType};base64,${imageData}`;

          // use the dataURL to display the image
          this.imgSrc = dataURL;
        }
        // retrieve all group members, and store in an alphabetical order
        GroupService.getGroupMembers(this.$route.params.groupId).then(
          (response) => {
            console.log(this.group.created_by);
            this.groupMembers = response.data;
            this.groupMembers.forEach((member) => {
              // if the user id matches the admin id, store admin name
              if (member.id === this.group.created_by) {
                this.adminName = `${member.first_name} ${member.last_name}`;
              } else {
                this.memberNames.push(
                  `${member.first_name} ${member.last_name}`
                );
              }
              if (member.id == this.$store.state.user.id) {
                this.buttonText = "Already a Member";
                this.isDisabled = true;
              }
            });
            this.memberNames.sort();
          }
        );
      }
    );

    //Ensure that this user does not have a pending request
    RequestService.getAllCurrentRequests(this.$route.params.groupId)
      .then((response) => {
        this.allRequests = response.data;
        this.allRequests.forEach((request) => {
          if (request.joiningUserId == this.$store.state.user.id) {
            this.buttonText = "Request Sent";
            this.isDisabled = true;
          }
        });
      })
      .catch((error) => {
        this.handleError(error);
      });
  },
  methods: {
    renderSection(event) {
      let text = event.target.innerText;
      switch (text) {
        case "About":
          this.currentTab = "About";
          break;
        case "Events":
          this.currentTab = "Events";
          break;
        case "Members":
          this.currentTab = "Members";
          break;
        default:
          this.currentTab = "About";
          break;
      }
    },

    createRequest() {
      //Sends join request, greys out button and changes text once sent
      this.request.groupId = this.$route.params.groupId;
      this.request.joiningUserId = this.$store.state.user.id;
      RequestService.sendRequestToJoin(this.request)
        .then((response) => {
          if (response.status == 200 || response.status == 201) {
            this.buttonText = "Request Sent";
            this.isDisabled = true;
          }
        })
        .catch((error) => {
          this.handleError(error);
        });
    },

    handleError(error) {
      //A reusable error function to be used in the catch statements
      if (error.request) {
        this.errorMsg =
          "Error submitting request. Server could not be reached.";
      } else {
        this.errorMsg = "An error occurred, please try again later.";
      }
    },
  },
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
hr {
  border: 1px solid rgb(232, 229, 229);
}
.split-view {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
}
.title-image {
  max-width: 100%;
  height: 100%;
  object-fit: cover; 
  grid-column: 1 / span 3;
}
.details {
  grid-column: 4 / span 2;
}

.container {
  position: relative;
  display: flex;
  flex-direction: column;
  row-gap: 10px;
  left: 15px;
}

div.container span {
  padding: 10px;
}

section.tabs span {
  margin: 70px;
  margin-left: 10px;
  font-weight: bold;
}

section.tabs {
  margin: 20px;
}

section.tabs span:hover {
  color: #2a9d8f;
  cursor: pointer;
}

section.tabs span.highlight {
  color: #2a9d8f;
}

section.tabs button {
  margin-left: 50px;
}

@media (max-width: 554px) {
  .split-view {
    display: flex;
    flex-flow: row wrap;
    max-width: 100%;
    justify-content: center;
  }
  .details {
    padding-right: 40px;
  }
}
</style>
