<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
    <h2>Đăng ký tài khoản mới</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="register" method="post">
        <div>
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required />
        </div>

        <div>
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required />
        </div>

        <div>
            <label for="confirmPassword">Xác nhận mật khẩu:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required />
        </div>

        <div>
            <label for="fullname">Họ và tên:</label>
            <input type="text" id="fullname" name="fullname" required />
        </div>
        
        <button type="submit">Đăng ký</button>
    </form>

    <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
</body>
</html>
