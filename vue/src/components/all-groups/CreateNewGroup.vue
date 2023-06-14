<template>
    <div>
        <form action="">
            <!-- <label for="groupName">Group Name: </label>
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
            <input type="text" id="autocomplete" placeholder="Address" ref="origin"/> -->

            <div>
                <input type="text" name="location" id="location" v-model="location">
                <ul>
                    <li v-for="(result, i) in searchResults" :key="i">
                        {{ result }} //list of places
                    </li>
                </ul>
            </div>
           
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
            location: '',
            searchResults: [],
            service: null //will reveal this later
        }
    },
    metaInfo(){
        return {
            script: [{
                src: `https://maps.googleapis.com/maps/api/js?key=AIzaSyB4VHlZLaqtdPglGyfl0Uk2-AHDeeuJBfU&libraries=places`,
                async: true,
                defer: true,
                callback: () => this.MapsInit() //will declare it in methods
            }]
        }
    },

    created() {

    },
    // mounted(){
    //     const google = window.google;
    //     const originAutocomplete = new google.maps.places.Autocomplete(
    //         this.$refs["origin"], 
    //         {
    //             bounds: new google
    //         }   
    //     );

    //     originAutocomplete.addListener("place_changed", () => {
    //         originAutocomplete.getPlace();
    //     });
   
    // },

    methods: {
        MapsInit () {
            this.service = new window.google.maps.places.AutocompleteService()
        },
        displaySuggestions (predictions, status) {
            if (status !== window.google.maps.places.PlacesServiceStatus.OK) {
                this.searchResults = []
                return
            }
            this.searchResults = predictions.map(prediction => prediction.description)
        },

        handleError(error){
      //A reusable error function to be used in the catch statements
      if(error.request){
        this.errorMsg = "Error submitting request. Server could not be reached.";
      } else {
        this.errorMsg = "An error occurred, please try again later.";
      }
    }
    },
    watch: {
        location (newValue) {
            if (newValue) {
                this.service.getPlacePredictions({
                    componentRestrictions: { country: 'us'},
                    input: this.location,
                    types: ['(address)']
                }, this.displaySuggestions)
            }
        }
    }
    
}
</script>
<style scoped>
    
</style>
