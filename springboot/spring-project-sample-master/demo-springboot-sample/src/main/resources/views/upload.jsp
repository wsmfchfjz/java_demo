<%--
  Created by IntelliJ IDEA.
  User: AnDong
  Date: 2017/3/9
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>

<div class="upload">
    <form action="/file/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file"/><br/>
        <input type="submit" value="上传"/>
    </form>
</div>

</body>
</html>