<%-- 
    Document   : ListeEtatStock
    Created on : 17 nov. 2023, 08:35:01
    Author     : Ny Aina Ratolo
--%>

<%@page import="Models.EtatStock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%EtatStock etts = (EtatStock)request.getAttribute("etatStock");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <title>Etat de stock</title>
    </head>
    <body>
        <div class="table-responsive">
            <label class="form-label" style=" margin-left: 30px;margin-top: 30px;"><h3>Etat de stock entre <% out.print(etts.getDateDebut());%> et <% out.print(etts.getDateFin());%></h3></label>
            <table class="table table-bordered" style="margin-left: 40px;width: 1000px">
            <thead>
                <tr>
                    <th>Reference</th>
                    <th>Nom d'article</th>
                    <th>Quantite Initial</th>
                    <th>Quantite Sortie</th>
                    <th>Quantite Restante</th>
                    <th>Prix Unitaire</th>
                    <th>Montant</th>
                    <th>Magasin</th>
                </tr>
            </thead>
            <tbody>
                <% for(int i=0 ; i< etts.getListeStock().size();i++){%>
                <tr>
                    <td><% out.print(etts.getListeStock().get(i).getReference());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getNomArticle());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getQuantiteInitial());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getQuantiteSortie());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getQuantiteRestante());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getPU());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getMontant());%></td>
                    <td><% out.print(etts.getListeStock().get(i).getNomMagasin());%></td>
                </tr>
                <%}%>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><label><h5>Somme:</h5></label><% out.print(etts.getMontant());%></td>
                    <td></td>
                </tr>
            </tbody>
            </table>
        </div>  
                    <a href="index.html">Home</a>
    </body>
</html>
