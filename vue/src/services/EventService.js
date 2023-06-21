import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/event",
});

export default{
    getEventsByGroupId(groupId){
        return http.get(`/${groupId}`);
    },

    addNewEvent(groupId, event){
        return http.post(`/add/${groupId}`, event);
    },

    getEventDetails(eventId){
        return http.get(`/details/${eventId}`);
    },

    updateEventDetails(eventId, event){
        return http.put(`/update/${eventId}`, event);
    },

    deleteEvent(eventId){
        return http.delete(`/delete/${eventId}`);
    },

    joinEvent(eventId, userId){
        return http.post(`/${eventId}/join/${userId}`);
    },

    getMatchesByEventId(eventId){
        return http.get(`/matches/${eventId}`);
    }
}