<div class="form-group row">
   <label class="col-sm-12">Pokemon moves</label>
   <c:set var="j" value="1" scope="page" />
   <c:forEach items="${movesValues}" var ="moveValue">
      <select class="custom-select col-12 col-sm-6 col-md-6 col-lg-6" name="pokemonMove${j}" id="pokemonMove${j}">
         <c:if test = "${moveValue eq 'Move '.concat(j)}"><option selected>${moveValue}</option></c:if>
         <c:if test = "${moveValue ne 'Move '.concat(j)}"><option>${'Move '.concat(j)}</option></c:if>
         <c:forEach items="${moves}" var="move">
            <option value="${move.id}" <c:if test = "${moveValue eq move.id}">selected</c:if>>${move.name}</option>
         </c:forEach>
      </select>
      <c:set var="j" value="${j + 1}" scope="page"/>
   </c:forEach>
</div>