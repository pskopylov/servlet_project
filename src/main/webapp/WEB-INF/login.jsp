<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Authorization</title>
    </head>
    <body>
        <form id="locale" action="locale" method="post" onchange="document.getElementById('locale').submit()">
            <select name="selectLanguage">
                <option value="en" ${selectEn}>${en}</option>
                <option value="ru" ${selectRu}>${ru}</option>
                <option value="de" ${selectDe}>${de}</option>
            </select>
        </form>
        <center>
            <h3><font color="red" face="arial">${error}</font></h3>
            <form action="login" method="post">
                ${enterLogin}:<br/>  
                <input type="text" name="login" value="" /><br/>  
                ${enterPassword}:<br/> 
                <input type="password" name="password" value="" /><br><br>
                <input type="submit" value= "${loginUser}" /><br><br>
            </form>
            <form action="guest" method="post">
                <input type="submit" value="${enterGuest}"/><br/>
            </form>
            <br>
        </center>
    </body>
</html>

