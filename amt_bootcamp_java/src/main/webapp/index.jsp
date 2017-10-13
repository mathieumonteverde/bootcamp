<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Online Pokedex</title>
      
      <jsp:include page="./WEB-INF/views/parts/htmlHead.jsp" />
   </head>
   <body>
      
      <jsp:include page="./WEB-INF/views/parts/header.jsp" />
      
      <section class="container-fluid">
         <section id="home-cover" class="row text-center">
            <div class="cover col-md-12">
               
            </div>
         </section>
         <section class="row text-center">
            <article class="padding-sm col-md-12 col-lg-12">
               <h1>The Pokedex</h1>
               <h2>Manage your Pokemon Pokedex !</h2>

               <a href="/amt_bootcamp_java-1.0-SNAPSHOT/pokemons" class="btn btn-primary">Manage the Pokedex</a>
               <a href="/amt_bootcamp_java-1.0-SNAPSHOT/configuration" class="btn btn-secondary">Configuration</a>
            </article>
         </section>
      </section>
                
      <jsp:include page="./WEB-INF/views/parts/footer.jsp" />
   </body>
</html>
