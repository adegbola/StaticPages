<%-- 
    Document   : addPost
    Created on : Jul 21, 2017, 9:56:01 AM
    Author     : Becca
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head> 
        <title>Bethesda</title>

        <style>
            #loginInfo{
                color:white;
            }
        </style>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>

        <script src="${pageContext.request.contextPath}/plugins/tinymce/jquery.tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-tagsinput.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-tagsinput-angular.js"></script>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/bootstrap-tagsinput.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">
        <script>
            tinymce.activeEditor.uploadImages(function (success) {
                document.forms[0].submit();
            });
        </script>

            <script>
                $(document).ready(function () {
                $('#blogCheck').hide();
                $('#pending').hide();
            });
            </script>
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
                    <li><div id ="loginInfo" class="col-md-3">
                            <div class="dropdown">
                                <select name="accountChoice" class="dropbtn" onChange="window.location.href = this.value">
                                    <div class="dropdown-content">
                                        <option value="${pageContext.request.contextPath}">${profile.firstName}</option>
                                        <option value="account">Account</option>
                                        <option value="pendingList">Pending</option>
                                        <option value="logOut">Log Out</option>
                                    </div>
                                </select>
                            </div>
                        </div>
                    </li>
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
                <form class="form-vertical" 
                      role="form" method="POST" 
                      action="addBlogPost"
                      id="blogPost">
                    <div class="form-group">
                        <div class="col-md-9">
                            <label for="hashTags">HashTags:</label>
                            <br/>
                            <select data-role="tagsinput" multiple="multiple" name="tags" id="hashTags">
                                <option value="Bethesda">Bethesda</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-9">
                            <input type="checkbox" name="blogPost" value="true" id="blogCheck" checked>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <input type="checkbox" name="pending" value="true" id="pending">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                        <label style="text-align: left;" for="picture">Picture URL:</label>
                            <input type="text" class="form-control" id="picture" name="picture" value="http://cdn4.gamepur.com/images/feature/bethesda-logo.jpg" required>
                            <br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                        <label style="text-align: left;" for="picture">Blog Title:</label>
                            <input type="text" class="form-control" name="title" required>
                            <br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <textarea class = "tinymce" name="texteditor" form="blogPost"></textarea>
                            <br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <button type="submit" name="addBlogPost" value="Add Post" class="btn btn-default">Add Post</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
