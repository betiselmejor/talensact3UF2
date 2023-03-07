<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<style>
    table, th, td {
        border:1px solid black;
    }
</style>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<table style="width:90%">
    <form action="formusuarios" method="post">

    <tr>
        <th style="width: 20%">  <label for="nom">Nombre Cliente:</label></th>
        <td>  <input type="text" id="nom" name="nom" value=""></td>
    </tr>


    <tr>
        <th style="width: 20%">  <label for="dni">ID, DNI</label></th>
        <td>  <input type="text" id="dni" name="dni" value=""></td>
    </tr>


    <tr>
        <th style="width: 20%">  <label for="email">Email</label></th>
        <td>  <input type="text" id="email" name="email" value=""></td>
    </tr>

    <tr>
        <th style="width: 20%">  <label for="pais">Pais</label></th>
        <td>  <input type="text" id="pais" name="pais" value=""></td>
    </tr>

    <tr>
        <th style="width: 20%">  <label for="cuenta">Cuenta</label></th>
        <td>  <input type="text" id="cuenta" name="cuenta" value=""></td>
    </tr>

    <tr>
        <th style="width: 20%">  <label for="ingresI">ingreso inicial(€)</label></th>
        <td>  <input type="text" id="ingresI" name="ingresI" value=""></td>
    </tr>

    <tr>
        <th colspan="2">  <button class="submit" type="submit" value="Submit" name="create">Create</button></th>
    </tr>
    </form>



</table>

<br>
<table>
    <form action="buscarusuarios" method="post">
    <tr>
        <th style="width: 20%">  <label for="dnibuscar">DNI</label></th>
        <td>  <input type="text" id="dnibuscar" name="dnibuscar" value=""></td>
    </tr>
    <tr>
        <th colspan="2">  <button class="submit" type="submit" value="Submit" name="mostrarusuarios">Mostrar client i les seves comptes</button></th>
    </tr>
    </form>
</table>

    <br>
<table>
    <form action="buscarcuentas" method="post">
    <tr>
        <th style="width: 20%">  <label for="ibanbuscar">IBAN</label></th>
        <td>  <input type="text" id="ibanbuscar" name="ibanbuscar" value=""></td>
    </tr>
    <tr>
        <th colspan="2">  <button class="submit" type="submit" value="Submit" name="mostrarusuarios">mostra compte i el seu usuari</button></th>
    </tr>
    </form>
</table>


<%--    <tr>--%>
<%--        <th colspan="2">  <input type="submit" id="mostrarC" value="Mostrar clientes"> </th>--%>
<%--    </tr>--%>


</body>
</html>