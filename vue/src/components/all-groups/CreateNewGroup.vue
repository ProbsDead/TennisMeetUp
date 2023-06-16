<template>
    <div>
        <form action="">
            <div>
                <label for="groupName">Group Name: </label>
                <input type="text" id="groupName" v-model="group.groupName">
            </div>
            <div>
                <label for="autocomplete">Meet-Up Home Location: </label>
                <br>
                <input type="text" placeholder="Address or Place" id="autocomplete" v-model="group.address">
            </div>
            <div>
                <input type="text" id="city" placeholder="City" v-model="group.city">
            </div>
            <div>
                <select name="state" id="state" placeholder="Select State" v-model="group.state">
                    <option
                        v-for="(stateAbbrev, index) in this.$store.state.stateAbbrev"
                        v-bind:key="index"
                    >
                    {{ stateAbbrev }}
                    </option>
                </select>
            </div>
            <div>
                <input type="text" name="zip" id="zip" placeholder="Zip Code" v-model="zip">
            </div>
            <div>
                <label for="description">Tell us about this group:</label>
                <br>
                <textarea name="description" id="description" cols="70" rows="6" v-model="group.about"></textarea>
            </div>
            <div>
                <p>Do you want this group to be visible to the public?</p>
                <label for="yes-public">Yes: </label>
                <input type="checkbox" name="yes-public" id="yes-public">
                <br>
                <label for="no-public">No: </label>
                <input type="checkbox" name="no-public" id="no-public" @click="togglePublic">
            </div>

            <button type="submit" @click="submitBtn">Create Group!</button>
           
            
        </form>

    </div>
</template>
<script>
import GroupService from '../../services/GroupService';

export default {
    name: "create-new-group",

    components: {
        
    },

    data() {
        return{
            group: {
                groupName: "",
                address: "",
                state: "",
                city: "",
                createdBy: null,
                about: "",
                isPublic: true
            },
            zip: "",
            
        }
    },
    
    mounted(){
        this.initAutocomplete();
    },

    methods: {
        initAutocomplete(){
            const autocomplete = new window.google.maps.places.Autocomplete(
                document.getElementById("autocomplete"),
            );

            autocomplete.setComponentRestrictions({
                country: ["us"]
            })
        },
        splitAddressIntoBoxes(){

        },

        togglePublic (){
            if(this.group.isPublic === true){
                this.group.isPublic = false;
            } else {
                this.group.isPublic = true;
            }
        },

        submitBtn(){
            this.group.createdBy = this.$store.state.user.id;

            GroupService.createNewGroup(this.group).then((response) => {
                if (response.status === 200 || response.status === 201){
                    console.log("Group successfully created!")
                }
            }).catch((error) =>{
                this.handleError(error);
            })
        },

        handleError(error){
      //A reusable error function to be used in the catch statements
      if(error.request){
        this.errorMsg = "Error submitting request. Server could not be reached.";
      } else {
        this.errorMsg = "An error occurred, please try again later.";
      }
    }
    }
    
}
</script>
<style scoped>
    
</style>
