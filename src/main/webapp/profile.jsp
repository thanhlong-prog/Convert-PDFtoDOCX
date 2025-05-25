<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.BEAN.UserSessionInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% UserSessionInfo user=(UserSessionInfo) session.getAttribute("user"); if (user==null) {
    response.sendRedirect("login.jsp"); return; } %>
<html>

<head>
    <title>Thông tin cá nhân</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./css/profile.css">
    <link rel="stylesheet" href="./css/styles.css">
    <link rel="stylesheet" href="./css/header.css">
</head>

<body>
    <div class="header">
        <div class="header-home">
            <i class='bx  bx-home'></i>
            <a href="home">
                <span>Trang chủ</span>
            </a>
        </div>
        <div class="profile">
            <div class="user" onclick="toggleDropdown()">
                <i class='bx bx-user'></i>
                <span class="full-name"><c:out value="${user.fullname}" /></span>
            </div>
            <div class="dropdown">
                <div class="dropdown-content">
                    <a href="profile">Trang cá nhân</a>
                    <a href="listJobs">Danh sách file</a>
                    <a href="password">Đổi mật khẩu</a>
                    <a href="logout">Đăng xuất</a>
                </div>
            </div>
        </div>
    </div>
    <div class="w-page">
        <div class="page-info">
            <h2>Thông tin cá nhân</h2>
            <div class="page-profile">
                <div class="info account">
                    <span class="tl">Tên đăng nhập:</span>
                    <span>${user.username}</span>
                </div>
                <div class="info full-name">
                    <span class="tl">Tên hiển thị:</span>
                    <span>${user.fullname}</span>
                    <button type="button" class="edit-name"> Đổi tên</button>
                </div>
                <form action="changeFullname" method="post" class="change-name-form">
                    <div class="info">
                        <label class="tl" for="newFullname">Tên mới:</label>
                        <input type="text" id="newFullname" name="newFullname" required>
                        <button class="update-name" type="submit">Cập nhật</button>
                        <button class="cancel-update" type="button">Huỷ bỏ</button>
                    </div>
                </form>
                <div class="info count-convert">
                    <span class="tl">Số PDF đã convert: </span>
                    <span>${convertCount}</span>
                </div>
                <span class="edit-password"><a href="password">Đổi mật khẩu</a></span>

                <span><a href="listJobs">Xem danh sách file đã chuyển đổi</a></span>

                <div class="error">
                    <c:if test="${not empty message}">
                        <p style="color:green">${message}</p>
                    </c:if>
                    <c:if test="${not empty error}">
                        <p style="color:red">${error}</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <script src="./js/scripts.js"></script>
    <script src="./js/header.js"></script>
</body>

</html>