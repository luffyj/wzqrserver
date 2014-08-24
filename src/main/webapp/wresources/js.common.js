function getCurrentUrlInfo() {
    return parseUrl(location.href);
}
function parseUrl(url) {
    var index = url.indexOf("?");
    var p = {};
    var url2 = url;
    if (index > 0) {
        url2 = url.substring(0, index);
        var ss = url.substring(index + 1).split("&");
        for (var i = 0; i < ss.length; i++) {
            var ss2 = ss[i].split("=");
            p[ss2[0]] = ss2[1];
        }
    }
    return {
        url: url2,
        params: p
    };
}
function refreshCurrentPage() {
    var info = getCurrentUrlInfo();
    info.params["_"] = (new Date()).getTime();
    location.href = info.url + "?" + $.param(info.params);
}

function trimEnd(s) {
    if (!s)
        return "";
    return s.replace(/[ \s]+$/g, "");
}

function makeArrayParams(name, values) {
    var ret = [];
    for (var i = 0, len = values.length; i < len; i++) {
        ret.push({ name: name, value: values[i] });
    }
    return ret;
}

function logout(url) {
    if (confirm("您确定要退出系统吗？"))
        location.href = url;
    return false;
}

//初期化输入页面
function initailizeInput(options, context) {
    var $ctx = $(context || "body");
    var arry = [];
    if ($.isArray(options)) arry = options;
    else if (typeof (options) === "String") arry = [options];
    if ($.inArray("date", arry) >= 0) { //日期控件
        $ctx.find("input.date").each(function () {
            var options = { "buttonImage": baseUrl + "images/calendar.gif" };
            var yearRange = $(this).attr("data-year-range");
            if (yearRange)
                options["yearRange"] = yearRange;
            $(this).datepicker(options);
        });
    }
    if ($.inArray("required", arry) >= 0) { //必须图标
        var requiredHtml = "<img src='" + baseUrl + "images/required.gif' title='必须' />";
        $ctx.find("input.required,select.required").after(requiredHtml);
    }
}

function postToSelfPage(url, params) {
	postToTarget(url, params, "_self");
}

function postToTarget(url, params, target) {
	target = target || "_self";
	$form = $("<form method='post' action='" + url + "' target='" + target + "'></form>").appendTo("body");
	for(var key in params) {
		$("<input type='hidden'></input>").attr("name", key).val(params[key]).appendTo($form);
	}
	$form.submit();
	$form.remove();
}