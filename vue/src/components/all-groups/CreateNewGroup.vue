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
        <vue-google-autocomplete 
          ref="address"
          id="map" 
          classname="form-control" 
          placeholder="Address" 
          v-on:placechanged="getAddressData" 
          country="us">
        </vue-google-autocomplete>
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
        ></textarea>
      </div>
      <div>
        <p>Do you want this group to be private (not visible to the public)?</p>
        <label for="yes-public">Yes: </label>
        <!-- if user clicks on checkbox, isPublic will be true; if not clicked, false-->
        <input type="checkbox" name="isPrivate" v-model="isPrivate" />
        <!-- <input type="checkbox" name="yes-public" id="yes-public" />
        <br />
        <label for="no-public">No: </label>
        <input
          type="checkbox"
          name="no-public"
          id="no-public"
          @click="togglePublic"
        /> -->
      </div>

      <button type="submit" @click="submitBtn">Create Group!</button>
    </form>
  </div>
</template>
<script>
import GroupService from "../../services/GroupService";
// import loadjs from "loadjs";
import VueGoogleAutocomplete from 'vue-google-autocomplete';

export default {
  name: "create-new-group",

  components: {
    VueGoogleAutocomplete
  },

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
      
    };
  },

  mounted() {
    this.$refs.address.focus();
  },

  methods: {
    getAddressData: function(addressData){
      this.group.state = addressData.administrative_area_level_1;
      this.zip = addressData.postal_code;
      this.group.city = addressData.locality;
      this.group.location = addressData.street_number + " " + addressData.route + ", " + this.group.city + ", " + this.group.state + " " + this.zip;
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
<style scoped></style>
