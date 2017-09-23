<%-- 
    Document   : addPost
    Created on : Jul 21, 2017, 9:56:01 AM
    Author     : Becca
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head> 
        <title>Bethesda</title>
        <c:if test="${param.login_error == 1}">
        <h3>Wrong id or password!</h3>
    </c:if>

    <style>
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/tinymce/jquery.tinymce.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/tinymce/tinymce.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 

        <style>
            #staticButton {
                background:none!important;
                border:none; 
                padding:0!important;

                font-family:arial,sans-serif; /*input has OS specific font-family*/
                color:#069;
                cursor:pointer;
            }
            #staticButton:hover{
                text-decoration:underline;
            }
            .navbar{
                background-color: #2c4163;
                border-color: #323944;
            }

            #blogImage{
                height:100%;
                margin: 0px;
                padding:0px;
            }
            .blogPost{
                height:250px;
                margin:0px;
                padding: 0px;
                overflow:hidden;

            }
            h2 span { 
                color: white; 
                font: bold 16px Helvetica, Sans-Serif; 
                letter-spacing: -1px;  
                background: rgb(0, 0, 0); /* fallback color */
                background: rgba(0, 0, 0, 0.7);
                padding: 10px; 
            }

            #blogImage:hover { background-color: #00000; opacity: 0.8; }


        </style>

    <style>
        #loginInfo{
            color:white;
        }
        #buttons{
            display:inline-block;
        }
        #authorDate{
            font-style: italic;
        }
        h2 { 
            position: absolute;
            left: 0;
            top: 0;
            width: 100%; 
            margin-left: 5%;
        }

    </style>
  
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Bethesda</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/addPost">Add Blog Post</a></li>

                    <li><a href="${pageContext.request.contextPath}/static/addStaticPost">Add Page</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <!--                   USERPIC WILL APPEAR HERE-->
                </ul>
            </div>
        </div>
    </nav>
</head>
<body>
    <div class="container">

        <div class="col-md-12" style="margin-top: 150px; margin-left: 100px;">
            <div class ="col-md-offset-2 col-md-8">
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="j_spring_security_check">
                    <!--                      id="login">-->
                    <div class="form-group">
                        <label for="j_userName" class="col-md-3">User Name:</label>
                        <div class="col-md-9">
                            <input type="text" name="j_username" style="width: 200px;" class="form-control" placeholder="User Name"/>                           
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="j_password" class="col-md-3">Password:</label>
                        <div class="col-md-9">

                            <input type="password" style="width: 200px;" class="form-control" name="j_password" placeholder="Password"/>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-9">
                            <button type="submit" name="login" value="Login" class="btn btn-default">Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="${pageContext.request.contextPath}/j_spring_security_logout" />Logout</a>


</c:if>

<c:forEach var="user" items="${users}">
    <c:out value="${user.username}"/> |
    <a href="deleteUser?username=${user.username}">Delete</a><br/><br/>
</c:forEach>



<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
