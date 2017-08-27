/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engcpp.demos;

import com.engcpp.demos.apis.LoginServices;
import com.engcpp.demos.apis.LoginServicesImpl;
import com.engcpp.demos.apis.UsersServiceImpl;
import com.engcpp.demos.apis.UsersServices;
import com.engcpp.servlet.Monitor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import java.io.IOException;
import java.net.URI;
import javax.servlet.ServletRegistration;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author engcpp
 */
public class Application {
     private static URI getBaseURI() {
        return UriBuilder.fromPath("/api/")
                         .scheme("http")
                         .host("0.0.0.0")
                         .port(8080).build();
    }    
    
    public static HttpServer startServer() {        
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig()
            .packages(true, "com.engcpp.demos.apis")
            .setClassLoader(ClassLoader.getSystemClassLoader());
        
        rc.register(getJacksonProvider());

        // Bind interface to the implementation, making injections possible
        rc.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UsersServiceImpl.class).to(UsersServices.class);
                bind(LoginServicesImpl.class).to(LoginServices.class);
            }
        });        

        // create and start a new instance of grizzly http server exposing the Jersey application at BASE_URI
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(getBaseURI(), rc);
        
        // Register Monitor Servlet
        WebappContext context = new WebappContext("WebappContext", "");
        ServletRegistration registration = context.addServlet("Monitor", Monitor.class);
        registration.addMapping("/monitor/");
        context.deploy(server);   
        
        // Register static resources like javascript pages (Angular / Bootstrap)
        HttpHandler httpHandler = new CLStaticHttpHandler(HttpServer.class.getClassLoader(), "/static/");        
        server.getServerConfiguration().addHttpHandler(httpHandler);                

        return server;
    }

    public static JacksonJaxbJsonProvider getJacksonProvider(){
        // create custom ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);

        // create JsonProvider to provide custom ObjectMapper
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        return provider;
    }    
    
    public static void main(String[] args) throws IOException {        
        final HttpServer server = startServer();
    
        
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", getBaseURI()));

        System.in.read();
        server.stop();
    }
}
