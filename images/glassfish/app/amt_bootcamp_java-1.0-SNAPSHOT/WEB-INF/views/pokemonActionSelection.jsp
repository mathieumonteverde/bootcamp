<%-- 
    Document   : pokemonList
    Created on : 12 oct. 2017, 18:13:29
    Author     : mathieu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

      <title>${requestScope.title}</title>

      <jsp:include page="./parts/htmlHead.jsp" />
   </head>
   <body>

      <jsp:include page="./parts/header.jsp" />

      <section class="container-fluid">
         <section class="row text-center">
            <div class="cover col-md-12">

            </div>
         </section>
         <section class="row text-center justify-content-md-center">
            <article class="padding-sm col-md-12 col-lg-8">

               <!-- Form to Edit the Pokemon -->
               <form class="text-left" action="${pageContext.request.contextPath}${requestScope.actionUrl}" method="GET">
                 
                  <div class="form-group row">
                     <label class="col-sm-12" for="pokemonName">${requestScope.title}</label>
                     <select class="custom-select col-xs-8 col-sm-8 col-md-8 col-lg-8" id="pokemon" name="pokemon">
                        <option value="none" selected>None</option>
                        <c:forEach items="${pokemons}" var="pokemon">
                           <option value="${pokemon.no}">${pokemon.name}</option>
                        </c:forEach>
                     </select>

                  </div>

                  <div class="row">
                     <input class="btn btn-primary col-xs-12 col-sm-6 col-md-6 col-lg-4" type="submit" value="${requestScope.submitText}">
                  </div>
               </form>
            </article>
         </section>
      </section>

      <jsp:include page="./parts/footer.jsp" />

   </body>
</html>
