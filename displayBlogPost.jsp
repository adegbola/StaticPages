<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script>
            document.getElementById('accountChoice').onchange = function () {
                window.location.href = this.children[this.selectedIndex].getAttribute('href');
            }
        </script>
        <title>Bethesda</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">
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
            #blogPost{
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

            #postContainer{
                box-shadow: 0px 3px 10px;
                padding:2%;
            }
            #postTitle{
                font: bold 30px Helvetica, Sans-Serif; 
                letter-spacing: -1px;  
            }
            #postDescription{
                margin-top: 10px; 
            }

            #postFooter{
                margin-top: 10px; 
            }
            #postTags{
                color: grey;
                font-size: 16px;
            }


        </style>


        <!--    Navbar-->
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
                    <li id="loginButton"><a href="${pageContext.request.contextPath}/loginPage">Login</a></li>
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
                <form method="get" action="categorizeBlogPosts" class="form-inline my-2 my-lg-0 navbar-right">
                    <input class="form-control mr-sm-2" name="hashTagSearch" type="text" placeholder="Search By #Hashtag">
                </form>
            </div>
        </div>
    </nav>
</head>
<body>
    <div class="container">


        <div id="postContainer" class="col-md-offset-2 col-md-8">
            <div id="postTitle" class="col-md-12">
                ${post.title}
            </div>
            <div id="postDescription" class="col-md-12">
                ${post.description}
            </div>
            <div id="postFooter" class="col-md-12">
                <div id="postTags" class="col-md-9">
                    <c:forEach var="tag" items="${post.hashTags}">#${tag.tag}
                    </c:forEach>
                </div>
                <div id="editDelete" class="col-md-3">
                    <form action="updatePost" method="GET" role="form" id="updatePost">
                        <div class="col-md-6">
                        <button type="submit" name="editButton" value="${post.postID}" class="btn btn-default">Edit</button>
                        </div>
                    </form>
                    <form action="deletePost" method="POST" role="form" id="deletePost">
                        <div class="col-md-6">
                        <button type="submit" name="deleteButton" value="${post.postID}" class="btn btn-default">Delete</button>
                        </div>
                    </form>
                </div>
            </div>


        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
<footer>
    <div id="navigation" class="col-md-offset-2 col-md-8">
        <form method="post" action="static/displayStaticPage" name="staticPage"id="staticPageForm">
            <c:forEach var="post" items="${pageList}">
                <c:forEach var="hashTag" items="${post.hashTags}">
                    <button type="submit" name="staticPage" id="staticButton" value="${hashTag.hashTagID}">${hashTag.tag}</button> |
                </c:forEach>
            </c:forEach>
        </form>  
    </div>
</footer>
</html>

