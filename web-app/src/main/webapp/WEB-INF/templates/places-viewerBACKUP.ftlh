<!DOCTYPE html>
<html lang="pl">
<head>
    <title>APP_NAME</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/jquery.paginate.css">
    <script src="js/scripts.js"></script>
</head>
<body>

<!-- Navbar menu -->
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
<#--<div class="row">-->
<section class="row bg-light" id="main-body" role="main">
    <#--<div class="sectionContent container sectionDesc" id="home">-->
        <div class="col-4 pt-5 mt-5"></div>
        <div class="col-8 pt-5 mt-5">
        <div id="accordion">
            <div id="paginate">
            <#list places as p>
                    <#if p?is_odd_item>
                        <#assign cardStyle="text-white bg-secondary mb-3">
                    <#else>
                        <#assign cardStyle="bg-light mb-3">
                    </#if>
                <div class="card mx-1">
                    <div class="card-header ${cardStyle}" id="heading${p.id}" data-toggle="collapse" data-target="#collapse${p.id}" aria-expanded="true" aria-controls="collapse${p.id}">
                        <#--<h5 class="mb-5">-->
                            <#--<button class="btn btn-link" data-toggle="collapse" data-target="#collapse${p.id}" aria-expanded="true" aria-controls="collapse${p.id}">-->
                                <h2 class="text-center">${p.placeMain.name}</h2>
                            <#--</button>-->
                        <#--</h5>-->
                    </div>
                    <div id="collapse${p.id}" class="collapse" aria-labelledby="heading${p.id}" data-parent="#accordion">
                        <div class="card-body">
                            <p>#ID:     ${p.id}</p>
                            <p>Typ:     ${p.placeMain.type}</p>
                            <p>Opis:    ${p.placeMain.description}</p>
                        </div>
                    </div>
                </div>
            </#list>
            </div>
        </div>
        </div>
    <#--</div>-->
</section>
<#--</div>-->

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery.paginate.js"></script>
<script>
    $('#paginate').paginate({
        perPage: 10
    })
</script>

</body>
</html>