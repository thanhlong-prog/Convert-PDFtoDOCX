<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="Model.BEAN.ConvertJob" %>
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
    <title>Danh sách file đã chuyển đổi</title>
</head>
<body>
<h2>Danh sách file đã chuyển đổi của <c:out value="${user.username}"/></h2>

<table border="1" cellpadding="6" cellspacing="0">
    <thead>
    <tr>
        <th>ID Job</th>
        <th>File PDF gốc</th>
        <th>Trạng thái</th>
        <th>File DOCX</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="job" items="${jobs}">
        <tr>
            <td><c:out value="${job.id}"/></td>
            <td><c:out value="${job.title}"/></td>
            <td><c:out value="${job.status}"/></td>
            <td>
                <c:choose>
                    <c:when test="${job.status == 'Completed'}">
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

<p><a href="home.jsp">Quay về trang chủ</a></p>
</body>
</html>
