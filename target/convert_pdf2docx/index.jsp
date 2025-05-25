<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <h2>Chào mừng tới web của bọn tao</h2>
    <form id="convertForm" action="convert" method="post" enctype="multipart/form-data">
        <label>Chọn file PDF:</label>
        <input type="file" id="pdfFileInput" name="pdfFile" accept=".pdf" required />
        <button type="submit">Chuyển đổi</button>
    </form>
</body>
</html>



<script>
    const inputFile = document.getElementById('pdfFileInput');
    inputFile.addEventListener('click', function(event) {
        event.preventDefault();  
        window.location.href = 'login';  
    });
    const form = document.getElementById('convertForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); 
        window.location.href = 'login'; 
    });
</script>
