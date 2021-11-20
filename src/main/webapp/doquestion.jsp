<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*, others.*" %>
<%
    String mode = (String) session.getAttribute("mode");
    List<Question> questions = null;
    Map<Integer, List<AnswerItem>> map = null;
    List<Integer> chosen = null;
    long leftTime = 0;
    

    if("casual".equals(mode)){
        questions = (List<Question>)session.getAttribute("questions");
        map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
    } else if("test".equals(mode)){
        String userId ="" + (int) session.getAttribute("userId");
        TestRecord tr = (TestRecord) application.getAttribute(userId);
        questions = tr.getQuestions();
        map = tr.getMap();
        chosen = tr.getChosen();
        long starts = tr.getTime().getTime();
        Date now = new Date();
        long nows = now.getTime();
        leftTime = (3 * 60 * 1000 - (nows - starts))/1000;
    } else if("recite".equals(mode)){
        questions = (List<Question>)session.getAttribute("questions");
        map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
    } else if("special".equals(mode)){
        questions = (List<Question>)session.getAttribute("questions");
        map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
    }

%>
<html>

<head>
    <meta charset="utf-8">
    <title>Java Web面试题库系统</title>
    <link rel="stylesheet" type="text/css" href="others/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="./css/index.css">

    <script src="others/jquery.js"></script>
    <script src="others/layui/layui.js"></script>
    <script src="others/layer/layer.js"></script>
    <script src="others/jquery.js"></script>
    <script src="js/header/header.js"></script>


    <style>
        .answerElement{
            display: none;
        }
        .layui-card-body.answerButton{
            margin: 2%;
            line-height: 35px;
        }
        .layui-card-body.answerButton:hover {
            background-color: #e4dcae87;
        }
        .letter{
            padding: 10px 15px;
            background-color: rgb(0 0 0 / 20%);
            border-radius: 100%;
        }
        #timer{
            text-align: center;
            background-color: #efefef;
            border: 2px solid #16c4af;
            border-radius: 10px;
            position: fixed;
            background-color: white;
            text-align: center;
            top: 110px;
            left: 200px;
        }
    </style>

<%-- 红： #fe6d68
     绿： #16c4af
    --%>

    <script>
    var mode = "<%=mode %>";
    var leftTime = "<%=leftTime%>"
    var timer1;

    var chosen = [
        <%
            if("test".equals(mode))
            {
                for(int i = 0; i < chosen.size(); ++i)
                {
                    %>"a<%=chosen.get(i) %>"<%
                    if(i < chosen.size() - 1){
                        %>,<%
                    }
                }
            }
        %>
    ];

    var questions = [
            <%
                for(int i = 0; i < questions.size(); ++i)
                {
                    int qId = questions.get(i).getQuestionId();
                    %>"q<%=qId%>"<%
                    if(i < questions.size() - 1)
                    {
                        %>,<%
                    }
                }
            %>
        ];

        var checks={
            <%
                for(int i = 0; i < questions.size(); ++i)
                {
                    int qId = questions.get(i).getQuestionId();
                    List<AnswerItem> aList = map.get(qId);
                    %>"q<%=qId%>" : [<%
                        boolean lastPrint = false;
                        for(int j = 0; j < aList.size(); ++j){
                             AnswerItem ai = aList.get(j);
                             if(ai.isTrue()){
                                 if(lastPrint){%>,<%}
                                 %><%=ai.getAnswerItemId() %><%
                                 lastPrint = true;
                            }
                        }%>]<%
                        if(i < questions.size() - 1){%>,<%}
                }
            %>
        };

        var end = false;
        function pick(Id){
            if(end) return;
            var e = document.getElementById(Id);
            var es = document.getElementById(Id + "s");
            if(es.checked == false){
                e.style["background-color"] = "#e4dcae87";
                es.checked = true;
                if(mode == "recite")
                    e.style["color"] = "#00000000";
            } else if(es.checked == true){
                e.style["background-color"] = "white";
                es.checked = false;
                if(mode == "recite")
                    e.style["color"] = "black";
            }
            if(mode == "test")
            {
                $.ajax({
                    type: "POST",
                    dataType: "text",
                    url: "/iqds/chosen?Id=" + Id.slice(1),
                    async: true, 
                    data: {},
                    success: function (data) {},
                    error : function() {}
                });
            }
            
        }

        function getWrittenAnswer(name){
            var obj = document.getElementsByName(name);
            var check_val = [];
            for (var k in obj) {
                if (obj[k].checked)
                    check_val.push(obj[k].value);
            }
            return check_val;
        }

        function checkQuestion(type){
            if(end) return;
            clearInterval(timer1);
            for(var que = 0; que < questions.length; ++que)
            {
                var qu = questions[que];
                var thisCorrect = false;
                var color = "white";
                var imgurl;
                var answerYouChoose = getWrittenAnswer(qu);
                /* 判断答案是否正确 */
                if(answerYouChoose.toString() == checks[qu].toString()) 
                    thisCorrect = true;
                if(thisCorrect)
                {
                    color = "#16c4af";
                    imgurl = "<img src='/iqds/icons/correct.png' style='height:48px'>";
                } else {
                    color = "#fe6d68";
                    imgurl = "<img src='/iqds/icons/wrong.png' style='height:48px'>";
                }
                var anletter = [];
                var uchooseletter = [];
                /* 获取正确答案的选项字母，并将对应的背景色改变 */
                for(var i = 0; i < checks[qu].length; ++i)
                {
                    var an = checks[qu][i];
                    var e = document.getElementById("anspan" + an);
                    e.style["background-color"] = color;
                    anletter.push(e.innerHTML);
                }
                /* 获取用户选择的答案的选项字母 */
                for(var i = 0; i < answerYouChoose.length; ++i)
                {   
                    var an = answerYouChoose[i];
                    var e = document.getElementById("anspan" + an);
                    uchooseletter.push(e.innerHTML);
                }
                var str = "<div class='layui-card-body'>正确答案：";
                for(var i = 0; i < anletter.length; ++i)
                {
                    str += anletter[i];
                }
                str += "  你的答案：";
                for(var i = 0; i < uchooseletter.length; ++i)
                {
                    str += uchooseletter[i];
                }
                str += imgurl;
                str += "</div>";
                var e = document.getElementById(qu);
                e.innerHTML += str;
                if(type){
                    $.post('/iqds/donequestion?questionId=' + qu.slice(1) + "&isCorrect=" + thisCorrect, {}, function(str){});
                    $.post('/iqds/finishtest');
                }
            }

            end = true;
            finish.style["display"] = "none";
            
        }

        function checkChosen(){
            if(mode != "test") return;
            for(var i = 0; i < chosen.length; ++i){
                pick(chosen[i]);
            }
        }

        function timing(mode){
            var e = document.getElementById("timer");
            if(mode != "test"){ 
                e.style["display"] = "none";
                return;
            }
            e.innerHTML = "<img src='icons/时间.png'><div id='intimer'></div>";
            e = document.getElementById("intimer");
            timer1 = setInterval(function(){
                leftTime--;
                if(leftTime >= 0)
                e.innerHTML ="剩余时间：" + parseInt(leftTime/60) + ":" + (leftTime%60 < 10 ? "0" + leftTime%60: leftTime%60);
                if(leftTime <= 0 ){
                    layer.alert('时间到，即将自动提交', {
                        time: 2 * 1000,
                        offset: ['0']
                        ,success: function(layero, index){
                            var timeNum = this.time/1000, setText = function(start){
                                layer.title((start ? timeNum : --timeNum) + ' 秒后自动提交', index);
                            };
                            setText(!0);
                            this.timer = setInterval(setText, 1000);
                            if(timeNum <= 0) clearInterval(this.timer);
                        }
                        ,end: function(){
                            clearInterval(this.timer);
                            checkQuestion(${loginState.type});
                        }
                    });
                }
            }, 1000);
        }

        function checkthis(qId){
            var answerYouChoose = getWrittenAnswer(qId);
            document.getElementById(qId + 'b').style["display"] = "none";
            var thisCorrect = false;
            if(answerYouChoose.toString() == checks[qId].toString()) 
                thisCorrect = true;
            var anletter = [];
            var uchooseletter = [];
            if(thisCorrect)
            {
                color = "#16c4af";
                imgurl = "<img src='/iqds/icons/correct.png' style='height:48px'>";
            } else {
                color = "#fe6d68";
                imgurl = "<img src='/iqds/icons/wrong.png' style='height:48px'>";
            }
            /* 获取正确答案的选项字母，并将对应的背景色改变 */
            for(var i = 0; i < checks[qId].length; ++i)
            {
                var an = checks[qId][i];
                var e = document.getElementById("anspan" + an);
                e.style["background-color"] = color;
                anletter.push(e.innerHTML);
            }
            /* 获取用户选择的答案的选项字母 */
            for(var i = 0; i < answerYouChoose.length; ++i)
            {   
                var an = answerYouChoose[i];
                var e = document.getElementById("anspan" + an);
                uchooseletter.push(e.innerHTML);
            }
            var str = "<div class='layui-card-body'>正确答案：";
            for(var i = 0; i < anletter.length; ++i)
            {
                str += anletter[i];
            }
            str += "  你的答案：";
            for(var i = 0; i < uchooseletter.length; ++i)
            {
                str += uchooseletter[i];
            }
            str += imgurl;
            str += "</div>";
            var e = document.getElementById(qId);
            e.innerHTML += str;
        }

    </script>

</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container" style="background-color: rgb(212 212 212 / 38%);">
    
        <div id="questionsdiv">
        </div>
        <div id='timer'></div>
    </div>

    <script>
        $.ajax({
            type: "POST",
            dataType: "text",
            url: "/iqds/pages/doquestion/doquestion_get.jsp",
            async: false, 
            data: {},
            success: function (data) {
                questionsdiv.innerHTML = data;
            },
            error : function() {
                layer.msg('异常');
            }
        });
        timing("${mode}");
        checkChosen();
    </script>
</body>

</html>
