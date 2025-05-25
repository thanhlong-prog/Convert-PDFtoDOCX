<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/styles.css">
</head>

<body>
    <div class="header">
        <div class="home">
            <i class='bx  bx-home'></i>
            <a href="/home.jsp">
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
                    <a href="#">Trang cá nhân</a>
                    <a href="/listJobs.jsp">Danh sách file</a>
                    <a href="/changePassword.jsp">Đổi mật khẩu</a>
                    <a href="/logout">Đăng xuất</a>
                </div>
            </div>
        </div>
    </div>
    <script src="./js/header.js"></script>
</body>

</html>