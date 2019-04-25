<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Logged Page</title>
    </head>
    <body>
        <center>
            <h3><font color=green face=arial>${text}</font></h3>
            <form action="log_out" method="post">
                <input type="submit" value="Log out"><br/>
            </form>
        </center>
    </body>
</html>
