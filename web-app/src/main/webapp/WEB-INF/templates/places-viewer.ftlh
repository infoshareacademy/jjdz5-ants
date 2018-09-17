<!DOCTYPE html>
<html lang="en">
<head>
    <title>APP_NAME</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "head-stylesheets-scripts.ftlh">
</head>
<body class="bg-light">

<!-- Navbar menu -->
<#include "navbar.ftlh">

<div class="container-fluid bg-light">

    <div class="row mt-5 pt-4">

        <!--Submenu-->
        <div class="col-3 pl-4 pt-3 pb-5">
            <div class="list-group text-center pb-2" id="list-tab" role="tablist">
                <a class="list-group-item filter-list-group-item-primary">Filtrowanie</a>
                <div class="accordion" id="filterMenu">
                    <div class="card">
                        <div class="card-header" id="filterTypeHeading" data-toggle="collapse" data-target="#filterTypeMenu" aria-expanded="true" aria-controls="filterTypeMenu">
                        <span>
                            Typ atrakcji
                        </span>
                        </div>

                        <div id="filterTypeMenu" class="collapse" aria-labelledby="filterTypeHeading" data-parent="#filterMenu">
                            <div class="card-body">
                                <p>Wkrótce zostanie dodana możliwość filtrowania typu atrakcji.</p>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header" id="filterLocationHeading" data-toggle="collapse" data-target="#filterLocationMenu" aria-expanded="true" aria-controls="filterLocationMenu">
                        <span>
                            Lokalizacja
                        </span>
                        </div>

                        <div id="filterLocationMenu" class="collapse" aria-labelledby="filterLocationHeading" data-parent="#filterMenu">
                            <div class="card-body">
                                <p>Wkrótce zostanie dodana możliwość filtrowania lokalizacji atrakcji.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="pt-2 pb-4">
                <input class="places-form-control" type="text" placeholder="Podaj szukaną nazwę" aria-label="Search">
            </div>

            <div class="list-group" id="places-list" role="tablist">
                <#list places as p>
                <a class="list-group-item list-group-item-action" id="list-${p.id}-list" data-toggle="list" href="#list-${p.id}" role="tab" aria-controls="${p.id}">
                    <div class="row">
                        <div class="col-8">
                    <p class="d-inline-block float-left">
                        ${p.placeMain.name}
                    </p>
                        </div>
                        <div class="col-4 align-middle">
                    <span class="float-right">
                        ${p.placeAdditional.averageRating?string("0.00")} &#9733;
                    </span>
                        </div>
                    </div>
                </a>
                </#list>
            </div>
        </div>

        <!--Content-->
        <div class="col-9 pr-4 pt-3 pb-5 separator-left">
            <div class="tab-content" id="nav-placesContent">
                <div class="tab-pane show active" role="tabpanel">
                    <div class="alert alert-primary">
                        <h1 class="text-center">Proszę wybrać atrakcję turystyczną</h1>
                    </div>
                </div>
                <#list places as p>
                <div class="tab-pane fade" id="list-${p.id}" role="tabpanel" aria-labelledby="list-${p.id}-list">
                    <div class="alert alert-primary">
                        <h1 class="text-center">${p.placeMain.name}</h1>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-6">
                            <h5 class="alert alert-dark text-muted text-center">${p.placeMain.type}</h5><hr>
                            <h4>Średnia ocena: ${p.placeAdditional.averageRating?string("0.00")} &#9733;</h4>
                            <h4>Ilość ocen: ${p.placeAdditional.ratingsAmount}</h4><hr>
                            <div class="card bg-light">
                                <div class="card-header">
                                    <h5 class="text-muted" style="margin-bottom: 0">Adres</h5>
                                </div>
                                <div class="card-body">
                                    <h5 class="text-muted">
                                        ${p.placeLocation.street}
                                        <#if p.placeLocation.buildingNumber != 0>
                                            ${p.placeLocation.buildingNumber}<#if p.placeLocation.buildingLetter != "">${p.placeLocation.buildingLetter}</#if>
                                            <#if p.placeLocation.apartmentNumber != 0>/ ${p.placeLocation.apartmentNumber}</#if>
                                        </#if>
                                    </h5>
                                    <h5 class="text-muted">
                                        ${p.placeLocation.city}
                                    </h5>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-6">
                                    <div class="card bg-light">
                                        <div class="card-header">
                                            <h5 class="text-muted" style="margin-bottom: 0">Ceny</h5>
                                        </div>
                                        <ul class="list-group list-group-flush">
                                            <#list p.placeAdditional.prices as priceType, priceValue>
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <span class="text-muted float-left">${priceType}</span>
                                                        </div>
                                                        <div class="col-5">
                                                            <#assign price = priceValue>
                                                            <span class="text-muted float-right">
                                                                <#if price <= 0>
                                                                    Bezpłatnie
                                                                    <#else>
                                                                    ${price?string("0.00")} PLN
                                                                </#if>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </li>
                                            </#list>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="card bg-light">
                                        <div class="card-header">
                                            <h5 class="text-muted" style="margin-bottom: 0">Godziny otwarcia</h5>
                                        </div>
                                        <ul class="list-group list-group-flush">
                                            <#list p.placeAdditional.openingHours as hoursDay, hoursValue>
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <span class="text-muted float-left">${hoursDay}</span>
                                                        </div>
                                                        <div class="col-5">
                                                            <span class="text-muted float-right">${hoursValue}</span>
                                                        </div>
                                                    </div>
                                                </li>
                                            </#list>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-6 separator-left">
                            <h3>${p.placeMain.description}</h3>
                        </div>
                    </div>
                </div>
                </#list>
                <hr>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery.paginate.js"></script>
<script>
    $('#places-list').paginate({
        perPage: 10,
        autoScroll: false
    })
</script>

</body>
</html>