;(function() {
	function InputHelper(options) {
		this.settings = $.extend(null, {
	        calendarImage: "wresources/images/calendar.gif",
	        requiredImage: "wresources/images/required.gif",
	        context: $("body"),
	        initParams: []
	    }, options);
		
		function _init($ctx, arry, settings) {
			if (arry.length > 0) {
		        if ($.inArray("required", arry) >= 0) { //必须图标
		            var requiredHtml = "<img src='" + settings.requiredImage + "' title='必须' />";
		            $ctx.find(".required").after(requiredHtml);
		        }
		        if ($.inArray("date", arry) >= 0) { //日期控件
		            $ctx.find("input.date").each(function () {
		                var options = { "buttonImage": settings.calendarImage };
		                var yearRange = $(this).attr("data-year-range");
		                if (yearRange)
		                    options["yearRange"] = yearRange;
		                $(this).datepicker(options);
		            });
		        }
		    }
		}
		
		this.init = function(params) {
			params = params || this.settings.initParams;
			this.settings.initParams = params;
		    var $ctx = this.settings.context;
		    var arry = [];
		    if ($.isArray(params)) arry = params;
		    else if (typeof (params) === "String") arry = [params];
		    _init($ctx, arry, this.settings);
		    return this;
		};

		this.reinit = function (ctx) {
		    var $ctx = ctx ? $(ctx) : this.settings.context;
		    _init($ctx, this.settings.initParams, this.settings);
		    return this;
		};

		this.getElements = function () {
		    return this.settings.context.find("input, select, textarea").not(":submit, :reset, :image, :button, [disabled]").not(this.settings.ignore);
		};

		this.acceptChange = function () {
		    var elems = this.getElements().toArray();
		    this.settings.elements = elems;
		    for (var i = 0, len = elems.length; i < len; i++) {
		        $(elems[i]).attr("data-old-value", $(elems[i]).val());
		    };
		    return this;
		};

		this.isChanged = function () {
		    var elems = this.getElements().toArray();
		    if (!this.settings.elements || elems.length != this.settings.elements.length) {
		    	if (this.settings.debug) {
		    		var e1s = this.settings.elements;
		    		var e2s = elems;
		    		if (e1s.length > e2s.length) {
		    			e1s = elems;
		    			e2s = this.settings.elements;
		    		}
		    		for (var i = 0, len = e1s.length; i < len; i++) {
		    			if (e1s[i] != e2s[i]) {
		    				alert('id=' + $(e1s[i]).attr("id") + ',name=' + $(e1s[i]).attr("name"));
		    				break;
		    			}
		    		}
		    	}
		        return true;
		    }
		    for (var i = 0, len = elems.length; i < len; i++) {
		        if (elems[i] !== this.settings.elements[i])
		            return true;
		        if ($(elems[i]).val() != $(elems[i]).attr("data-old-value"))
		            return true;
		    }
		    return false;
		};
		
		 if (this.settings.initParams)
	         this.init(this.settings.initParams);
		 return this;
	};
	if (!jshelper) 
		jshelper = {};
	jshelper.inputHelper = function(options) {
		return new InputHelper(options);
	};
})();

; (function ($) {
    $.fn.extend({
        "inputHelper": function (options) {
            if (!this.length) {
                alert("inputHelper : nothing selected.");
                return;
            }
            options = options || {};
            options.context = this;
            return jshelper.inputHelper(options);
        }
    });
})(jQuery);
