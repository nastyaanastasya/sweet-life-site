$(document).ready(function () {

    $('#file-input-settings').on('change', function () {
        let source = event.target.files;

        if (FileReader && source && source.length) {
            let reader = new FileReader();

            reader.addEventListener('load', function () {
                $('#image-changed').src = reader.result;
            });
            reader.readAsDataURL(source[0]);
        }
    });
});