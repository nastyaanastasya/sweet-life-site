$(document).ready(function () {
    let num = 2;

    showImages();

    $('#recipe-save-btn').on('click', function () {
        let ingredients = document.getElementsByName('ingredient');
        let amounts = document.getElementsByName('amount');
        let units = document.getElementsByName('unit');

        let json = '';

        for (let i = 0; i < ingredients.length; i++) {
            json += json +
                '{"name":' + '"' + ingredients[i].value + '",' +
                '"amount":' + '"' + amounts[i].value + '",' +
                '"unit":' + '"' + units[i].value + '"}';

            if (i !== ingredients.length - 1) {
                json = json + ',';
            }
        }
        json = "[" + json + "]";

        let form = new FormData(this);
        $.ajax({
            url: '/sweetlife/recipe/edit',
            method: 'POST',
            data: {
                ingredients: json,
                form:form
            },
            error: console.log()
        });
    });

    $('#recipe-delete-btn').on('click', function () {
        let id = $(this).data('id');

        $.ajax({
            url: '/sweetlife/recipe/delete',
            method: 'DELETE',
            data: {recipeId: id},
            success: function () {
                location.href = 'http://localhost:8080/sweetlife'
            },
            error: log('unable to delete recipe with id=' + id)
        });
    });

    $('#loaded-img').on('change', function () {
        $(".col.preview-image.preview-show").remove();
        readImage();
    });

    $('#new-ingredient').on('click', function () {
        let output = $('#ingredients-view');
        let html = addNextIngredient("", "", "");
        output.append(html);
    });

    $(document).on('click', '.delete-ingredient', function () {
        let no = $(this).data('no');
        $(".ingredient-item-" + no).remove();
    });

    function readImage() {
        let num = 1;
        if (FileReader) {
            let source = event.target.files;
            let output = $("#selected-img");

            for (let i = 0; i < source.length; i++) {
                let file = source[i];
                let reader = new FileReader();

                reader.addEventListener('load', function (event) {
                    let img = event.target;
                    let html = addNextImage(img.result, num++);
                    output.append(html);
                });
                reader.readAsDataURL(file);
            }
        }
    }

    function showImages() {
        let num = 1;
        let output = $("#selected-img");
        let paths = output.data('attrs');
        for (let i = 0; i < paths.length; i++) {
            let path = paths[i];
            let html = addNextImage(path, num++);
            output.append(html);
        }
    }

    function addNextIngredient(name, amount, unit) {
        let html = '<div class="ingredient-item-' + num + ' mb-2 d-flex justify-content-start">' +
            '<input class="form-control ingredient ms-3" type="text" id="ingredient" value="' + name + '" name="ingredient" placeholder="Ingredient" style="width: 250px;">' +
            '<input class="form-control amount ms-3" type="text" id="amount" value="' + amount + '" name="amount"  placeholder="Amount" style="width: 150px;">' +
            '<input class="form-control amount ms-3" type="text" id="unit" value="' + unit + '" name="unit" placeholder="Unit" style="width: 150px;">' +
            '<div id="delete-ingredient" class="delete-ingredient" data-no="' + num + '"><i class="fas fa-minus ms-3 mt-2" ></i></div>' +
            '</div>';
        num++;
        return html;
    }

    function addNextImage(path) {
        return '<div class="col preview-image preview-show">' +
            '<div class="image-zone"><img id="pro-img" src="' + path + '"></div>' +
            '</div>';
    }

    function log(msg) {
        console.log(msg);
    }
});