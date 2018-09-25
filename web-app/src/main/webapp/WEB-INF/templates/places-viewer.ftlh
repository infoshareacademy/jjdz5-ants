<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tam i tu - Atrakcje</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "head-stylesheets-scripts.ftlh">
</head>
<body class="bg-light">

<!-- Navbar menu -->
<#include "navbar.ftlh">

<div class="container-fluid mb-5 bg-light">

    <div class="row mt-5 pt-4 mb-5">

        <!--Submenu-->
        <div class="col-3 pl-4 pt-3 pb-5">

            <div class="pt-2">

                <#--Search by name-->
                <form autocomplete="off" action="/web-app/places-viewer" method="get">
                    <div class="autocomplete">
                        <input id="nameInput" class="form-control" type="text" name="pName" placeholder="Nazwa atrakcji">
                    </div>
                    <button type="submit" class="btn btn-primary" style="background-color: #0c3d53">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
                <br>

                <#--Filter by type-->
                <form class="form-inline" action="/web-app/places-viewer" method="get">
                    <label class="my-1 mr-2" for="inlineTypeSelector">Wyświetlany typ atrakcji</label>
                    <div class="align-self-end ml-auto">
                        <select class="custom-select my-1 mr-sm-2" id="inlineTypeSelector" name="pType">
                            <#if pType??>
                                <#assign ptp = pType?first>
                            </#if>
                            <option value="">Wszystkie</option>
                            <#list placesTypes as placeType>
                                <#if placeType == "CASTLE">
                                    <#assign pt = "Zamek">
                                <#elseif placeType == "CHURCH">
                                    <#assign pt = "Kościół">
                                <#elseif placeType == "MUSEUM">
                                    <#assign pt = "Muzeum">
                                <#elseif placeType == "PARK">
                                    <#assign pt = "Park">
                                <#elseif placeType == "MONUMENT">
                                    <#assign pt = "Pomnik">
                                <#elseif placeType == "WRONG_TYPE">
                                    <#assign pt = "Inny">
                                </#if>
                                <option value="${placeType}">
                                    ${pt}
                                </option>
                            </#list>
                        </select>
                    </div>

                    <#--Filter by minimal average rating-->
                    <label class="my-1 mr-2" for="inlineTypeSelector">Minimalna średnia ocena atrakcji</label>
                    <div class="align-self-end ml-auto">
                        <select class="custom-select my-1 mr-sm-2" id="inlineTypeSelector" name="pMinRate">
                        <option value="">Wszystkie</option>
                        <#list [1, 2, 3, 4, 5] as rate>
                            <option value="${rate}" style="text-align: right">
                                ${rate} &#9733;
                            </option>
                        </#list>
                        </select>
                    </div>

                    <#--Submit button-->
                    <div class="align-self-center mx-auto mt-1">
                        <button type="submit" class="btn btn-primary" style="background-color: #0c3d53">
                            Filtruj <i class="fa fa-filter"></i>
                        </button>
                    </div>

                </form>

            </div>
            <hr>

            <#--Places List-->
            <#if places?size == 0>
                <div class="alert alert-secondary">
                    <h5>Niestety, brak atrakcji o podanych kryteriach.</h5>
                    <h5>Prosimy spróbować ponownie.</h5>
                </div>
            </#if>
            <div class="pt-4 list-group" id="places-list" role="tablist">
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
                                    ${p.placeAdditional.averageRating?string("0.00")} <i class="fa fa-star"></i>
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
                <#list placeIdByNameSelection as psid>
                    <#if psid == 0>
                        <div class="tab-pane show active" role="tabpanel">
                            <div class="alert alert-primary">
                                <h1 class="text-center">Proszę wybrać atrakcję turystyczną</h1>
                            </div>
                            <#if pName?has_content>
                                <#list pName as pnp>
                                <#if pnp?length &gt; 0>
                                    <div class="alert alert-secondary">
                                        <h5>Niestety, brak atrakcji o poszukiwanej nazwie: <strong>"${pnp}"</strong>.</h5>
                                        <h5>Prosimy spróbować ponownie.</h5>
                                    </div>
                                </#if>
                                </#list>
                            </#if>
                        </div>
                    </#if>
                <#list places as p>
                <#if psid == p.id>
                    <#assign activeStatus = " show active">
                <#else>
                    <#assign activeStatus = "">
                </#if>
                <div class="tab-pane fade${activeStatus}" id="list-${p.id}" role="tabpanel" aria-labelledby="list-${p.id}-list">
                    <div class="alert alert-primary">
                        <h1 class="text-center">${p.placeMain.name}</h1>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-6">
                            <#if p.placeMain.type == "CASTLE">
                                <#assign type = "Zamek">
                            <#elseif p.placeMain.type == "CHURCH">
                                <#assign type = "Kościół">
                            <#elseif p.placeMain.type == "MUSEUM">
                                <#assign type = "Muzeum">
                            <#elseif p.placeMain.type == "PARK">
                                <#assign type = "Park">
                            <#elseif p.placeMain.type == "MONUMENT">
                                <#assign type = "Pomnik">
                            <#elseif p.placeMain.type == "WRONG_TYPE">
                                <#assign type = "Inny">
                            </#if>
                            <h5 class="alert alert-dark text-muted text-center">${type}</h5><hr>
                            <h4>Średnia ocena: ${p.placeAdditional.averageRating?string("0.00")} <i class="fa fa-star"></i> </h4>
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
                                                <#if hoursDay == "MONDAY">
                                                    <#assign hday = "Poniedziałek">
                                                <#elseif hoursDay == "TUESDAY">
                                                    <#assign hday = "Wtorek">
                                                <#elseif hoursDay == "WEDNESDAY">
                                                    <#assign hday = "Środa">
                                                <#elseif hoursDay == "THURSDAY">
                                                    <#assign hday = "Czwartek">
                                                <#elseif hoursDay == "FRIDAY">
                                                    <#assign hday = "Piątek">
                                                <#elseif hoursDay == "SATURDAY">
                                                    <#assign hday = "Sobota">
                                                <#elseif hoursDay == "SUNDAY">
                                                    <#assign hday = "Niedziela">
                                                <#else>
                                                    <#assign hday = hoursDay>
                                                </#if>
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <span class="text-muted float-left">${hday}</span>
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
                            <#if rName?has_content>
                                <#list rName as rnp>
                                    <#--<#if rnp?length &gt; 0>-->
                                    <#assign rnpLink = rnp>
                                        <div class="row" style="padding-left: 15px; padding-right: 15px">
                                            <a href="/web-app/routes-viewer?rName=${rnpLink?replace(" ", "+")}">
                                                <button class="btn btn-primary" style="background-color: #0c3d53;">
                                                    <i class="fas fa-undo"></i><span>  Powrót do trasy turystycznej "${rnp}"</span>
                                                </button>
                                            </a>
                                        </div>
                                        <hr>
                                    <#--</#if>-->
                                </#list>
                            </#if>
                            <div class="row" style="padding-left: 15px; padding-right: 15px">
                                <h3>${p.placeMain.description}</h3>
                            </div>
                        </div>
                    </div>
                </div>
                </#list>
                <hr>
                </#list>
            </div>
        </div>
    </div>
<#--</div>-->

<!-- Footer -->
<#include "footer.ftlh">

</div>

<#if places?size &gt; 10>
    <script src="js/jquery.paginate.js"></script>
    <script>
        $('#places-list').paginate({
            perPage: 10,
            autoScroll: true
        })
    </script>
</#if>

<script src="js/autocomplete.js"></script>
<script>
    var placesNames = [<#list placesNames as p>"${p}"<#if p?is_last><#else>,</#if></#list>];

    autocomplete(document.getElementById("nameInput"), placesNames);
</script>

</body>
</html>