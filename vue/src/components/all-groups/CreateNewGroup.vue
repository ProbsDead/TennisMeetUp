<template>
    <div>
        <form action="">
            <div>
                <label for="groupName">Group Name: </label>
                <input type="text" id="groupName" v-model="groupName">
            </div>
            <div>
                <label for="autocomplete">Meet-Up Home Location: </label>
                <br>
                <input type="text" placeholder="Address or Place" id="autocomplete">
            </div>
            <div>
                <input type="text" id="city" placeholder="City">
            </div>
            <div>
                <select name="state" id="state" placeholder="Select State">
                    <option
                        v-for="(stateAbbrev, index) in this.$store.state.stateAbbrev"
                        v-bind:key="index"
                    >
                    {{ stateAbbrev }}
                    </option>
                </select>
            </div>
            <div>
                <input type="text" name="zip" id="zip" placeholder="Zip Code">
            </div>
            <div>
                <label for="description">Tell us about this group:</label>
                <br>
                <textarea name="description" id="description" cols="70" rows="6"></textarea>
            </div>
            <div>
                <p>Do you want this group to be visible to the public?</p>
                <label for="yes-public">Yes: </label>
                <input type="checkbox" name="yes-public" id="yes-public">
                <br>
                <label for="no-public">No: </label>
                <input type="checkbox" name="no-public" id="no-public">
            </div>

            <button type="submit">Create Group!</button>
           
            this is a test
        </form>

    </div>
</template>
<script>

export default {
    name: "create-new-group",

    components: {
        
    },

    data() {
        return{
            groupName: "",
            
        }
    },
    
    mounted(){
        const autocomplete = new window.google.maps.places.Autocomplete(
            document.getElementById("autocomplete"),
        );

        autocomplete.setComponentRestrictions({
            country: ["us"]
        })
   
    },

    methods: {

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
