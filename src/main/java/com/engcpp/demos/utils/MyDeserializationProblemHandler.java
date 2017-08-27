/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.utils;

import com.engcpp.demos.apis.WeirdString;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

/**
 *
 * @author engcpp
 */
public class MyDeserializationProblemHandler extends DeserializationProblemHandler { 
    public Object handleUnexpectedToken(DeserializationContext ctxt, Class<?> targetType, JsonToken t, JsonParser p, String failureMsg) throws IOException {
        System.out.println("handleUnexpectedToken");
        
        if (targetType.getName().equals(String.class.getName())){
            WeirdString w = p.readValueAs(WeirdString.class);
            if (w!=null)
                return w.getText();
            
        }
        
        return super.handleUnexpectedToken(ctxt, targetType, t, p, failureMsg);
    }
}
