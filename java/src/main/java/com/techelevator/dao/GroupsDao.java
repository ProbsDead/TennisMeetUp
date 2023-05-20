package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;

import java.util.List;

public interface GroupsDao {
    Group getGroupInfo(int groupId);
    List<User> getGroupMembers(int groupId);
    void inviteNewMember(int groupId, int userId);

}
