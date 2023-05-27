package com.techelevator.controller;

import com.techelevator.dao.JdbcRequestDao;
import com.techelevator.dao.RequestDao;
import org.springframework.web.bind.annotation.*;

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
    public void sendRequestOrInvite(@PathVariable int groupId, @PathVariable int userId) {
         requestDao.inviteNewMember(groupId, userId);
    }
}