<%--
  Created by IntelliJ IDEA.
  User: AnDong
  Date: 2017/4/5
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet Async Support</title>
</head>
<body>
<div id="msgPush"></div>

<script type="text/javascript" src="../assets/lib/jquery.min.js"></script>
<script type="text/javascript">
    s = '';

    function deferred() {
        $.get('/defer', function (data) {
            s += data + "<br/>";
            $("#msgPush").html(s);
            console.log(data);
            deferred()
        })
    }

    deferred();
</script>

</body>
</html>