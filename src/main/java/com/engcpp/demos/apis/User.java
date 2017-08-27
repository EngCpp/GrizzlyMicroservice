/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.apis;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author engcpp
 */
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private int age;
    private List<String> emails;

    public User(){}
    
    public User(long id, String firstName, String lastName, int age, String password, String...email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emails = Arrays.asList(email);
        this.age = age;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }        
}