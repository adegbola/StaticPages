<%-- 
    Document   : staticPage
    Created on : Jul 24, 2017, 3:33:40 PM
    Author     : Becca
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#loginInfo').hide();

                if (${isLoggedIn} == true) {
                    $('#loginButton').hide();
                    $('#loginInfo').show();
                }
            });
        </script>
        <title>Bethesda</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <style>
            #loginInfo{
                color:white;
            }

        </style>


    </head>
    <body>
        <div class="container">
            <div style="background-color: black" class="col-md-12">
                <div style="text-align: center;" class="col-md-9">
                    <a href="${pageContext.request.contextPath}"><h1 style="color: red; font-family: monospace">Bethesda</h1></a>
                </div>
                <div id ="loginButton" class="col-md-3" style="text-align: center;">
                    <form method="get" action="loginPage">
                        <br/>
                        <button type="submit" class="btn btn-default" name="login" style="background-color: red; color: #07a">Login</button>
                    </form>
                </div>
                <div id ="loginInfo" class="col-md-3" style="text-align: center;">
                    Welcome, <c:out value="${profile.userName}"/>
                </div>
            </div>
            <div id="staticPage">
                ${page.description}
            </div>

        </div>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>

</html>
