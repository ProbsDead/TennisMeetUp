<template>
<div class="div-parent">
  <div class="form-parent">
    <h2>Create A New Group</h2>
    <form class="form-body">
      <div class="name-group">
        <label for="groupName">Group Name: </label>
        <input type="text" id="groupName" v-model="group.group_name" required />
      </div>
      <div class="address-group">
        <label for="autocomplete">Meet-Up Home Location: </label>
        
        <vue-google-autocomplete 
          ref="address"
          id="map" 
          classname="form-control" 
          placeholder="Address" 
          v-on:placechanged="getAddressData" 
          country="us"
          style="width: 300px;">
        </vue-google-autocomplete>
      </div>
      <div class="address-group">
        <label for="city">Group Base City: </label>
        <input
          type="text"
          id="city"
          placeholder="City"
          v-model="group.city"
          required
        />
      </div>
      <div class="address-group">
        <label for="state">Group Base State: </label>
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
        <input type="file" ref="uploadImage" @change="uploadImage" />
      </div>
      <div class="private-public">
        <p>Do you want this group to be private (not visible to the public)?</p>
        <label for="yes-public">Yes: </label>
        <!-- if user clicks on checkbox, isPublic will be true; if not clicked, false-->
        <input type="checkbox" name="isPrivate" v-model="isPrivate" />
      
      </div>
        <div class="button-holder">
            <button type="cancel" @click="TogglePopup()">Cancel</button>
            <button type="submit" @click="submitBtn">Create Group!</button>
        </div>
      
    </form>
  </div>
  </div>
</template>
<script>
import GroupService from "../../services/GroupService";
import VueGoogleAutocomplete from 'vue-google-autocomplete';
import ImageService from '../../services/ImageService';

export default {
  name: "create-new-group",

  components: {
    VueGoogleAutocomplete
  },
  props: ['TogglePopup'],

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
      newGroupId: 0,
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
            this.newGroupId = response.data.group_id;
            if (this.imageData) {
        ImageService.uploadImage(this.imageData, this.newGroupId)
          .then((response) => {
            console.log(response);
            if (response.status === 200 || response.status === 201) {
              console.log("Image successfully uploaded!");
            }
          })
          .catch(() => {
            console.log("Image was not uploaded");
          });
      }
          }
        })
        .catch((error) => {
          this.handleError(error);
        });
        this.TogglePopup();
        window.location.reload;
    },
    uploadImage(event) {
      const file = event.target.files[0];
      this.imageData = file;

      const formData = new FormData();
      formData.append("image", file);

      this.imageData = formData;
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
    .div-parent{
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        z-index: 99;
        background-color: rgba(255,255,255,0.5);
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .form-parent{
        background-color: rgba(42, 157, 143, 0.8);
        border: 2px;
        border-style: solid;
        border-color: black;
        border-radius: 10%;
        padding: 8px;
        display: block;
        text-align: center;
    }
    .form-body{
        display: inline-block;
        margin-left: auto;
        margin-right: auto;
        text-align: left;
    }
    div{
        margin-bottom: 20px;
    }
    .button-holder{
        text-align: center;
    }
    p{
        display: inline-block;
        margin-right: 5px;
    }
  
</style>
