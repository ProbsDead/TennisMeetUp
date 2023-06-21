import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/event",
});

export default{
    getEventsByGroupId(groupId){
        return http.get(`/${groupId}`);
    },

    addNewEvent(groupId, event){
        return http.get(`/add/${groupId}`, event);
    }

    
}