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
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/styles.css">
    <link rel="stylesheet" href="./css/header.css">
</head>

<body>
    <div class="header">
        <div class="header-home">
            <i class='bx  bx-home'></i>
            <a href="/home.jsp">
                <span>Trang chủ</span>
            </a>
        </div>
        <div class="profile">
            <div class="user" onclick="toggleDropdown()">
                <i class='bx bx-user'></i>
                <span class="full-name">Shiro</span>
            </div>
            <div class="dropdown">
                <div class="dropdown-content">
                    <a href="#">Trang cá nhân</a>
                    <a href="/listJobs.jsp">Danh sách file</a>
                    <a href="/changePassword.jsp">Đổi mật khẩu</a>
                    <a href="/logout">Đăng xuất</a>
                </div>
            </div>
        </div>
    </div>
    <div class="page-home">
        <div class="w-home">
            <!-- <h2>Chào mừng,
                    <c:out value="${user.username}" />
                </h2> -->

            <c:if test="${not empty message}">
                <p style="color: green;">
                    <c:out value="${message}" />
                </p>
            </c:if>
            <span class="title">Chuyển PDF sang Word</span>
            <div class="home">
                <form action="convert" method="post" enctype="multipart/form-data">
                    <div class="form-input">
                        <label for="pdf-upload" class="file-upload">
                            <i class='bx  bx-folder-open'></i>
                            <span>Chọn file PDF</span>
                        </label>
                        <span class="file-name"></span>
                        <input type="file" id="pdf-upload" name="pdfFile" accept=".pdf">
                    </div>
                    <button class="btn-submit" type="submit"><i class='bx bx-right-arrow-alt'></i> </button>
                </form>
            </div>
            <p><a href="listJobs">Xem danh sách file đã chuyển đổi</a></p>
        </div>
    </div>
    <script src="./js/header.js"></script>
    <script src="./js/home.js"></script>
</body>

</html>