<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>丹姿综合管理系统</title>
    <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="../manager/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="../manager/dist/css/_all-skins.min.css">
    <link rel="stylesheet"
          href="../manager/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="../manager/plugins/daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="../manager/bootstrap/css/bootstrap.min.css">
    <!--<link rel="stylesheet" href="../manager/dist/css/bootstrap.min.css">-->
    <!-- jQuery 2.1.4 -->
    <script src="../manager/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="../manager/bootstrap/js/bootstrap.min.js"></script>
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
                <li><a href="../manager/index.html"><i
                        class="fa fa-dashboard"></i>首页</a></li>
                <li>产品备案信息修改</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title"></h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" onsubmit="return update()" method="post"
                      enctype="multipart/form-data">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">产品编码</label>
                            <div class="col-sm-6">
                                <input type="text" id="inpProductsCode" class="form-control" name="productsCode"
                                       required value="${proRecordList.productcode}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">产品名称</label>
                            <div class="col-sm-6">
                                <input type="text" id="inpProductsName" class="form-control" name="productsName"
                                       value="${proRecordList.productname}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">包材编码</label>
                            <div class="col-sm-6">
                                <input type="text" id="inpBomCode" class="form-control" name="bomCode" required
                                       value="${proRecordList.packagcoding}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">包材属性</label>
                            <div class="col-sm-6">
                                <input type="text" id="inpBomdPro" class="form-control" name="inpBomdPro"
                                       value="${proRecordList.packagpro}">
                                <input type="text" id="inpBomdID" class="form-control" name="inpBomdID"
                                       value="${proRecordList.id}" style="display: none">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">老编码对应</label>
                            <div class="col-sm-6">
                                <input type="text" id="inpOldBomCode" class="form-control" name="inpOldBomCode" value="${proRecordList.oldcoding}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label"></label>
                            <div class="col-sm-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion"
                                               href="#collapseOne" class="center-block">
                                                系统备案信息
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="input-group-addone">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label"></label>
                            <div class="col-sm-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion"
                                               href="#collapseTwo" class="center-block">
                                                备案地面审查信息
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseTwo" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="input-group-add">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label"></label>
                        <div class="col-sm-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseThree" class="center-block">
                                            备案后抽查信息
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="input-group-addthree">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <div class="col-sm-6">
                            <button type="reset"
                                    class="col-sm-2 btn btn-default pull-right">取消
                            </button>
                        </div>
                        <div class="col-sm-6">
                            <button type="submit" class="col-sm-2 btn btn-info">修改</button>
                        </div>
                    </div>
                    <!-- /.box-footer -->
                </form>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->
<script src="../manager/dist/js/angular.min.js"></script>
<!-- AdminLTE App -->
<script src="../manager/dist/js/app.min.js"></script>
<!-- DataTables -->
<script src="../manager/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../manager/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- date-range-picker -->
<script src="../manager/plugins/daterangepicker/daterangepicker.js"></script>
<script src="../manager/dist/js/inputGroup.js" charset="utf-8"></script>
<!-- pac.pro.js -->
<script src="../manager/dist/js/pac.pro.1.0.js" charset="utf-8"></script>
<script src="../manager/dist/js/json2.js"></script>
<script type="text/javascript">
    $(function () {
        var lenground = ${fn:length(proRecordList.groundInfoMessageList)};
        var lensys = ${fn:length(proRecordList.sysMessageList)};
        var lenalter = ${fn:length(proRecordList.alterInfoMessagesList)};
        var i=0,j=0,k=0;
        //Date range picker
        $('#reservation').daterangepicker();
        <c:choose>
        <c:when test="${!empty proRecordList.groundInfoMessageList }">
        <c:forEach var="systemList" items="${proRecordList.groundInfoMessageList }">
        i=i+1;
        var ifAdd = 0;

        $('.input-group-add').initInputGroup({
            'text1': '审查状态',
            'text2': '生产地',
            'selectName': 'one',
            'inputName': '',
            'ifadd':ifAdd,
            'order': i,
            'id':'${systemList.id }',
            'ifstatus':'${systemList.ifstatus }',
            'bornSpace': '${systemList.bornspace }'
        });
        if(i==lenground){
            ifAdd = 1;
            $('.input-group-add').initInputGroup({
                'text1': '审查状态',
                'text2': '生产地',
                'selectName': 'one',
                'inputName': '',
                'ifadd':ifAdd,
                'order': i+1
            });
        }
        </c:forEach>
        </c:when>
        <c:otherwise>
        $('.input-group-add').initInputGroup({
            'text1': '审查状态',
            'text2': '生产地',
            'selectName': 'one',
            'inputName': '',
            'bornSpace': ''
        });
        </c:otherwise>
        </c:choose>
        <c:choose>
        <c:when test="${!empty proRecordList.sysMessageList }">
        <c:forEach var="systemList" items="${proRecordList.sysMessageList }">
        j=j+1;
        var ifAdd = 0;

        $('.input-group-addone').initInputGroup({
            'text1': '备案状态',
            'text2': '生产地',
            'selectName': 'two',
            'inputName': '',
            'ifadd':ifAdd,
            'order':j,
            'id':'${systemList.id }',
            'ifstatus':'${systemList.ifstatus }',
            'bornSpace': '${systemList.bornspace }'
        });
        if(j==lensys){
            ifAdd = 1;
            $('.input-group-addone').initInputGroup({
                'text1': '备案状态',
                'text2': '生产地',
                'selectName': 'two',
                'inputName': '',
                'ifadd':ifAdd,
                'order':j+1
            });
        }
        </c:forEach>
        </c:when>
        <c:otherwise>
        $('.input-group-addone').initInputGroup({
            'text1': '备案状态',
            'text2': '生产地',
            'selectName': 'two',
            'inputName': '',
            'bornSpace': ''
        });
        </c:otherwise>
        </c:choose>
        <c:choose>
        <c:when test="${!empty proRecordList.groundInfoMessageList }">
        <c:forEach var="systemList" items="${proRecordList.alterInfoMessagesList }">
        k=k+1;
        var ifAdd = 0;

        $('.input-group-addthree').initInputGroup({
            'text1': '备案状态',
            'text2': '备案系统链接',
            'selectName': 'three',
            'inputName': '',
            'ifadd':ifAdd,
            'order':k,
            'id':'${systemList.id }',
            'ifstatus':'${systemList.ifstatus }',
            'bornSpace': '${systemList.bornspace }'
        });
        if(k==lenalter){
            ifAdd = 1;
            $('.input-group-addthree').initInputGroup({
                'text1': '备案状态',
                'text2': '备案系统链接',
                'selectName': 'three',
                'inputName': '',
                'ifadd':ifAdd,
                'order':k+1
            });
        }
        </c:forEach>
        </c:when>
        <c:otherwise>
        $('.input-group-addthree').initInputGroup({
            'text1': '备案状态',
            'text2': '备案系统链接',
            'selectName': 'three',
            'inputName': '',
            'bornSpace': ''
        });
        </c:otherwise>
        </c:choose>
    });
</script>
</body>
</html>
