<template>
  <div>
    <form action="">
      <div>
        <label for="groupName">Group Name: </label>
        <input type="text" id="groupName" v-model="group.group_name" required />
      </div>
      <div>
        <label for="autocomplete">Meet-Up Home Location: </label>
        <br />
        <input
          type="text"
          placeholder="Address or Place"
          id="autocomplete"
          v-model="group.location"
          required
        />
      </div>
      <div>
        <input
          type="text"
          id="city"
          placeholder="City"
          v-model="group.city"
          required
        />
      </div>
      <div>
        <select
          name="state"
          id="state"
          placeholder="Select State"
          v-model="group.state"
          required
        >
          <option
            v-for="(stateAbbrev, index) in this.$store.state.stateAbbrev"
            v-bind:key="index"
          >
            {{ stateAbbrev }}
          </option>
        </select>
      </div>
      <div>
        <input
          type="text"
          name="zip"
          id="zip"
          placeholder="Zip Code"
          v-model="zip"
          required
        />
      </div>
      <div>
        <label for="description">Tell us about this group:</label>
        <br />
        <textarea
          name="description"
          id="description"
          cols="70"
          rows="6"
          v-model="group.about"
          required
        ></textarea>
      </div>
      <div>
        <input type="file" ref="fileInput" @change="uploadImage" />
      </div>
      <div>
        <p>Do you want this group to be private (not visible to the public)?</p>
        <label for="yes-public">Yes: </label>
        <!-- if user clicks on checkbox, isPublic will be true; if not clicked, false-->
        <input type="checkbox" name="isPrivate" v-model="isPrivate" />
      </div>

      <button type="submit" @click.prevent="submitBtn">Create Group!</button>
    </form>
  </div>
</template>

<script>
import GroupService from "../services/GroupService";
import ImageService from "../services/ImageService";

export default {
  name: "create-new-group",

  components: {},

  data() {
    return {
      group: {
        group_name: "",
        location: "",
        state: "",
        city: "",
        created_by: null,
        about: "",
        is_public: true,
      },
      zip: "",
      isPrivate: false,
      imageData: null,
    };
  },
  methods: {
    initAutocomplete() {
      const autocomplete = new window.google.maps.places.Autocomplete(
        document.getElementById("autocomplete")
      );

      autocomplete.setComponentRestrictions({
        country: ["us"],
      });
    },
    submitBtn() {
      this.group.created_by = this.$store.state.user.id;
      if (this.isPrivate) this.group.is_public = false;

      GroupService.createNewGroup(this.group)
        .then((response) => {
          if (response.status === 200 || response.status === 201) {
            console.log("Group successfully created!");
          }
        })
        .catch((error) => {
          this.handleError(error);
        });

      // upload the image separately
      ImageService.uploadImage(this.imageData, )
        .then((response) => {
          if (response.status === 200 || response.status === 201) {
            console.log("Image successfully uploaded!");
          }
        })
        .catch(() => {
          console.log("Image was not uploaded");
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

    uploadImage(event) {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append("image", file);

      this.imageData = formData;
    },
  },
};
</script>

<style></style>
