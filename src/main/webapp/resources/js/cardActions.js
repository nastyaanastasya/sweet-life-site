$(document).ready(function (){

    $('#recipe-edit-btn').on('click', function (){
        let id = $(this).data('id')

        $.ajax({
            url: '/sweetlife/recipe/edit',
            method: 'GET',
            data: {recipeId: id},
            success: function (){
                location.href = 'http://localhost:8080/sweetlife/recipe/edit/' + id
            }
        });
    });
});