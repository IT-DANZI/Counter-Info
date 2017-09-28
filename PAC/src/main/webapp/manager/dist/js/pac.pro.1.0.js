function check() {
    var groundInfoMessList = new Array();
    var alterInfoMessList = new Array();
    var sysMessList = new Array();

    var inpone = document.getElementsByName("inpone");
    var inptwo = document.getElementsByName("inptwo");
    var inpthree = document.getElementsByName("inpthree");
    var selone = document.getElementsByName("selone");
    var seltwo = document.getElementsByName("seltwo");
    var selthree = document.getElementsByName("selthree");
    var inponeval = document.getElementsByName("inponeval");
    var inptwoval = document.getElementsByName("inptwoval");
    var inpthreeval = document.getElementsByName("inpthreeval");

    var flag = false;
    for (var i = 0; i < inpone.length; i++) {
        var bornspace = inponeval[i].value;
        if (bornspace != "") {
            var groundInfoMess = new Object();
            var ifstatus = selone[i].value;
            groundInfoMess.bornspace = bornspace;
            groundInfoMess.ifstatus = ifstatus;
            groundInfoMess.id = -1;
            groundInfoMessList.push(groundInfoMess);
        }
    }
    for (var i = 0; i < inptwo.length; i++) {
        var bornspace = inptwoval[i].value;
        if (bornspace != "") {
            var sysMess = new Object();
            var ifstatus = seltwo[i].value;
            sysMess.bornspace = bornspace;
            sysMess.ifstatus = ifstatus;
            sysMess.id = -1;
            sysMessList.push(sysMess);
        }
    }
    for (var i = 0; i < inpthree.length; i++) {
        var bornspace = inpthreeval[i].value;
        if (bornspace != "") {
            var alterinfomess = new Object();
            var ifstatus = selthree[i].value;
            alterinfomess.bornspace = bornspace;
            alterinfomess.ifstatus = ifstatus;
            alterinfomess.id = -1;
            alterInfoMessList.push(alterinfomess);
        }
    }

    $
        .ajax({
            data: {
                'groundInfoMessList': JSON.stringify(groundInfoMessList),
                'alterInfoMessList': JSON.stringify(alterInfoMessList),
                'sysMessList': JSON.stringify(sysMessList),
                'productcode': $("#inpProductsCode").val(),
                'productname': $("#inpProductsName").val(),
                'packagcoding': $("#inpBomCode").val(),
                'packagpro': $("#inpBomdPro").val(),
                'oldcoding': $("#inpOldBomCode").val()
            },
            dataType: "json",
            type: "POST",
            url: "/pac/ProRecordMainController/addProRecord.do",
            error: function () {
            },
            success: function (json) {

                if(json.data == "-1" ){
                    alert("该产品编码以及包材编码已经存在！");
                }else {
                    alert("添加成功！");
                }
            }
        });
}

function update() {
    var groundInfoMessList = new Array();
    var alterInfoMessList = new Array();
    var sysMessList = new Array();

    var inpone = document.getElementsByName("inpone");
    var inptwo = document.getElementsByName("inptwo");
    var inpthree = document.getElementsByName("inpthree");
    var selone = document.getElementsByName("selone");
    var seltwo = document.getElementsByName("seltwo");
    var selthree = document.getElementsByName("selthree");
    var inponeval = document.getElementsByName("inponeval");
    var inptwoval = document.getElementsByName("inptwoval");
    var inpthreeval = document.getElementsByName("inpthreeval");
    var seloneisNull = document.getElementsByName("inponeisnull");
    var seltwoisNull = document.getElementsByName("inptwoisnull");
    var selthreeisNull = document.getElementsByName("inpthreeisnull");

    var flag = false;
    for (var i = 0; i < inpone.length; i++) {
        var bornspace = inponeval[i].value;
        if (bornspace != "") {
            var groundInfoMess = new Object();
            var ifstatus = selone[i].value;
            var id ;
            if(seloneisNull[i].value == ''){
                id=-1;
            }else{
                id = seloneisNull[i].value;
            }
            groundInfoMess.bornspace = bornspace;
            groundInfoMess.ifstatus = ifstatus;
            groundInfoMess.id = id;
            groundInfoMessList.push(groundInfoMess);
        }
    }
    for (var i = 0; i < inptwo.length; i++) {
        var bornspace = inptwoval[i].value;
        if (bornspace != "") {
            var sysMess = new Object();
            var ifstatus = seltwo[i].value;
            var id ;
            if(seltwoisNull[i].value == ''){
                id=-1;
            }else{
                id = seltwoisNull[i].value;
            }
            sysMess.bornspace = bornspace;
            sysMess.ifstatus = ifstatus;
            sysMess.id = id;
            sysMessList.push(sysMess);
        }
    }
    for (var i = 0; i < inpthree.length; i++) {
        var bornspace = inpthreeval[i].value;
        if (bornspace != "") {
            var alterinfomess = new Object();
            var ifstatus = selthree[i].value;
            var id ;
            if(selthreeisNull[i].value == ''){
                id=-1;
            }else{
                id = selthreeisNull[i].value;
            }
            alterinfomess.bornspace = bornspace;
            alterinfomess.ifstatus = ifstatus;
            alterinfomess.id = id;
            alterInfoMessList.push(alterinfomess);
        }
    }
    $
        .ajax({
            data: {
                'groundInfoMessList': JSON.stringify(groundInfoMessList),
                'alterInfoMessList': JSON.stringify(alterInfoMessList),
                'sysMessList': JSON.stringify(sysMessList),
                'productcode': $("#inpProductsCode").val(),
                'productname': $("#inpProductsName").val(),
                'packagcoding': $("#inpBomCode").val(),
                'id': $("#inpBomdID").val(),
                'packagpro': $("#inpBomdPro").val(),
                'oldcoding': $("#inpOldBomCode").val()
            },
            dataType: "json",
            type: "POST",
            url: "/pac/ProRecordMainController/updateProRecordMessage.do",
            error: function () {
                window.location = "../login.html";
            },
            success: function (json) {
                if(json.data == true){
                    alert("修改信息成功!");
                }else {
                    window.location = "../login.html";
                }
            }
        });
}

function delmoneMessage(id) {
    if(id==""){
        return;
    }
    $
        .ajax({
            data: {
                'id': id
            },
            dataType: "json",
            type: "POST",
            url: "/pac/ProRecordMainController/delGroundMessage.do",
            error: function () {
                window.location = "../login.html";
            },
            success: function (json) {
                if(json.data == "1" ){
                    alert("删除地面备案信息成功！");
                }
            }
        });
}
function delmtwoMessage(id) {
    if(id==""){
        return;
    }
    $
        .ajax({
            data: {
                'id': id
            },
            dataType: "json",
            type: "POST",
            url: "/pac/ProRecordMainController/delSystemMessage.do",
            error: function () {
                window.location = "../login.html";
            },
            success: function (json) {
                if(json.data == "1" ){
                    alert("删除系统备案信息成功！");
                }
            }
        });
}
function delmthreeMessage(id) {
    if(id==""){
        return;
    }
    $
        .ajax({
            data: {
                'id': id
            },
            dataType: "json",
            type: "POST",
            url: "/pac/ProRecordMainController/delAlterMessage.do",
            error: function () {
                window.location = "../login.html";
            },
            success: function (json) {
                if(json.data == "1" ){
                    alert("删除备案后审查信息成功！");
                }
            }
        });
}


function delProRecordMessage() {
    var proRecordMessageList = new Array();
    var proRecordMessage = document.getElementsByName("inpSellections");
    var flag = false;
    for (var i = 0; i < proRecordMessage.length; i++) {
        if (proRecordMessage[i].checked) {
            var proRecordMessageOBJ = new Object();
            var id = proRecordMessage[i].value;
            proRecordMessageOBJ.id = id;
            proRecordMessageList.push(proRecordMessageOBJ);
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
                'proRecordMessageList': JSON.stringify(proRecordMessageList)
            },
            dataType: "json",
            type: "POST",
            url: "/pac/ProRecordMainController/delProRecordMessage.do",
            error: function () {
            },
            success: function (json) {
                if (json.data == "-1") {
                    alert("没有权限删除！")
                } else {
                    alert("删除成功！");
                    window.location = "/pac/ProRecordMainController/showProRecord.do";
                }
            }
        });
}

