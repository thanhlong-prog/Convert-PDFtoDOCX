<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="login" method="post">
        <h2>Đăng nhập</h2>
        <div>
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required placeholder="Nhập tên đăng nhập..."/>
        </div>

        <div>
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu..."/>
        </div>

        <button type="submit">Đăng nhập</button>
        <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a></p>
    </form>

    
</body>
</html>
