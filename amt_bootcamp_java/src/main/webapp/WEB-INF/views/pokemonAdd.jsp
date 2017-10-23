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
         <section class="row text-center justify-content-center justify-content-sm-center justify-content-md-center justify-content-lg-center">
            <article class="col-10 col-sm-10col-md-10 col-lg-8">

               <h1 class="margin-sm">Add a pokemon</h1>
               
               <c:if test="${constraintError != null}">
                  <div class="alert alert-danger" role="alert">
                    The value ${constraintError} already exist.
                  </div>
               </c:if>

               <!-- Form to Add the Pokemon -->
               <form class="text-left" action="${pageContext.request.contextPath}/pokemons/add" method="POST">
                 
                  <div class="form-group row">
                     <input type="text" class="form-control" id="pokemonNo" value="${pokemonNo}" name="pokemonNo" placeholder="Pokemon no..." />
                      <c:if test="${noError != null}">
                         <span class="text-error">${noError}</span>
                      </c:if>
                  </div>
                  
                  <div class="form-group row">
                     <input type="text" class="form-control" id="pokemonName" value="${pokemonName}" name="pokemonName" placeholder="Pokemon name..." />
                     <c:if test="${nameError != null}">
                        <span class="text-error">${nameError}</span>
                     </c:if>
                  </div>
                  
                     
                  <%@include file="./parts/typeSelect.jsp"%>
                  <div class="row">
                     <c:if test="${typesError != null}">
                        <span class="text-error">${typesError}</span>
                     </c:if>
                  </div>
                  

                  <%@include file="./parts/moveSelect.jsp"%>
                  <div class="row">
                     <c:if test="${movesError != null}">
                        <span class="text-error">${movesError}</span>
                     </c:if>
                  </div>

                  <div class="row">
                     <input class="btn btn-primary col-12 col-sm-6 col-md-6 col-lg-4" type="submit" value="Add pokemon...">
                  </div>
               </form>
            </article>
         </section>
      </section>

      <jsp:include page="./parts/footer.jsp" />
   </body>
</html>
