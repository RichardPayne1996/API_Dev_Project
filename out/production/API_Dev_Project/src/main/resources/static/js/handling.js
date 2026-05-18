alert("JS Loaded")


document.getElementById("delete").addEventListener('click', (e) => {

        const confirmed = confirm('Are you sure you want to delete this course?');

        if (!confirmed) {
            e.preventDefault();
        }

});