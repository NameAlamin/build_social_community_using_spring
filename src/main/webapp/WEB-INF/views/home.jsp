<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 19-May-22
  Time: 08:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>

<head>
    <!-- Bootstrap CSS -->

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <title>study-with-alamin</title>

    <style>
        .project-container {
            background-color: #6ecdeb;
        }

        .asdf {
            border: 2px solid rgb(25, 0, 255);
            background-color: #069fce;
        }
    </style>
</head>

<body>
<!-- Navbar part   ...................... part-1 ..................  -->

<nav class="sticky-top navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand font-weight-bolder text-white ms-5 fs-3" href="#">Social Community</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav m-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">User</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Status</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Location</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Home Part  ...................... part-2 ..................  -->

<div class="project-container">
    <div class="container">
        <%--        <div class="row">--%>
        <%--            <div class="col-lg-12">--%>
        <%--                <h1 class="text-center text-dark mt-4 mb-4">Home Page</h1>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <!-- 1st row start  -->--%>
        <div class="row pt-4">
            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="card mb-4">
                    <h2 class="card-header">User</h2>
                    <div class="card-body">
                        <button class="btn btn-primary create-user">Create User</button>
                        <button class="btn btn-primary user-table">User Table</button>
                        <br />
                    </div>
                </div>

                <div class="card mb-4">
                    <h2 class="card-header">Status</h2>
                    <div class="card-body">
                        <button class="btn btn-primary create-status">Create Status</button>
                        <button class="btn btn-primary status-table">Status Table</button>
                        <br />
                    </div>
                </div>

                <div class="card mb-5">
                    <h2 class="card-header">Location</h2>
                    <div class="card-body">
                        <button class="btn btn-primary create-location">Create Location</button>
                        <button class="btn btn-primary location-table">Location Table</button>
                        <br />
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-6 col-sm-12 asdf mb-5"></div>
        </div>

        <!-- 1st row end  -->

    </div>
</div>


</body>

<script>
    const loadPage = (url, element) => {
        $.ajax({
            url: url,
            success: (response) => element.html(response)
        });
    };

    $(document).ready(() => {

        $('.create-user').click(() => {
            loadPage('${pageContext.request.contextPath}/user/create', $('.asdf'));
        });

        $('.user-table').click(() => {
            loadPage('${pageContext.request.contextPath}/user/list', $('.asdf'));
        });

        $('.create-status').click(() => {
            loadPage('${pageContext.request.contextPath}/status/create', $('.asdf'));
        });

        $('.status-table').click(() => {
            loadPage('${pageContext.request.contextPath}/status/list', $('.asdf'));
        });

        $('.create-location').click(() => {
            loadPage('${pageContext.request.contextPath}/location/create', $('.asdf'));
        });

        $('.location-table').click(() => {
            loadPage('${pageContext.request.contextPath}/location/list', $('.asdf'));
        });
    });
</script>

</html>