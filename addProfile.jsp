<%-- 
    Document   : addPost
    Created on : Jul 21, 2017, 9:56:01 AM
    Author     : Becca
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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
    <nav class="navbar navbar-default">
         <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
              | <a href="${pageContext.request.contextPath}/j_spring_security_logout" />Logout</a>
            </p>
        </c:if>
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
                    <li class="active"><a href="${pageContext.request.contextPath}/addPost">Add Blog Post</a></li>
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

        <div class="col-md-12">
            <div class ="col-md-offset-2 col-md-8">
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createProfile"
                      id="addProfile">
                    <div class="form-group">
                        <label for="userName" class="col-md-3">User Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userName" placeholder="User Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3">Password:</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="password" placeholder="Password" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="col-md-3">First Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="firstName" placeholder="First Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="col-md-3">Last Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="lastName" placeholder="Last Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-3">Email:</label>
                        <div class="col-md-9">
                            <input type="email" class="form-control" name="email" placeholder="Email" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="datepicker" class="col-md-3">Birthday:</label>
                        <div class="col-md-9">
                            <input id="datepicker" class="form-control" name="datepicker"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <button type="submit" name="login" value="Login" class="btn btn-default">Create Login</button>
                        </div>
                    </div>

                    </body>
                    </html>
