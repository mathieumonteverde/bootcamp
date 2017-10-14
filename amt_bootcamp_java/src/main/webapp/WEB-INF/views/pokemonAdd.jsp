<%-- 
    Document   : pokemonEdit
    Created on : 11 oct. 2017, 14:32:57
    Author     : mathieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Add a Pokemon</title>
      <jsp:include page="./parts/htmlHead.jsp" />
   </head>
   <body>

      <jsp:include page="./parts/header.jsp" />

      <section class="container-fluid">
         <section class="row text-center">
            <div class="cover col-md-12">

            </div>
         </section>

         <!-- Edit pokemon form section -->
         <section class="row text-center justify-content-md-center">
            <article class="padding-sm col-md-12 col-lg-8">

               <h1>Add a pokemon</h1>

               <!-- Form to Add the Pokemon -->
               <form class="text-left" action="${pageContext.request.contextPath}/pokemons/add" method="POST">
                  <div class="form-group row">
                     <input type="text" class="form-control" id="pokemonNo" name="pokemonNo" placeholder="Pokemon no..." />
                  </div>
                  
                  <div class="form-group row">
                     <input type="text" class="form-control" id="pokemonName" name="pokemonName" placeholder="Pokemon name..." />
                  </div>
                  
                  <div class="form-group row">
                     <label class="col-sm-12" for="pokemonType1">Pokemon types</label>
                     <select class="custom-select col-xs-8 col-sm-8 col-md-8 col-lg-8" id="PokemonType1">
                        <option selected>Type 1</option>
                        <c:forEach items="${types}" var="type">
                           <option value="${type.name}">${type.name}</option>
                        </c:forEach>
                     </select>
                     <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
                     <button type="button" class="btn btn-secondary col-xs-3 col-sm-3 col-md-3col-lg-2">Add type...</button>
                  </div>

                  <div class="form-group row">
                     <label class="col-sm-12" for="pokemonType1">Pokemon moves</label>
                     <select class="custom-select col-xs-8 col-sm-8 col-md-8 col-lg-8" id="PokemonType1">
                        <option selected>Move 1</option>
                        <c:forEach items="${moves}" var="move">
                           <option value="${move.id}">${move.name}</option>
                        </c:forEach>
                     </select>
                     <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
                     <button type="button" class="btn btn-secondary col-xs-3 col-sm-3 col-md-3 col-lg-3">Add type...</button>
                  </div>

                  <div class="row">
                     <input class="btn btn-primary col-xs-12 col-sm-6 col-md-6 col-lg-4" type="submit" value="Add pokemon...">
                  </div>
               </form>
            </article>
         </section>
      </section>

      <jsp:include page="./parts/footer.jsp" />
   </body>
</html>
