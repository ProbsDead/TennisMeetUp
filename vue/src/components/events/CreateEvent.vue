<template>
    <div>
        <form action="">
            <div class="event_name">
                <label for="name">Event Name: </label>
                <input type="text" name="name" id="name" v-model="event.event_name">
            </div>
            <div class="description">
                <label for="desc">Event Description: </label>
                <input type="text" name="desc" id="desc" v-model="event.description">
            </div>
            <div class="start-time">
                <label for="start">Start Time: </label>
                <input type="datetime-local" name="start" id="start" v-model="event.start_time">
            </div>
            <div class="end-time">
                <label for="end">End Time: </label>
                <input type="datetime-local" name="end" id="end" v-model="event.end_time">
            </div>
            <div class="address">
                <label for="map">Event Location: </label>
                <vue-google-autocomplete
                    ref="address"
                    id="map"
                    classname="form-control"
                    placeholder="Address"
                    v-model="event.location"
                    country="us"
                    style="width: 250px">
                </vue-google-autocomplete>
            </div>
            <div class="buttons">
                <button>Cancel</button>
                <button type="submit">Create</button>
            </div>
                
            
        </form>

    </div>
</template>

<script>
import VueGoogleAutocomplete from "vue-google-autocomplete";

export default{

    components: {
        VueGoogleAutocomplete,
    },

    data(){
        return {
            event: {
                event_name: "",
                description: "",
                start_time: null,
                end_time: null,
                location: "",
                created_by: 0
            }
        }
    }
}
</script>

<style scoped>

</style>