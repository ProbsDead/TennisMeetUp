package com.techelevator.controller;

import com.techelevator.dao.JdbcRequestDao;
import com.techelevator.dao.RequestDao;
import com.techelevator.model.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestDao requestDao;

    public RequestController(JdbcRequestDao requestDao) {
        this.requestDao = requestDao;
    }

        /**
     * This method creates an invitation to join a group of a request to join a group
     * @param groupId
     * @param userId
     */
    @PostMapping(path="/{groupId}/{userId}")
    public void inviteNewMember(@PathVariable int groupId, @PathVariable int userId) {
         requestDao.inviteNewMember(groupId, userId);
    }

    @GetMapping(path = "/{groupId}")
    public List<Request> getAllCurrentRequests(@PathVariable int groupId){
        return requestDao.getAllCurrentRequests(groupId);
    }

    @PostMapping(path = "/status")
    public void approveOrDeclineRequest(@RequestBody Request request){
        requestDao.approveOrDeclineRequest(request);
    }
}
