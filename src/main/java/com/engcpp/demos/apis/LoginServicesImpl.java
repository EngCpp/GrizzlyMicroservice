/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.apis;

import javax.inject.Inject;

/**
 *
 * @author engcpp
 */
public class LoginServicesImpl implements LoginServices {
    @Inject
    private UsersServices usersService;
    
    @Override
    public User login(LoginRequest request) {
        return  usersService.findByEmailPassword(
                request.getEmail(), request.getPassword());
    }    
}