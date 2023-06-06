import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000/request",
});

export default{
    sendRequestToJoin(request) {
        return http.post('/send', request);
    },

    approveOrDecline(adminId){
        return http.post(`/status/${adminId}`);
    },

    getAllCurrentRequests(groupId){
        return http.get(`/${groupId}`);
    },

    inviteNewMember(groupId, userId){
        return http.post(`/${groupId}/${userId}`);
    }
}