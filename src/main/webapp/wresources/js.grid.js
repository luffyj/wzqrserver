function getGridByGridName(gridName) {
    if (typeof (gridName) == "string") {
        var $grid = $('#' + gridName + "ListTable");
        if ($grid.length == 0)
            $grid = $('#' + gridName);
        return $grid;
    }
    return gridName;
}
// gridSelectAll(表格名称)
function gridSelectAll(gridName) {
    $grid = getGridByGridName(gridName);
    var $chks = $grid.find("tbody").find("td.selected").find(":checkbox");
    if ($chks.filter(":checked").length == $chks.length) {
        $chks.each(function () {
            $(this).attr("checked", false);
        });
    } else {
        $chks.each(function () {
            $(this).attr("checked", true);
        });
    }
}

function getGridFunc(gridName, funcName) {
    var grid = window['grid-' + gridName];
    if (!grid)
        return null;
    if ($.isFunction(grid[funcName]))
        return grid[funcName];
    else
        return null;
}
//gridChangePage(页码,表格名称)
function gridChangePage(pageNumber, gridName) {
    var func = getGridFunc(gridName, 'changePage');
    if (func != null)
        func(pageNumber);
    else
        alert($.format("table({0}) changePage {1}", gridName, pageNumber));
}
// gridSortBy(列名,升序降序,表格名称)
function gridSortBy(columnName, isAsc, gridName) {
    var func = getGridFunc(gridName, 'sortBy');
    if (func != null)
        func(columnName, isAsc);
    else
        alert($.format("table({0}) sort by {1},{2}", gridName, columnName,
				isAsc ? "asc" : "desc"));
}
// gridRowClick(动作,键值,表格名称,对象)
function gridRowClick(action, key, gridName, sender) {
    var func = getGridFunc(gridName, 'rowClick');
    if (func != null)
        func(action, key, sender);
    else
        alert($.format("table({0}) row key({1}) action({2})", gridName, key, action));
}

// gridTable
;
(function ($) {
    var _helper = {
    	usedImageActions: {},
        createSelectableTd: function (gridName, key) {
            if (typeof (key) === "undefined") {
                var func = $.format("javascript:gridSelectAll('{0}');return false;", gridName);
                return $.format("<td class='selected'><a href='javascript:void(0)' onclick=\"{0}\" title='全选/反选'><div class='select_all'></div></td>",
								func);
            } else {
                return $.format("<td class='selected'><input name='{0}Key' type='checkbox' value='{1}'/></td>", gridName, key);
            }
        },
        createNoTd: function (rowIndex) {
            if (typeof (rowIndex) === "undefined")
                return "<td class='no'>序</td>";
            else
                return $.format("<td class='no'>{0}</td>", rowIndex + 1);
        },
        createOrder: function (gridName, columnText, orderInfo) {
            var ss = orderInfo.split(",");
            var currentIsAsc = (ss.length === 2) && (ss[1] === "asc");
            var clsAscDesc = "";
            if (ss.length == 2) {
                clsAscDesc = currentIsAsc ? "asc" : "desc";
            }
            var func = $.format("javascript:gridSortBy('{0}',{1},'{2}');return false;",
					ss[0], currentIsAsc ? "false" : "true", gridName);
            $a = $($.format("<a href='javascript:void(0)' title='排序' onclick=\"{0}\"></a>",
							func));
            $a.append($("<div class='sortable " + clsAscDesc + "'></div>")
					.text(columnText));
            return $a;
        },
        createDetailLink: function (text, key, gridName) {
            var func = $.format("javascript:gridRowClick('detail','{0}','{1}',this);return false;",
							key, gridName);
            return $($.format("<a href='javascript:void(0)' title='详情' onclick=\"{0}\"></a>", func)).text(text);
        },
        createAction: function (actions, key, gridName) {
        	var $div = $("<div>").addClass("action");
            for (var i = 0, len = actions.length; i < len; i++) {
            	var act = _jsGridActions[actions[i]];
                var func = $.format("javascript:gridRowClick('{0}','{1}','{2}',this);return false;", actions[i], key, gridName);
                var title = act ? act.title : actions[i];
                var text = act ? act.text : null;
                var imageName = (act && act.image) ? act.image : actions[i] + ".gif";
                var aInnerHtml = "";
                if (text) {
                	aInnerHtml = text;
                } else {
                	_helper.usedImageActions[imageName] = title;
                	aInnerHtml = "<img title='" + title + "' " + " src=\"" + baseUrl + "images/actions/" + imageName + "\"/>";
                }
                $div.append((i > 0 ? "&nbsp;" : "") + "<a href='javascript:void(0)' onclick=\"" + func + (title ? " title='" + title + "' " : '') + "\">" + aInnerHtml + "</a>");
            }
            return $div;
        },
        createPaging: function (totalCnt, pageCnt, pageIndex, gridName) {
            return myui.paging.createPaging({
                name: gridName,
                count: totalCnt,
                pageCount: pageCnt,
                pageNumber: pageIndex,
                changePage: 'gridChangePage'
            });
        },
        createPagingSummary: function (totalCnt, pageCnt, pageIndex, gridName) {
            return myui.paging.createSummary({
                name: gridName,
                count: totalCnt,
                pageCount: pageCnt,
                pageNumber: pageIndex,
                changePage: 'gridChangePage'
            });
        }
    };

    $.fn.extend({
        "gridTable": function (gridName, options) {
            options = $.extend({
                chkCol: true,
                numCol: false,
                legendVisible: true,
                //legendContainer: div.legend-container,
                //currentRowClass: 'current_row',
                debug: false
            }, options);
            delete options.currentRow;
            var colCnt = 0, $tbl = $(this);
            
            _helper.usedImageActions = {};

            $tbl.find("thead").find("tr").each(
					function () {
						if (options.numCol) {
					        $(this).prepend(_helper.createNoTd());
					    }
					    if (options.chkCol) {
					        $(this).prepend(
									_helper.createSelectableTd(gridName));
					    }					   
					    $(this).find("td")
								.each(function () {
								    var orderInfo = $(this).attr(
													"data-order");
								    if (orderInfo) {
								        var columnText = $.trim($(this)
														.text());
								        $(this).empty().append(
														_helper.createOrder(
																gridName,
																columnText,
																orderInfo));
								    }
								    $(this).attr("nowrap", "nowrap");
								});
					    colCnt = $(this).find("td").length;					    
					});
            $tbl.find("tbody").find("tr").each(
					function (i, n) {
					    var key = $(this).attr("data-key");
					    if (key == options.currentRowDataKey) {
					        options.currentRow = $(this);
					        options.currentRow.addClass(options.currentRowClass);
					        delete options.currentRowDataKey;
					    }
					    
					    if (options.numCol) {
					        $(this).prepend(_helper.createNoTd(i));
					    }
					    if (options.chkCol) {
					        $(this).prepend(
									_helper.createSelectableTd(gridName, key));
					    }
					   
					    if (i % 2 == 1)
					        $(this).addClass("odd");
					    $(this).find("td").each(
								function () {
								    if ($(this).hasClass("action")) {
								        var ss = $.trim($(this).text()).split(",");
								        var actions = [];
								        for (var i = 0, len = ss.length; i < len; i++) {
								        	var actionName = $.trim(ss[i]);
								        	if (actionName) {
								            	actions.push(actionName);
								        	}
								        }
								        if (actions.length > 0) {
								            $(this).empty().append(_helper.createAction(actions, key, gridName));
								            $(this).attr("nowrap", "nowrap");
								        } else {
								        	$(this).empty();
								        }
								    } else if ($(this).hasClass("detail")) {
								        var text = $.trim($(this).text());
								        $(this).empty().append(
												_helper.createDetailLink(text,
														key, gridName));
								    }
								});					    
					});
            $tbl.find("tfoot").find("tr").each(
					function (i, n) {
					    $td = $(this).find("td");
					    if ($td.length == 1)
					        $td.attr("colspan", colCnt);
					    $container = $td.find(".paging_container");
					    var pagingInfo = $.trim($container.text());
					    if (pagingInfo) {
					        var ss = pagingInfo.split(",");
					        if (ss.length == 3) {
					        	$container.empty();
					        	$container.append(
										_helper.createPagingSummary(parseInt(ss[0]),
												parseInt(ss[1]),
												parseInt(ss[2]), gridName));
					            $container.append(
										_helper.createPaging(parseInt(ss[0]),
												parseInt(ss[1]),
												parseInt(ss[2]), gridName));
					        } else
					            alert("paging is invalid:" + paging);
					    }
					});
            if (options.debug) {
                var $log = $("#log");
                if ($log.length == 1)
                    $log.remove();
                $("<textarea id='log' cols=100 rows=30></textarea>").val(
						$(this).html()).appendTo("body");
            }
            if (options.currentRowClass) {
            	$tbl.find("tbody>tr").bind('click', function () {
                    if (options.currentRow)
                        options.currentRow.removeClass(options.currentRowClass);
                    options.currentRow = $(this);
                    options.currentRow.addClass(options.currentRowClass);
                });
            }
            
            //legend
            if (options.legendVisible) {
		    	var htmls = [], $container;
		    	for(var imageName in _helper.usedImageActions) {
		    		var title = _helper.usedImageActions[imageName];
		    		htmls.push('<img src=\"' + baseUrl + 'images/actions/' + imageName + '\"/> <span>' + title + '</span>');
		    	}		    	
		    	if (options.legendContainer) {
		    		$container = $(options.legendContainer);
		    	} else {
		    		$container = $tbl.parent().children('.action-legend');
		    		if (!$container.length) {
		    			$container = $('<div class="action-legend"></div>').appendTo($tbl.parent());
		    		}
		    	}
		    	$container.empty();
		    	if (htmls.length) {
		    		$container.html("操作图标说明：" + htmls.join('&nbsp;&nbsp;&nbsp;&nbsp;'));
		    	}
            }
            
            return options;
        }
    });
})(jQuery);

// buildListPage
function buildListPage(name, title, options) {
    options = $.extend({
        searchForm: $.format("#{0}SearchForm", name),
        listHeader: $.format("#{0}ListHeader", name),
        listTable: $.format("#{0}ListTable", name),
        searchUrl: null,
        debug: false
    }, options);
    options.name = name;
    options.title = title;
    delete options.currentRow;

    var $seach = $(options.searchForm);
    options.searchForm = $seach.length == 1 ? $seach : null;
    var $list = $(options.listTable);
    options.listTable = $list.length == 1 ? $list : null;
    var $head = $(options.listHeader);
    options.listHeader = $head.length == 1 ? $head : null;

    if (!options.listTable) {
        alert("not found list table");
        return;
    }
    
    //2013年7月28日
    var showTotalAboveSearchBtn = function() {};
    if (options.searchForm && $('.search-data-total-count').length) {
    	showTotalAboveSearchBtn = function() {
        	if (options.searchForm) {
        		var totalText = options.listTable.find('.data-total-count').text();
        		$('.search-data-total-count').text(totalText);
        	}
        }
    	
        var oldAfterSearch = options.afterSearch;        
        options.afterSearch = function() {
        	if (oldAfterSearch) {
        		oldAfterSearch();
        	}
        	showTotalAboveSearchBtn();
        };
    }

    options.setListHeaderPaging = function () {
        if (options.listHeader) {
        	var $hp = $(".paging_container", options.listHeader);
        	if ($hp.length == 0) {
        		$hp = $("<div class='paging_container'></div>");
        		options.listHeader.prepend($hp);
        	}
        	$hp.html($(".paging_container", options.listTable).html());
        }
    };

    options.setResultHtml = function (html) {
        var $grid = $(html);
        options.listTable.replaceWith($grid);
        options.listTable = $grid;
        options.listTable.gridTable(options.name, options.listOptions);
        options.setListHeaderPaging();
    };

    options.listOptions = options.listTable.gridTable(name, options.listOptions);
    options.setListHeaderPaging();   

    if (options.searchForm) {
    	options.saveSearchCond = function() {
        	options.cond = {};
            var fields = options.searchForm.formToArray();
            for (var i = 0; i < fields.length; i++)
                options.cond[fields[i].name] = fields[i].value;
        };
        
        $(".btn-search", options.searchForm).click(function (event) {
        	event.preventDefault();
        	if (options.beforeSearch && !options.beforeSearch(options.searchForm))
        		return false;
            ajaxSubmitForm(options.searchForm, function (result) {
                if (result.success) {
                    options.setResultHtml(result.html);
                    // 保存检索条件
                    options.saveSearchCond();
                    if (options.listLoad)
                    	options.listLoad(options);
                    if (options.afterSearch){
                    	options.afterSearch();
                    }
                } else
                    alert($.format("{0}查询失败！", options.title));
            }, "myhtml");
        });
        options.searchUrl = options.searchForm.attr("action");
        options.saveSearchCond();
        showTotalAboveSearchBtn();
    }

    options.research = function (data, afterResponse) {
        ajaxPost(options.searchUrl, data, function (result) {
            if (result.success) {
                options.setResultHtml(result.html);
                // 保存检索条件
                options.cond = data;
                if (options.listLoad)
                	options.listLoad(options);
                if ($.isFunction(afterResponse))
                    afterResponse(result);
                showTotalAboveSearchBtn();
            } else {
                alert($.format("{0}查询失败！", options.title));
                if ($.isFunction(afterResponse))
                    afterResponse(result);
            }
        }, "myhtml");
    };

    window["grid-" + name] = {
        changePage: function (page) {
            var data = $.extend({}, options.cond);
            data["cond.page.pageIndex"] = page - 1;
            options.research(data);
        },
        sortBy: function (columnName, isAsc) {
            var data = $.extend({}, options.cond);
            data["cond.orderByClause"] = columnName + ' ' + (isAsc ? 'asc' : 'desc');
            data["cond.page.pageIndex"] = 0;
            options.research(data);
        },
        research: function(queryCond, afterResponse) {
        	var data = $.extend({}, queryCond);
            data["cond.page.pageIndex"] = 0;
            options.research(data, afterResponse);
        },
        refresh: function (url, afterResponse, params) {
            if (options.listOptions.currentRow) {
                options.listOptions.currentRowDataKey = options.listOptions.currentRow.attr("data-key");
            }
            if (url) {
                options.searchUrl = url;
                return options.research(params, afterResponse);
            } else {
                return options.research(options.cond, afterResponse);
            }
        },
        getSelectedKeys: function () {
            var keys = [];
            options.listTable.find("td.selected").find(":checked").each(function () {
                keys.push($(this).val());
            });
            return keys;
        },
        getQuery: function() { return $.extend({}, options.cond); }        
    };
    return window["grid-" + name];
}