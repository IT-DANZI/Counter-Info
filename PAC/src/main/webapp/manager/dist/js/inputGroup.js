/**
 * Created by Lily .
 */
$(function () {
    var flag = 1;
    $.fn.initInputGroup = function (options) {
        //1.Settings 初始化设置
        var c = $.extend({
            'widget': 'input',
            'widget1': 'input',
            'text1': '文本一',
            'text2': '文本二',
            'selectName': 'selectName',
            'inputName': 'inputName',
            'ifadd': '1',
            'bornSpace': '',
            'ifstatus': '',
            'order':'1',
            'id':'',
            'add': "<span class=\"glyphicon glyphicon-plus\"></span>",
            'del': "<span class=\"glyphicon glyphicon-minus\"></span>"
        }, options);

        var _this = $(this);

        //添加序号为1的输入框组
        addInputGroup(c.order);

        /**
         * 添加序号为order的输入框组
         * @param order 输入框组的序号
         */
        function addInputGroup(order) {

            //1.创建输入框组
            var inputGroup = $("<div class='input-group' style='margin: 10px 0'></div>");
            //2.输入框组的序号
            var inputGroupAddon1 = $("<span class='input-group-addon'></span>");
            //3.设置输入框组的序号
            inputGroupAddon1.html(" " + order + " ");
            var head1;
            //输入框组头部内容
            if (c.ifstatus == '1') {
                head1 = $("<div class='form-group'><input type='text' style='display: none' name='inp" + c.selectName + "'/><label class='col-sm-3 control-label'>" + c.text1 + "</label><div class='col-sm-6'> <select class='form-control' name='sel" + c.selectName + "'><option value='0'></option><option value='1' selected>通过</option><option value='2'>不通过</option><option value='3'>待定</option></select></div></div>");
            } else if (c.ifstatus == '2') {
                head1 = $("<div class='form-group'><input type='text' style='display: none' name='inp" + c.selectName + "'/><label class='col-sm-3 control-label'>" + c.text1 + "</label><div class='col-sm-6'> <select class='form-control' name='sel" + c.selectName + "'><option value='0'></option><option value='1'>通过</option><option value='2' selected>不通过</option><option value='3'>待定</option></select></div></div>");
            } else if (c.ifstatus == '3') {
                head1 = $("<div class='form-group'><input type='text' style='display: none' name='inp" + c.selectName + "'/><label class='col-sm-3 control-label'>" + c.text1 + "</label><div class='col-sm-6'> <select class='form-control' name='sel" + c.selectName + "'><option value='0'></option><option value='1'>通过</option><option value='2'>不通过</option><option value='3' selected>待定</option></select></div></div>");
            } else {
                head1 = $("<div class='form-group'><input type='text' style='display: none' name='inp" + c.selectName + "'/><label class='col-sm-3 control-label'>" + c.text1 + "</label><div class='col-sm-6'> <select class='form-control' name='sel" + c.selectName + "'><option value='0'></option><option value='1'>通过</option><option value='2'>不通过</option><option value='3'>待定</option></select></div></div>");
            }
           // var head1 = $("<div class='form-group'><input type='text' style='display: none' name='inp" + c.selectName + "'/><label class='col-sm-3 control-label'>" + c.text1 + "</label><div class='col-sm-6'> <select class='form-control' name='sel" + c.selectName + "'><option value='0'></option><option value='1'>通过</option><option value='2'>不通过</option><option value='3'>待定</option></select></div></div>");
            var head2 = $("<div class='form-group'><input type='text' style='display: none' name='inp" + c.selectName + "isnull' value='"+c.id+"'/><label class='col-sm-3 control-label'>" + c.text2 + "</label><div class='col-sm-6'><input type='text' name='inp" + c.selectName + "val' class='form-control' value='" + c.bornSpace + "'></div></div>");
            //输入框尾
            var foot = $("</div></div>");

            //4.创建输入框组中的输入控件（input或textarea）
            var widget = '', widget1 = '', inputGroupAddon2;
            // widget = $("<input type='text' name='productsPri2' class='form-control' style='margin:10px 0'>");
            // widget1 = $("");
            inputGroupAddon2 = $("<span class='input-group-btn'></span>");
            //5.创建输入框组中最后面的操作按钮
            var addBtn;
            if (c.ifadd == 1) {
                addBtn = $("<button class='btn-circle' type='button'>" + c.add + "</button>");
            } else {
                addBtn = $("<button class='btn-circle' type='button' onclick='delm"+c.selectName+"Message("+c.id+")'>" + c.del + "</button>");
            }

            addBtn.appendTo(inputGroupAddon2).on('click', function () {
                //6.响应删除和添加操作按钮事件
                if ($(this).html() == c.del) {
                    $(this).parents('.input-group').remove();
                } else if ($(this).html() == c.add) {
                    $(this).html(c.del);
                    addInputGroup(order++);
                }
                //7.重新排序输入框组的序号
                resort();
            });

            inputGroup.append(inputGroupAddon1).append(head1).append(head2).append(inputGroupAddon2);

            _this.append(inputGroup);
            c.bornSpace = '';
            c.ifstatus = '';
            c.id = '';
        }

        function resort() {
            var child = _this.children();
            $.each(child, function (i) {
                $(this).find(".input-group-addon").eq(0).html(' ' + (i + 1) + ' ');
            });
        }
    }
});