$(document).ready(function () {

    $('#like-comment-icon').on('click', function () {
        let elem = $(this);
        let id = elem.data('id');

        if (elem.data('state') === 0) {
            $.ajax({
                url: '/sweetlife/comment/like',
                method: 'PUT',
                data: {commentId: id},
                success: function () {
                    changeElemView(
                        elem,
                        1,
                        '',
                        'state-active'
                    );
                    addCommentLikesCount(id);
                    location.reload();
                },
                error: log('Unable to like comment with id=' + id)
            });
        } else {
            $.ajax({
                url: '/sweetlife/comment/unfollow',
                method: 'PUT',
                data: {commentId: id},
                success: function () {
                    changeElemView(
                        elem,
                        0,
                        'state-active',
                        ''
                    );
                    removeCommentLikesCount(id);
                    location.reload();
                },
                error: log('Unable to unfollow recipe with id=' + id)
            });
        }
    });

    $('#like-recipe-icon').on('click', function () {
        let elem = $(this);
        let id = elem.data('id');

        if (elem.data('state') === 0) {
            $.ajax({
                url: '/sweetlife/recipe/like',
                method: 'PUT',
                data: {recipeId: id},
                success: function () {
                    changeElemView(
                        elem,
                        1,
                        '',
                        'state-active'
                    );
                    addLikesCount();
                    location.reload();
                },
                error: log('Unable to like recipe with id=' + id)
            });
        } else {
            $.ajax({
                url: '/sweetlife/recipe/unfollow',
                method: 'PUT',
                data: {recipeId: id},
                success: function () {
                    changeElemView(
                        elem,
                        0,
                        'state-active',
                        ''
                    );
                    removeLikesCount();
                    location.reload();
                },
                error: log('Unable to unfollow recipe with id=' + id)
            });
        }
    });

    $('#save-recipe-icon').on('click', function () {
        let elem = $(this);
        let id = elem.data('id');

        if (elem.data('state') === 0) {
            $.ajax({
                url: '/sweetlife/recipe/save',
                method: 'PUT',
                data: {recipeId: id},
                success: function () {
                    changeElemView(
                        elem,
                        1,
                        '',
                        'state-active'
                    );
                    addSavesCount();
                    location.reload();
                },
                error: log('Unable to save recipe with id=' + id)
            });
        } else {
            $.ajax({
                url: '/sweetlife/recipe/cancelSave',
                method: 'PUT',
                data: {recipeId: id},
                success: function () {
                    changeElemView(
                        elem,
                        0,
                        'state-active',
                        ''
                    );
                    removeSavesCount();
                    location.reload();
                },
                error: log('Unable to cancel saving recipe with id=' + id)
            });
        }
    });

    function addLikesCount() {
        let elem = $('#likes-count');
        elem.val(elem.val() + 1);
    }

    function removeLikesCount() {
        let elem = $('#likes-count');
        elem.val(elem.val() - 1);
    }

    function addSavesCount() {
        let elem = $('#likes-count');
        elem.val(elem.val() + 1);
    }

    function removeSavesCount() {
        let elem = $('#likes-count');
        elem.val(elem.val() - 1);
    }

    function changeElemView(elem, state, rmCssClass, cssClass) {
        elem.data('state', state);
        elem.removeClass(rmCssClass);
        elem.addClass(cssClass);
    }

    function addCommentLikesCount(commentId) {
        let elem = $('.comment-likes-count-no-' + commentId);
        elem.val(elem.val() + 1);
        console.log(elem.val())
    }

    function removeCommentLikesCount(commentId) {
        let elem = $('.comment-likes-count-no-' + commentId);
        elem.val(elem.val() - 1);
        console.log(elem.val())
    }

    function log(msg) {
        console.log(msg);
    }
});