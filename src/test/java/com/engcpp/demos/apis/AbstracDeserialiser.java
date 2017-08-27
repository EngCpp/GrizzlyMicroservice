/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos.apis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.junit.Assert;

/**
 *
 * @author engcpp
 */
public class AbstracDeserialiser{
    private Logger logger = Logger.getLogger(AbstracDeserialiser.class.getName());
    protected ObjectMapper json;
    
    public AbstracDeserialiser(){
        json = new ObjectMapper();
        json.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        json.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        //json.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        json.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        
        //json.addHandler(new MyDeserializationProblemHandler());
        json.addHandler(new DeserializationProblemHandler(){
        
            public Object handleUnexpectedToken(DeserializationContext ctxt, 
                                                Class<?> targetType, 
                                                JsonToken t, 
                                                JsonParser p, 
                                                String failureMsg) throws IOException {
                
                if (targetType.getName().equals(String.class.getName())) {
                    WeirdString w = p.readValueAs(WeirdString.class);
                    if (w!=null)
                        return w.getText();
                }

                return super.handleUnexpectedToken(ctxt, targetType, t, p, failureMsg);
            }                
        });        
    }

    public <T> T load(Class<T>type) {
        return load(type, type.getSimpleName());
    }
    
    public <T> T load(Class<T>type, String fileName) {
        try {

            File file = new File(type.getSimpleName(), fileName); 

            return json.readValue(file, type);

        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }

        return null;
    }
}
