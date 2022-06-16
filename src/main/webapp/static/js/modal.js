let modal = document.querySelector("#myModal");
let btn = document.querySelector("#schedule-btn");
let span = document.querySelector(".close-modal");
btn.onclick = function() {
    modal.style.display = "block";
}
span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}