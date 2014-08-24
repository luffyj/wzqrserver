//tableRowEditor
; (function ($) {
    $.fn.extend({
        "listInput": function (options) {
            options = $.extend({}, {
                initialRowCnt: 1,
                maxRowCnt: 10
            }, options);
            var $tbl = $(this);
            var $currentRow = null;
            $tbl.find("tbody>tr").live('click', function () {
                if ($currentRow != null)
                    $currentRow.removeClass("current_row");
                $currentRow = $(this);
                $currentRow.addClass("current_row");
            });
            var resetInputName = function (startRowIndex) {
                var $trs = $tbl.find("tbody>tr");
                var $testInput = $("<input type='hidden' name='_$_old_name'>").appendTo("body").attr("name", "_$_new_name");
                var canChangeName = $testInput.wrap("<div>").parent().html().indexOf('_$_new_name') > 0;
                $testInput.parent().remove();
                for (var i = startRowIndex, len = $trs.length; i < len; i++) {                	
                    var $inputs = $($trs[i]).find("input[name],select[name],textarea[name]");
                    $inputs.each(function () {
                        var oldName = $(this).attr("name");
                        var newName = oldName;
                        var dotIndex = newName.lastIndexOf(".");
                        var bracketIndex = newName.lastIndexOf("[");                        
                        if (bracketIndex > dotIndex) {
                            newName = newName.substring(0, bracketIndex);
                        }
                        if (dotIndex < 0) { //newName
                            newName = newName + "[" + i + "]";
                        } else {
                            var prefix = newName.substring(0, dotIndex);
                            if (prefix.charAt(prefix.length - 1) == ']') {
                                bracketIndex = prefix.lastIndexOf("[");
                                prefix = prefix.substring(0, bracketIndex);
                            }
                            newName = prefix + "[" + i + "]" + newName.substr(dotIndex);
                        }
                        
                        if (oldName != newName) {
	                        if (canChangeName)
	                        	$(this).attr("name", newName);
	                        else {
	                        	$p = $(this).wrap("<div>").parent();
	                        	$p.html($p.html().replace(oldName, newName));
	                        	$p.children(":first").unwrap();
	                        }
                        }
                    });
                }
            };
            var cleanRow = function (row) {
                row.removeClass("current_row").find("input").val("").removeAttr("checked");
                row.find("textarea").text('');
                row.find("select").each(function() {
                	$(this).find("option[selected]").removeAttr("selected");
                	var html = $(this).parent().html();
                	if (html.indexOf("selected=\"\"") > 0) {
                		$(this).parent().html(html.replace("selected=\"\"", ""));
                	}
                });
            };
            var afterRowAdded = function (row, isResetName) {
                if (isResetName)
                    resetInputName($tbl.find("tbody>tr").index(row));
                $tbl.find("tbody>tr").removeClass("odd").filter(":odd").addClass("odd");
            };
            var afterRowDeleted = function (rowIndex, isResetName) {
                if (isResetName)
                    resetInputName(rowIndex);
                $tbl.find("tbody>tr").removeClass("odd").filter(":odd").addClass("odd");
            };

            $tbl.find("tbody").find("input,textarea").removeAttr("id");
            var clonedRow = $tbl.find("tbody>tr:first").clone(false);
            cleanRow(clonedRow, false);

            var addRow = function (isResetName) {
                var $rows = $tbl.find("tbody>tr");
                if ($rows.length >= options.maxRowCnt) {
                    alert("已达到最大行数限制！");
                    return null;
                }
                var $row = clonedRow.clone(false).insertAfter($rows.filter("tbody>tr:last"));
                afterRowAdded($row, isResetName);
                return $row;
            };

            var rowCnt = $tbl.find("tbody>tr").length;
            while (rowCnt < options.initialRowCnt) {
                rowCnt++;
                addRow(false);
            }
            resetInputName(0);

            $tbl.find("tbody>tr").filter(":odd").addClass("odd");

            return {
                addRow: function (isResetName) {
                    return addRow(isResetName);
                },
                insertRow: function (isResetName) {
                    if ($currentRow == null) {
                        alert("请指定插入行的位置。");
                        return;
                    }
                    if ($tbl.find("tbody>tr").length >= options.maxRowCnt) {
                        alert("已达到最大行数限制！");
                        return;
                    }
                    var $row = clonedRow.clone(false).insertBefore($currentRow);
                    afterRowAdded($row, isResetName);
                    return $row;
                },
                deleteRow: function (isResetName) {
                    if ($currentRow == null) {
                        alert("请选择要删除的行。");
                        return;
                    }
                    if ($tbl.find("tbody>tr").length == 1) {
                        alert("不能删除唯一的行。");
                        return;
                    }
                    var index = $tbl.find("tbody>tr").index($currentRow);
                    $currentRow.remove();
                    $currentRow = null;
                    afterRowDeleted(index, isResetName);
                    return index;
                },
                resetInputName: function () {
                    resetInputName(0);
                    return this;
                },
                rowCount: function () {
                    return $tbl.find("tbody>tr").length;
                }
            };
        }
    });
})(jQuery);