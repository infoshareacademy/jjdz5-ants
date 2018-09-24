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
                            <#if rName?has_content>
                                <#list rName as rnp>
                                    <#if rnp?length &gt; 0>
                                    <div class="alert alert-secondary">
                                        <h5>Niestety, brak trasy o poszukiwanej nazwie: <strong>"${rnp}"</strong>.</h5>
                                        <h5>Prosimy spróbować ponownie.</h5>
                                    </div>
                                    </#if>
                                </#list>
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
                                <div class="col-6">

                                </div>
                                <div class="col-6 separator-left">
                                    <h3>${r.description}</h3>
                                </div>
                            </div>
                        </div>
                    </#list>
                <hr>
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