document.addEventListener("DOMContentLoaded", function () {
    const editButton = document.querySelector(".edit-name");
    const form = document.querySelector(".change-name-form");
    const cancelButton = document.querySelector(".cancel-update");

    editButton.addEventListener("click", function () {
        form.style.display = "block";
        editButton.style.display = "none";
    });

    cancelButton.addEventListener("click", function () {
        form.style.display = "none";
        editButton.style.display = "inline-block";
        form.reset(); 
    });
});