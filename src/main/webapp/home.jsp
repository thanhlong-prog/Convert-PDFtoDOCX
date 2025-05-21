<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.BEAN.UserSessionInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    UserSessionInfo user = (UserSessionInfo) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <h2>Chào mừng, <c:out value="${user.username}"/></h2>

    <p>Đây là trang chính sau khi bạn đăng nhập thành công.</p>

    <form action="upload" method="post" enctype="multipart/form-data">
        <label>Chọn tệp PDF:</label>
        <input type="file" name="pdfFile" accept="application/pdf" required>
        <button type="submit">Tải lên</button>
    </form>

    <form action="logout" method="post">
        <button type="submit">Đăng xuất</button>
    </form>
</body>
</html>
