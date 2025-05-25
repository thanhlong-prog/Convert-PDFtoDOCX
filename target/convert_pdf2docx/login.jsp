<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="./css/login.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <style>
      .password-wrapper {
        position: relative;
      }

      .password-wrapper input {
        width: 100%;
        padding-right: 35px;
      }

      .toggle-password {
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translateY(-50%);
        cursor: pointer;
        color: #555;
        font-size: 1rem;
      }

      .toggle-password:hover {
        color: #000;
      }
    </style>
  </head>
  <body>
    <c:if test="${not empty error}">
      <p style="color: red">${error}</p>
    </c:if>

    <form action="login" method="post">
      <h2>Đăng nhập</h2>

      <div>
        <label for="username">Tên đăng nhập:</label>
        <input
          type="text"
          id="username"
          name="username"
          required
          placeholder="Nhập tên đăng nhập..."
        />
      </div>

      <div>
        <label for="password">Mật khẩu:</label>
        <div class="password-wrapper">
          <input
            type="password"
            id="password"
            name="password"
            required
            placeholder="Nhập mật khẩu..."
          />
          <i class="fa-solid fa-eye toggle-password" id="togglePassword"></i>
        </div>
      </div>

      <button type="submit">Đăng nhập</button>
      <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a></p>
    </form>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector("form");
        const usernameInput = document.getElementById("username");
        const passwordInput = document.getElementById("password");
        const togglePassword = document.getElementById("togglePassword");

        togglePassword.addEventListener("click", function () {
          const isPassword = passwordInput.type === "password";
          passwordInput.type = isPassword ? "text" : "password";
          this.classList.toggle("fa-eye");
          this.classList.toggle("fa-eye-slash");
        });

        form.addEventListener("submit", function (e) {
          if (!usernameInput.value.trim() || !passwordInput.value.trim()) {
            e.preventDefault();
            alert("Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            return;
          }

          const submitButton = form.querySelector("button[type='submit']");
          submitButton.disabled = true;
          submitButton.innerText = "Đang đăng nhập...";
        });
      });
    </script>
  </body>
</html>
