<%@ page language="java" import="java.util.*,java.sql.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>

<html>

<head>
    <meta charset="utf-8">

    <link rel="stylesheet" type="text/css" href="others/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <%-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
    <script src="others/jquery.js"></script>
    <script src="js/header/header.js"></script>
    <script src="/iqds/others/layer/layer.js"></script>

<style>

    .buttondiv
    {
        display: block;
        text-decoration: none;
        color: rgba(18,20,28,0.7);
        width: 100%;
        padding: 10px 0 10px 0;
    }


    #navbarul li
    {
        width: 100%;
        padding: 0;
    }

    #navbarul li .buttondiv:hover
    {
        background-color: rgb(255, 153, 0);
        /* border-radius: 10px; */
        color: rgb(0, 47, 255);
        width: 100%;
    }

    #navbarul
    {
        background: rgb(239, 239, 239);
        width: 100%;
        /* border-radius: 10px;
        margin: 4px; */
    }

    #navbar
    {
        /* position: fixed;  */
        /* flex: 1; */
        /* padding: 2px; */
        font-size: 20px;
        float: left;
        text-align: center;
        /* border-radius: 10px; */
        position: fixed;
        background: rgb(249, 249, 249);
        width: 10%;
        height: calc(100vh - 50px);
    }

    #contentright
    {
        height: calc(100vh - 50px);
        width: 90%;
        padding: 0 0 0 10%;
    }

    .insertbtn
    {
        font-size: 20px;
        color: #336df1;
    }

    body .AnswerTips
    {
        font-size: 20px;
    }


</style>

    <script>
        function ajaxGetRight(url)
        {
            var item = document.getElementById("contentright");
            ajaxGet(item, url);
        }
        function ajaxGet(item, url) {
            $.ajax({
                type: "POST",
                dataType: "text",
                url: url,
                async: false, 
                data: "",
                success: function (data) {
                    item.innerHTML = data;
                },
                error : function() {
                    layer.msg('异常');
                }
            });
        }
        function InsertNew(type) {
            $.post('/iqds/pages/forms/' + type + '.jsp', {}, function(str){
                layer.open({
                    type: 0,
                    title: '添加',
                    content: str,
                    btn: ['确认', '取消'],
                    skin: 'layui-layer-rim',
                    area: ['640px', '700px'],
                    yes: function (index, layero) {
                        $.ajax({
                            type: "POST",
                            dataType: "text",
                            url: "/iqds/" + type + "insert",
                            async: true, 
                            data: $('#InsertForm').serialize(),
                            success: function (data) {
                                layer.msg('添加成功！');
                                ajaxGetRight('/iqds/' + type + 'mng');
                            },
                            error : function() {
                                layer.msg('异常');
                            }
                        });
                    }
                });
            });
        }
        function Delete(type, itemId) {
            layer.confirm('确定删除' + itemId + '？', 
                {
                    btn: ['确定','取消']
                }, 
                function(){
                    $.ajax({
                        type: "POST",
                        dataType: "text",
                        url: "/iqds/delete?type=" + type + "&" + "itemId=" + itemId,
                        async: true, 
                        data: {},
                        success: function (data) {
                            layer.msg('删除成功！');
                            ajaxGetRight('/iqds/' + type + 'mng');
                        },
                        error : function() {
                            layer.msg('异常');
                        }
                    });
                }
            );
        }
        function LookAnswer(questionId){
            $.post('/iqds/lookanswer?questionId=' + questionId, {}, function(str){
                layer.tips(str,
                    '#la' + questionId, {
                        tips: [1, '#3595CC'],
                        time: 4000
                    }
                );
            });
        }
        function Update(type, itemId) {
            $.post('/iqds/' + type + "update?" + type + "Id=" + itemId, {}, function(str){
                layer.open({
                    type: 0,
                    title: '修改',
                    content: str,
                    btn: ['确认', '取消'],
                    skin: 'layui-layer-rim',
                    area: ['640px', '700px'],
                    yes: function (index, layero) {
                        $.ajax({
                            type: "POST",
                            dataType: "text",
                            url: "/iqds/delete?type=" + type + "&itemId=" + itemId,
                            async: true, 
                            data: {},
                            success: function (data) {
                                $.ajax({
                                    type: "POST",
                                    dataType: "text",
                                    url: "/iqds/" + type + "insert?" + type + "Id=" + itemId,
                                    async: true, 
                                    data: $('#InsertForm').serialize(),
                                    success: function (data) {
                                        layer.msg('修改成功！');
                                        ajaxGetRight('/iqds/' + type + 'mng');
                                    },
                                    error : function() {
                                        layer.msg('异常');
                                    }
                                });
                            },
                            error : function() {layer.msg('删除异常');}
                        });
                        
                    }
                });
            });
        }
        function changePower(type, itemId){
            $.ajax({
                type: "POST",
                dataType: "text",
                url: "/iqds/changepower?" + type + "Id=" + itemId,
                async: true, 
                data: "",
                success: function (data) {
                    layer.msg('变更成功！');
                    ajaxGetRight('/iqds/' + type + 'mng');
                },
                error : function() {
                    layer.msg('异常');
                }
            });
        }
        function changeFail(){
            layer.msg('你不能变更自己的权限！');
        }
        function searchRow(){
            var kw = document.getElementById("keyword").value;
            var table = document.getElementById("right_table");
            var rows = table.rows;
            var pattern = new RegExp(".*" + kw + ".*", i);
            for(var i = 1; i < rows.length; ++i)
            {
                var row = rows[i];
                var id = row.cells[0].innerHTML;
                var name = row.cells[1].innerHTML;
                id = replaceIt(id);
                name = replaceIt(name);
                if(pattern.test(id) || pattern.test(name))
                {
                    row.style["display"] = "table-row";
                } else {
                    row.style["display"] = "none";
                }
            }
        }
        function replaceIt(str){
            str = str.replace(/&nbsp;/," ");
            str = str.replace(/&quot;/," ");
            str = str.replace(/<br>/," ");
            str = str.replace(/&lt;/,"<");
            str = str.replace(/&gt;/,">");
            str = str.replace(/&amp;/,"&");
            return str;
        }
    </script>

</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container">
            <div id="navbar">
                <ul id="navbarul">
                    <li><div class="buttondiv" onclick="ajaxGetRight('/iqds/usermng')">用户管理</div></li>
                    <li><div class="buttondiv" onclick="ajaxGetRight('/iqds/questionmng')">题库管理</div></li>
                </ul>
            </div>
            <div id="contentright"></div>
    </div>
</body>

</html>
