import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000/group",
});

export default {
  getGroupDetails(groupId) {
    return http.get(`/${groupId}`);
  },

  getGroupByEventId(eventId) {
    return http.get(`/event/${eventId}`);
  },

  getGroupMembers(groupId) {
    return http.get(`/${groupId}/players`);
  },

  sendRequestOrInvite(groupId, userId) {
    return http.post(`/${groupId}/${userId}`);
  },
  getAllPublicGroups() {
    return http.get('/public');
  },
  getGroupsByUser(userId) {
    return http.get(`/user/${userId}`);
  },
  createNewGroup(group){
    return http.post('/add', group);
  },
};
