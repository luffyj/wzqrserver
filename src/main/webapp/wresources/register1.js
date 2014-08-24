$(function() {

    $("#btnOrgSave").click(function() {
//        var areaAddress = $.trim($(".city").val());
//        if ($.trim($(".district").val()) != "") {
//            areaAddress = areaAddress + "/" + $.trim($(".district").val())
//        }
//
//        $("#org_area").val(areaAddress);

        if ($("#org_resetpwd").val() != "" && $("#org_password").val() != "" && $("#org_password").val() != $("#org_resetpwd").val()) {
            alert("两次密码输入不一致。")
            return;
        }

        ajaxSubmitForm("#editForm", function(result) {
            if (result.data) {
                var data = result.data;
                var msg = data.originalMessage;
                if (data.code === 200) {
                    alert(msg);
                    location.href = "loginPage";
                } else {
                    alert(msg);
                    $(function() {
                        setTimeout(function() {
                            $.unblockUI();
                        }, 3000);
                    });
                }
                return;
            }
            if (result.success) {
                alert("已将注册信息提交给管理员进行审核，审核结果会以短信或邮件的形式通知您，请耐心等候！")
                location.href = "loginPage";
            } else {
                alert(result.message);
            }
        });
    });
    $("#org_introduction").attr("maxlength", "2000");
    _inputHelper = $("#editForm").inputHelper().init(["required", "date"]).acceptChange();
//    $(this).zjAddressSelector({originalAddress: $("#org_area")});
});