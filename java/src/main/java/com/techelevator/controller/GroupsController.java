package com.techelevator.controller;

import com.techelevator.dao.GroupsDao;
import com.techelevator.dao.JdbcGroupsDao;
import com.techelevator.model.Group;
import com.techelevator.model.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupsController {

    private final GroupsDao groupsDao;

    public GroupsController(JdbcGroupsDao groupsDao) {
        this.groupsDao = groupsDao;
    }

    @GetMapping(path="/{groupId}")
    public Group getGroupDetails(@PathVariable int groupId) {
        return groupsDao.getGroupInfo(groupId);
    }

    //TODO: write this once JdbcGroupsDao has the method implemented
    //@GetMapping(path="/{groupId}/players")

    @PostMapping(path="/{groupId}/{userId}")
    public void sendRequestOrInvite(@PathVariable int groupId, @PathVariable int userId) {
         groupsDao.inviteNewMember(groupId, userId);
    }

}
