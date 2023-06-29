<template>
  <div class="list-groups">
   
    <div>
      <h2>{{ title }}</h2>
      <div class="btn"><button
        class="create-group"
        @click="() => TogglePopup('buttonTrigger')"
        v-if="$store.state.token != ''"
      >
        Create New Group
      </button></div>
      
    </div>
    <create-new-group
      v-if="popupTrigger.buttonTrigger"
      :TogglePopup="() => TogglePopup('buttonTrigger')"
    >
    </create-new-group>

    <div class="groups">
      <section
        class="group-box"
        v-for="item in allGroups"
        v-bind:key="item.groupId"
      >
        <div class="group-name">{{ item.group_name }}</div>
        <div class="group-city">{{ item.city }}, {{ item.state }}</div>

        <div class="group-description">
          <p>{{ item.about }}</p>
        </div>
        <router-link
          v-bind:to="{
            name: 'group-mainpage',
            params: { groupId: item.group_id },
          }"
          >Learn More</router-link
        >
      </section>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import CreateNewGroup from "./CreateNewGroup.vue";

export default {
  components: { CreateNewGroup },
  setup() {
    const popupTrigger = ref({
      buttonTrigger: false,
    });
    const TogglePopup = (trigger) => {
      popupTrigger.value[trigger] = !popupTrigger.value[trigger];
    };
    return {
      CreateNewGroup,
      popupTrigger,
      TogglePopup,
    };
  },
  props: {
    allGroups: Array,
    title: String,
  },
  data() {
    return {
      filteredGroupsByName: [],
      filteredGroupsByCity: [],
    };
  },
};
</script>

<style scoped>
div.list-groups > h2 {
  padding-left: 20px;
}

h2, .btn{
   text-align: center;
}

div.groups {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
}

.group-box {
  max-width: 35vw;
  font-family: "Poppins", sans-serif;
  padding-left: 20px;
  padding-right: 20px;
  box-shadow: rgba(0, 0, 0, 0.16) 4px 3px 4px;
  border-radius: 5px;
  margin: 10px;
}
.group-box:hover {
  box-shadow: 5px 5px 12px rgba(33, 33, 33, 0.2);
}
.group-name {
  font-size: 1.5em;
}
@media print {
  .noprint {
    display: none;
  }
}
</style>
