function login() {
    $.ajax({
        data: {
            'username': $("#username").val(),
            'newpassword': $("#password").val()
        },
        dataType: "json",
        type: "POST",
        url: "/pac/HrResourceController/login.do",
        error: function () {
            window.location = "../common/404.html";
        },
        success: function (json) {
            if (json.data.msg == "登录成功") {
                window.location = "index.html";
            } else
                alert("账号或密码错误！");
        }
    });
}

function addManager() {
    var sex = document.managerform.sex.value;
    if ($("#inpPassword").val() == '' | $("#inpUserName").val() == '') {
        alert("账号密码不能为空！");
        return;
    }
    if ($("#inpPassword").val() == $("#inpPassword2").val()) {

        $.ajax({
            data: {
                'username': $("#inpUserName").val(),
                'user': $("#inpName").val(),
                'newpassword': $("#inpPassword").val(),
                'hrresourcecol1': sex,
                'phone': $("#inpPhone").val()
            },
            dataType: "json",
            type: "POST",
            url: "/pac/HrResourceController/addManager.do",
            error: function () {
                window.location = "../common/404.html";
            },
            success: function (json) {
                if (json.data == true) {
                    alert("添加用户成功！");
                } else if(json.data == false){
                    alert("用户已存在！");
                }else {
                    alert("该账号权限不足！");
                }
            }
        });
    } else {
        alert("两次密码不一样");
    }
}

function upManageMessage(username) {
    if ($("#" + username + "_inpPassword").val() != '' | $("#" + username + "_inpPassword2").val() != '') {
        if ($("#" + username + "_inpPassword").val() != $("#" + username + "_inpPassword2").val()) {
            alert("两次密码不一样");
            return;
        }
    }
    $.ajax({
        data: {
            'username': $("#" + username + "_inpUserName").val(),
            'user': $("#" + username + "_inpName").val(),
            'newpassword': $("#" + username + "_inpPassword").val(),
            'phone': $("#" + username + "_inpPhone").val()
        },
        dataType: "json",
        type: "POST",
        url: "/pac/HrResourceController/updateManagerMessage.do",
        error: function () {
            window.location = "../common/404.html";
        },
        success: function (json) {
            if (json.data == 1) {
                alert("修改用户成功！");
                window.location = "/pac/HrResourceController/showAllManager.do";
            } else if (json.date == -1) {
                alert("新密码不能与旧密码相同！");
            } else {
                alert("权限不足！");
            }
        }
    });
}

function delManager() {
    var managerUsernameList = new Array();
    var managerUsername = document.getElementsByName("inpSellections");
    var flag = false;
    for (var i = 0; i < managerUsername.length; i++) {
        if (managerUsername[i].checked) {
            var manager = new Object();
            var username = managerUsername[i].value;
            manager.username = username;
            managerUsernameList.push(manager);
            flag = true;
        }
    }
    if (!flag) {
        alert("请最少选择一项！");
        return false;
    }
    $
        .ajax({
            data: {
                'managerList': JSON.stringify(managerUsernameList)
            },
            dataType: "json",
            type: "POST",
            url: "/pac/HrResourceController/delManager.do",
            error: function () {
            },
            success: function (json) {
                if (json.data == "-1") {
                    alert("没有权限删除！")
                } else {
                    alert("删除" + json.data + "用户");
                    window.location = "/pac/HrResourceController/showAllManager.do";
                }
            }
        });
}

