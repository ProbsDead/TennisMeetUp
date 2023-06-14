import axios from 'axios';

const https = axios.create({
    baseURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyB4VHlZLaqtdPglGyfl0Uk2-AHDeeuJBfU&libraries=places&callback=initAutocomplete",
});

export default {
    autoCompleteAddress () {
        return https.get
    }
}