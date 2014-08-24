var defaultDialogOptions = { 
		width: 800, 
		height: 600,
		name: "_blank",
		resizable: "no"
	};

function openWindow(options) {
    options = options || {};
    var w = options.width || defaultDialogOptions.width,
    	h = options.height || defaultDialogOptions.height,
    	maxWidth = screen.availWidth,
    	maxHeight = screen.availHeight - 66;
    if (w > maxWidth) {
    	w = maxWidth;
    }
    if (h > maxHeight) {
    	h = maxHeight;
    }

    var n = options.name || defaultDialogOptions.name;
    resizable = options.resizable || defaultDialogOptions.resizable;

    var features = "height=" + h + ",width=" + w + ",resizable=" + resizable
			+ ",toolbar=no,menubar=no,scrollbars=yes,location=no,status=yes";
    
    return window.open(options.url, n, features);
}

function closeWindow(notExecuteCanClose) {
    if (!notExecuteCanClose && window.canCloseWindow && !window.canCloseWindow())
        return;
    window.close();
    try {
        if (window.opener._currentDialog)
            window.opener.onDialogClosed();
    } catch (e) {
    }
}

var _currentDialog = null;
function showDialog(options, callback) {
    $.blockUI({
        message: null
    });
    _currentDialog = $.extend({}, { childWnd: null, callback: null, result: null }, options);
    if ($.isFunction(callback))
        _currentDialog.callback = callback;
    _currentDialog.childWnd = openWindow(options);
    $('.blockOverlay').click(function () {
        try {
            if (isChildWndClosed())
                onDialogClosed();
            else
                _currentDialog.childWnd.focus();
        } catch (e) {
            onDialogClosed();
        }
    });
    var failedCnt = 0;
    _currentDialog.watcher = setInterval(function () {
        try {
            if (isChildWndClosed())
                failedCnt++;
            else
                failedCnt = 0;
        } catch (e) {
            failedCnt++;
        }
        if (failedCnt >= 2) {
            onDialogClosed();
        }
    }, 500);
    return _currentDialog.childWnd;
}

function isChildWndClosed() {
	if (!_currentDialog || !(_currentDialog.childWnd) || !(_currentDialog.childWnd.document))
		return true;
	if (_currentDialog.childWnd.closed)
		return true;
	return false;
}

function onDialogClosed() {
    if (_currentDialog && _currentDialog.watcher) {
        clearInterval(_currentDialog.watcher);
        _currentDialog.watcher = null;
    }
    $.unblockUI();
    if (_currentDialog && _currentDialog.callback && _currentDialog.result)
        _currentDialog.callback.call(_currentDialog, _currentDialog.result);
    _currentDialog = null;
}

//对话框页面调用此方法设置回调参数
function setDialogResult(result) {
    if (window.opener && window.opener._currentDialog)
        window.opener._currentDialog.result = result;
}

//对话框页面调用此方法关闭页面并设置返回值
function closeDialog(result) {
    setDialogResult(result);
    window.close();
    try {
        if (window.opener && window.opener.onDialogClosed)
            window.opener.onDialogClosed();
    } catch (e) { }
}

function showWaitMessage(message) {
    return $.blockUI({
        message: message || "正在操作，请稍等...",
        baseZ: 2000,
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff',
            'font-size': '16px'
        }
    });
}

function closeWaitMessage() {
    $.unblockUI();
}

function _getFailedAjaxResult(data) {
	if (data && data.message) {
		return data;
	}
	if (data && typeof(data) === "string") {
		if (data.indexOf("<!--login-->") >= 0) {
			return {
		        success: false,
		        message: "登录超时，需要重新登录。",
		        html: data
		    };
		} else if (data.indexOf("<!--success-->") > 0) {
			return {
				success: true,
		        html: data
		    };
		}
	}	
	return {
        success: false,
        message: "您请求的操作执行失败。",
        data: data
    };
}

function ajaxSubmitForm(form, options, dataType) {
    if ($.isFunction(options)) {
        options = { response: options };
    } else  {
    	options = options || {};
    }
    if (dataType)
        options.dataType = dataType == "myhtml" ? "html" : dataType;
    options.beforeSubmit = function () {
        showWaitMessage(options.waitMsg);
        if (options.onSubmiting)
        	options.onSubmiting();
        return true;
    };
    options.success = function () {
        if (options.response) {
            if (dataType == "myhtml") {
                var html = arguments[0];
                if (html && html.indexOf("<!--success-->") > 0)
                    options.response.apply(options, [{
                        success: true,
                        html: html
                    }]);
                else {
                	options.response(_getFailedAjaxResult(html));
                }
            } else {
            	if (arguments[0] && arguments[0].success) {
            		options.response(arguments[0]);
            	} else {
            		options.response(_getFailedAjaxResult(arguments[0]));
            	}
            }
        }
    };
    options.error = function () {
    	options.response(_getFailedAjaxResult(arguments[0]));
    };

    $(":text", form).each(function () {
        var s1 = $(this).val();
        var s2 = trimEnd(s1);
        if (s1 != s2)
            $(this).val(s2);
    });

    if ($(form).hasClass("validate") && !$(form).validate().form())
        return;

    if (window.onBeforeSubmit && !onBeforeSubmit($(form))) {
        return;
    }

    options.dataType = options.dataType || "json";
    $(form).ajaxSubmit(options);
}

function ajaxUploadLargeFile(form, response) {
	var $progressSpan = null;
	var $progressBar = null;
	var progress = 0;
	function _showProcess() {
		if ($progressSpan) {
			$.ajax({type: "GET", cache: false, global: false, dataType: "json", async: true,
				url: baseUrl + "a1/auth/getFileUploadProgress",
				success: function(result) {
					if ($progressSpan) {
						if (result.success) {
							if (result.progress < 0) {
								if (progress > 0) {
									progress = 100;
								}
							} else {
								progress = Math.round(result.progress * 100);
							}
							$progressSpan.text("" + progress + "%");
							$progressBar.progressbar("value", progress);
						} else
							$progressSpan.text("?");
						setTimeout(_showProcess, 1000);
					}
				},
				error: function(result) {
					if ($progressSpan) {
						$progressSpan.text("?");
						setTimeout(_showProcess, 1000);
					}
				}
			});
		}
	}
	
	ajaxSubmitForm(form, {
		response : function(result) {
			$progressSpan = null;
			$(".blockMsg").empty();
			response(result);
		},
		onSubmiting : function() {
			$progressSpan = $("<span>0%</span>");
			$progressBar = $("<div></div>").progressbar({
				value: 0
			});
			$(".blockMsg").empty().css("opacity", 0).css("line-height", "20px")
					.append("<span>文件上传进度：</span>").append($progressSpan).append($progressBar);
			setTimeout(_showProcess, 1000);
		}
	});	
}

function ajaxPost(url, data, response, dataType, waitMsg) {
    var isMyHtml = dataType == "myhtml";
    dataType = dataType == "myhtml" ? "html" : dataType;
    showWaitMessage(waitMsg);
    $.post(url, data, function (data) {
        if (response) {
            if (isMyHtml) {
                if (data && data.indexOf("<!--success-->") > 0)
                    response({
                        success: true,
                        html: data
                    });
                else {
                	response(_getFailedAjaxResult(data));
                }
            } else {
            	if (data && data.success) {
            		response(data);
            	} else {
            		response(_getFailedAjaxResult(data));
            	}
            }                
        }
    }, dataType).error(function (data) {
    	response(_getFailedAjaxResult(data));
    });
}

function showHelpWindow(url) {
	openWindow({
		url: url,
		width: 1024,
		height: 800
	});
}

function getChildDialogOptions(url) {
	var options = {
			url: url,
			width: defaultDialogOptions.width,
			height: defaultDialogOptions.height
	};
	var s = url;
	if (s.indexOf("?") > -1) {
		s = s.substring(0, s.indexOf("?"));
	}
	if (s.indexOf('/') == -1) {
		var s1 = location.pathname;
		s1 = s1.substring(0, s1.lastIndexOf('/') + 1);
		s = s1 + s;
	}
	if (s.length > baseUrl.length && s.substr(0, baseUrl.length) == baseUrl) {
		s = s.substr(baseUrl.length);
	} else if (s.length > baseUrlContext.length && s.substr(0, baseUrlContext.length) == baseUrlContext) {
		s = s.substr(baseUrlContext.length);
	}
	
	var ss = s.split("/");
	if (ss.length == 3) {
		if (url.indexOf('a2/verification/resultDetail') > 0 || url.indexOf('a2/verification/certification') > 0) {
			options.width = 1000;
			options.height = 800;
		} else if (url.indexOf('a2/verification/appedit') > 0) {
			options.width = 900;
		} else if (ss[1] == "prj" || ss[1] == "apply") {
			options.width = 1000;
			options.height = 800;
			if (ss[2] == "edit" || ss[2] == "add") {
				options.width = 1100;
			} else if (ss[2] == "maintain") {
				options.width = 500;
				options.height = 300;
			}
		} else if (ss[1] == "finalReview" || ss[1] == "review" || ss[1] == "verification") {
			if (ss[2] == "applydetail") {
				options.width = 1000;
				options.height = 800;
			}		
		} else if (ss[1] == "thesis" || ss[1] == "patent") {
			options.width = 1000;
			options.height = 800;
			if (ss[2] == "verify") {
				options.width = 1400;
				options.height = 900;
			}
		} else if (ss[1] == "exp") {
			if (ss[2] == "edit") {
				options.width = 900;
				options.height = 800;
			} else if (ss[2].indexOf("import") == 0) {
				options.width = 800;
				options.height = 600;
			} else {
				options.height = 640;
			}
		} else if (ss[1] == "helpDoc") {
			if (ss[2] == "editTagIndex") {
				options.width = 600;
				options.height = 500;
			}
		}
	} else if (ss.length == 2) {
		if (ss[1] == "art")
			options.height = 800;
	}
	return options;
}

function showChildDialog(url, callback) {
	return showDialog(getChildDialogOptions(url), callback);
}