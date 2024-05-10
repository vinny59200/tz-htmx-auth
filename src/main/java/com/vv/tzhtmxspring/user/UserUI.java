package com.vv.tzhtmxspring.user;

import java.util.List;

public final class UserUI {

    private UserUI() {
    }

    public static  String getBegin() {
        return """
                <html lang="en">
                            
                <head>
                    <title>TZ App</title>
                    <link rel="stylesheet" href="/css/sakura.css" type="text/css">
                    <script src="/htmx_1.9.11.js"></script>
                    <script src="/htmx_debug.js"></script>
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
                        </tr>
                        </thead>
                        """;
    }

    public static String getRows( final List<EasyUser> list ) {
        String loop = "";
        for ( EasyUser e : list ) {
            loop = loop + "<tbody id='userTableBody'>";
            String row = getRow( e );
            loop = loop + row + " </tbody>";
        }
        return loop;
    }

    public static  String getRow( final EasyUser e ) {
        return """
                <tr id="user-XX-UUID">
                    <td>
                       XX-UUID
                    </td>
                    <td>
                        XX-USERNAME
                    </td>
                    <td>
                        XX-PASSWORD
                    </td>
                    <td>
                        <button hx-get="XX-ACTION"
                                hx-target="#modalContainer">
                            Edit
                        </button>
                    </td>
                </tr>""".replace( "XX-UUID", e.uuid.toString() )
                        .replace( "XX-USERNAME", e.username )
                        .replace( "XX-PASSWORD", e.password )
                        .replace( "XX-ACTION", "/save-user/modal/" + e.uuid.toString() );
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
        return """
                <div style="width: 100dvw; height: 100dvh; position: fixed; top: 0;left: 0; background-color: rgba(128,128,128,0.69); display: flex; justify-content: center; align-items: center;">
                    <form style="background-color: whitesmoke; padding: 2rem;" >
                        <label>
                            UUID
                            <input type="text" readonly name="uuid" value="XX-UUID">
                        </label>
                        <label>
                            Username
                            <input type="text" name="username" value="XX-USERNAME">
                        </label>
                        <label>
                            Password
                            <input type="text" name="password" value="XX-PASSWORD">
                        </label>
                        <button type="submit"  hx-post="/save-user" >
                            Save User
                        </button>
                    </form>
                </div>
                """.replace( "XX-UUID", user.uuid.toString() )
                   .replace( "XX-USERNAME", user.username )
                   .replace( "XX-PASSWORD", user.password );
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
