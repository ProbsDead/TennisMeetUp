package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcGroupsDaoTests extends BaseDaoTests {

    protected static final Group GROUP_1 = new Group(1, "Volley Girls", 1, "Burlington", "VT","45 Tennis Ct, South Burlington, VT 05403", true, "This is a group for women and girls who love tennis and are looking for a casual to competative environment they can hone their skills.");
    protected static final Group GROUP_2 = new Group(2, "group2", 2, "city2", "CA","location2", false, "about2");
    protected static final Group GROUP_3 = new Group(3, "group3", 3, "city3", "VT","location3", true, "about3");

    private JdbcGroupsDao sut; //sut stands for System Under Test

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcGroupsDao(jdbcTemplate);
    }

    @Test
    public void getGroupInfo_given_invalid_group_id_returns_null(){
        Group group = sut.getGroupInfo(-1);

        Assert.assertNull(group);
    }

    @Test
    public void getGroupMembers_given_invalid_group_id_returns_empty_list(){
        List<User> members = sut.getGroupMembers(-1);
        List<User> empty = new ArrayList<>();

        Assert.assertArrayEquals(empty.toArray(), members.toArray());
    }

    @Test
    public void inviteNewMembers_given_invalid_group_id_returns_null(){
    //Maybe move this method to its own controller?
    }

    @Test
    public void getGroupInfo_returns_correct_information(){
        Group group = sut.getGroupInfo(GROUP_1.getGroupId());
        System.out.println(GROUP_1.getGroupId());

        Assert.assertEquals(GROUP_1, group);
    }
}
