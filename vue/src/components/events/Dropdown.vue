<template>
  <div>
  <div class="selected-player" v-if="selectedItem" @click="resetSearch"> {{selectedItem}}</div>
  <div class="dropdown-popover" v-else>
    <!-- @focusout="isVisible=false" -->
    <input @click="isVisible = true"  type="text" v-model="searchQuery" placeholder="Search player" />
    <div v-if="isVisible" class="options">
      <ul>
        <li @click="updatePlayer" v-for="player in filteredPlayers" :key="player.id">{{ player.first_name }} {{ player.last_name }}</li>
      </ul>
    </div>
  </div>

  </div>
</template>

<script>
export default {
  props: {
    players: Array,
  },
  data() {
    return {
      searchQuery: "",
      selectedItem: null,
      isVisible: false
    };
  },
  computed:{
    filteredPlayers() {
      const query = this.searchQuery.toLowerCase();
      return this.players.filter((player) => {
        const name = `${player.first_name} ${player.last_name}`;
        return name.includes(query);
      })
    }
  },
  methods:{
    updatePlayer(event) {
      this.selectedItem = event.target.innerText;
      console.log(this.selectedItem);
    },
    resetSearch() {
      this.selectedItem = null;
      this.isVisible = true;
    }
  }
};
</script>

<style scoped>
.dropdown-popover {
  max-width: 250px;
  /* border: 2px solid lightgray; */
  background-color: #fff;
  font-size: 15px;
  font-family: arial;
  text-align: center;
}

input {
  width: 96%;
  height: 25px;
  border: 2px solid lightgray;
}

.options {
  width: 100%;
  border-right:1px solid lightgray;
  border-left: 1px solid lightgray;
 border-bottom:1px solid lightgray;
 
}

.options ul {
  list-style: none;
  text-align: left;
  padding:0px;
  margin: 0px;
  max-height: 100px;
  overflow-y: scroll;
  overflow-x: hidden;
}

li{
 width: 100%; 
  padding: 5px;
  background-color:#fbfbfb;
  cursor: pointer;
  border-bottom: 1px solid lightgray;
 
}
li:hover{
  background:#77adb0;
  color: #fff;

}
</style>
