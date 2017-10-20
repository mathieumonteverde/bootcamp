<%-- 
    Document   : deleteConfirmation
    Created on : 20 oct. 2017, 19:20:40
    Author     : mathieu
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
            <h1>Confirm Pokemon deletion</h1>
            <article class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
               <form id="confirmDelete" action="pokemon/delete" method="POST">
                  <p>You are about to delete a Pokemon. This action cannot be reverted.</p>
                  <p> Please confirm your choice</p>

                  <div class="form-check">
                     <label class="form-check-label">
                        <input type="checkbox" class="form-check-input">
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
