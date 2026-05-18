document.querySelectorAll(".delete").forEach(button => {

    button.addEventListener("click", function (e) {

        const confirmed = confirm("Are you sure you want to delete this?");

        if (!confirmed) {
            e.preventDefault();
        }

    });

});