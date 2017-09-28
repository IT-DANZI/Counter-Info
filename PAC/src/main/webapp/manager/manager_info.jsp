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
                <li>系统管理员管理</li>
                <li>管理员信息管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header">
                    <h3 class="box-title">管理员信息列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="mailbox-messages">
                        <table id="danzitbone" class="table table-hover">
                            <thead>
                            <tr>
                                <th>
                                    <button class="btn btn-default btn-sm checkbox-toggle">
                                        <i class="fa fa-square-o"></i>
                                    </button>
                                    <button class="btn btn-default btn-sm" onclick="delManager()">
                                        <i class="fa fa-trash-o"></i>
                                    </button>
                                </th>
                                <th>ID</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>电话</th>
                                <th>创建日期</th>
                                <th><a href="../manager/manager_add.html">
                                    <button
                                            class="btn btn-info btn-sm">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </a></th>
                            </tr>
                            </thead>
                            <tbody id="tbd">
                            <c:forEach var="mltResource"
                                       items="${requestScope.mltResource}">
                                <tr>
                                    <td><input type="checkbox"
                                               name="inpSellections" value="${mltResource.username }"></td>
                                    <td >${mltResource.id }</td>
                                    <td>${mltResource.username }</td>
                                    <td>${mltResource.user }</td>
                                    <td>${mltResource.hrresourcecol1 }</td>
                                    <td>${mltResource.phone }</td>
                                    <td>${mltResource.createTimeStr }</td>
                                    <td onmousedown="myShow(${mltResource.id })"><a
                                            href="#">编辑</a></td>
                                </tr>
                                <!-- ./wrapper -->
                                <div data-spy="scroll" class="modal fade" id="${mltResource.id }" tabindex="-1"
                                     role="dialog" aria-labelledby="myModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">
                                                    &times;
                                                </button>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    管理员详细信息--${mltResource.user }
                                                </h4>
                                            </div>
                                            <form class="form-horizontal" >
                                                <div class="modal-body" data-spy="scroll" data-target="#myScrollspy"
                                                     data-offset="0"
                                                     style="height:400px;overflow:auto; position: relative;">
                                                    <div class="form-group">
                                                        <label class="col-sm-4 control-label">用户名</label>
                                                        <div class="col-sm-8">
                                                            <input type="text" class="form-control" id="${mltResource.username }_inpUserName"
                                                                   disabled value="${mltResource.username }">
                                                        </div>
                                                    </div>
                                                    </br>
                                                    <div class="form-group">
                                                        <label class="col-sm-4 control-label">姓名</label>
                                                        <div class="col-sm-8">
                                                            <input type="text" class="form-control" id="${mltResource.username }_inpName"
                                                                   value="${mltResource.user }">
                                                        </div>
                                                    </div>
                                                    </br>
                                                    <div class="form-group">
                                                        <label class="col-sm-4 control-label">密码</label>
                                                        <div class="col-sm-8">
                                                            <input type="password" class="form-control"
                                                                   id="${mltResource.username }_inpPassword"  placeholder="***************">
                                                        </div>
                                                    </div>
                                                    </br>
                                                    <div class="form-group">
                                                        <label class="col-sm-4 control-label">确认密码</label>
                                                        <div class="col-sm-8">
                                                            <input type="password" class="form-control"
                                                                   id="${mltResource.username }_inpPassword2" placeholder="***************">
                                                        </div>
                                                    </div>
                                                    </br>
                                                    <div class="form-group">
                                                        <label class="col-sm-4 control-label">电话</label>
                                                        <div class="col-sm-8">
                                                            <input type="text" class="form-control" id="${mltResource.username }_inpPhone" value="${mltResource.phone }">
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default active"
                                                        data-dismiss="modal" onclick="upManageMessage('${mltResource.username}')">修改
                                                </button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>

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
<!-- ./wrapper -->


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
<!-- pac.main.js -->
<script src="../manager/dist/js/pac.main.1.0.js"></script>
<!-- page script -->
<script>
    function myShow(myModal) {
        $('#' + myModal).modal('show');
    }
    $(function () {

        $("#danzitbone").DataTable({
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

</script>
</body>
</html>
