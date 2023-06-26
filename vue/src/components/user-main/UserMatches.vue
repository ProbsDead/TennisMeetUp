<template>
  <div class="div-parent">
    <div class="content-box">
      <div class="x-icon"><i class='bx bx-x' @click="TogglePopup()"></i></div>
      <p v-if="!matches.length">You have no match history.</p>
      <table v-else>
        <thead>
          <tr>
            <th class="tdata">Date</th>
            <th class="tdata">Opponent</th>
            <th class="tdata">Length</th>
            <th class="tdata">Score</th>
            <th class="tdata">Result</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="match in matches" :key="match.match_id">
            <td class="tdata">{{ formattedDate(match.date) }}</td>
            <td class="tdata">{{}}</td>
            <td class="tdata">{{ match.match_length }} min</td>
            <td class="tdata">{{ match.score }}</td>
            <td class="tdata">{{ match.result }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
// import UserService from "../../services/UserService.js";
// import EventService from "../../services/EventService.js";

export default {
  props: ["TogglePopup", "matches"],
  mounted() {
    this.$refs.address.focus();
  },
 
  methods: {
    formattedDate(startTime) {
      const dateObject = new Date(startTime);
      const year = dateObject.getFullYear();
      const month = dateObject.getMonth() + 1;
      const day = dateObject.getDate();

      return `${month < 10 ? "0" + month : month}/${
        day < 10 ? "0" + day : day
      }/${year}`;
    },
    handleError(error) {
      //A reusable error function to be used in the catch statements
      if (error.request) {
        this.errorMsg =
          "Error submitting request. Server could not be reached.";
      } else {
        this.errorMsg = "An error occurred, please try again later.";
      }
    },
  },
};
</script>

<style scoped>
.div-parent {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 99;
  background-color: rgba(255, 255, 255, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.content-box {
  background-color: rgb(153, 194, 188);
  border-radius: 10px;
  padding: 8px;
  display: block;
  text-align: center;
  min-height: 85vh;
  min-width: 85vh;
}

div.x-icon{
  font-size: 30px;
  text-align: right;
}
table {
  padding: 0.5em;
  min-width: 100%;
  margin: 0 auto;
  font-weight: bold;
}

tr > th {
  font-size: 18px;
}
.tdata {
  padding-top: 15px;
}
</style>
