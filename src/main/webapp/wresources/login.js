/* 
 * 实现ajax login 避免chrome等浏览器的bug影响登录
 */
var wzqrserver = wzqrserver || {};

wzqrserver.login = function(form) {
//    console.log($('#loginForm').attr('action'));
//    console.log($('input[name=username]').val());

    $.post($('#loginForm').attr('action'), {
        username: $('input[name=username]').val(),
        password: $('input[name=password]').val(),
        jcaptcha: $('input[name=jcaptcha]').val(),
        ajax: true
    }, function(data, textStatus, jqXHR) {
//        console.log(jqXHR,textStatus);
//        console.log(jqXHR.getResponseHeader('Location'));
        if (jqXHR.status === 200) {
            $.ajax('queryUser',{complete:function(){
                var href = window.location.href;
                var index = href.lastIndexOf('/');
                var newHref = href.substring(0,index+1);
                window.location.href = newHref;
            }})
//            window.location.reload();
        }
    }).error(function(data, textStatus) {
//        console.log(data);
        if (data.status === 410) {
            $('img[title=点击刷新]').click();
            $('#errorMsg').html('验证码错误');
            return;
        }

        if (data.status === 500) {
            $('img[title=点击刷新]').click();
            $('#errorMsg').html('系统繁忙，请重试');
            return;
        }

        if (data.status === 401) {
            $('img[title=点击刷新]').click();
            $('#errorMsg').html('用户名或者密码错误');
            return;
        }

        $('img[title=点击刷新]').click();
        $('#errorMsg').html('未知错误');
    });
    return false;
};


