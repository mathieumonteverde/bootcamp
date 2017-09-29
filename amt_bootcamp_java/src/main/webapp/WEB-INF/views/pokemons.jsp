<%-- 
    Document   : pokemons
    Created on : 29 sept. 2017, 14:55:36
    Author     : Mathieu Monteverde, Sathiya Kirushnapillai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>View all Pokemons</title>

      <!-- Latest compiled and minified CSS -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />

      <!-- Optional theme -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" />

      <!-- Latest compiled and minified JavaScript -->
      <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   </head>
   <body>
      <h1>The chosen Pokemon is</h1>
      <h2>${requestScope.pokemon.name}</h2>
   </body>
</html>
