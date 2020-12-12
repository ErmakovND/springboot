<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h3>
    Sign Up
</h3>
<h5>
    ${message}
</h5>
<form action="signup" method="post">
    <label>
        Name <br/>
        <input type="text" name="name" autofocus/>
    </label>
    <br/>
    <label>
        Password <br/>
        <input type="password" name="password"/>
    </label>
    <br/>
    <input type="submit" value="Sign Up"/>
</form>
</body>
</html>