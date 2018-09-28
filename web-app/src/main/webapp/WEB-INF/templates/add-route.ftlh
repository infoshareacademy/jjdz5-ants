<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/formValidation.js"></script>
    </head>
<body>

<form name="additionForm" action="#"
      onsubmit="return validateAdditionForm()" method="post">

    Name: <input type="text" name="name">
    Type: <input type="text" name="desc"> <br>

    <table>
        <tr>
            <th>Dodaj</th>
            <th>Nazwa</th>
            <th>Typ</th>
            <th>Średnia ocena</th>
        </tr>
        <#list places as p>
        <#include "place-type-assign.ftlh">
            <tr>
                <td><input type="checkbox" name="pId" value="${p.id}"></td>
                <td>${p.placeMain.name}</td>
                <td>${type}</td>
                <td>${p.placeAdditional.averageRating}</td>
            </tr>
        </#list>
    </table>

    <input type="submit" value="Submit">
</form>

</body>
</html>