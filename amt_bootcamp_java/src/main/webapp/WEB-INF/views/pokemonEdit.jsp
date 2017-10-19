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
      <title>Edit ${requestScope.pokemon.name}</title>
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

               <h1>Edit this pokemon</h1>

               <!-- Form to Edit the Pokemon -->
               <form class="text-left" action="${pageContext.request.contextPath}/pokemons/edit" method="POST">
                  <!-- Name of the pokemon to edit -->
                  <div class="form-group">
                     <h2>${requestScope.pokemon.name}</h2>
                  </div>
                  
                  <%@include file="./parts/typeSelect.jsp"%>

                  <%@include file="./parts/moveSelect.jsp"%>

                  <div class="row">
                     <input class="btn btn-primary col-xs-12 col-sm-6 col-md-6 col-lg-4" type="submit" value="Edit pokemon...">
                  </div>
               </form>
            </article>
         </section>
      </section>

      <jsp:include page="./parts/footer.jsp" />
   </body>
</html>
