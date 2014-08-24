if (jQuery.validator) {
	
	jQuery.validator.showFieldError = function($field, errorMsg) {
		$field.focus();
		$field.addClass("validate-error");
		alert(errorMsg);
		$field.removeClass("validate-error");
	};
	
	jQuery.validator.showError = function(name, errorMsg) {
		var $field = $("[name='" + name + "']");
		var fieldName = $field.attr("data-label");
		if (!fieldName) {
			var id = $field.attr("id");
			if (id)
				fieldName = $("label[for='" + id + "']").text();
		}
		if (fieldName)
			errorMsg = $.trim(fieldName) + "：" + errorMsg;
		jQuery.validator.showFieldError($field, errorMsg);
	};
	
	jQuery.extend(jQuery.validator.messages, {
		required : "不能为空",
		remote : "请修正该字段",
		email : "请输入正确格式的电子邮件",
		url : "请输入合法的网址",
		date : "请输入合法的日期",
		dateISO : "请输入合法的日期 (ISO).",
		number : "请输入合法的数字",
		digits : "只能输入整数",
		creditcard : "请输入合法的信用卡号",
		equalTo : "请再次输入相同的值",
		accept : "请输入拥有合法后缀名的字符串",
		maxlength : jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength : jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength : jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range : jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max : jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min : jQuery.validator.format("请输入一个最小为 {0} 的值")
	});

	jQuery.validator.setDefaults({
		invalidHandler : function(form, validator) {
			$.each(validator.invalid, function(name, errorMsg) {
				validator.invalid = {};
				validator.invalid[name] = errorMsg;
				jQuery.validator.showError(name, errorMsg);
				return false;
			});
		},
		errorPlacement : function(error, element) {
		},
		onkeyup : false,
		onfocusout : false,
		onsubmit : false
	});
	jQuery.validator.addMethod("numeric", function(value, element, params) {
		return this.optional(element) || /^[0-9]*$/.test(value);
	}, "只能输入数字");
	jQuery.validator.addMethod("required2", function(value, element, params) {
		var v1 = $.trim($(element).val());
		var v2 = $.trim($(params).val());
		return !(v1 == "" && v2 != "");
	}, "不能为空");
}