<template>
  <div class="list-groups">
    <section class="card">
      <img class="card-image" :src="getImage" alt="" />
      <div class="headline">
        {{ groupBox.group_name }}
      </div>
      <div class="meta">{{ groupBox.city }}, {{ groupBox.state }}</div>
      <div class="extra-content">
        <span class="link">
          <router-link
            v-bind:to="{
              name: 'group-mainpage',
              params: { groupId: groupBox.group_id },
            }"
            >view group page</router-link
          >
        </span>
      </div>
    </section>
  </div>
</template>

<script>
import GroupService from "../../services/GroupService";

export default {
  props: {
    groupBox: Object,
  },
  data() {
    return {
      imgSrc: require("../../assets/tennis-court.jpg"),
    };
  },
  created() {
    GroupService.getGroupDetails(this.groupBox.group_id).then((response) => {
      this.group = response.data;
      const imageData = response.data.group_image;
      const imageType = response.data.image_type;

      // if image is not null, Convert the byte array to a data URL
      if (imageData != null) {
        // const base64Img = btoa(String.fromCharCode.apply(null, imageData));
        const dataURL = `data:${imageType};base64,${imageData}`;

        // use the dataURL to display the image
        this.imgSrc = dataURL;
      }
    });
  },
  computed: {
    getImage() {
      return this.imgSrc;
    },
  },
};
</script>

<style scoped>
.card {
  width: 200px;
  height: 274px;
  background: white;
  padding: 0.4em;
  border-radius: 6px;
  border-inline-end: 3px solid rgb(226, 226, 226);
  border-bottom: 3px solid rgb(226, 226, 226);
}

.card-image {
  background-color: rgb(236, 236, 236);
  width: 100%;
  height: 60%;
  border-radius: 6px 6px 0 0;
}

.headline {
  text-transform: uppercase;
  font-size: 1.2em;
  font-weight: 800;
  color: #264653;
  padding: 10px 7px 0;
}

.meta {
  font-weight: 600;
  color: rgb(88, 87, 87);
  padding: 7px;
}

a {
  padding: 7px;
  text-decoration: none;
  color: #264653;
}

div.extra-content span.link a:hover {
  cursor: pointer;
  color: #158479;
}
</style>
