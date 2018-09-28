<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#include "head-stylesheets-scripts.ftlh">
    <script src="js/formValidation.js"></script>
    </head>
<body>

<#include "navbar.ftlh">

<div class="container-fluid mb-5 bg-light">

    <div class="row mt-5 pt-4 pl-3 pr-3">
        <h2 class="alert alert-secondary w-100 text-center">Dodawanie nowej trasy</h2>
    </div>

    <form name="additionForm" action="#" onsubmit="return validateAdditionForm()" method="post">

        <div class="row">
            <div class="col-3">

                <div class="row pl-5 pr-5 pb-1">

                    <div class="form-group">
                        <label>Nazwa trasy</label>
                        <input type="text" class="form-control" name="name" aria-describedby="nameHelp" placeholder="Proszę podać nazwę nowej trasy">
                        <small id="nameHelp" class="form-text text-muted">Nazwa powinna się składać z minimum 3 znaków.</small>
                    </div>

                </div>

            </div>
            <div class="col-3">

                <div class="row pl-5 pr-5">

                    <div class="form-group">
                        <label>Opis trasy</label>
                        <textarea class="form-control" rows="3" name="desc" aria-describedby="descHelp" placeholder="Proszę podać krótki opis nowej trasy"></textarea>
                        <small id="descHelp" class="form-text text-muted">Opis powinien się składać z minimum 10 znaków.</small>
                    </div>

                </div>

            </div>

            <div class="col-6 separator-left">
                aaa
            </div>

        </div>

        <hr>

        <div class="row">

            <div class="col-6 pl-5" style="padding: 20px">
                <table class="table table-bordered table-striped text-center">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nazwa</th>
                            <th>Typ</th>
                            <th>Średnia ocena</th>
                            <th>Dodaj</th>
                        </tr>
                    </thead>
                    <tbody id="sourceTable">
                        <#list places as p>
                            <#include "place-type-assign.ftlh">
                            <tr id="tr${p.id}">
                                <td>${p.placeMain.name}</td>
                                <td>${type}</td>
                                <td>${p.placeAdditional.averageRating}</td>
                                <td><a href="#"><i class="fas fa-sync" onclick="moveRow(this);"></i></a></td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>

            <div class="col-6 pr-5 separator-left" style="padding: 20px">
                <table class="table thead-dark table-bordered table-striped text-center">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nazwa</th>
                            <th>Typ</th>
                            <th>Średnia ocena</th>
                            <th>Usuń</th>
                        </tr>
                    </thead>
                    <tbody id="selectionTable">
                    </tbody>
                </table>
            </div>

        </div>

        <input type="submit" value="Submit">
    </form>

</div>

<script>
    function moveRow(tr){
        while (tr.parentNode&&tr.nodeName.toUpperCase()!=='TR'){
            tr=tr.parentNode
        }
        var table1=document.getElementById('sourceTable');
        if (!this.rows){
            var rows=table1.getElementsByTagName('TR');
            this.rows=[];
            for (var z0=0;z0<rows.length;z0++){
                this.rows[z0]=rows[z0];
            }
        }
        var table2=document.getElementById('selectionTable');
        if (tr.parentNode!==table2){
            table2.appendChild(tr);
        }
        else {
            table1.appendChild(tr);
            for (var z0=0;z0<this.rows.length;z0++){
                if (this.rows[z0].parentNode===table1){
                    table1.appendChild(this.rows[z0]);
                }
            }
        }
    }
</script>

</body>
</html>