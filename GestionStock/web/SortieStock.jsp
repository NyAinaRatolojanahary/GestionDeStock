<%-- 
    Document   : SortieStock
    Created on : 13 nov. 2023, 10:01:43
    Author     : Ny Aina Ratolo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.*"%>
<% Article ar = new Article();
    ArrayList<Article> lsArt = ar.getAllArticle();
%>
<% Magasin mg = new Magasin();
    ArrayList<Magasin> lsMg = mg.getAllMagasin();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <title>Sortie Stock</title>
    </head>
    <body>
        <div class="container text-center">
            <div class="modal-header" style='margin-bottom: 30px'>
                <h5 class="modal-title">Sortie de Stock</h5>
            </div>
            <form class="form-control" action="./SStock" method="post">
                <label>Date:</label><input class="form-control" type="date"  name="dateSortie" required>
                <label>Article:</label>
                <select name="article" class="form-select">
                    <%  for(int i=0; i<lsArt.size(); i++){%>
                     <option value="<% out.print(lsArt.get(i).getIdArticle());%>"><% out.print(lsArt.get(i).getNom());%> (<% out.print(lsArt.get(i).getReference());%>)</option>
                    <% } %>
                </select>
                <label>Quantite:</label><input class="form-control" type="number" min="0" name="quantite" required placeholder="Quantite">
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
