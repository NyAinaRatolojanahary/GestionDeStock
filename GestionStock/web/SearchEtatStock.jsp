<%-- 
    Document   : SearchEtatStock
    Created on : 13 nov. 2023, 10:05:42
    Author     : Ny Aina Ratolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.*"%>
<% Article ar = new Article();
    ArrayList<Article> lsArt = ar.getAllArticle();
%>
<% Magasin mg = new Magasin();
    ArrayList<Magasin> lsMg = mg.getAllMagasin();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <title>Etat Stock Recherche</title>
    </head>
    <body>
        <div class="container text-center">
            <div class="modal-header" style='margin-bottom: 30px'>
                <h5 class="modal-title">Rechercher un etat de Stock</h5>
            </div>
            <form class="form-control" action="./EtatStockServlet" method="post">
                <label>Date Debut:</label><input class="form-control" type="date"  name="dateDebut" required>
                <label>Date Fin:</label><input class="form-control" type="date"  name="dateFin" required>
                <label>Article:</label>
                <select name="article" class="form-select">
                    <%  for(int i=0; i<lsArt.size(); i++){%>
                     <option value="<% out.print(lsArt.get(i).getIdArticle());%>"><% out.print(lsArt.get(i).getNom());%> (<% out.print(lsArt.get(i).getReference());%>)</option>
                    <% } %>
                </select>
                <label>Magasin:</label>
                <select name="magasin" class="form-select">
                    <% for(int i=0; i<lsMg.size(); i++){%>
                        <option value="<% out.print(lsMg.get(i).getIdMagasin());%>"><% out.print(lsMg.get(i).getNomMagasin());%></option>
                    <% }%>
                </select>
                <input class="btn btn-primary mb-3" type="submit" value="Send" style="margin-top: 30px;">
            </form>
        </div>
        <a href="index.html">Home</a>
    </body>
</html>

