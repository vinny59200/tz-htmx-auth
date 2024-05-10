package com.vv.tzhtmxspring;

import com.vv.tzhtmxspring.user.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class TzHtmxSpringApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TzHtmxSpringApplication.class, args );
    }

    @Bean
    public ApplicationRunner initializeUsers( UserService userService ) {
        return ( args ) -> Map.of( "user1", "pass1", "user2", "pass2", "user3", "pass3" )
                              .entrySet()
                              .forEach(
                                      (( entry ) -> userService.createUser(
                                              entry.getKey(),
                                              entry.getValue() )));
    }
}
