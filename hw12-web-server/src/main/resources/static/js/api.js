$(document).ready(function() {
    updateListUsers();

    const $tbody = $("#users-list tbody");
    const $form = $("#add-user");

    $form.on("submit", function(e) {
        e.preventDefault();
        const newUser = {
            name: $("#name").val(),
            login: $("#login").val(),
            password: $("#password").val(),
            age: $("#age").val()
        };
        $.ajax({
            url: "api/users",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(newUser),
            success: function(data) {
                $form.trigger("reset");
                addUserInList(data);
            },
            error: function() {
                alert("Ошибка сохранения!")
            }
        })
    })

    $("#update").click(function() {
        updateListUsers();
    })

    function updateListUsers() {
        fetch("api/users")
            .then(response => response.json())
            .then(usersList => {
                $tbody.empty();
                usersList.forEach(user => {
                    addUserInList(user);
                })
            });
    }

    function addUserInList(user) {
        const $tr = $("<tr>");
        $tr.append($("<td>").text(user.id));
        $tr.append($("<td>").text(user.name));
        $tr.append($("<td>").text(user.age));
        $tr.append($("<td>").text(user.login));
        $tr.append($("<td>").text(user.password));
        $tbody.append($tr);
    }

});