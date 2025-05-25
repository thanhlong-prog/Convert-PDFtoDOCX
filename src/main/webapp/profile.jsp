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
    <title>Thông tin cá nhân</title>
</head>
<body>
    <h2>Thông tin cá nhân</h2>
    <p><b>Username:</b> ${user.username}</p>
    <p><b>Fullname:</b> ${user.fullname}</p>

    <h3>Số PDF đã convert: ${convertCount}</h3>
     <p><a href="listJobs">Xem danh sách file đã chuyển đổi</a></p>
      <p><a href="password">Đổi mật khẩu</a></p>

    <c:if test="${not empty message}">
        <p style="color:green">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</body>
</html>
