$(function() {
    var closeWaitMessage = 'closeWaitMessage';
    var minHeight = 0;
    if ($.browser.msie) {
        minHeight = document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
    } else {
        minHeight = self.innerHeight;
    }
    minHeight = minHeight - $(".login_header").height() - $("#header").height() - $("#nav").height() - $("#footer").height() - 30;
    $(".main_wrap").css("min-height", minHeight).css("_height", minHeight);
    $(":button").button();
    $('input[type="submit"]').button();
    $(document).ajaxStop(closeWaitMessage);

    $(".themes-list ul li a").click(function() {
        var url = baseUrl + 'a1/auth/saveTheme';
        ajaxPost(url, {"theme": $(this).text()}, function(result) {
            if (result.success) {
                refreshCurrentPage();
            } else {
                alert(result.message);
            }
        })
    });

    $(".btnDropdownList .selectBtnList").button({text: true, icons: {primary: "ui-icon-triangle-1-s"}}).click(function() {
        var menu = $(this).parent().next().show().position({my: "right top", at: "right bottom", of: this});
        $(document).one("click", function() {
            menu.hide();
        });
        return false;
    }).parent().buttonset().next().hide().menu();
});