
<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
    pageEncoding="ISO-8859-2"%>
<!DOCTYPE html>>
<html>
<head>

<title>Tamitu</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/style.css">
  <script src="js/scripts.js"></script>

  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <meta name="google-signin-scope" content="profile email">
  <meta name="google-signin-client_id"
     content="336118141863-o0p2nu68s5t1n3ater25gh5ic6dsn0rd.apps.googleusercontent.com">

</head>
<body onload="skipBanner()">


 <!-- Navbar menu -->
  <div class="topMenu">
    <div class="hideOverflow" id="myNavbar">
      <a class="navbarButton dropDownBars" href="javascript:void(0);" onclick="toggleMenu()" title="Toggle Navigation Menu">
        <i class="fa fa-bars"></i>
      </a>

      <a class="navbarButton navbar-brand hideMenuOnMobile" href="#">
          <img src="img/logo.png" width="30" height="30" alt="">
        </a>

      <a href="#contentTab" class="navbarButton singleNavbarButton" onclick="$('#main-body').load( 'content/main.html' );">HOME</a>

      <div class="navbarButton hideMenuOnMobile dropdown">
          <div class="nav-link dropdown-toggle"  role="button" data-toggle="dropdown">
            <i class="fa fa-map"></i> TRASY
          </div>
          <div class="dropdown-menu" >
            <a href="#contentTab" class="dropdown-item" onclick="$('#main-body').load( 'content/routes.html' );">Dostępne trasy</a>
            <div class="dropdown-divider"></div>
            <a href="#contentTab" class="dropdown-item">Moje trasy</a>
            <a href="#contentTab" class="dropdown-item">Dodaj nową</a>
          </div>
      </div>

      <div class="navbarButton hideMenuOnMobile dropdown">
        <div class="nav-link dropdown-toggle"  role="button" data-toggle="dropdown">
          <i class="fa fa-map"></i> ATRAKCJE
        </div>
        <div class="dropdown-menu" >
          <a href="places-viewer" class="dropdown-item" href="#">Lista atrakcji</a>
          <div class="dropdown-divider"></div>
          <a href="#contentTab" class="dropdown-item" href="#">Moje ulubione</a>
          <a href="#contentTab" class="dropdown-item" href="#">Dodaj nową</a>
        </div>
    </div>

      <a href="#contentTab" class="navbarButton hideMenuOnMobile singleNavbarButton">
        <i class="fa fa-question-circle"></i> POMOC</a>
      <a href="signin" class="navbarButton hideMenuOnMobile navbarRight singleNavbarButton">
        <i class="fa fa-user"></i> LOGOWANIE</a>
    </div>

    <!-- Navbar on small screens -->
    <div id="navDemo" class="bg-light">
      <a href="#contentTab" class="navbarButton" onclick="toggleMenu()">HOME</a>
      <a href="#contentTab" class="navbarButton" onclick="toggleMenu()"><i class="fa fa-map"></i> TRASY</a>
      <a href="#attractions" class="navbarButton" onclick="toggleMenu()"><i class="fa fa-map-pin"></i> ATRAKCJE</a>
      <a href="#contentTab" class="navbarButton" onclick="toggleMenu()"><i class="fa fa-question-circle"></i> POMOC</a>
      <a href="signin" class="navbarButton" onclick="toggleMenu()"><i class="fa fa-user"></i> LOGOWANIE</a>
    </div>
  </div>

  <!-- Main banner -->
  <div class="bannerMain bannerContainer" id="home">
    <div class="middle row" style="white-space:nowrap;">
      <div class="col-12 center logoText">
        <img src="img/logo.png">
        <h2>Tamitu</h2>
      </div>
    </div>
  </div>

<span id="contentTab"></span>

  <!-- Welcome section -->
  <section class="row bg-light" id="main-body" role="main">

        <!--  forward to index page for login if user info is not in session  -->
        <% if (session.getAttribute("userName") == null) {%>
            <jsp:forward page="/index.jsp"/>
        <% } %>
    <div class=".text-center">
        <h3>Welcome  ${userName}</h3>
    </div>
    </section>

 <!-- Footer -->
  <footer class="footer center">
    <a href="#home" class="footerButton">
      <i class="fa fa-arrow-up fa-fw"></i>To the top</a>
    <div class="section">
      <a href="#" class="fa fa-facebook-official fa-3x fa-fw"></a>
      <a href="#" class="fa fa-linkedin fa-3x"></a>
      <a href="#" class="fa fa-instagram fa-3x"></a>
    </div>
  </footer>

</body>
</html>

