<%-- 
    Document   : deleteConfirmation
    Author     : Mathieu Monteverde, Sathiya Kirushnapillai
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Confirm Pokemon deletion</title>

      <jsp:include page="./parts/htmlHead.jsp" />
   </head>
   <body>
      <jsp:include page="./parts/header.jsp" />

      <section class="container-fluid">
         <section class="row text-center">
            <div class="cover col-md-12">

            </div>
         </section>
         <section class="row text-center">
            <div class="col-1 col-1 col-sm-1 col-md-1 col-lg-2"></div>
            <article class="col-10 col-sm-10 col-md-10 col-lg-8 text-center">
               <h1 class="text-center margin-sm">Confirm Pokemon deletion</h1>
               <form id="confirmDelete" action="${pageContext.request.contextPath}/pokemons/delete" method="POST">
                  <p>You are about to delete the Pokemon n°${pokemon.no} <b>${pokemon.name}</b>. This action cannot be reverted.</p>
                  <p> Please confirm your choice</p>
                  
                  <input type="text" style="display:none;" name="pokemon" value="${pokemon.no}" />

                  <div class="form-check">
                     <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" name="notAskAgain">
                        Don't ask me again.
                     </label>
                  </div>

                  <input class="btn btn-primary" type="submit" id="confirmDeleteSubmit" value="Confirm deletion"/>
               </form>
            </article>
         </section>
      </section>
      <jsp:include page="./parts/footer.jsp" />
   </body>
</html>
