package com.vv.tzhtmxspring.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    public final List<EasyUser> easyUserList = new ArrayList<>();

    public List<EasyUser> findAll() {
        return this.easyUserList;
    }

    public EasyUser createUser( String username, String password ) {
        EasyUser newUser = new EasyUser( username, password );
        this.easyUserList.add( newUser );
        return newUser;
    }

    public EasyUser findById( UUID uuid ) {
        return this.easyUserList.stream()
                                .filter( it -> Objects.equals( uuid, it.uuid ) )
                                .findFirst()
                                .orElseThrow( () -> new RuntimeException( "User Not Found" ) );
    }

    public EasyUser saveUser( UUID uuid, String username, String password ) {
        EasyUser userToUpdate = findById( uuid );
        EasyUser newUser = new EasyUser( userToUpdate.uuid, username, password );
        this.easyUserList.set( this.easyUserList.indexOf( userToUpdate ), newUser );
        return newUser;
    }
}
