import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000/image-upload",
});

export default {
  uploadImage(image, groupId) {
    return http.post(`/${groupId}`, image);
  },
};
