function toggleDropdown() {
    const dropdown = document.querySelector('.dropdown');
    dropdown.classList.toggle('show');
}

// Ẩn dropdown khi click bên ngoài
window.addEventListener('click', function (event) {
    if (!event.target.closest('.profile')) {
        document.querySelector('.dropdown')?.classList.remove('show');
    }
});