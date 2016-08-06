<%--
  Created by IntelliJ IDEA.
  User: daisongsong
  Date: 16/8/1
  Time: 下午8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/login" method="post">

    用户名 <input name="name"/><br>
    密 码 <input name="password" type="password"/><br>
    <input type="submit" value="提交" onclick="onSubmitClicked()"/>
</form>
</body>
</html>
