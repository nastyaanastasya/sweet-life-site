$(document).ready(function () {

    $('#search-btn').on('click', function (){
        let elem = $(this);
        let value = elem.data('value');
        let query = $('#queryInput').val();

        console.log(query);
        if (value === 'users') {
            $.ajax({
                url: '/sweetlife/search/users',
                method: 'POST',
                data: {query: query},
                success: function () {

                }
            });
        } else {
            $.ajax({
                url: '/sweetlife/search/recipes',
                method: 'POST',
                data: {query: query},
                success: function () {

                }
            });
        }
    });

})