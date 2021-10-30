<%@ page contentType="text/html;UTF-8" %>
<html lang="en">
<head>
    <title>main page</title>
</head>
<body>

<%
    String user = null;
    String sessionUser = (String) session.getAttribute("username");
    if (sessionUser == null) {
//        response.sendRedirect("mainpage.html");
        response.sendRedirect("/www/blog.html");
    } else {
        user = sessionUser;
    }
    String cookieUser = null;
    String sessionId = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie: cookies) {
            if ("username".equals(cookie.getName())) cookieUser = cookie.getValue();
            if ("JSESSIONID".equals(cookie.getName())) sessionId = cookie.getValue();
        }
    } else {
        sessionId = session.getId();
    }
%>

<h3>
    Hello, <%=user%>! Login Successful!
    <br>
    Session ID = <%=sessionId%>
    <br>
    Cookie Username = <%=cookieUser%>
</h3>

</body>
</html>