<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.BEAN.ConvertJob" %>
<%@ page import="Model.BEAN.UserSessionInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% UserSessionInfo user=(UserSessionInfo) session.getAttribute("user"); if (user==null) {
  response.sendRedirect("login.jsp"); return; } %>
<!DOCTYPE html>
<html>

<head>
  <title>Danh sách các file đã được chuyển đổi</title>
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link rel="stylesheet" href="./css/listJobs.css" />
  <link rel="stylesheet" href="./css/header.css">
</head>

<body>
  <div class="header">
    <div class="header-home">
      <i class='bx  bx-home'></i>
      <a href="/home">
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
          <a href="profile">Trang cá nhân</a>
          <a href="listJobs">Danh sách file</a>
          <a href="password">Đổi mật khẩu</a>
          <a href="logout">Đăng xuất</a>
        </div>
      </div>
    </div>
  </div>
  <div class="page-list-content">
    <h2>
      Danh sách các file đã được chuyển đổi của
      <c:out value="${user.fullname}" />
    </h2>

    <table border="1" cellpadding="6" cellspacing="0">
      <thead>
        <tr>
          <th>STT</th>
          <th>File PDF gốc</th>
          <th>Trạng thái</th>
          <th>File DOCX đã được chuyển đổi</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="job" items="${jobs}" varStatus="loop">
          <tr>
            <td>
              <c:out value="${loop.index + 1}" />
            </td>
            <td>
              <c:out value="${job.title}" />
            </td>
            <td
              class="${job.status == 'Hoàn thành' ? 'status-completed' : job.status == 'Thất bại' ? 'status-failed' : 'status-pending'}">
              <c:out value="${job.status}" />
            </td>
            <td>
              <c:choose>
                <c:when test="${job.status == 'Hoàn thành'}">
                  <a href="${pageContext.request.contextPath}/${job.docPath}">Tải xuống</a>
                </c:when>
                <c:otherwise>
                  <i>Chưa có file</i>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <p><a href="home">Quay về trang chủ</a></p>
  </div>
  <script src="./js/header.js"></script>
</body>

</html>