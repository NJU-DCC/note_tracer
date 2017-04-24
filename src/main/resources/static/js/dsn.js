/**
 * Created by dsn on 17/4/24.
 */
$(document).ready(function() {
    $(document).on("click", "#add-pic-btn", function () {

        var content = $("#content").val();

        $.ajax({
            url: '/note/uploadImg',
            type: 'POST',
            cache: false,
            data: new FormData($('#add-pic-form')[0]),
            processData: false,
            contentType: false,
            success: function (msg) {
                console.log(msg);
                content += ("![]("+msg+")");
                $("#content").val(content);
                $('#preview').html(marked($('#content').val()));
            },
            error: function () {
                console.log("err in identify");
            }
        });

    });
});