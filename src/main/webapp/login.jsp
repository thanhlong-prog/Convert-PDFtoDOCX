<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <h2>Đăng nhập</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="login" method="post">
        <div>
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required />
        </div>

        <div>
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required />
        </div>

        <button type="submit">Đăng nhập</button>
    </form>

    <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a></p>
</body>
</html>
