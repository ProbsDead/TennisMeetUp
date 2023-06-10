<template>
  <div class="list-groups">
    <section class="group-box">
      <div class="headline">
        <h2 class="group-name">{{ groupBox.group_name }}</h2>
        <h4 class="group-city">{{ groupBox.city }}</h4>
      </div>
      <div class="group-description">
        <p>{{ groupBox.about }}</p>
      </div>
      <router-link
        v-bind:to="{
          name: 'group-mainpage',
          params: { groupId: groupBox.group_id },
        }"
        >Learn More</router-link
      >
    </section>
  </div>
</template>

<script>
export default {
  props: {
    groupBox: Object,
  },
};
</script>

<style scoped></style>
