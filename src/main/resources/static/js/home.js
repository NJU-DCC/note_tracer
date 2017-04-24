/**
 * Created by cdn on 17/4/23.
 */
$(document).ready(function(){

    $(document).on("click","#search-btn",function(){
        var key = $("#search-key").val();
        var data = {"keyword":key};
        console.log(key);
        jQuery.ajax({
            async: false,
            data: data,
            url: "/note/search",
            type: "get",
            success: function (msg) {
                console.log(msg);
            },
            error: function () {
                console.log("err in search");
            }
        });
    });

    $(document).on("click","#new-dir-btn",function () {
        var btn = $(this);
        var dir_name = btn.prev().val();
        var description = "";
        var data = {
            "name":dir_name,
            "description":description
        };
        btn.addClass("active");
        jQuery.ajax({
            async: false,
            data: data,
            url: "/note/addDir",
            type: "get",
            success: function (msg) {
                // console.log(msg);
            },
            error: function () {
                console.log("err in show");
            }
        });
    });

    $(document).on("click","#new-note-btn",function () {
        var btn = $(this);
        var note_name = btn.prev().val();
        // console.log(note_name);

        // $("#editing").append(note_name);
        $("#editing").val(note_name);
        $("#content").val("");
        $('#preview').html(marked($('#content').val()));

    });

    $(document).on("click",".note-btn",function () {
        var btn = $(this);
        var noteId = btn.next().val();
        var data = {"noteId":noteId};

        $("a.active").removeClass("active");
        btn.addClass("active");

        jQuery.ajax({
            async: false,
            data: data,
            url: "/note/show",
            type: "get",
            success: function (msg) {
                // btn.addClass("active");
                // console.log(msg);
                $("#content").val(msg["content"]);
                $("#editing").val(msg["name"]);
                $('#preview').html(marked($('#content').val()));

            },
            error: function () {
                console.log("err in show");
            }
        });
    });

    $(document).on("click","#create-btn",function () {

        var name = $("#editing").val();
        var dirId = $("#select-dir").val();
        var dirName = $("#select-dir").find("option:selected").text();
        var content = $("#content").val();

        console.log(name);
        console.log(dirId);
        console.log(dirName);
        console.log(content);

        var data = {
            "name":name,
            "dirId":dirId,
            "dirName":dirName,
            "content":content
        };
        jQuery.ajax({
            async: false,
            data: data,
            url: "/note/add",
            type: "post",
            success: function (msg) {
                // console.log(msg);
            },
            error: function (data) {
                // console.log(JSON.stringify(data));
            }
        });

    });
    $(document).on("click","#identify-btn",function () {

        var content = $("#content").val();

        $.ajax({
            url: '/note/transform',
            type: 'POST',
            cache: false,
            data: new FormData($('#identify-form')[0]),
            processData: false,
            contentType: false,
            success: function (msg) {
                console.log(msg);
                content += msg;
                $("#content").val(content);
                $('#preview').html(marked($('#content').val()));
            },
            error: function () {
                console.log("err in identify");
            }
        });

    });



});