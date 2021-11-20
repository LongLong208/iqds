<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*, DAOs.*" %>
<%! boolean update = false; 
    Question question = null;
    List<AnswerItem> answerList = null;
    List<Knowledge> knowledgeList = null;
%>
<%
    question = (Question)request.getAttribute("question");
    answerList = (List<AnswerItem>)request.getAttribute("answers");
    knowledgeList = KnowledgeDAO.checkAll();
    if(question != null && answerList != null)
        update = true;
%>
<html>

<head>

    <script>
        var lastC,lastD;
        var lastAnswerC,lastAnswerD;
        function choose() {
            answerwrite.innerHTML =
                '<tr><td>答案：</td><td>答案数目：<select name=\'answerNum\' onchange=\'answerNumChange()\' style="width:65px"><option id=\"option2\" value=2>2</option><option id=\"option3\" value=3>3</option><option id=\"option4\" value=4 selected>4</option></select></td></tr><tr id=\'answerA\'><td><input id=\"checkbox0\" type=\'checkbox\' name=\'correctAnswers\' value=0>A</td><td><textarea id=\"textarea0\" rows=\'3\' cols=\'30\' name=\'answers\'></textarea></td></tr><tr id=\'answerB\'><td><input id=\"checkbox1\" type=\'checkbox\' name=\'correctAnswers\' value=1>B</td><td><textarea id=\"textarea1\" rows=\'3\' cols=\'30\' name=\'answers\'></textarea></td></tr><tr id=\'answerC\'><td><input id=\"checkbox2\" type=\'checkbox\' name=\'correctAnswers\' value=2>C</td><td><textarea id=\"textarea2\" rows=\'3\' cols=\'30\' name=\'answers\'></textarea></td></tr><tr id=\'answerD\'><td><input id=\"checkbox3\" type=\'checkbox\' name=\'correctAnswers\' value=3>D</td><td><textarea id=\"textarea3\" rows=\'3\' cols=\'30\' name=\'answers\'></textarea></td></tr>'
        }
        function speak(){
            answerwrite.innerHTML='<tr><td>答案：</td><td><textarea id=\'textarea0\' rows=\'5\' cols=\'30\' name=\'answers\'></textarea></td></tr>';
        }
        function answerNumChange() {
            if(answerC.innerHTML != ""){
                lastAnswerC = new String(textarea2.value);
                lastC = new String(answerC.innerHTML);
            }
            if(answerD.innerHTML != ""){
                lastAnswerD = new String(textarea3.value);
                lastD = new String(answerD.innerHTML);
            }
            answerC.innerHTML = answerD.innerHTML = "";
            switch (InsertForm.answerNum.value) {
                case '4':
                    answerD.innerHTML = lastD;
                    textarea3.value = lastAnswerD;
                case '3':
                    answerC.innerHTML = lastC;
                    textarea2.value = lastAnswerC;
                case '2':
            }
        }
        function knowledgeChange(){
            if(InsertForm.knowledgeId.value == 0){
                newKnowledge.innerHTML = "<tr><td>知识点：</td><td><input id='knowledgeNameText' type='text' name='knowledgeName'></td></tr>";
            } else {
                newKnowledge.innerHTML = "";
            }
        }
    </script>
</head>

<body>
    <form id="InsertForm">
        <table>
            <tr>
                <td>题目：</td>
                <td><textarea id="titletextarea" rows='5' cols="50" name="title"></textarea></td>
            </tr>
            <tr>
                <td>类型：</td>
                <td><input id="questiontype0" type="radio" name="questionType" value="0" onchange="choose()">选择题
                    <input id="questiontype1" type="radio" name="questionType" value="1" onchange="speak()">简答题
                </td>
            </tr>
            <tr>
                <td>知识点：</td>
                <td>
                    <select name="knowledgeId" onchange="knowledgeChange()">
                        <option id="kloption_1" style="display:none" value=-1>请选择</option>
                        <option id="kloption0" value=0>新增知识点</option>
                        <%
                            for(int i = 0; i < knowledgeList.size(); ++i){
                                %><option id="kloption<%= knowledgeList.get(i).getKnowledgeId() %>" value= <%= knowledgeList.get(i).getKnowledgeId() %>><%=knowledgeList.get(i).getKnowledgeName()%></option><%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <table id="newKnowledge"></table>
        <table id="answerwrite"></table>
    </form>
    <script>knowledgeChange();</script>
    <%  
        if(update){  
                %><script>
                kloption<%= question.getKnowledgeId()%>.selected=true;
                knowledgeChange();
                titletextarea.value ="<%= question.getTitle() %>";
                questiontype<%= question.getQuestionType()%>.checked=true;
                if(InsertForm.questionType.value == 0)
                    choose();
                else
                    speak();
    <%
                if(question.getQuestionType() == 0){
                    %>option<%= answerList.size()%>.selected = true;<%
                }
                for(int i = 0; i < answerList.size(); ++i){
                    %>textarea<%= i%>.value = "<%=  answerList.get(i).getTitle()%>";<%
                    if(question.getQuestionType() == 0){
                        %>checkbox<%= i%>.checked = <%= answerList.get(i).isTrue() %>;<%
                    }
                }
    %>
            </script>
    <%
            request.removeAttribute("question");
            request.removeAttribute("answerList");
            update = false;
        }
    %>
</body>

</html>
