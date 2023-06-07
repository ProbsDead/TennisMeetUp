import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000/user",
});

export default {
  getUserInfo(userId) {
    return http.get(`/${userId}`);
  },
  getAllUserEvents(userId) {
    return http.get(`/${userId}/events`);
  },
  getUpcomingUserEvents(userId) {
    return http.get(`/${userId}/future-events`);
  },
  getUserMatches(userId) {
    return http.get(`${userId}/matches`);
  },
  removeUserFromEvent(userId, eventId) {
    return http.delete(`/${userId}/events/${eventId}`);
  },

  updateUserGoal(userId, user) {
    return http.put(`/${userId}/goal`, user);
  },
};
