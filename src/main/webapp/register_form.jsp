<%--
  Created by IntelliJ IDEA.
  User: daisongsong
  Date: 16/8/1
  Time: 下午8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="application/javascript">

        function onSubmitClicked() {
            var passwordNode = document.getElementsByName("password");
            var passwordRepeatNode = document.getElementById("password_repeat");
        }

    </script>
</head>
<body>

    <form action="/user/register" method="post">

        用户名 <input name="name" /><br>
        密 码 <input  name="password" type="password" /><br>
        重复  <input name="password_repeat" type="password" /><br>
        <input type="submit" value="提交" onclick="onSubmitClicked()" />
    </form>

</body>
</html>
