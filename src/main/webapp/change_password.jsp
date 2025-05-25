<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.BEAN.UserSessionInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    UserSessionInfo user = (UserSessionInfo) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="./css/login.css">
    <link rel="stylesheet" href="./css/header.css">
</head>
<body>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="password" method="post">
        <h2>Đổi mật khẩu</h2>
        <label for="newPassword">Mật khẩu mới:</label>
        <input type="password" id="newPassword" name="newPassword" required/>
        <label for="newPassword">Nhập lại mật khẩu:</label>
        <input type="password" id="reTypePassword" name="reTypePassword" required/>
        <button type="submit">Cập nhật</button>
    </form>

    <p><a href="profile">Quay lại trang thông tin cá nhân</a></p>

</body>
</html>
