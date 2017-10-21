<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Errro - Online Pokedex</title>
      
      <jsp:include page="./WEB-INF/views/parts/htmlHead.jsp" />
   </head>
   <body>
      
      <jsp:include page="./WEB-INF/views/parts/header.jsp" />
      
      <section class="container-fluid">
         <section class="row text-center padding-lg justify-content-center justify-content-sm-center justify-content-md-center justify-content-lg-center">
            <article class="container-404 col-10 col-sm-8 col-md-6 col-lg-6">
               <div class="box-shadow">
                  <div class="blue-bg container-img">
                     <img src="${pageContext.request.contextPath}/static/img/error_white.png" title="" alt="" />
                  </div>
                  <div class="padding-sm">
                     <h1>An error occured...</h1>
                     <h2>Please come back later...</h2>

                     <a href="${pageContext.request.contextPath}" class="btn btn-primary">Home</a>
                  </div>
               </div>
            </article>
         </section>
      </section>
                
      <jsp:include page="./WEB-INF/views/parts/footer.jsp" />
   </body>
</html>
