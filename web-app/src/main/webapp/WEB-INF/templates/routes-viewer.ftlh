<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tam i tu - Trasy</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "head-stylesheets-scripts.ftlh">
</head>
<body>

<!-- Navbar menu -->
<#include "navbar.ftlh">

<div class="container-fluid mb-5 bg-light">

    <div class="row mt-5 pt-4">

        <!--Submenu-->
        <div class="col-3 pl-4 pt-3 pb-5">

            <div class="pt-2">

            <#--Search by name-->
                <form autocomplete="off" action="/web-app/routes-viewer" method="get">
                    <div class="autocomplete">
                        <input id="nameInput" class="form-control" type="text" name="rName" placeholder="Nazwa trasy">
                    </div>
                    <button type="submit" class="btn btn-primary" style="background-color: #0c3d53">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
                <br>

                <form class="form-inline" action="/web-app/routes-viewer" method="get">

                    <#assign qtyRange = routesPlaceQuantityRange?first..routesPlaceQuantityRange?last>
                    <label class="my-1 mr-2" for="inlineTypeSelector">Minimalna ilość atrakcji</label>
                    <div class="align-self-end ml-auto">
                        <select class="custom-select my-1 mr-sm-2" id="inlineTypeSelector" name="rMinQty">
                            <option value="">Wszystkie</option>
                            <#list qtyRange as qtyRange>
                                <option value="${qtyRange}">
                                    ${qtyRange}
                                </option>
                            </#list>
                        </select>
                    </div>

                    <label class="my-1 mr-2" for="inlineTypeSelector">Maksymalna ilość atrakcji
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="align-self-end ml-auto">
                        <select class="custom-select my-1 mr-sm-2" id="inlineTypeSelector" name="rMaxQty">
                            <option value="">Wszystkie</option>
                            <#list qtyRange as qtyRange>
                                <option value="${qtyRange}">
                                    ${qtyRange}
                                </option>
                            </#list>
                        </select>
                    </div>

                    <div class="align-self-center mx-auto mt-1">
                        <button type="submit" class="btn btn-primary" style="background-color: #0c3d53">
                            Filtruj <i class="fa fa-filter"></i>
                        </button>
                    </div>

                </form>

            </div>
            <hr>

            <#--Routes List-->
            <#if routes?size == 0>
                <div class="alert alert-secondary">
                    <h5>Niestety, brak tras o podanych kryteriach.</h5>
                    <h5>Prosimy spróbować ponownie.</h5>
                </div>
            </#if>
            <div class="pt-4 list-group" id="routes-list" role="tablist">
                <#list routes as r>
                    <a class="list-group-item list-group-item-action" id="list-${r.id}-list" data-toggle="list" href="#list-${r.id}" role="tab" aria-controls="${r.id}">
                        <div class="row">
                            <div class="col-8">
                                <p class="d-inline-block float-left">
                                    ${r.name}
                                </p>
                            </div>
                            <div class="col-4 align-middle">
                                <span class="float-right">
                                    ${r.placesQuantity} <i class="fa fa-landmark"></i>
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
                <#list routeIdByNameSelection as rsid>
                    <#if rsid == 0>
                        <div class="tab-pane show active" role="tabpanel">
                            <div class="alert alert-primary">
                                <h1 class="text-center">Proszę wybrać trasę turystyczną</h1>
                            </div>
                            <div class="mt-5 mb-5">
                                <hr>
                            </div>
                            <#if rName?has_content>
                                <#list rName as rnp>
                                    <#if rnp?length &gt; 0>
                                        <div class="alert alert-secondary">
                                            <h5>Niestety, brak trasy o poszukiwanej nazwie: <strong>"${rnp}"</strong>.</h5>
                                            <h5>Prosimy spróbować ponownie.</h5>
                                        </div>
                                    </#if>
                                </#list>
                            <#elseif promotedRoutes?has_content && promotedRoutes?size == 3>
                                <div class="alert alert-secondary text-center">
                                    <h3>Proponowane trasy</h3>
                                </div>
                                <div class="row mt-5">
                                    <#list promotedRoutes as pr>
                                        <#list routes as r>
                                            <#if pr == r.id>
                                                <div class="col-4">
                                                    <div class="card text-center">
                                                        <div class="card-body">
                                                            <h4 class="card-title">${r.name}</h4>
                                                            <h5 class="card-text">${r.placesQuantity} <i class="fa fa-landmark"></i></h5>
                                                            <h5 class="card-text">
                                                                ${r.overallDistance} km   <i class="fas fa-route"></i>
                                                            </h5><br>
                                                            <a href="/web-app/routes-viewer?rName=${r.name}" class="btn btn-primary">Wyświetl</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#list>
                                </div>
                            </#if>
                        </div>
                    </#if>
                    <#list routes as r>
                        <#if rsid == r.id>
                            <#assign activeStatus = " show active">
                        <#else>
                            <#assign activeStatus = "">
                        </#if>
                        <div class="tab-pane fade${activeStatus}" id="list-${r.id}" role="tabpanel" aria-labelledby="list-${r.id}-list">
                            <div class="alert alert-primary">
                                <h1 class="text-center">${r.name}</h1>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-6 text-center">
                                    <h4>Ilość atrakcji: ${r.placesQuantity}   <i class="fa fa-landmark"></i></h4>
                                </div>
                                <div class="col-6 text-center">
                                    <h4>Ogólny dystans: ${r.overallDistance} km   <i class="fas fa-route"></i></h4>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-6">
                                    <div class="card bg-light mb-3">
                                        <div class="card-header bg-primary">
                                            <h4 class="text-center text-white" style="margin-bottom: 0">
                                                Przebieg trasy
                                            </h4>
                                        </div>
                                        <#assign pIndex = 0>
                                        <#list r.places as rp>
                                            <#if rp != 0>
                                                <#assign pIndex = pIndex + 1>
                                                <div class="card-body" style="padding: 10px">
                                                    <#list places as p>
                                                        <#if rp == p.id>
                                                            <#assign thisPlaceName = p.placeMain.name>
                                                        </#if>
                                                    </#list>
                                                <h5 class="text-muted">
                                                    <span class="float-left pt-2">
                                                    ${pIndex}. ${thisPlaceName}
                                                    </span>
                                                    <span class="float-right">
                                                        <#assign rName = r.name>
                                                        <a href="/web-app/places-viewer?pName=${thisPlaceName?replace(" ", "+")}&rName=${rName?replace(" ", "+")}">
                                                            <button class="btn btn-primary" style="background-color: #0c3d53">
                                                                <i class="fas fa-external-link-alt"></i>
                                                            </button>
                                                        </a>
                                                    </span>
                                                </h5>
                                                </div>
                                                <#if rp?has_next>
                                                    <hr style="margin: 0">
                                                    <div class="text-muted text-center" style="padding: 5px; background-color: rgba(0,0,0,.03)">
                                                        <span class="ml-3 float-left">
                                                                <i class="fas fa-arrow-alt-circle-down"></i>
                                                        </span>
                                                        <span class="float-none">
                                                            <#list r.distances as rd>
                                                                <#if rd_index == rp_index>
                                                                    ${rd} km <i class="fas fa-route"></i>
                                                                </#if>
                                                            </#list>
                                                        </span>
                                                        <span class="mr-3 float-right">
                                                                <i class="fas fa-arrow-alt-circle-down"></i>
                                                        </span>
                                                    </div>
                                                    <hr style="margin: 0">
                                                <#else>
                                                    <hr style="margin: 0">
                                                    <div class="text-muted text-center" style="padding: 5px; background-color: rgba(0,0,0,.03)">
                                                        <span class="ml-3 float-left">
                                                                <i class="fas fa-check-circle"></i>
                                                        </span>
                                                        <span class="float-none">
                                                            Koniec trasy
                                                        </span>
                                                        <span class="mr-3 float-right">
                                                                <i class="fas fa-check-circle"></i>
                                                        </span>
                                                    </div>
                                                </#if>
                                            </#if>
                                        </#list>
                                    </div>
                                </div>
                                <div class="col-6 separator-left">
                                    <h3>${r.description}</h3>
                                </div>
                            </div>
                        </div>
                    </#list>
                <div class="mt-5 mb-5">
                    <hr>
                </div>
                </#list>
            </div>
        </div>
</div>

<!-- Footer -->
<#include "footer.ftlh">

<#if routes?size &gt; 10>
    <script src="js/jquery.paginate.js"></script>
    <script>
        $('#routes-list').paginate({
            perPage: 10,
            autoScroll: true
        })
    </script>
</#if>

<script src="js/autocomplete.js"></script>
<script>
    var routesNames = [<#list routesNames as r>"${r}"<#if r?is_last><#else>,</#if></#list>];

    autocomplete(document.getElementById("nameInput"), routesNames);
</script>

</body>
</html>