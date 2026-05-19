document.querySelectorAll(".delete").forEach(button => {

    button.addEventListener("click", (e) => {

        const confirmed = confirm("Are you sure you want to delete this?");

        if (!confirmed) {
            e.preventDefault();
        }

    });

});

document.getElementById("logout").addEventListener("click", (e) => {
    const confirmed = confirm("Are you sure you want to log out?");
    if (!confirmed) {
        e.preventDefault();
    }

});