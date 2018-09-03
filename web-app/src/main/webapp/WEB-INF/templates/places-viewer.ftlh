<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Atrakcje turystyczne</title>
</head>
<body>

<h3>Liczba atrakcji: ${placeMains?size}</h3>

<table>
    <tr>
        <th>#</th>
        <th>Nazwa</th>
        <th>Typ</th>
        <th>Opis</th>
    </tr>

  <#list placeMains as p>
      <#if p?index % 2 == 0>
          <#assign color="#dcdcdc">
      <#else>
          <#assign color="ffffff">
      </#if>
    <tr bgcolor="${color}">
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.type}</td>
        <td>${p.description}</td>
    </tr>
  </#list>
</table>

</body>
</html>