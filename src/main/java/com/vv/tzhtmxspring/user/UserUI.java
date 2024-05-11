package com.vv.tzhtmxspring.user;

import java.util.List;

public final class UserUI {

    private UserUI() {
    }

    public static String getBegin() {
        return """
                <html lang="en">
                
                <head>
                    <title>TZ App</title>
                    <link rel="stylesheet" href="/sakura.css" type="text/css">
                    <script src="https://unpkg.com/htmx.org@1.9.12"></script>
                     <link rel="icon" type="image/x-icon" href="favicon.ico">
                </head>
                
                <body hx-ext="debug">
                <main>
                    <table>
                        <thead>
                        <tr>
                            <th>
                                uuid
                            </th>
                            <th>
                                username
                            </th>
                            <th>
                                password
                            </th>
                            <th>
                                action
                            </th>
                        </tr>
                        </thead>
                """;
    }

    public static String getRows( final List<EasyUser> list ) {
        StringBuilder loop = new StringBuilder();
        for ( EasyUser e : list ) {
            loop.append( STR."<tbody id='userTableBody'>\{getRow( e )} </tbody>" );
        }
        return loop.toString();
    }

    public static String getRow( final EasyUser e ) {
        return STR."""
                <tr id="user-\{e.uuid.toString()}">
                    <td>\{e.uuid.toString()}</td>
                    <td>\{e.username}</td>
                    <td>\{e.password}</td>
                    <td>
                        <button hx-get="/save-user/modal/\{e.uuid.toString()}"
                                hx-target="#modalContainer">
                            Edit
                        </button>
                    </td>
                </tr>""";
    }

    public static String getEnd() {
        return """
                        <tfoot>
                        <tr>
                            <td colspan="4">
                                <button hx-get="/create-user/modal" hx-target="#modalContainer">
                                    Create new User
                                </button>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                
                </main>
                </body>
                
                <div id="modalContainer" hx-on:"close-modal"="this.innerHTML = null">
                </div>
                
                </html>
                """;
    }

    public static String getEditModal( final EasyUser user ) {
        return STR."""
                <div style="width: 100dvw; height: 100dvh; position: fixed; top: 0;left: 0; background-color: rgba(128,128,128,0.69); display: flex; justify-content: center; align-items: center;">
                    <form style="background-color: whitesmoke; padding: 2rem;" >
                        <label>
                            UUID
                            <input type="text" readonly name="uuid" value="\{user.uuid.toString()}">
                        </label>
                        <label>
                            Username
                            <input type="text" name="username" value="\{user.username}">
                        </label>
                        <label>
                            Password
                            <input type="text" name="password" value="\{user.password}">
                        </label>
                        <button type="submit"  hx-post="/save-user" >
                            Save User
                        </button>
                    </form>
                </div>
                """;
    }

    public static String getCreateBlock() {
        return """
                <div style="width: 100dvw; height: 100dvh; position: fixed; top: 0;left: 0; background-color: rgba(128,128,128,0.69); display: flex; justify-content: center; align-items: center;">
                    <form style="background-color: whitesmoke; padding: 2rem;">
                        <label>
                            Username
                            <input type="text" name="username">
                        </label>
                        <label>
                            Password
                            <input type="text" name="password">
                        </label>
                        <button type="submit" hx-post="/create-user">
                            Save User
                        </button>
                    </form>
                </div>
                """;
    }
}
