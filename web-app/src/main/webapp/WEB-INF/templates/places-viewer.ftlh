<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Atrakcje turystyczne</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

<h3>Liczba atrakcji: ${places?size}</h3>

<#--<h3>${1.places.placeMain.name}</h3>-->

<table class="table">
    <tr class="thead-dark">
        <th>#</th>
        <th>Nazwa</th>
        <th>Typ</th>
    </tr>

  <#list places as p>
      <#if p?index % 2 == 0>
          <#assign color="#dcdcdc">
      <#else>
          <#assign color="ffffff">
      </#if>
    <tr bgcolor="${color}">
        <td>${p.id}</td>
        <td>${p.placeMain.name}</td>
        <td>${p.placeMain.type}</td>
        <#--<td><#list p.placeLocation.gpsCoordinates?values as v> ${v} </#list></td>-->
    </tr>
  </#list>
</table>
<br><br>

<div id="accordion">
    <#list places as p>
        <div class="card">
            <div class="card-header" id="heading${p.id}">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${p.id}" aria-expanded="true" aria-controls="collapse${p.id}">
                        ${p.placeMain.name}
                    </button>
                </h5>
            </div>
            <div id="collapse${p.id}" class="collapse show" aria-labelledby="heading${p.id}" data-parent="#accordion">
                <div class="card-body">
                    <p>#ID:     ${p.id}</p>
                    <p>Typ:     ${p.placeMain.type}</p>
                    <p>Opis:    ${p.placeMain.description}</p>
                </div>
            </div>
        </div>
    </#list>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="js/bootstrap.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

</body>
</html>