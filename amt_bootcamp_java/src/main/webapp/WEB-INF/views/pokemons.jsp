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
               <h1>List of Pokemons</h1>

               <div class="panel panel-default text-left">
                  <!-- Default panel contents -->
                  <div class="panel-heading">My Pokemons</div>

                  <!-- Table -->
                  <table class="table" class="text-left">
                     <thead>
                        <tr>
                           <th>Name</th>
                           <th>Moves</th>
                           <th>Types</th>
                           <th>Level</th>
                           <th>Manage</th>
                        </tr>
                     </thead>
                     <tbody>
                        <tr>
                           <td>${requestScope.pokemon.name}</td>
                           <td>Lightning - Headbump</td>
                           <td>Normal - Electric</td>
                           <td>2</td>
                           <td>
                              <a href="#" title="Edit">Edit</a>
                              |
                              <a href="#" title="Delete">Delete</a>
                           </td>
                        </tr>
                     <tbody>
                     <tfoot>
                        <tr>
                           <th>Name</th>
                           <th>Moves</th>
                           <th>Types</th>
                           <th>Level</th>
                           <th>Manage</th>
                        </tr>
                     </tfoot>
                  </table>
               </div>
            </article>
         </section>
      </section>
   </body>
</html>
