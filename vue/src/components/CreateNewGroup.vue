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

    methods: {

    }
    
}
</script>
<style scoped>

</style>
