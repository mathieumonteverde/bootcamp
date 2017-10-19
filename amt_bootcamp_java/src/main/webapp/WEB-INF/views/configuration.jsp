<%-- 
    Document   : configuration
    Created on : Oct 18, 2017, 10:21:45 AM
    Author     : Mathieu Monteverde, Sathiya Kirushnapillai
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

         <section class="row text-center justify-content-md-center">
            <article class="padding-sm col-md-12 col-lg-8">

               <h1>Generate pokemons</h1>

               <!-- Form to Add the Pokemon -->
               <form class="text-left" action="${pageContext.request.contextPath}/configuration" method="POST">
                  <div class="form-group row">
                     <input type="text" class="form-control" id="number" name="number" placeholder="Number of pokemon..." />
                  </div>

                  <div class="row">
                     <input class="btn btn-primary col-xs-12 col-sm-6 col-md-6 col-lg-4" type="submit" value="Generate...">
                  </div>
               </form>
            </article>
         </section>
      </section>

      <jsp:include page="./parts/footer.jsp" />
   </body>
</html>

