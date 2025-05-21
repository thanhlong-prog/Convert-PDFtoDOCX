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

    <c:if test="${not empty message}">
        <p style="color: green;"><c:out value="${message}"/></p>
    </c:if>

    <c:if test="${not empty downloadLink}">
        <p>
            <a href="${pageContext.request.contextPath}/${downloadLink}">
                <button type="button">Tải file DOCX</button>
            </a>
        </p>
    </c:if>


    <p>Đây là trang chính sau khi bạn đăng nhập thành công.</p>

    <form action="convert" method="post" enctype="multipart/form-data">
        <label>Chọn file PDF:</label>
        <input type="file" name="pdfFile" accept=".pdf" required />
        <button type="submit">Chuyển đổi</button>
    </form>

    <form action="logout" method="post">
        <button type="submit">Đăng xuất</button>
    </form>
    <p><a href="listJobs">Xem danh sách file đã chuyển đổi</a></p>

</body>
</html>
