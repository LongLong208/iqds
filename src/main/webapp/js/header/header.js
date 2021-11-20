function header(isManager, headerContent, loginType) {
    document.write("<title>在线考试系统</title>");
    document.write("<div class=\"header\">");
    document.write("<div style=\"float: left;font-weight: bolder;\"><a href=\"/iqds/\"><img src=\"./icons/题库编写.png\"");
    document.write("style=\"vertical-align:middle;height:100%\">在线考试系统</a></div>");
    document.write("<div style=\"float: right;height: 100%\">");
    document.write("<ul style=\"height: 100%; line-height: 50px;color: rgba(18,20,28,.7);\">");
    document.write("<li id=\"register\"><a href=\"/iqds/register.jsp\">注册</a></li>");
    document.write("<li id=\"manager\"><a href=\"/iqds/manager\">管理员页面</a></li>");
    document.write("<li id=\"logout\"><a href=\"/iqds/logout\">登出</a></li>");
    document.write("<li id=\"login\"></li>");
    document.write("</ul>");
    document.write("</div>");
    document.write("</div>");

    login.innerHTML = headerContent;
    if (isManager == 0 || isManager == undefined)
        manager.style["display"] = "none";
    if (loginType)
        register.style["display"] = "none";
    else logout.style["display"] = "none";
}
