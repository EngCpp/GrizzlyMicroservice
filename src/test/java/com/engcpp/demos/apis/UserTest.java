/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.apis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author engcpp
 */
@RunWith(JUnit4.class)
public class UserTest extends AbstracDeserialiser {
    private User user;

    @Test 
    public void user1Test() {        
        user = load(User.class, "User1.json");

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getEmails());
        Assert.assertEquals(1, user.getEmails().size());
    }

    @Test 
    public void user2Test() {        
        user = load(User.class, "User2.json");

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getEmails());
        Assert.assertEquals(2, user.getEmails().size());
    }    

    @Test 
    public void user3Test() {        
        user = load(User.class, "User3.json");

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getEmails());
        Assert.assertEquals(1, user.getEmails().size());
    } 

    @Test 
    public void user4Test() {        
        user = load(User.class, "User4.json");

        Assert.assertNotNull(user);
        Assert.assertNull(user.getEmails());
    }

    @Test 
    public void user5Test() {        
        user = load(User.class, "User5.json");

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getEmails());
        Assert.assertEquals(1, user.getEmails().size());
    }    
}