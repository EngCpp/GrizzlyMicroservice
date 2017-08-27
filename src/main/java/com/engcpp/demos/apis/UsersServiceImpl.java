/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.apis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author engcpp
 */
public class UsersServiceImpl implements UsersServices{
    private static List<User>users;
        
    static {        
        users = new ArrayList<>();
        users.add(new User(1, "Mel","Gibson", 56, "mel123","gibson@braveheart.com"));
        users.add(new User(2, "Stefan","Molynoux", 50,"stefan123","molynoux@freedomainradio.com"));
        users.add(new User(3, "Gavin","MacInnes",55, "gavin123","gavin@rabelmedia.com"));
        users.add(new User(4, "Dave","Rubin",45, "dave123", "dave@rubinreport.com"));
        users.add(new User(5, "Steev","Crowd", 32, "steev123", "steev@lowdercrowder.com"));
    }
     
    @Override
    public List<User> findAll() {        
        return users;
    }

    @Override
    public User findById(long id) {           
        Optional<User>usr = users.stream()
                                 .filter(s ->(s.getId() == id))
                                 .findFirst();
        if (usr.isPresent())
            return usr.get();
        else 
            return new User();
    }

    @Override
    public User findByEmailPassword(String email, String password) {
        Optional<User>usr = users.stream()
                    .filter(s->(s.getEmails().contains(email) && 
                                s.getPassword().endsWith(password)))
                    .findFirst();
        
        if (usr.isPresent())
            return usr.get();
        else 
            return new User();        
    }

    @Override
    public boolean deleteJson(long id) {
        users = 
        users.stream()
             .filter(u -> u.getId() != id)
             .collect(Collectors.toList());
        
        return true;
    }

    @Override
    public boolean postJson(User content) {
        if (!users.contains(content)) {
            users.add(content);
            return true;
        }
        
        return false;
    }

    @Override
    public boolean putJson(User content) {
        users =
        users.stream()
        .map(u -> u.getId() == content.getId() ? content: u)
        .collect(Collectors.toList());
        
        return true;
    }
}