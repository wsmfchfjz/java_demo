<%--
  Created by IntelliJ IDEA.
  User: AnDong
  Date: 2017/3/27
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSE Demo</title>
</head>
<body>

<div id="msgPush"></div>
<script type="text/javascript" src="../assets/lib/jquery.min.js"></script>
<script type="text/javascript">
    /**
     * EventSource 只有新式浏览器才会有
     */
    if (!!window.EventSource) {
        var source = new EventSource('/push');
        s = ' ';

        source.addEventListener('message', function (e) {
            s += e.data + "<br/>"
            $("#msgPush").html(s);
        });

        source.addEventListener('open', function (e) {
            console.log("链接打开");
        }, false);

        source.addEventListener('error', function (e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log("连接关闭");
            } else {
                console.log(e.readyState);
            }
        }, false);

    } else {
        console.log("浏览器不支持SSE");
    }

</script>

</body>
</html>