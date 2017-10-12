<%-- 
    Document   : pokemonEdit
    Created on : 11 oct. 2017, 14:32:57
    Author     : mathieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>JSP Page</title>
      <jsp:include page="./parts/htmlHead.jsp" />
   </head>
   <body>
      <section class="container-fluid">
         <section id="home-cover" class="row text-center">
            <div class="cover col-md-12">

            </div>
         </section>
         <section class="row text-center justify-content-md-center">
            <article class="padding-sm col-md-12 col-lg-8">
               <h1>Edit this pokemon</h1>

               <!-- Form to Edit the Pokemon -->
               <form class="text-left">
                  <div class="form-group">
                     <h2>Pikachu</h2>
                  </div>
                  
                  <div class="form-group row">
                     <label class="col-sm-12" for="pokemonType1">Pokemon types</label>
                     <select class="custom-select col-xs-8 col-sm-8 col-md-8 col-lg-8" id="PokemonType1">
                        <option selected>Type 1</option>
                        <option value="1">Electric</option>
                        <option value="2">Fire</option>
                        <option value="3">Water</option>
                     </select>
                     <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
                     <button type="button" class="btn btn-secondary col-xs-3 col-sm-3 col-md-3col-lg-2">Add type...</button>
                  </div>
                  
                  <div class="form-group row">
                     <label class="col-sm-12" for="pokemonType1">Pokemon moves</label>
                     <select class="custom-select col-xs-8 col-sm-8 col-md-8 col-lg-8" id="PokemonType1">
                        <option selected>Move 1</option>
                        <option value="1">Lightning</option>
                        <option value="2">Charge</option>
                        <option value="3">Smash</option>
                     </select>
                     <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
                     <button type="button" class="btn btn-secondary col-xs-3 col-sm-3 col-md-3 col-lg-3">Add type...</button>
                  </div>
                  
                  <div class="row">
                     <input class="btn btn-primary col-xs-12 col-sm-6 col-md-6 col-lg-4" type="submit" value="Submit">
                  </div>
               </form>
            </article>
         </section>
      </section>
   </body>
</html>
