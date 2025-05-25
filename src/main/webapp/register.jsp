<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="register" method="post">
        <h2>Đăng ký tài khoản mới</h2>

        <div>
            <label for="fullname">Họ và tên:</label>
            <input type="text" id="fullname" name="fullname" required placeholder="Nhập họ và tên..."/>
        </div>

        <div>
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required placeholder="Nhập tên đăng nhập..."/>
        </div>

        <div>
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu..."/>
        </div>

        <div>
            <label for="confirmPassword">Nhập lại mật khẩu:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Nhập lại mật khẩu..."/>
        </div>

        
        
        <button type="submit">Đăng ký</button>
        <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
    </form>

</body>
</html>
