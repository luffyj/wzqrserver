function jshelper() {
}
jshelper.urlArgs = function() {
    var args = {};                             // Start with an empty object
    var query = location.search.substring(1);  // Get query string, minus '?'
    var pairs = query.split("&");              // Split at ampersands
    for(var i = 0; i < pairs.length; i++) {    // For each fragment
        var pos = pairs[i].indexOf('=');       // Look for "name=value"
        if (pos == -1) continue;               // If not found, skip it
        var name = pairs[i].substring(0,pos);  // Extract the name
        var value = pairs[i].substring(pos+1); // Extract the value
        value = decodeURIComponent(value);     // Decode the value
        args[name] = value;                    // Store as a property
    }
    return args;                               // Return the parsed arguments
};

jshelper.generatePassword = function(passwordTarget) {
	if ($(passwordTarget).length == 0) {
		alert("请指定密码文本框对象。");
		return;
	}
	ajaxPost(baseUrl + "a1/auth/generatePassword", {}, function(result) {
		if (result.success)
			$(passwordTarget).val(result.password);
		else
			alert(result.message || "生成随机密码失败！");
	});
};

jshelper.validateUserName = function(userId, userName, response) {
	ajaxPost(baseUrl + "a1/auth/validateUserName", {userId: userId, userName: userName}, function(result) {
		if ($.isFunction(response))
			response(result);
		else
			alert(result.message || "检查用户是否存在失败！");
	});
};

jshelper.sendPassword = function(entryUrl, sendUrl, options) {
	options = $.extend({}, {
			dialogTitle: "发送密码",
			username: null,
			password: null,
			data: { email : null, mobile: null}
		}, options);
	var theId = "#email_sms_div";
	var $div = $(theId);
	if ($div.length > 0) {
		$div.remove();
	}
	$div = $("<div id='" + theId + "'>正在读取发送内容，请稍后...</div>").appendTo("body");
	var params = {username: options.username, password: options.password};
	if (options.data) {
		for(var key in options.data) {
			params[key] = options.data[key];
		}		
	}
	$div.dialog({
		title: options.dialogTitle,
		height: 320,
		width: 550,
		modal: true,
		bgiframe : true,
		close: function(event, ui) {
			if ($.isFunction(options.close))
				options.close(event, ui);
		}
	});
	$div.load(entryUrl + "?_=" + (new Date().getTime()), params, function() {
		if ($div.find("input[name='btnSend']").length == 0) {
			$div.html("<span class='error'>读取发送内容失败！</span>");
			return;
		} else {
			$div.find("textarea").css("width", 350).css("height", 120);
			$div.find("input[name='btnSend']").button().click(function() {
				var $form = $(this).parents("form:first");
				$form.attr("action", sendUrl);
				ajaxSubmitForm($form, function(result) {
					if (result.success) {
						openWindow({
							url: baseUrl + "a1/auth/notifyIndex?sid=" + result.sid,
							width: 1000,
							height: 700
						});
					} else {
						alert(result.message || "发送失败。");
					}
				});
			});
		}		
		$div.children().tabs();
	});
};

jshelper.sendAuditResult = function(entryUrl, sendUrl, options) {
	options = $.extend({}, {
			dialogTitle: "发送审核结果",
			data: { email : null, mobile: null}
		}, options);
	var theId = "#email_sms_div";
	var $div = $(theId);
	if ($div.length > 0) {
		$div.remove();
	}
	$div = $("<div id='" + theId + "'>正在读取发送内容，请稍后...</div>").appendTo("body");
	var params = {};
	if (options.data) {
		for(var key in options.data) {
			params[key] = options.data[key];
		}		
	}
	$div.dialog({
		title: options.dialogTitle,
		height: 320,
		width: 550,
		modal: true,
		bgiframe : true,
		close: function(event, ui) {
			if ($.isFunction(options.close))
				options.close(event, ui);
		}
	});
	$div.load(entryUrl + "?_=" + (new Date().getTime()), params, function() {
		if ($div.find("input[name='btnSend']").length == 0) {
			$div.html("<span class='error'>读取发送内容失败！</span>");
			return;
		} else {
			$div.find("textarea").css("width", 350).css("height", 120);
			$div.find("input[name='btnSend']").button().click(function() {
				var $form = $(this).parents("form:first");
				$form.attr("action", sendUrl);
				ajaxSubmitForm($form, function(result) {
					if (result.success) {
						openWindow({
							url: baseUrl + "a1/auth/notifyIndex?sid=" + result.sid,
							width: 1000,
							height: 700
						});
					} else {
						alert(result.message || "发送失败。");
					}
				});
			});
		}		
		$div.children().tabs();
	});
};

jshelper.sendApplyNotice = function(entryUrl, sendUrl, options) {
	options = $.extend({}, {
			dialogTitle: '申报状态变更通知',
			data: { applyExpId : null},
			turn2params:true
		}, options);
	var theId = "#email_sms_div";
	var $div = $(theId);
	if ($div.length > 0) {
		$div.remove();
	}
	$div = $("<div id='" + theId + "'>正在读取发送内容，请稍后...</div>").appendTo("body");
	var params = {};
	if (options.data) {
		if( !options.turn2params){
			params = options.data;
		}else{
			for(var key in options.data) {
				params[key] = options.data[key];
			}
		}
	}
	$div.dialog({
		title: options.dialogTitle,
		height: 450,
		width: 700,
		modal: true,
		bgiframe : true,
		close: function(event, ui) {
			if ($.isFunction(options.close))
				options.close(event, ui);
		}
	});
	$div.load(entryUrl + "?_=" + (new Date().getTime()), params, function() {
		if ($div.find("input[name='btnSend']").length == 0) {
			$div.html("<span class='error'>读取发送内容失败！</span>");
			return;
		} else {
			$div.find("textarea").css("width", "95%").css("height", 80);
			$div.find("#emailSubject").css("width", "95%");
			
			$div.find("#email_sms_tabs-sms input[type='checkbox'],#email_sms_tabs-email input[type='checkbox']").each(function(){
				if($(this).attr("checked") == "checked"){
					$(this).parents("tr:eq(0)").find("input.target").addClass("required");
				}
			});
			
			$div.find("#email_sms_tabs-sms input[type='checkbox'],#email_sms_tabs-email input[type='checkbox']").click(function(){
				if($(this).attr("checked") == "checked"){
					$(this).parents("tr:eq(0)").find("input.target").addClass("required");
				}else{
					$(this).parents("tr:eq(0)").find("input.target").removeClass("required");
				}
			});
			
			//$div.find("#emailSubject").val(options.dialogTitle);
			
			$div.find("input[name='btnSend']").button().click(function() {
				var $form = $(this).parents("form:first");
				$form.attr("action", sendUrl);
				
				$(":text", $form).each(function () {
			        var s1 = $(this).val();
			        var s2 = trimEnd(s1);
			        if (s1 != s2)
			            $(this).val(s2);
			    });
			    if ($form.hasClass("validate") && !$form.validate().form())
			        return;

			    if($form.find("input:checked").length == 0){
		    		alert("请至少选择一个通知对象。");
		    		return;
		    	}
			    
			    ajaxSubmitForm($form, function(result) {
					if (result.success) {
						openWindow({
							url: baseUrl + "a1/auth/notifyIndex?sid=" + result.sid,
							width: 1000,
							height: 700
						});
					} else {
						alert(result.message || "发送失败。");
					}
				});
			});
		}		
		$div.children().tabs();
	});
};

jshelper.sendNotify = function(entryUrl, sendUrl, options) {
	options = $.extend({}, {
			dialogTitle: '通知发送',
			data: { applyExpId : null},
			turn2params:true
		}, options);
	var theId = "#email_sms_div";
	var $div = $(theId);
	if ($div.length > 0) {
		$div.remove();
	}
	$div = $("<div id='" + theId + "'>正在读取发送内容，请稍后...</div>").appendTo("body");
	var params = {};
	if (options.data) {
		if( !options.turn2params){
			params = options.data;
		}else{
			for(var key in options.data) {
				params[key] = options.data[key];
			}
		}
	}
	$div.dialog({
		title: options.dialogTitle,
		height: 700,
		width: 700,
		modal: true,
		bgiframe : true,
		close: function(event, ui) {
			if ($.isFunction(options.close))
				options.close(event, ui);
		}
	});
	$div.load(entryUrl + "?_=" + (new Date().getTime()), params, function() {
		if ($div.find("input[name='btnSend']").length == 0) {
			$div.html("<span class='error'>读取发送内容失败！</span>");
			return;
		} else {
			$div.find("textarea").css("width", "95%").css("height", 80);
			$div.find("#emailSubject").css("width", "95%");
			
			$div.find("#email_sms_tabs-sms input[type='checkbox'],#email_sms_tabs-email input[type='checkbox']").each(function(){
				if($(this).attr("checked") == "checked"){
					$(this).parents("tr:eq(0)").find("input.target").addClass("required");
				}
			});
			
			$div.find("#email_sms_tabs-sms input[type='checkbox'],#email_sms_tabs-email input[type='checkbox']").click(function(){
				if($(this).attr("checked") == "checked"){
					$(this).parents("tr:eq(0)").find("input.target").addClass("required");
				}else{
					$(this).parents("tr:eq(0)").find("input.target").removeClass("required");
				}
			});
			
			//$div.find("#emailSubject").val(options.dialogTitle);
			
			$div.find("input[name='btnSend']").button().click(function() {
				var $form = $(this).parents("form:first");
				$form.attr("action", sendUrl);
				
				$(":text", $form).each(function () {
			        var s1 = $(this).val();
			        var s2 = trimEnd(s1);
			        if (s1 != s2)
			            $(this).val(s2);
			    });
			    if ($form.hasClass("validate") && !$form.validate().form())
			        return;

			    if($form.find("input:checked").length == 0){
		    		alert("请至少选择一个通知对象。");
		    		return;
		    	}
			    
				ajaxSubmitForm($form, function(result) {
					if (result.success) {
						openWindow({
							url: baseUrl + "a1/auth/notifyIndex?sid=" + result.sid,
							width: 1000,
							height: 700
						});
					} else {
						alert(result.message || "发送失败。");
					}
				});
			});
		}		
		$div.children().tabs();
	});
};