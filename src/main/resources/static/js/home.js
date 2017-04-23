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
                console.log(msg);
            },
            error: function () {
                console.log("err in show");
            }
        });
    });

    $(document).on("click","#new-note-btn",function () {
        var btn = $(this);
        var note_name = btn.prev().val();
        console.log(note_name);

        $("#editing").append(note_name);

    });

    $(document).on("click",".note-btn",function () {
        var btn = $(this);
        var noteId = btn.next().val();
        var data = {"noteId":noteId};
        btn.addClass("active");
        jQuery.ajax({
            async: false,
            data: data,
            url: "/note/show",
            type: "get",
            success: function (msg) {
                btn.addClass("active");
                console.log(msg);
            },
            error: function () {
                console.log("err in show");
            }
        });
    });

    $(document).on("click","#create-btn",function () {

        var name = $("#editing").innerHTML;
        var dirId = "";
        var dirName = $(this).prev().val();
        var content = "";

        console.log(name);

        var data = {
            "name":name,
            "dirId":dirId,
            "dirName":dirName,
            "content":content
        };
        // jQuery.ajax({
        //     async: false,
        //     data: data,
        //     url: "/note/add",
        //     type: "get",
        //     success: function (msg) {
        //         console.log(msg);
        //     },
        //     error: function () {
        //         console.log("err in add");
        //     }
        // });

    });



});