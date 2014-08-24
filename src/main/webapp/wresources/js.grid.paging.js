var myui = {};
myui.paging = {};
myui.paging.settings = {
    curPrevPageCnt: 1,
    curNextPageCnt: 2,
    firstPageCnt: 1,
    lastPageCnt: 1,
    pagingCssName: 'paging',
    summaryCssName: 'paging-summary',
    count: 0, /* 总记录数 */
    pageCount: 0, /* 总页数 */
    pageNumber: 1, /* 当前页，1开始 */
    summary: "<span class='data-total-count'>[总数:{count}]</span> [页码:{pageNumber}/{pageCount}]&nbsp;&nbsp;&nbsp;",
    //分页回调函数名称
    changePage: 'changePage' /*参数：页面数，1开始*/
};
myui.paging.createPaging = function (options) {
    options = $.extend([], myui.paging.settings, options);
    var maxCurPrevPageCnt = options.firstPageCnt + options.curPrevPageCnt + 1;
    var maxCurNextPageCnt = options.lastPageCnt + options.curNextPageCnt + 1;
    var maxPagingCnt = maxCurPrevPageCnt + maxCurNextPageCnt + 1;
    var pages = [];
    var index = 1;

    //前和当前
    if (options.pageNumber > maxCurPrevPageCnt + 1 && options.pageCount > maxPagingCnt) {
        while (index <= options.firstPageCnt) {
            pages.push(index);
            index++;
        }
        pages.push("...");
        index = options.pageNumber - options.curPrevPageCnt;
        var index2 = options.pageCount + 1 - (maxPagingCnt - pages.length);
        if (index > index2)
            index = index2;
    }
    while (index <= options.pageNumber) {
        pages.push(index);
        index++;
    }
    //后
    if (options.pageCount - options.pageNumber <= maxCurNextPageCnt
			|| options.pageCount <= maxPagingCnt) {
        while (index <= options.pageCount) {
            pages.push(index);
            index++;
        }
    } else {
        while (pages.length < maxPagingCnt - 2) {
            pages.push(index);
            index++;
        }
        pages.push("...");
        pages.push(options.pageCount);
    }

    var createLink = function (pageNumber, linkText) {
        if (!linkText) linkText = pageNumber;
        return $.format('<a href="javascript:void(0)" onclick="javascript:{0}({1},\'{2}\')">{3}</a>', options.changePage, pageNumber, options.name, linkText);
    };

    var htmls = [];
    if (options.pageNumber == 1)
        htmls.push('<span class="disabled">上一页</span>');
    else
        htmls.push(createLink(options.pageNumber - 1, '上一页'));

    for (var i = 0; i < pages.length; i++) {
        if (pages[i] == "...")
            htmls.push(pages[i]);
        else if (pages[i] == options.pageNumber)
            htmls.push($.format('<span class="current">{0}</span>', pages[i]));
        else
            htmls.push(createLink(pages[i]));
    }

    if (options.pageNumber < options.pageCount)
        htmls.push(createLink(options.pageNumber + 1, '下一页'));
    else
        htmls.push('<span class="disabled">下一页</span>');
    
    return $.format('<div class="{0}">{1}</div>', options.pagingCssName, htmls.join(""));
};
myui.paging.createSummary = function (options) {
	options = $.extend([], myui.paging.settings, options);
	if (!options.summary) {
		return "";
	}
	var summary = options.summary.replace("{count}", options.count);
	summary = summary.replace("{pageCount}", options.pageCount);
	summary = summary.replace("{pageNumber}", options.pageNumber);
	return $.format('<div class="{0}">{1}</div>', options.summaryCssName, summary);
};