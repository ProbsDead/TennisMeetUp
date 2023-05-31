package com.techelevator.controller;

import com.techelevator.dao.GroupsDao;
import com.techelevator.dao.JdbcGroupsDao;
import com.techelevator.model.Group;
import com.techelevator.model.Request;
import com.techelevator.model.User;
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

    /**
     * @param groupId
     * @return group object
     */
    @GetMapping(path="/{groupId}")
    public Group getGroupDetails(@PathVariable int groupId) {
        return groupsDao.getGroupInfo(groupId);
    }

    /**
     * @param groupId
     * @return list of users objects that belong to said group
     */
    @GetMapping(path="/{groupId}/players")
    public List<User> getGroupMembers(@PathVariable int groupId){
        return groupsDao.getGroupMembers(groupId);
    }

    /**
     * @return list of all groups
     * */
    @GetMapping()
    public List<Group> getAllGroups() {
        return groupsDao.getAllGroups();
    }

    /**
     * Create new group.  Group information must be sent as a group object
     * @param newGroup
     */
    @PostMapping(path="/add")
    public void createNewGroup(@RequestBody Group newGroup){
        groupsDao.createNewGroup(newGroup);
    }

//    /**
//     * This method creates an invitation to join a group of a request to join a group
//     * @param groupId
//     * @param userId
//     */
//    @PostMapping(path="/{groupId}/{userId}")
//    public void sendRequestOrInvite(@PathVariable int groupId, @PathVariable int userId) {
//         groupsDao.inviteNewMember(groupId, userId);
//    }

}
