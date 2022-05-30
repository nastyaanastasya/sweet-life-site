$(document).ready(function () {

    $('#subscribe-button').on('click', function () {
        let elem = $(this);
        let id = elem.data('id');

        if (elem.data('state') === 0) {
            $.ajax({
                url: '/sweetlife/profile/subscribe',
                method: 'PUT',
                data: {profileId: id},
                success: function () {
                    changeElemView(
                        elem,
                        1,
                        'Unfollow',
                        'btn-outline-secondary ms-2',
                        'btn-secondary ms-2'
                    );
                    location.reload();
                },
                error: log('Unable to follow user with id=' + id)
            });
        } else {
            $.ajax({
                url: '/sweetlife/profile/unsubscribe',
                method: 'PUT',
                data: {profileId: id},
                success: function () {
                    changeElemView(
                        elem,
                        0,
                        'Follow',
                        'btn-secondary ms-2',
                        'btn-outline-secondary ms-2'
                    );
                    location.reload();
                },
                error: log('Unable to unfollow user with id=' + id)
            });
        }
    });

    $('#ban-button').on('click', function () {
        let elem = $(this);
        let id = elem.data('id');

        if (elem.data('state') === 0) {
            $.ajax({
                url: '/sweetlife/profile/ban',
                method: 'PUT',
                data: {profileId: id},
                success: function () {
                    changeElemView(
                        elem,
                        0,
                        'Ban',
                        'btn-danger ms-2',
                        'btn-outline-danger ms-2'
                    );
                    location.reload();
                },
                error: log('Unable to ban user with id=' + id)
            });
        } else {
            $.ajax({
                url: '/sweetlife/profile/unban',
                method: 'PUT',
                data: {profileId: id},
                success: function () {
                    changeElemView(
                        elem,
                        1,
                        'Unban',
                        'btn-outline-danger ms-2',
                        'btn-danger ms-2'
                    );
                    location.reload();
                },
                error: log('Unable to unban user with id=' + id)
            });
        }
    });

    $('#add-new-recipe-btn').on('click', function () {
        $.ajax({
            url: '/sweetlife/recipe/edit',
            method: 'GET',
            success: function () {
                location.href = 'http://localhost:8080/sweetlife/recipe/edit'
            },
            error: function (msg) {
                log(msg)
            }
        });
    });

    function changeElemView(elem, state, html, rmCssClass, cssClass) {
        elem.data('state', state);
        elem.html(html);
        elem.removeClass(rmCssClass);
        elem.addClass(cssClass);
    }

    function log(msg) {
        console.log(msg);
    }
});