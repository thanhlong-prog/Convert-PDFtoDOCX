document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("pdf-upload");
    const fileNameSpan = document.querySelector(".file-name");

    fileInput.addEventListener("change", function () {
        if (fileInput.files.length > 0) {
            fileNameSpan.textContent = fileInput.files[0].name;
        } else {
            fileNameSpan.textContent = ""; // Không có file nào
        }
    });
});