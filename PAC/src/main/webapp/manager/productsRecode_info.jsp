<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>丹姿综合管理系统</title>
    <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
    <link rel="stylesheet" href="../manager/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="../manager/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="../manager/dist/css/_all-skins.min.css">
    <link rel="stylesheet"
          href="../manager/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="../manager/plugins/icheck/blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper" ng-app="">
    <header class="main-header">
        <a href="../manager/index.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"></span> <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>丹姿综合管理系统</b></span>
        </a>
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas"
               role="button"> <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu" ng-include="'../manager/header.html'">
            </div>
        </nav>
    </header>
    <aside class="main-sidebar" ng-include="'../manager/aside.html'"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h2></h2>
            <ol class="breadcrumb">
                <li><a href="index.html"><i class="fa fa-dashboard"></i>首页</a></li>
                <li>产品备案信息</li>
                <li>产品备案信息管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header">
                    <h3 class="box-title">产品备案信息列表（点击产品编码会显示详细信息）</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="mailbox-messages">
                        <table id="example1" class="table table-hover">
                            <thead>
                            <tr>
                                <th>
                                    <button class="btn btn-default btn-sm checkbox-toggle">
                                        <i class="fa fa-square-o"></i>
                                    </button>
                                    <button class="btn btn-default btn-sm" onclick="delProRecordMessage()">
                                        <i class="fa fa-trash-o"></i>
                                    </button>
                                </th>
                                <th>产品编码</th>
                                <th>产品名称</th>
                                <th>包材编码</th>
                                <th>包材属性</th>
                                <th>老编码对应</th>
                                <th>系统备案提交时间</th>
                                <th>备案后抽查信息（如国家级、省级）备案状态</th>
                                <th>备案系统链接</th>
                                <th><a href="manager_add.html">
                                    <button
                                            class="btn btn-info btn-sm">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </a></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="mProRecord" items="${requestScope.proRecord}">
                                <tr id="tr1">
                                    <td><input type="checkbox"
                                               name="inpSellections" value="${mProRecord.id }"></td>
                                    <td onmousedown="myShow(${mProRecord.id })">${mProRecord.productcode }</td>
                                    <td onmousedown="myShow(${mProRecord.id })">${mProRecord.productname }</td>
                                    <td onmousedown="myShow(${mProRecord.id })">${mProRecord.packagcoding }</td>
                                    <td onmousedown="myShow(${mProRecord.id })">${mProRecord.packagpro }</td>
                                    <td onmousedown="myShow(${mProRecord.id })">${mProRecord.oldcoding }</td>
                                    <td onmousedown="myShow(${mProRecord.id })">${mProRecord.createTimeStr }</td>
                                    <td>
                                        <c:if test="${mProRecord.alterInfoMessagesList[0].ifstatus eq 1}">通过</c:if>
                                        <c:if test="${mProRecord.alterInfoMessagesList[0].ifstatus eq 2}">不通过</c:if>
                                        <c:if test="${mProRecord.alterInfoMessagesList[0].ifstatus eq 3}">待定</c:if>
                                        <c:if test="${mProRecord.alterInfoMessagesList[0].ifstatus eq 0}"> </c:if>
                                    </td>
                                    <td><a
                                            href="${mProRecord.alterInfoMessagesList[0].bornspace}"
                                            target="_blank">国家备案系统链接</a>
                                    </td>
                                    <td><a
                                            href="/pac/ProRecordMainController/showDetailProRecord.do?id=${mProRecord.id }">编辑</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<c:forEach var="mProRecord" items="${requestScope.proRecord}">
    <!-- ./wrapper -->
    <div data-spy="scroll" class="modal fade" id="${mProRecord.id }" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        产品备案明细 产品编码：${mProRecord.productcode } 包材编码：${mProRecord.packagcoding }
                    </h4>
                </div>
                <div class="modal-body" data-spy="scroll" data-target="#myScrollspy" data-offset="0"
                     style="height:400px;overflow:auto; position: relative;">
                    <table class="table">
                        <caption>系统备案信息</caption>
                        <thead>
                        <tr>
                            <th>生产地</th>
                            <th>备案状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sysMessage" items="${mProRecord.sysMessageList}">
                            <tr>
                                <td>${sysMessage.bornspace}</td>
                                <td>
                                    <c:if test="${sysMessage.ifstatus eq 1}">通过</c:if>
                                    <c:if test="${sysMessage.ifstatus eq 2}">不通过</c:if>
                                    <c:if test="${sysMessage.ifstatus eq 3}">待定</c:if>
                                    <c:if test="${sysMessage.ifstatus eq 0}"> </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="table">
                        <caption>备案地面审查信息（3个月内送现场审查）</caption>
                        <thead>
                        <tr>
                            <th>生产地</th>
                            <th>审查状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="groudMessage" items="${mProRecord.groundInfoMessageList}">
                            <tr>
                                <td>${groudMessage.bornspace}</td>
                                <td>
                                    <c:if test="${groudMessage.ifstatus eq 1}">通过</c:if>
                                    <c:if test="${groudMessage.ifstatus eq 2}">不通过</c:if>
                                    <c:if test="${groudMessage.ifstatus eq 3}">待定</c:if>
                                    <c:if test="${groudMessage.ifstatus eq 0}"> </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default active" data-dismiss="modal">关闭
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</c:forEach>
<!-- REQUIRED JS SCRIPTS -->
<script src="../manager/dist/js/angular.min.js"></script>
<!-- jQuery 2.1.4 -->
<script src="../manager/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="../manager/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="../manager/dist/js/app.min.js"></script>
<!-- DataTables -->
<script src="../manager/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../manager/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- iCheck -->
<script src="../manager/plugins/icheck/icheck.min.js"></script>
<!-- pac.pro.js -->
<script src="../manager/dist/js/pac.pro.1.0.js" charset="utf-8"></script>
<!-- page script -->
<script>
    window.onload = function () {
        $("#ul-one-li-one").attr('class', 'a active');
        $("#ul-one-li-one").addClass('active1');
    }


    function myShow(id) {
        $('#' + id).modal('show');
//        alert("in");
//        $(".tr2").show();
    }
    function normalImg() {
//        $('#myModal').modal('hide')
//        alert("out");
    }
    $(function () {
        $("#example1").DataTable({
            "aoColumnDefs": [{
                "bSortable": false,
                "aTargets": [0, 3, 4, 5, 7]
            }],
            language: {
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第_START_至_END_项结果，共_TOTAL_项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "（由_MAX_项结果筛选）",
                "sSearch": "搜索：",
                "sEmptyTable": "表中数据为空",
                "oPaginate": {
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }
            }
        });

        //iCheck for checkbox and radio inputs
        $('.mailbox-messages input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $(".checkbox-toggle").click(
                function () {
                    var clicks = $(this).data('clicks');
                    if (clicks) {
                        //Uncheck all checkboxes
                        $(".mailbox-messages input[type='checkbox']")
                                .iCheck("uncheck");
                        $(".fa", this).removeClass("fa-check-square-o")
                                .addClass('fa-square-o');
                    } else {
                        //Check all checkboxes
                        $(".mailbox-messages input[type='checkbox']")
                                .iCheck("check");
                        $(".fa", this).removeClass("fa-square-o").addClass(
                                'fa-check-square-o');
                    }
                    $(this).data("clicks", !clicks);
                });
    });
    //		function delAdmins() {
    //			var adminList = new Array();
    //			var adminid = document.getElementsByName("id");
    //			var flag = false;
    //			for (var i = 0; i < adminid.length; i++) {
    //				if (adminid[i].checked) {
    //					var admin = new Object();
    //					var id = adminid[i].value;
    //					admin.id = id;
    //					adminList.push(admin);
    //					flag = true;
    //				}
    //			}
    //			if (!flag) {
    //				alert("请最少选择一项！");
    //				return false;
    //			}
    //			$
    //					.ajax({
    //						data : {
    //							'adminlist' : JSON.stringify(adminList)
    //						},
    //						dataType : "json",
    //						type : "POST",
    //						url : "/ourmall/adminController/delAdmins.do",
    //						error : function() {
    //						},
    //						success : function(json) {
    //							alert("删除" + json.data + "用户");
    //							window.location = "/ourmall/adminController/listAdmin.do";
    //						}
    //					})
    //
    //		}
</script>
<style>
    td {
        text-align: center;
    }

    th {
        text-align: center;
    }
</style>
</body>
</html>
