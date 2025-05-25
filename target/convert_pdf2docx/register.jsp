<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Đăng ký</title>
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
        top: 40%;
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

    <form action="register" method="post">
      <h2>Đăng ký tài khoản mới</h2>

      <div>
        <label for="fullname">Họ và tên:</label>
        <input
          type="text"
          id="fullname"
          name="fullname"
          required
          placeholder="Nhập họ và tên..."
        />
      </div>

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
          <i class="fa-solid fa-eye toggle-password" toggle="password"></i>
        </div>
      </div>

      <div>
        <label for="confirmPassword">Nhập lại mật khẩu:</label>
        <div class="password-wrapper">
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            required
            placeholder="Nhập lại mật khẩu..."
          />
          <i
            class="fa-solid fa-eye toggle-password"
            toggle="confirmPassword"
          ></i>
        </div>
      </div>

      <button type="submit">Đăng ký</button>
      <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
    </form>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const form = document.querySelector("form");
        const fullnameInput = document.getElementById("fullname");
        const usernameInput = document.getElementById("username");
        const passwordInput = document.getElementById("password");
        const confirmPasswordInput = document.getElementById("confirmPassword");
        const toggleIcons = document.querySelectorAll(".toggle-password");

        toggleIcons.forEach((icon) => {
          icon.addEventListener("click", function () {
            const targetId = this.getAttribute("toggle");
            const input = document.getElementById(targetId);
            const isPassword = input.type === "password";
            input.type = isPassword ? "text" : "password";
            this.classList.toggle("fa-eye");
            this.classList.toggle("fa-eye-slash");
          });
        });

        form.addEventListener("submit", function (e) {
          if (
            !fullnameInput.value.trim() ||
            !usernameInput.value.trim() ||
            !passwordInput.value.trim() ||
            !confirmPasswordInput.value.trim()
          ) {
            e.preventDefault();
            alert("Vui lòng điền đầy đủ thông tin.");
            return;
          }

          if (passwordInput.value !== confirmPasswordInput.value) {
            e.preventDefault();
            alert("Mật khẩu và xác nhận mật khẩu không khớp.");
            return;
          }

          const submitButton = form.querySelector("button[type='submit']");
          submitButton.disabled = true;
          submitButton.innerText = "Đang xử lý...";
        });
      });
    </script>
  </body>
</html>
