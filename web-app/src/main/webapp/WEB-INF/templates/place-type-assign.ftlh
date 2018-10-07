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