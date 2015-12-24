<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/resources/core/favicon.ico">

    <title>Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/core/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/resources/core/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/core/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/core/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="jumbo2">Home</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" method="POST" action="submit">
              <input pattern=".{7,10}" type="text" name="phoneNum" id="phoneNum" placeholder="7/10 digits. e.g. 1234567" class="form-control" required>
              <input type="hidden" name="page" value="1">
            <button class="btn btn-success" id="formSubmit" >Submit</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <div class="container">
      <div class="row">
          <h2 class="sub-header">${number}, ${count} combination(s) found:</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${entries}" var="entry">
                  <tr>
                      <td>${entry}</td>
                  </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
      </div>
    </div>

    <div class="row">
        <div class="lead col-center-block">
            <p style="text-align: center;">${currentPage}/${last}</p>
        </div>
    </div>

    <div text-align:center class="container">
      <hr>
          <form class="navbar-form navbar-right" method="POST" action="submit">
              <input type="hidden" name="phoneNum" value="${number}">
              <input type="hidden" name="page" value="${last}">
            <button class="btn btn-lg btn-warning">Last</button>
          </form>
          <form class="navbar-form navbar-right" method="POST" action="submit">
              <input type="hidden" name="phoneNum" value="${number}">
              <input type="hidden" name="page" value="${next}">
            <button class="btn btn-lg btn-success">Next</button>
          </form>
          <form class="navbar-form navbar-right" method="POST" action="submit">
              <input type="hidden" name="phoneNum" value="${number}">
              <input type="hidden" name="page" value="${prev}">
            <button class="btn btn-lg btn-primary">Prev</button>
          </form>
          <form class="navbar-form navbar-right" method="POST" action="submit">
              <input type="hidden" name="phoneNum" value="${number}">
              <input type="hidden" name="page" value="1">
            <button class="btn btn-lg btn-info">First</button>
          </form>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
     </div>

      <hr>

      <footer>
            <div class="row">
                <div class="col-center-block">
                    <p style="text-align: center;">&copy; 2015 Ming Teng, all rights reserved. Powered by Spring Boot, SpringMVC, Amazon EC2/ ElasticBeanstalk, Google App Engine and Bootstrap.</p>
                </div>
             </div>
      </footer>
    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="/resources/core/dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="/resources/core/assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/core/assets/js/ie10-viewport-bug-workaround.js"></script>



  </body>
</html>
