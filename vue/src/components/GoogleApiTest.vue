<template>
  <div>
    <input type="text" v-model="location" />
    <ul>
      <li v-for="(result, i) in searchResults" :key="i">
        {{ result }} 
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data: () => ({
    location: "",
    searchResults: [],
    service: null,
  }),
  methods: {
    MapsInit () { // function that's called when JS file is loaded
        this.service = new window.google.maps.places.AutocompleteService()
      },
      displaySuggestions (predictions, status) {
        if (status !== window.google.maps.places.PlacesServiceStatus.OK) {
          this.searchResults = []
          return
        }
        this.searchResults = predictions.map(prediction => prediction.description) 
      }
  },
  metaInfo () {
      return {
        script: [{
          src: `https://maps.googleapis.com/maps/api/js?key=AIzaSyB4VHlZLaqtdPglGyfl0Uk2-AHDeeuJBfU&libraries=places`,
          async: true,
          defer: true,
          callback: () => this.MapsInit() // will declare it in methods
        }]
      }
    },
    watch: {
      location (newValue) {
        if (newValue) {
          this.service.getPlacePredictions({
            componentRestrictions: {country: 'us'},
            input: this.location,
            types: ['(cities)', 'address']
          }, this.displaySuggestions)
        }
      }
    }
};
</script>

<style></style>
