<template>
    <div>
        <form action="">
            <label for="groupName">Group Name: </label>
            <input type="text" id="groupName">
            
            <label for="state">State: </label>
            <select name="state" id="state">
                <option
                    v-for="(stateAbbrev, index) in this.$store.state.stateAbbrev"
                    v-bind:key="index"
                >
                    {{ stateAbbrev }}
                </option>
            </select>
            <label for="city">City: </label>
            <input type="text" id="city">
            <label for="location">Meet Up Location: </label>
            <input type="text" id="autocomplete" placeholder="Address" ref="origin"/>
           
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

        }
    },

    created() {

    },
    mounted(){
        const google = window.google;
        const originAutocomplete = new google.maps.places.Autocomplete(
            this.$refs["origin"],    
        );

        originAutocomplete.addListener("place_changed", () => {
            originAutocomplete.getPlace();
        });
   
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
