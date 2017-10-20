<div class="row"> 
   <nav aria-label="Page navigation example" class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
      <ul class="pagination">
         <li class="page-item <c:if test="${page eq 1}">disabled </c:if>"><a class="page-link page-previous" href="?page=${page - 1}&pokemonsPerPage=${pokemonsPerPage}">Previous</a></li>
         <c:forEach items="${beforePagination}" var="currentPage" varStatus = "status">
            <li class="page-item before <c:if test="${currentPage eq page}">active</c:if>"><a class="page-link" href="?page=${currentPage}&pokemonsPerPage=${pokemonsPerPage}">${currentPage}</a></li>
         </c:forEach>
         <c:if test="${fn:length(beforePagination) gt 0}">
            <span class="pagination-separator">...</span>
         </c:if>
         <c:forEach items="${pagePagination}" var="currentPage" varStatus = "status">
            <li class="page-item page <c:if test="${currentPage eq page}">active</c:if>"><a class="page-link" href="?page=${currentPage}&pokemonsPerPage=${pokemonsPerPage}">${currentPage}</a></li>
         </c:forEach>
         <c:if test="${fn:length(afterPagination) gt 0}">
            <span class="pagination-separator">...</span>
         </c:if>
         <c:forEach items="${afterPagination}" var="currentPage" varStatus = "status">
            <li class="page-item after <c:if test="${currentPage eq page}">active</c:if>"><a class="page-link" href="?page=${currentPage}&pokemonsPerPage=${pokemonsPerPage}">${currentPage}</a></li>
         </c:forEach>
         <li class="page-item <c:if test="${page eq maxNbPage}">disabled </c:if>"><a class="page-link page-next" href="?page=${page + 1}&pokemonsPerPage=${pokemonsPerPage}">Next</a></li>
      </ul>
   </nav>
   <div class="text-pagination text-right col-xs-2 col-sm-2 col-md-2 col-lg-2">Lines</div>
   <select class="pokemonsPerPageSelect custom-select col-xs-1 col-sm-1 col-md-1 col-lg-1" name="pokemonsPerPage">
      <option value="?page=${page}&pokemonsPerPage=5" <c:if test="${pokemonsPerPage eq 5}">selected</c:if>>5</option>
      <option value="?page=${page}&pokemonsPerPage=10" <c:if test="${pokemonsPerPage eq 10}">selected</c:if>>10</option>
      <option value="?page=${page}&pokemonsPerPage=20" <c:if test="${pokemonsPerPage eq 20}">selected</c:if>>20</option>
      <option value="?page=${page}&pokemonsPerPage=50" <c:if test="${pokemonsPerPage eq 50}">selected</c:if>>50</option>
   </select>
</div>
