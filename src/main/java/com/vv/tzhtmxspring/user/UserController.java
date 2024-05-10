package com.vv.tzhtmxspring.user;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

import static com.vv.tzhtmxspring.user.UserUI.*;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping( value = "/",
                 produces = MediaType.TEXT_HTML_VALUE )
    @ResponseBody
    public String index() {
        List<EasyUser> list = userService.findAll();

        String begin = getBegin();
        String loop = getRows( list );
        String end = getEnd();

        return begin + loop + end;
    }

    @GetMapping( value = "/save-user/modal/{uuid}",
                 produces = MediaType.TEXT_HTML_VALUE )
    @ResponseBody
    public String editUserModal( @PathVariable UUID uuid ) {
        var user = userService.findById( uuid );
        return getEditModal( user );
    }

    @PostMapping( value = "/save-user",
                  produces = MediaType.TEXT_HTML_VALUE )
    public String saveUser( UUID uuid, String username, String password, HttpServletResponse response ) {
        EasyUser user = userService.saveUser( uuid, username, password );

        response.addHeader( "HX-Retarget", "#user-" + user.uuid );
        response.addHeader( "HX-Reswap", "outerHTML" );
        response.addHeader( "HX-Trigger", "close-modal" );
        response.addHeader( "HX-Refresh", "true" );

        return getRow( user );
    }

    @GetMapping( value = "/create-user/modal",
                 produces = MediaType.TEXT_HTML_VALUE )
    @ResponseBody
    public String getCreateUserModal() {
        return getCreateBlock();
    }

    @PostMapping( value = "/create-user",
                  produces = MediaType.TEXT_HTML_VALUE )
    public String createUser( String username, String password, HttpServletResponse response ) {
        EasyUser user = userService.createUser( username, password );

        response.addHeader( "HX-Retarget", "#userTableBody" );
        response.addHeader( "HX-Reswap", "afterbegin" );
        response.addHeader( "HX-Trigger", "close-modal" );
        response.addHeader( "HX-Refresh", "true" );

        return getRow( user );
    }

}
