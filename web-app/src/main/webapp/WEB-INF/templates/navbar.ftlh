<div class="topMenu mb-5">
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
        <a href="#" class="navbarButton hideMenuOnMobile navbarRight singleNavbarButton">
            <i class="fa fa-user"></i> LOGOWANIE</a>
    </div>
</div>