<!-- Header file for header navigation -->
<header>
   <ul class="nav nav-left">
      <li class="nav-item">
         <a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="${pageContext.request.contextPath}/pokemons">Pokedex</a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="${pageContext.request.contextPath}/configuration">Configuration</a>
      </li>
   </ul>
   
   <ul class="nav nav-right">
      <li class="nav-item  add-pokemon-button">
         <a class="nav-link" href="${pageContext.request.contextPath}/pokemons/add">
            <%@include  file="plus-icon.html" %>
            <span class="text">Add a pokemon</span>
         </a>
      </li>
   </ul>
            
   <div class="nav-trigger">
      <div></div>
      <div></div>
      <div></div>
   </div>
</header>