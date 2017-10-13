<%-- 
    Document   : pokemons
    Created on : 29 sept. 2017, 14:55:36
    Author     : Mathieu Monteverde, Sathiya Kirushnapillai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>View all Pokemons</title>

      <title>JSP Page</title>
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
               <h1>Pokedex</h1>

               <div class="panel panel-default text-left">
                  <!-- Default panel contents -->
                  <div class="panel-heading text-center"></div>

                  <!-- Table -->
                  <table class="table" class="text-left">
                     <thead>
                        <tr>
                           <th>No</th>
                           <th>Name</th>
                           <th>Moves</th>
                           <th>Types</th>
                           <th><a href="${pageContext.request.contextPath}/pokemons/add" title="Add new Pokemon">Add new</a></th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${pokemons}" var="pokemon">
                           <tr>
                              <td>${pokemon.no}</td>
                              <td>${pokemon.name}</td>
                              <td>
                                 <c:forEach items="${pokemon.moves}" var="move" varStatus = "status">
                                    ${move.name} 
                                    ${status.last ? '' : '-'}
                                 </c:forEach>
                              </td>
                              <td>
                                <c:forEach items="${pokemon.types}" var="type" varStatus = "status">
                                    ${type.name} 
                                    ${status.last ? '' : '-'}
                                 </c:forEach>
                              </td>
                              <td>
                                 <a href="${pageContext.request.contextPath}/pokemons/edit?pokemon=${pokemon.no}" title="Edit">Edit</a>
                                 |
                                 <a href="${pageContext.request.contextPath}/pokemons/delete?pokemon=${pokemon.no}" title="Delete">Delete</a>
                              </td>
                           </tr>
                        </c:forEach>
                     <tbody>
                     <tfoot>
                        <tr>
                           <th>No</th>
                           <th>Name</th>
                           <th>Moves</th>
                           <th>Types</th>
                           <th>Manage</th>
                        </tr>
                     </tfoot>
                  </table>
               </div>
            </article>
         </section>
      </section>
                           
      <jsp:include page="./parts/header.jsp" />
   </body>
</html>
