_jsGridActions = {
	"edit": { title: "编辑" },
    "delete": { title: "删除"},
    "shangbao": { title: "上报申报材料，上报后不能再编辑"},
    "vote" : { title: "审核", image:"audit.gif"},
    "audit-org" : { title: "审核", image:"audit.gif"},
    "audit1" : { title: "形审", image:"audit.gif"},
    "audit2" : { title: "复审", image:"audit.gif"},
    "suborg" : { title: "下级机构"},
    "apply-unit": { title: "申报单位"},
    "edit-apply-user": { title: "修改申报人个人账号"},
    "print": { title: "导出", image: "pdf.gif"},
    "pdf": { title: "导出", image: "pdf.gif"},
    "print-audit": { title: "导出", image: "pdf.gif"},
    "review-setting": { title: "评审设置"},
    "review-result": { title: "网评结果", image:"review.gif"},
    "recover-wait-review2": { title: "取回", image:"recover.gif"},
    "recover-wait-xingshen": { title: "取回", image:"recover.gif"},
    "edit-apply": { title: "编辑", image:"edit.gif" },
    "runas": { title: "以该用户身份运行" },
    "edit-zdgj-applicant": {title: "编辑该专家重点关注的申报人"},
    "detail": { title: "详情" },
    "delete-applicant":{title:"删除", image:"delete.gif"},
    "copy-applicant": { title: "复制申报人信息到当前批次" },
    "clear-score": { title: "清除专家评分及点评" },
    "recover-expert-reviewing" : {title: "撤回到‘评审中’状态", image:"recover.gif"},
    "expert-mark" : { text: "评分" },
    "expert-review" : { text: "点评" },
    "audit3-reject" : { title: "网评不通过" , image:"reject.gif" },
    "audit3-approved" : { title: "网评通过" , image:"approve.gif" },
    "applicant-export-expert" : { title: "导出申报人到专家库", image:"export2expert.gif"  },
    "remove-applicant":{title:"移去申报人", image:"remove.gif"},
    "remove-expert":{title:"移去评审专家", image:"remove.gif"},
    "detail-score":{title: "评分详情", image: "score.gif" },
    "recover-group-reviewing":{title: "撤回到‘评审中’状态", image:"recover.gif"},
    "edit-expert-user":{title: "修改评审专家账号", image:"edit-apply-user.gif"},
    "expert-lock":{title: "锁定专家信息", image:"unlock.gif"},
    "expert-unlock":{title: "解锁专家信息", image:"lock.gif"},
    "recover-wait-wangping": { title: "取回", image:"recover.gif"},
    "apply-gotounit": { title: "跳转到该单位", image:"go-into.png"},
    "finalReview" : { title: "最终评审",image:"detail.gif" },
    "finalReview-approved": { title: "评审通过", image:"approve.gif"},
    "finalReview-reject": { title: "评审不通过" , image:"reject.gif"},
    "deleteToRecycleBin":{title:"删除", image:"delete.gif"},
    "deleteFromBase":{title:"彻底删除", image:"delete.gif"},
    "rejectNotify" : { text: "取消"},
    "resendNotify" : { text: "重发"},
    "apply-verification" : { text: "查证" }
};

function gotoUnitSignaturePage(url) {
	if (!window.canCloseWindow || canCloseWindow()) {
		window.close();
		try {
			window.opener.location = url;
			window.opener.focus();
		} catch (e) {
			window.open(url);
		}
	}
}