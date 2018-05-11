
baseUrl="http://localhost"

//去除字符串两端空格的原型方法
if (!("Trim" in String.prototype)) {
    String.prototype.Trim = function () {
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
}
//日期格式化
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function isEmptyObj(obj) {
    if(obj == null || typeof(obj) == 'undefined') {
        return true;
    } else {
        return false;
    }
}
function isEmptyString(obj) {
    if(obj == null || typeof(obj) == 'undefined' || obj == '') {
        return true;
    } else {
        return false;
    }
}

baseConfig = {
    ajax: {
        post:function(configure){
            if(isEmptyObj(configure.async)){
                async = true;
            }else{
                async = configure.async
            }
            $.ajax({
                url: baseUrl+configure.url,
                type: 'post',
                contentType:"application/json",
                async: async,
                data:JSON.stringify({
                    params:configure.data
                }),
                success:function(data){
                    configure.success(data)
                },
                error:function(){
                    if(!isEmptyObj(configure.error)){
                        configure.error()
                    }
                }
            });
            return false;
        },get:function(configure){
            $.ajax({
                url: baseUrl+configure.url,
                type: 'get',
                contentType:"application/json",
                data:JSON.stringify({
                    params:configure.data
                }),
                success:function(data){
                    configure.success(data)
                },
                error:function(){
                    if(!isEmptyObj(configure.error)){
                        configure.error()
                    }
                }
            });
            return false;
        }
    },
    layui:{
        init:function(configure){
            if(!isEmptyObj(configure)){
                if(!isEmptyObj(configure.use)){
                    layui.use(configure.use, function() {
                        var element = layui.element;
                        var $ = layui.jquery, layer = layui.layer;
                        var laydate = layui.laydate;
                        var table = layui.table;
                        var form = layui.form;
                        layerTips = parent.layer === undefined ? layui.layer : parent.layer //获取父窗口的layer对象
                        if (!isEmptyObj(configure.useTable)) {
                            layui.use('table', function () {
                                var table = layui.table;
                                //监听工具条
                                table.on('tool(' + configure.useTable.toolName + ')', function (obj) {
                                    configure.useTable.onTool(obj)
                                })
                            })
                        }
                        if (!isEmptyObj(configure.tableRender)) {
                            table.render({
                                elem: '#' + configure.tableRender.elem
                                , url: baseUrl + configure.tableRender.url
                                , cols: configure.tableRender.cols
                                , id: configure.tableRender.id
                                , page: true
                                , height: 'full-200'
                                , cellMinWidth: 80
                                , limit: 10
                            });
                        }
                        if (!isEmptyObj(configure.tree)){
                            if (!isEmptyObj(configure.tree.treeData)) {
                                layui.tree({
                                    elem: '#productTypeTree' //指定元素
                                    , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
                                    , click: function (item) { //点击节点回调
                                        if (!isEmptyObj(configure.tree.clickFunction)) {
                                            configure.tree.clickFunction(item)
                                        }
                                    }
                                    , nodes: [ //节点
                                        configure.tree.treeData
                                    ]
                                });
                            }
                        }
                    })
                }
            }
        },
        openyes:function(configure){
            var buttonName;
            if(isEmptyObj()){
                buttonName = 'update'
            }else if(isEmptyString(configure.buttonName)){
                buttonName = update
            }
            $('form.layui-form').find('button[lay-filter='+buttonName+']').click();
        },
        opensuccess:function(configure){
            var form = layui.form;
            layui.form.render();
            form.on('submit('+configure.submitId+')', function(data) {
                configure.onSubmit(data)
                return false
            });
        },
        reloadTable:function(configure) {
            //执行重载
            layui.table.reload(configure.renderId, {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    params:configure.params}
            });
        }
    },
     ajaxFull:function(elem) {
        var win = window.top === window.self ? window : parent.window;
        $(win).on('resize', function() {
            var $this = $(this);
            elem.width($this.width()).height($this.height()).css({
                top: 0,
                left: 0
            });
            elem.children('div.layui-layer-content').height($this.height() - 95);
        });
    }
}