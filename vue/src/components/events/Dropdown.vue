<template>
  <div>
    <div class="selected-player" v-if="selectedItem" @click="resetSearch">
      {{ selectedItem }}
    </div>
    <div class="dropdown-popover" v-else>
      <input
        @click="isVisible = true"
        type="text"
        v-model="searchQuery"
        placeholder="Search player"
      />
      <div v-if="isVisible" class="options">
        <ul>
          <li
            v-for="player in filteredPlayers"
            :key="player.id"
            @click="updatePlayer(player, $event)"
          >
            {{ player.first_name }} {{ player.last_name }}
          </li>
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
      selectedPlayerId: 0,
      isVisible: false,
    };
  },
  computed: {
    filteredPlayers() {
      const query = this.searchQuery.toLowerCase();
      return this.players.filter((player) => {
        const name = `${player.first_name} ${player.last_name}`;
        return name.includes(query);
      });
    },
  },

  methods: {
    updatePlayer(player, event) {
      this.selectedItem = event.target.innerText;
      this.selectedPlayerId = player.id;

      // to emit the selected playerId to the parent component, SingleMatchForm
      this.$emit('child-playerId-input', this.selectedPlayerId);
    },
    resetSearch() {
      this.selectedItem = null;
      this.isVisible = true;
    },

    // method to run when user clicks outside the dropdown & search bar area
    handleDocumentClick(event) {
      const target = event.target;
      const searchBar = this.$el.querySelector(".dropdown-popover");

      if (searchBar && !searchBar.contains(target)) {
        this.isVisible = false;
      }
    },

  },

  mounted() {
    document.addEventListener("click", this.handleDocumentClick);
  },
  beforeUnmount() {
    document.removeEventListener("click", this.handleDocumentClick);
  },
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
  border-right: 1px solid lightgray;
  border-left: 1px solid lightgray;
  border-bottom: 1px solid lightgray;
}

.options ul {
  list-style: none;
  text-align: left;
  padding: 0px;
  margin: 0px;
  max-height: 100px;
  overflow-y: scroll;
  overflow-x: hidden;
}

li {
  width: 100%;
  padding: 5px;
  background-color: #fbfbfb;
  cursor: pointer;
  border-bottom: 1px solid lightgray;
}
li:hover {
  background: #77adb0;
  color: #fff;
}
</style>
